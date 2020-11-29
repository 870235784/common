package com.tca.common.cache.test.req;

import lombok.Data;

/**
 * @author zhouan
 * @Date 2020/11/23
 */
@Data
public class CacheRedisReq {

    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private String value;
}
