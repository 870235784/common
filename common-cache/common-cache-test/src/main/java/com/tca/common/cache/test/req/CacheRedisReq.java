package com.tca.common.cache.test.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhouan
 * @Date 2020/11/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheRedisReq implements Serializable {

    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private String value;
}
