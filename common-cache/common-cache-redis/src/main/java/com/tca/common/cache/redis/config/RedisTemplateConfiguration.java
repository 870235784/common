/*
package com.tca.common.cache.redis.config;

import com.tca.common.cache.redis.properties.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

*/
/**
 * @author zhoua
 * @Date 2021/1/4
 *//*

@Configuration
@Slf4j
public class RedisTemplateConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisConnectionFactory redisConnectionFactory = new LettuceConnectionFactory();
        return redisConnectionFactory;
    }

    @Configuration
    public class RedissonClientConfiguration {

        */
/**
         * 单机模式
         * @return
         *//*

        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "single", matchIfMissing = true)
        StringRedisTemplate stringRedisTemplateSingle() {
            StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
            return stringRedisTemplate;
        }


        */
/**
         * 集群模式
         * @return
         *//*

        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "cluster")
        StringRedisTemplate stringRedisTemplateCluster(RedisConnectionFactory factory) {
            StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
            stringRedisTemplate.setConnectionFactory(factory);
            return stringRedisTemplate;
        }

        */
/**
         * 哨兵模式
         * @return
         *//*

        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "sentinel")
        StringRedisTemplate stringRedisTemplateSentinel() {
            StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
            return stringRedisTemplate;
        }
    }
}
*/
