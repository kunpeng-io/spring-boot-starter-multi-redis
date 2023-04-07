package cn.ark.springframework.multiRedis.core;

import cn.ark.springframework.multiRedis.util.RedisConfigUtil;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.redis.core.RedisTemplate;

import static cn.ark.springframework.multiRedis.core.MultiAutoProperties.prefix;

/**
 * Description
 * Copyright ©
 *
 * @author Noah
 * @date 2023/3/21
 */
public class RedisTemplateBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private ConfigurableEnvironment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BindResult<MultiAutoProperties> bind = Binder.get(environment).bind(prefix,MultiAutoProperties.class);
        if (bind.isBound()) {
            MultiAutoProperties multiAutoProperties = bind.get();
            for (MultiRedisProperties multiRedisProperties : multiAutoProperties.getMulti()) {
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RedisTemplate.class);
                beanDefinitionBuilder.setPrimary(multiRedisProperties.getDefaultCache());
                AbstractBeanDefinition rawBeanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
                resolveProperties(beanDefinitionBuilder, multiRedisProperties);
                registry.registerBeanDefinition(multiRedisProperties.getName(), rawBeanDefinition);
            }
        }
    }

    public void resolveProperties(BeanDefinitionBuilder beanDefinitionBuilder, MultiRedisProperties multiRedisProperties) {
        beanDefinitionBuilder.addPropertyValue("connectionFactory", RedisConfigUtil.createLettuceConnectionFactory(multiRedisProperties));
        beanDefinitionBuilder.addPropertyValue("keySerializer", multiRedisProperties.getKey().conversion());
        beanDefinitionBuilder.addPropertyValue("valueSerializer", multiRedisProperties.getValue().conversion());
        beanDefinitionBuilder.addPropertyValue("hashKeySerializer", multiRedisProperties.getHashKey().conversion());
        beanDefinitionBuilder.addPropertyValue("hashValueSerializer", multiRedisProperties.getHashValue().conversion());
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }
}