package com.tca.common.utils.test;

import com.tca.common.utils.IdUtils;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouan
 * @Date 2020/11/27
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        int num = 1000;
        HashSet<Long> set = new HashSet<>();

        ExecutorService executorService = Executors.newFixedThreadPool(num);
        for (int i = 0; i < num; i++) {
            executorService.submit(() -> {
                Long id = IdUtils.getId();
                System.out.println(id);
                set.add(id);
            });
        }
        executorService.shutdown();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(set.size());
    }

}
