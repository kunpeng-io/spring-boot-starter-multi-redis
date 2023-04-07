package cn.ark.springframework.multiRedis.core;

import org.springframework.data.redis.serializer.RedisSerializer;

public enum RedisSerializerEnum {
    string, json, java;

    public RedisSerializer conversion() {
        RedisSerializer redisSerializer;
        switch (this) {
            case string:
                redisSerializer = RedisSerializer.string();
                break;
            case json:
                redisSerializer = RedisSerializer.json();
                break;
            default:
                redisSerializer = RedisSerializer.java();
        }
        return redisSerializer;
    }
}
