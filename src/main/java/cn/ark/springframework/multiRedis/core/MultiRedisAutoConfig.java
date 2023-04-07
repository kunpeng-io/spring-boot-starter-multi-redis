package cn.ark.springframework.multiRedis.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Description
 * Copyright ©
 *
 * @author Noah
 * @date 2023/3/21
 */

@EnableConfigurationProperties(MultiAutoProperties.class)
@Configuration
@Import(RedisTemplateBeanDefinitionRegistrar.class)
public class MultiRedisAutoConfig {

}