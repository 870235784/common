package com.tca.common.cache.test.controller;

import com.tca.common.cache.redis.bean.CacheHelper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhouan
 * @Date 2020/11/24
 */
@RestController
@RequestMapping("/stock")
@Slf4j
public class StockController {

    @Autowired
    private CacheHelper cacheHelper;

    private Lock lock = new ReentrantLock();

    private RedissonClient redissonClient;

    private RLock rLock;

    public StockController (RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
        this.rLock = redissonClient.getLock("stock:lock");
    }

    /**
     * 下订单(不用分布式锁时)
     * @return
     */
    @GetMapping("/order")
    public String order() {
        int stock = Integer.parseInt(cacheHelper.get("stock"));
        if (stock > 0) {
            log.info("获取第 {} 个", stock);
            stock--;
            cacheHelper.set("stock", stock);
            return "ok";
        }
        log.info("库存不足");
        return "库存不足";
    }

    /**
     * 使用普通锁
     * @return
     */
    @GetMapping("/orderWithLock")
    public String orderWithLock() {
        try {
            lock.lock();
            int stock = Integer.parseInt(cacheHelper.get("stock"));
            if (stock > 0) {
                log.info("orderWithLock-获取第 {} 个, {}", stock, Thread.currentThread().getName());
                stock--;
                cacheHelper.set("stock", stock);
            } else {
                log.info("orderWithLock-库存不足");
            }
        } finally {
            lock.unlock();
        }
        return "ok";
    }

    /**
     * 使用分布式锁
     * @return
     */
    @GetMapping("/orderWithDistributeLock")
    public String orderWithDistributeLock() {
        try {
            rLock.tryLock(1, TimeUnit.SECONDS);
            int stock = Integer.parseInt(cacheHelper.get("stock"));
            if (stock > 0) {
                log.info("orderWithDistributeLock-获取第 {} 个, {}", stock, Thread.currentThread().getName());
                stock--;
                cacheHelper.set("stock", stock);
            } else {
                log.info("orderWithDistributeLock-库存不足");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
            return "ok";
        }
    }
}
