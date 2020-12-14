package com.tca.common.cache.test.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.tca.common.core.bean.Result;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * 测试基于 guava 的令牌桶限流
 *
 * @author zhoua
 * @Date 2020/12/13
 */
@RestController
@RequestMapping(value = "/rateLimit")
public class RateLimitController {

    /**
     * guava 的 RateLimiter 采用令牌桶算法
     *
     * create的参数表示 每秒发放令牌数
     */
    private RateLimiter rateLimiter = RateLimiter.create(1);

    @PostMapping("/order")
    public Result order() {
        if (rateLimiter.tryAcquire(200, TimeUnit.MILLISECONDS)) {
            return Result.success();
        }
        return Result.failure("服务器忙, 请稍后重试");

    }

    @GetMapping("/order")
    public Result orderGet() {
        if (rateLimiter.tryAcquire(200, TimeUnit.MILLISECONDS)) {
            return Result.success();
        }
        return Result.failure("服务器忙, 请稍后重试");

    }



}
