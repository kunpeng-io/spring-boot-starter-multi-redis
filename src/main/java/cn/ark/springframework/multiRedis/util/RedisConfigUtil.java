package cn.ark.springframework.multiRedis.util;


import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

/**
 * Description
 * Copyright © 
 *
 * @author Noah
 * @date 2023/3/30
 */
public class RedisConfigUtil {
    /**
     * 自定义LettuceConnectionFactory,这一步的作用就是返回根据你传入参数而配置的
     * LettuceConnectionFactory，
     * 也可以说是LettuceConnectionFactory的原理了，
     * <p>
     * 这里定义的方法 createLettuceConnectionFactory，方便快速使用
     */
    public static LettuceConnectionFactory createLettuceConnectionFactory(
            RedisProperties redisProperties) {
        //redis配置
        RedisConfiguration redisConfiguration = new
                RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
        ((RedisStandaloneConfiguration) redisConfiguration).setDatabase(redisProperties.getDatabase());
        ((RedisStandaloneConfiguration) redisConfiguration).setPassword(redisProperties.getPassword());

        //连接池配置
        GenericObjectPoolConfig genericObjectPoolConfig =
                new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(redisProperties.getLettuce().getPool().getMaxIdle());
        genericObjectPoolConfig.setMinIdle(redisProperties.getLettuce().getPool().getMinIdle());
        genericObjectPoolConfig.setMaxTotal(redisProperties.getLettuce().getPool().getMaxActive());
        genericObjectPoolConfig.setMaxWait(redisProperties.getLettuce().getPool().getMaxWait());
        genericObjectPoolConfig.setTimeBetweenEvictionRuns(redisProperties.getLettuce().getPool().getTimeBetweenEvictionRuns());
        //redis客户端配置
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder
                builder = LettucePoolingClientConfiguration.builder().
                commandTimeout(redisProperties.getTimeout());
        builder.shutdownTimeout(redisProperties.getLettuce().getShutdownTimeout());
        builder.poolConfig(genericObjectPoolConfig);
        LettuceClientConfiguration lettuceClientConfiguration = builder.build();
        //根据配置和客户端配置创建连接
        LettuceConnectionFactory lettuceConnectionFactory = new
                LettuceConnectionFactory(redisConfiguration, lettuceClientConfiguration);
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }
}