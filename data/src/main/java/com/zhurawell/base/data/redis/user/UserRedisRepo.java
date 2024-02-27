package com.zhurawell.base.data.redis.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPooled;

@Component
public class UserRedisRepo { // TODO handle connection exception redis.clients.jedis.exceptions.JedisConnectionException

    @Autowired
    private JedisPooled jedis;

    @Value("${access.token.expiration.min}")
    private long accessTokeValidityInMinutes;

    @Value("${refresh.token.expiration.min}")
    private long refreshTokeValidityInMinutes;

    private final int MIN_TO_SEC = 60;

    public void addTokensToBlackList(String accessToken, String refreshToken) {
        jedis.setex(accessToken, accessTokeValidityInMinutes * MIN_TO_SEC, "");
        jedis.setex(refreshToken, refreshTokeValidityInMinutes * MIN_TO_SEC, "");
    }

    public boolean isPresentInBlackList(String token) {
        return jedis.exists(token);
    }
}
