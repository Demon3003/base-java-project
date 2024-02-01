package com.zhurawell.base.api.security.jwt;

import com.zhurawell.base.data.redis.user.UserRedisIntegration;
import com.zhurawell.base.service.exception.ErrorCodes;
import com.zhurawell.base.service.exception.CustomExceptionHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret.access}")
    private String secretAccessKey;

    @Value("${jwt.secret.refresh}")
    private String secretRefreshKey;

    @Value("${access.token.expiration.min}")
    private long accessTokeValidityInMinutes;

    @Value("${refresh.token.expiration.min}")
    private long refreshTokeValidityInMinutes;

    private UserDetailsService userDetailsService;

    private UserRedisIntegration userRedisIntegration;


    @Autowired
    public JwtTokenProvider(UserDetailsService userDetailsService, UserRedisIntegration redisClient) {
        this.userDetailsService = userDetailsService;
        this.userRedisIntegration = redisClient;
    }

    @PostConstruct
    protected void init() {
        this.secretAccessKey = Base64.getEncoder().encodeToString(secretAccessKey.getBytes());
        this.secretRefreshKey = Base64.getEncoder().encodeToString(secretRefreshKey.getBytes());
    }


    public String createAccessToken(String username) {
        return createToken(username, accessTokeValidityInMinutes, secretAccessKey);
    }

    public String createRefreshToken(String username) {
        return createToken(username, refreshTokeValidityInMinutes, secretRefreshKey);
    }

    protected String createToken(String username, long validityInMinutes, String secret) {
        Claims claims = Jwts.claims().setSubject(username);
        Date issuedAt = new Date();
        Date validity = Date.from(
                LocalDateTime.now()
                        .plusMinutes(validityInMinutes)
                        .atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Pair<String, String> createAccessAndRefreshTokens(String username) {
        return Pair.of(this.createAccessToken(username), this.createRefreshToken(username));
    }

    public String validateAccessToken(String token) {
        return this.validateToken(token, secretAccessKey);
    }

    public String validateRefreshToken(String token) {
        return this.validateToken(token, secretRefreshKey);
    }

    protected String validateToken(String token, String singKey) {
        if (userRedisIntegration.isPresentInBlackList(token))
            CustomExceptionHandler.getInstance().withErrorCode(ErrorCodes.C_300).buildAndThrow();
        try {
            Jwts.parser().setSigningKey(singKey).parseClaimsJws(token);
        } catch (ExpiredJwtException expiredEx) {
            CustomExceptionHandler.getInstance().withErrorCode(ErrorCodes.C_301).buildAndThrow();
        } catch (Exception ex) {
            CustomExceptionHandler.getInstance().withErrorCode(ErrorCodes.C_302).buildAndThrow();
        }
        return token;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsernameAccessToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsernameAccessToken(String token) {
        return Jwts.parser().setSigningKey(secretAccessKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String getUsernameFromRefreshToken(String token) {
        return Jwts.parser().setSigningKey(secretRefreshKey).parseClaimsJws(token).getBody().getSubject();
    }

}
