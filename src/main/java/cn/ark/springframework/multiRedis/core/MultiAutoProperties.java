package cn.ark.springframework.multiRedis.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;
import static cn.ark.springframework.multiRedis.core.MultiAutoProperties.prefix;

/**
 * Description
 * Copyright ©
 *
 * @author Noah
 * @date 2023/4/7
 */
@ConfigurationProperties(prefix)
public class MultiAutoProperties {

    public static final String prefix = "spring.redis";
    private List<MultiRedisProperties> multi;

    public List<MultiRedisProperties> getMulti() {
        return multi;
    }

    public void setMulti(List<MultiRedisProperties> multi) {
        this.multi = multi;
    }
}