# Spring Boot Starter Multi Redis

Spring Boot Starter Multi Redis 是一个基于 Spring Boot 的 Redis 多数据源启动器，可以轻松地在 Spring Boot 项目中使用多个 Redis 数据源。

本项目通过自动配置类的方式，实现了在 Spring Boot 中配置和管理多个 Redis 数据源的功能。您可以根据自己的需求添加或删除 Redis 数据源，并为每个数据源配置不同的 Redis 连接属性。

## 特性

- 支持配置多个 Redis 数据源
- 可以为每个数据源配置不同的 Redis 连接属性
- 使用自动配置类实现配置和管理多个Redis数据源

## 安装

您可以在 Maven 或 Gradle 项目中添加以下依赖项来使用该 Starter：

```xml
<dependency>
    <groupId>io.github.kunpeng-io</groupId>
    <artifactId>spring-boot-starter-multi-redis</artifactId>
    <version>1.0.0</version>
</dependency>
```

```groovy
implementation 'io.github.kunpeng-io:spring-boot-starter-multi-redis:1.0.0'
```

## 快速开始

1. 添加依赖项并配置 Redis 数据源

在 `application.yml` 文件中添加 Redis 数据源的连接属性，例如：

```yaml
spring:
  redis:
    multi:
      - name: masterRedis
        #声明为主数据源
        default-cache: true
        host: 127.0.0.1
        password: 123
        port: 6379
        database: 0
        #指定序列化方式
        key: string
        value: json
        hash-key: string
        hash-value: json
        
      - name: slaverRedis
        host: 127.0.0.1
        password: 123
        port: 6380
```

**注意**：客户端连接池默认使用lettuce 配置为jedis无效


2. 使用 `@Qualifier` 注解

在需要使用 Redis 的地方，可以使用 `@Qualifier` 注解来指定连接的 Redis 数据源 value为配置文件中数据源的name，例如：

```java
import org.springframework.beans.factory.annotation.Qualifier;

@Component
public class MyComponent {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("slaverRedis")
    private RedisTemplate slaverRedis;

    // ...
}
```


## 参考文献

- [Spring Boot 官方文档](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Data Redis 官方文档](https://docs.spring.io/spring-data/redis/docs/current/reference/html/)
- [Redis 官方文档](https://redis.io/documentation)