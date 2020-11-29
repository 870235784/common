package com.tca.common.cache.test.controller;

import com.tca.common.cache.redis.bean.CacheHelper;
import com.tca.common.cache.test.req.CacheRedisReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhouan
 * @Date 2020/11/23
 */
@RestController
@RequestMapping("/cache/redis")
public class CacheRedisController {

    @Autowired
    private CacheHelper cacheHelper;

    @PostMapping("/set")
    public String set(@RequestBody CacheRedisReq cacheRedisReq) {
        cacheHelper.set(cacheRedisReq.getKey(), cacheRedisReq.getValue());
        return "ok";
    }

    @GetMapping("/{key}")
    public String get(@PathVariable(value = "key") String key) {
        return cacheHelper.get(key);
    }

    @PostMapping("/lpush")
    public String lpush() {
        for (int i = 0; i < 10; i++) {
            cacheHelper.leftPush("index:integer", i);
        }
        return "ok";
    }

    @GetMapping("lpop")
    public String lpop() {
        return cacheHelper.leftPopString("index:integer");
    }

}
