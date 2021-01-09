package com.tca.common.cache.redis.enums;

import lombok.Getter;

/**
 * @author zhouan
 * @Date 2020/11/26
 * redis模式
 */
@Getter
public enum RedisModeEnum {

    /**
     * 单机模式
     */
    STANDALONE("standalone"),

    /**
     * 集群模式
     */
    CLUSTER("cluster"),

    /**
     * 哨兵模式
     */
    SENTINEL("sentinel");

    /**
     * 模式
     */
    private String mode;

    /**
     * 构造器
     * @param mode
     */
    RedisModeEnum(String mode) {
        this.mode = mode;
    }

}
