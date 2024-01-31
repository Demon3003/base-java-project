package com.zhurawell.base.api.security.filters;

import com.zhurawell.base.api.exceptions.BaseException;
import com.zhurawell.base.api.security.jwt.JwtAuthenticationException;
import com.zhurawell.base.api.security.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String authorizationHeader;

    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = servletRequest.getHeader(authorizationHeader);
        try {
            if (token != null) {
                jwtTokenProvider.validateAccessToken(token);
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (JwtAuthenticationException e) {
            log.error("Error during token processing", e);
            SecurityContextHolder.clearContext();
            servletResponse.sendError(e.getHttpStatus().value(), e.getMessage());
        } catch (UsernameNotFoundException | BaseException e) {
            log.error("Error during token processing", e);
            SecurityContextHolder.clearContext();
            servletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}