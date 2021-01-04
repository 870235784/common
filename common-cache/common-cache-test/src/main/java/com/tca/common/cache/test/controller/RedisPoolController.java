package com.tca.common.cache.test.controller;

import com.tca.common.cache.redis.bean.CacheHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoua
 * @Date 2020/12/31
 */
@RequestMapping("/cache/pool")
@RestController
@Slf4j
public class RedisPoolController {

    @Autowired
    private CacheHelper cacheHelper;

    @GetMapping("/test")
    public String test() {
        new Thread(() -> {
            // 并发访问10个
            for (int i = 0; i < 10; i++) {
                String result = cacheHelper.get("hello");
                log.info("result = {}", result);
            }
            for (int i = 0; i < 1000; i++) {
                String result = cacheHelper.get("hello");
                log.info("result = {}", result);
            }
        }).start();
        return "ok";
    }

    @GetMapping("/test2")
    public String test2() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                String result = cacheHelper.get("hello");
                log.info("result = {}", result);
            }).start();
        }

        return "ok";
    }

}
