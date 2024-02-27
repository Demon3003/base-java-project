package com.zhurawell.base.api.controllers.authorization;

import com.zhurawell.base.api.dto.jwt.JwtDetailsDto;
import com.zhurawell.base.api.dto.user.UserDto;
import com.zhurawell.base.api.security.jwt.JwtTokenProvider;
import com.zhurawell.base.data.redis.user.UserRedisRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserRedisRepo userRedisRepo;

    @Autowired
    public AuthorizationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                                   UserRedisRepo userRedisRepo) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRedisRepo = userRedisRepo;
    }

    @PostMapping("/auth")
    public ResponseEntity login(@RequestBody UserDto user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
        Pair<String, String> tokens = jwtTokenProvider.createAccessAndRefreshTokens(user.getLogin());
        return ResponseEntity.ok(JwtDetailsDto.builder()
                .login(user.getLogin())
                .accessToken(tokens.getFirst())
                .refreshToken(tokens.getSecond())
                .build());
    }

    @PostMapping("/user/logout")
    public ResponseEntity logout(@RequestBody JwtDetailsDto details) {
        SecurityContextHolder.clearContext();
        userRedisRepo.addTokensToBlackList(details.getAccessToken(), details.getRefreshToken());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/refreshToken")
    public ResponseEntity refreshToken(@RequestParam("token") String token) {
        jwtTokenProvider.validateRefreshToken(token);
        String login = jwtTokenProvider.getUsernameFromRefreshToken(token);
        Pair<String, String> tokens = jwtTokenProvider.createAccessAndRefreshTokens(login);
        return ResponseEntity.ok(JwtDetailsDto.builder()
                .login(login)
                .accessToken(tokens.getFirst())
                .refreshToken(tokens.getSecond())
                .build());
    }

}
