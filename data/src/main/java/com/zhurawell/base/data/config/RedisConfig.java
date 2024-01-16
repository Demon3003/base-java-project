package com.zhurawell.base.data.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.clients.jedis.JedisPooled;

@Slf4j
@Configuration
@Profile({"!test"})
public class RedisConfig {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private Integer redisPort;

    @Value("${redis.password}")
    private String redisPass;

    @Value("${redis.login}")
    private String redisLogin;

    @Bean
    JedisPooled jedisPooledBean() {
        return new JedisPooled(redisHost, redisPort, redisLogin, redisPass);
    }
}
