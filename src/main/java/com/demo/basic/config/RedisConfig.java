package com.demo.basic.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 拆分Reis存儲位置
 * Created on 2018/12/26
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    protected String redisHostName;

    @Value("${redis.port}")
    protected int redisPort;

    @Value("${redis.password}")
    protected String password;

    @Value("${redis.jedis.pool.min-idle}")
    protected int minIdle;

    @Value("${redis.jedis.pool.max-idle}")
    protected int maxIdle;

    @Value("${redis.jedis.pool.test-on-borrow}")
    protected boolean testOnBorrow;

    @Value("${redis.jedis.pool.test-on-return}")
    protected boolean testOnReturn;

    @Value("${redis.cache.database}")
    protected int cacheDatabase;

    @Value("${redis.oauth.database}")
    protected int oauthDatabase;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean("tokenRedis")
    @Primary
    JedisConnectionFactory tokenRedis() {
        JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig());
        factory.setHostName(redisHostName);
        factory.setPort(redisPort);
        factory.setDatabase(oauthDatabase);
        factory.setUsePool(true);
        return factory;
    }

    @Bean("cacheRedis")
    public JedisConnectionFactory cacheRedis() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(jedisPoolConfig());
        connectionFactory.setHostName(redisHostName);
        connectionFactory.setPort(redisPort);
        connectionFactory.setUsePool(true);
        connectionFactory.setPassword(password);
        connectionFactory.setDatabase(cacheDatabase);
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }

    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(20000);
        poolConfig.setTestOnBorrow(testOnBorrow);
        poolConfig.setTestOnReturn(testOnReturn);
        return poolConfig;
    }

    @Bean
    public RedisCacheManager cacheManager() {

        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(cacheRedis()),
                this.getRedisCacheConfigurationWithTtl(30), // 默認策略，未配置的 key 會使用這個
                this.getRedisCacheConfigurationMap() // 指定 key 策略
        );
    }

    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        //SsoCache和BasicDataCache進行過期的時間配置
        redisCacheConfigurationMap.put("userInfo", this.getRedisCacheConfigurationWithTtl(3600));
        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(jackson2JsonRedisSerializer)
        ).entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }
}
