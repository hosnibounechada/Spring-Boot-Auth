package com.hb.auth.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb.auth.common.UserEmailRedis;
import com.hb.auth.exception.NotFoundException;
import com.hb.auth.util.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setValueEx(String key, String value, Duration duration) {
        redisTemplate.opsForValue().set(key, value, duration);
    }

    public String getValueEx(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setHashValueEx(String key, String value) {
        /*UserEmailRedis userEmailRedis = new UserEmailRedis(value, 0);

        String userEmailRedisJson = JsonSerializer.JacksonToJsonRedisSerializer(userEmailRedis);

        redisTemplate.opsForHash().put("EMAIL", key, userEmailRedisJson);
//        redisTemplate.opsForHash().put(key, userEmailRedisJson);

        // set TTL for the key to 60 seconds
        redisTemplate.opsForHash().getOperations().expire("EMAIL:" + key, 60, TimeUnit.SECONDS);*/
        Map<String, String> map = new HashMap<>();
        map.put("code", "123456");
        map.put("counter", "0");
        redisTemplate.opsForHash().putAll(key, map);
        redisTemplate.opsForHash().getOperations().expire(key, 60, TimeUnit.SECONDS);
    }

    public String getHashValueEx(String key, String field) {
        Object code = redisTemplate.opsForHash().get(key, field);

        if(code == null) throw new NotFoundException("Code already expired");

        return code.toString();
    }

    public void increment(String key, String field, Long incrBy) {
        redisTemplate.opsForHash().increment(key, field, incrBy);
    }

}
