package cn.ark.springframework.multiRedis.core;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.time.Duration;
import java.util.Objects;


/**
 * Description
 * Copyright ©
 *
 * @author Noah
 * @date 2023/3/21
 */
public class MultiRedisProperties extends RedisProperties {
    private String name;
    private Boolean defaultCache = Boolean.FALSE;
    private RedisSerializerEnum key = RedisSerializerEnum.string;
    private RedisSerializerEnum value = RedisSerializerEnum.json;
    private RedisSerializerEnum hashKey = RedisSerializerEnum.string;
    private RedisSerializerEnum hashValue = RedisSerializerEnum.json;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDefaultCache() {
        return defaultCache;
    }

    public void setDefaultCache(Boolean defaultCache) {
        this.defaultCache = defaultCache;
    }

    public RedisSerializerEnum getKey() {
        return key;
    }

    public void setKey(RedisSerializerEnum key) {
        this.key = key;
    }

    public RedisSerializerEnum getValue() {
        return value;
    }

    public void setValue(RedisSerializerEnum value) {
        this.value = value;
    }

    public RedisSerializerEnum getHashKey() {
        return hashKey;
    }

    public void setHashKey(RedisSerializerEnum hashKey) {
        this.hashKey = hashKey;
    }

    public RedisSerializerEnum getHashValue() {
        return hashValue;
    }

    public void setHashValue(RedisSerializerEnum hashValue) {
        this.hashValue = hashValue;
    }

    @Override
    public void setClientType(ClientType clientType) {
        super.setClientType(ClientType.LETTUCE);
    }

    @Override
    public Duration getTimeout() {
        return Objects.isNull(super.getTimeout()) ? Duration.ofMillis(2000) : super.getTimeout();
    }
}