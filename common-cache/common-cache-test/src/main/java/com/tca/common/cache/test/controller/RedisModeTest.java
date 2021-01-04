package com.tca.common.cache.test.controller;

import com.tca.common.cache.redis.bean.CacheHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoua
 * @Date 2021/1/3
 */
@RestController
@RequestMapping("/mode")
@Slf4j
public class RedisModeTest {

    @Autowired
    private CacheHelper cacheHelper;

    @GetMapping("/cluster")
    public String cluster() {
        for (int i = 0; i < 1000; i++) {
            cacheHelper.set("k" + i, i, 10, TimeUnit.MINUTES);
        }
        for (int i = 0; i < 1000; i++) {
            log.info("k{} = {}", i, cacheHelper.get("k" + i));
        }
        return "cluster";
    }

    @GetMapping("/sentinel")
    public String sentinel() {
        for (int i = 0; i < 1000; i++) {
            cacheHelper.set("s" + i, i, 10, TimeUnit.MINUTES);
        }
        for (int i = 0; i < 1000; i++) {
            log.info("s{} = {}", i, cacheHelper.get("s" + i));
        }
        return "sentinel";
    }
}
