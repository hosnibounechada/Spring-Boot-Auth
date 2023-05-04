package com.hb.auth.common.service;

import com.hb.auth.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.hb.auth.constant.RedisEmailConfirmationHash.*;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;

    /**
     * Set Key-Value pair
     * @param key String
     * @param value String
     */
    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * Get value of key
     * @param key String
     * @return String
     */
    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * Set Key-Value pair with TTL
     * @param key String
     * @param value String
     * @param duration Long
     */
    public void setValueEx(String key, String value, Duration duration) {
        redisTemplate.opsForValue().set(key, value, duration);
    }

    /**
     * Get Value of key which has TTL flag
     * @param key String
     * @return String
     */
    public String getValueEx(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * Set TTL Time-To-Live to a key
     * @param key String
     * @param ttl Long
     */
    public void setTTL(String key, Long ttl) {
        redisTemplate.opsForHash().getOperations().expire(key, ttl, TimeUnit.SECONDS);
    }

    /**
     * Get Hash field value by its Key and Field names
     * @param key String
     * @param field String
     * @return String
     */
    public String getHashValueEx(String key, String field) {
        Object code = redisTemplate.opsForHash().get(key, field);

        if(code == null) throw new NotFoundException("Code already expired");

        return code.toString();
    }

    /**
     * Increment field value of Hash by given number
     * @param key String
     * @param field String
     * @param incrBy Long
     */
    public void increment(String key, String field, Long incrBy) {
        redisTemplate.opsForHash().increment(key, field, incrBy);
    }

    /**
     * Set Hash by key and entries
     * @param key String
     * @param entries Map<String, String>
     */
    public void setHashByKeyAndHashMap(String key, Map<String, String> entries){
        redisTemplate.opsForHash().putAll(key, entries);
    }


    /**
     * Get HSet by the provided Key
     * @param key String
     * @return Map<Object, Object>
     */
    public Map<Object, Object> getHashByKey(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * Set Hash into Redis by the key and HashMap
     * @param key String
     * @param values Map<String, String>
     * @param ttl Long
     */
    public void createHashWithTtl(String key, Map<String, String> values, long ttl) {
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();
        ops.putAll(key, values);
        redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
    }

    /*public Map<String, String> getFieldValuesAndIncrement(String key) {
        String script = "local values = redis.call('HMGET', KEYS[1], 'code', 'counter'); " +
                "redis.call('HINCRBY', KEYS[1], 'counter', 1); " +
                "return {values[1], values[2], tostring(tonumber(values[2]) + 1)};";
        List<String> keys = Collections.singletonList(key);
        List<String> resultList = redisTemplate.execute(new DefaultRedisScript<>(script, List.class), keys);
        Map<String, String> resultMap = new HashMap<>();
        assert resultList != null;
        resultMap.put(CODE, resultList.get(0));
        resultMap.put(COUNTER, resultList.get(1));
        return resultMap;
    }*/

    /**
     * Get Hash by the provided key
     * @param key String
     * @return Map<String, String>
     */
    public Map<String, String> getFieldValuesAndIncrement(String key) {
        /*String script = "local values = redis.call('HMGET', KEYS[1], 'code', 'counter'); " +
                "redis.call('HINCRBY', KEYS[1], 'counter', 1); " +
                "if tonumber(values[2]) > 3 then " +
                "   redis.call('DEL', KEYS[1]) " +
                "end " +
                "return {values[1], values[2], tostring(tonumber(values[2]) + 1)};";*/
        String script = "local key = KEYS[1]; " +
                "if redis.call('EXISTS', key) == 0 then" +
                "    return false " +
                "end " +
                "local values = redis.call('HMGET', key, 'code', 'counter'); " +
                "redis.call('HINCRBY', key, 'counter', 1); " +
                "if tonumber(values[2]) > 3 then" +
                "    redis.call('DEL', key); " +
                "end " +
                "return {values[1], values[2], tostring(tonumber(values[2]) + 1)};";

        List<String> keys = Collections.singletonList(key);
        List<String> resultList = redisTemplate.execute(new DefaultRedisScript<>(script, List.class), keys);
        Map<String, String> resultMap = new HashMap<>();
        if (resultList == null) throw new AssertionError();
        if (resultList.get(0) == null) return null;
        resultMap.put(CODE, resultList.get(0));
        resultMap.put(COUNTER, resultList.get(1));
        return resultMap;
    }
}
