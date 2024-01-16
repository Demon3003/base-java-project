package com.zhurawell.base.api.controllers.authorization;

import com.zhurawell.base.api.dto.jwt.JwtDetailsDto;
import com.zhurawell.base.data.redis.client.user.UserRedisClient;
import com.zhurawell.base.security.jwt.JwtTokenProvider;
import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.repo.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserRepository userRepository;

    private UserRedisClient userRedisClient;

    @Autowired
    public AuthorizationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                                   UserRepository userRepository, UserRedisClient userRedisClient) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.userRedisClient = userRedisClient;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
        Pair<String, String> tokens = jwtTokenProvider.createAccessAndRefreshTokens(user.getLogin());
        return ResponseEntity.ok(JwtDetailsDto.builder()
                .login(user.getLogin())
                .accessToken(tokens.getFirst())
                .refreshToken(tokens.getSecond())
                .build());
    }

    @PostMapping("/user/logout")
    public ResponseEntity logout(@RequestBody JwtDetailsDto details) { // TODO token black list
        SecurityContextHolder.clearContext();
        userRedisClient.addTokensToBlackList(details.getAccessToken(), details.getRefreshToken());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/refreshToken")
    public ResponseEntity refreshToken(@RequestParam("token") String token) {  // TODO token black list
        jwtTokenProvider.validateRefreshToken(token);
        String login = jwtTokenProvider.getUsernameFromRefreshToken(token);
        User usr = userRepository.findByEmailOrLogin(login, login).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        Pair<String, String> tokens = jwtTokenProvider.createAccessAndRefreshTokens(usr.getLogin());
        return ResponseEntity.ok(JwtDetailsDto.builder()
                .login(usr.getLogin())
                .accessToken(tokens.getFirst())
                .refreshToken(tokens.getSecond())
                .build());
    }

}
