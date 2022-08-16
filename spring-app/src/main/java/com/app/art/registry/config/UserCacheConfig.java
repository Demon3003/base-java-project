package com.app.art.registry.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;

import java.util.concurrent.TimeUnit;

@Configuration
public class UserCacheConfig {

    @Value("${cache.name.for.user}")
    private String userCacheName;

    @Value("${cache.expiration.time}")
    private long cacheItemLiveInMinutes;

    @Value("${cache.max.size}")
    private long cacheMaxSize;

    @Autowired
    @Qualifier("CommonUserDetailsService")
    private UserDetailsService customUserDetailsService;

    @Bean
    public UserCache createUserCache() {

        CaffeineCache caffeineCache = new CaffeineCache(userCacheName,
                Caffeine
                .newBuilder()
                .expireAfterWrite(cacheItemLiveInMinutes, TimeUnit.MINUTES)
                .maximumSize(cacheMaxSize)
                .build());

        SpringCacheBasedUserCache cache = new SpringCacheBasedUserCache(caffeineCache);
        return cache;
    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        CachingUserDetailsService service = new CachingUserDetailsService(customUserDetailsService);
        service.setUserCache(createUserCache());
        return service;
    }

}
