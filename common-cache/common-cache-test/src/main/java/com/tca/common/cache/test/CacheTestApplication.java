package com.tca.common.cache.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhouan
 * @Date 2020/11/23
 */
@SpringBootApplication
@ComponentScan("com.tca")
public class CacheTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheTestApplication.class, args);
    }
}
