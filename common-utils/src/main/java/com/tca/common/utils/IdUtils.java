package com.tca.common.utils;


import com.tca.common.utils.helper.IdWorker;

/**
 * @author zhouan
 * @Date 2020-11-23
 * 分布式id生成器
 */
public class IdUtils {

    private IdUtils() {
    }

    /**
     * 生成id
     * @return
     */
    public static Long getId(){
       return IdWorker.getId();
    }

}
