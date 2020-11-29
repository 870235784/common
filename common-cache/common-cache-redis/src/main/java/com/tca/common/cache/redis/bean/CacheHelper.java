package com.tca.common.cache.redis.bean;

import com.alibaba.fastjson.JSONObject;
import com.tca.common.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouan
 * @Date 2020/11/23
 */
@Component
public class CacheHelper {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * set string value
     * @param key
     * @param value
     */
    private void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * set string value
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     */
    private void setString(String key, String value, long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    /**
     * set
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        if (ValidateUtils.isEmpty(key) || ValidateUtils.isEmpty(value)) {
            return;
        }
        if (value instanceof String) {
            setString(key, (String)value);
            return;
        }
        setString(key, JSONObject.toJSONString(value));
    }

    /**
     * set
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     */
    public void set(String key, Object value, long time, TimeUnit timeUnit) {
        if (ValidateUtils.isEmpty(key) || ValidateUtils.isEmpty(value)) {
            return;
        }
        if (value instanceof String) {
            setString(key, (String)value, time, timeUnit);
            return;
        }
        setString(key, JSONObject.toJSONString(value), time, timeUnit);
    }

    /**
     * get
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        String value = stringRedisTemplate.opsForValue().get(key);
        if (value != null && clazz != null) {
            return JSONObject.parseObject(value, clazz);
        }
        return null;
    }

    /**
     * 取值
     * @param key
     * @return
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * setnx string value
     * @param key
     * @param value
     * @return
     */
    private boolean setnxString(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }


    /**
     * setnx string value
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     * @return
     */
    private boolean setnxString(String key, String value, long time, TimeUnit timeUnit) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
    }

    /**
     * setnx
     * @param key
     * @param value
     * @return
     */
    public boolean setnx(String key, Object value) {
        if (ValidateUtils.isEmpty(key) || ValidateUtils.isEmpty(value)) {
            return false;
        }
        if (value instanceof String) {
            return setnxString(key, (String)value);
        }
        return setnxString(key, JSONObject.toJSONString(value));
    }

    /**
     * setnx
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     * @return
     */
    public boolean setnx(String key, String value, long time, TimeUnit timeUnit) {
        if (ValidateUtils.isEmpty(key) || ValidateUtils.isEmpty(value)) {
            return false;
        }
        if (value instanceof String) {
            return setnxString(key, (String)value, time, timeUnit);
        }
        return setnxString(key, JSONObject.toJSONString(value), time, timeUnit);
    }

    /**
     * list lpush
     * @param key
     * @param value
     * @return
     */
    public Long leftPush(String key, Object value) {
        if (ValidateUtils.isEmpty(key) || ValidateUtils.isEmpty(value)) {
            return null;
        }
        String cacheValue;
        if (value instanceof String) {
            cacheValue = value.toString();
        } else {
            cacheValue = JSONObject.toJSONString(value);
        }
        return stringRedisTemplate.opsForList().leftPush(key, cacheValue);
    }

    /**
     * list rpush
     * @param key
     * @param value
     * @return
     */
    public Long rightPush(String key, Object value) {
        if (ValidateUtils.isEmpty(key) || ValidateUtils.isEmpty(value)) {
            return null;
        }
        String cacheValue;
        if (value instanceof String) {
            cacheValue = value.toString();
        } else {
            cacheValue = JSONObject.toJSONString(value);
        }
        return stringRedisTemplate.opsForList().rightPush(key, cacheValue);
    }

    /**
     * list rpop
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T rightPop(String key, Class<T> clazz) {
        if (ValidateUtils.isEmpty(key)) {
            return null;
        }
        String value = stringRedisTemplate.opsForList().rightPop(key);
        if (ValidateUtils.isEmpty(value)) {
            return null;
        }
        if (clazz == String.class) {
            return (T)value;
        }
        return JSONObject.parseObject(value, clazz);
    }

    /**
     * list rpop string
     * @param key
     * @return
     */
    public String rightPopString(String key) {
        if (ValidateUtils.isEmpty(key)) {
            return null;
        }
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    /**
     * list lpop
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T leftPop(String key, Class<T> clazz) {
        if (ValidateUtils.isEmpty(key)) {
            return null;
        }
        String value = stringRedisTemplate.opsForList().leftPop(key);
        if (ValidateUtils.isEmpty(value)) {
            return null;
        }
        if (clazz == String.class) {
            return (T)value;
        }
        return JSONObject.parseObject(value, clazz);
    }

    /**
     * lpop string
     * @param key
     * @return
     */
    public String leftPopString(String key) {
        if (ValidateUtils.isEmpty(key)) {
            return null;
        }
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    /***
     * list size
     * @param key
     * @return
     */
    public Long getListLength(String key) {
        if (StringUtils.isEmpty(key)) {
            return 0L;
        }

        return stringRedisTemplate.opsForList().size(key);
    }
}
