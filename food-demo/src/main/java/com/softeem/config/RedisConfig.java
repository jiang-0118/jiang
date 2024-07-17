package com.softeem.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    RedisTemplate redisTemplate(@Autowired RedisConnectionFactory connectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        // 指定Redis连接
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(
                RedisSerializer.string()
        );  // 设置Key
        redisTemplate.setValueSerializer(
                RedisSerializer.string()
        ); // 设置Value 序列化方式
        // Jackson  json序列化
        // JSON
        ObjectMapper objectMapper = new ObjectMapper();
        // 开放对象属性的访问权限
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer
                = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }
}
