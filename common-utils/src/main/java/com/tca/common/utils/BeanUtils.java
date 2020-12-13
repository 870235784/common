package com.tca.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * BeanUtils
 *
 * @author zhoua
 *
 */
public class BeanUtils {

    private BeanUtils() {
    }

    /**
     * 属性拷贝
     *
     * @param source      被拷贝的对象
     * @param targetClass 目的对象的Class
     * @param <T>
     * @return
     */
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T target = null;
        try {
            if(Number.class.isAssignableFrom(targetClass)){
                return (T)source;
            }
            target = targetClass.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }


    /**
     * 深属性拷贝
     *
     * @param source      被拷贝的对象
     * @param targetClass 目的对象的Class
     * @param <T>
     * @return
     */
    public static <T> T deepCopyProperties(Object source, Class<T> targetClass) {
        if(source == null){
            return null;
        }
        return JSONObject.parseObject(JSONObject.toJSONString(source), targetClass);
    }

    /**
     * 属性拷贝
     *
     * @param source      被拷贝的对象
     * @param target      目的对象
     * @param <T>
     * @return
     */
    public static <T> T copyProperties(Object source, T target) {
        if (source == null) {
            return null;
        }
        try {
            if(Number.class.isAssignableFrom(target.getClass())){
                return (T)source;
            }
            org.springframework.beans.BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * List属性拷贝
     *
     * @param sourceList  被拷贝的对象
     * @param targetClass 目的对象的Class
     * @param <T>
     * @return
     */
    public static <T> List<T> copyListProperties(List<?> sourceList, Class<T> targetClass) {
        if (ValidateUtils.isEmpty(sourceList)) {
            return null;
        }
        ArrayList<T> ts = new ArrayList<>(sourceList.size());
        sourceList.forEach(item -> {
            ts.add(copyProperties(item, targetClass));
        });
        return ts;
    }



    /**
     * List属性深拷贝
     *
     * @param sourceList  被拷贝的对象
     * @param targetClass 目的对象的Class
     * @param <T>
     * @return
     */
    public static <T> List<T> deepCopyListProperties(List<?> sourceList, Class<T> targetClass) {
        if (ValidateUtils.isEmpty(sourceList)) {
            return null;
        }
        return JSONArray.parseArray(JSONObject.toJSONString(sourceList), targetClass);
    }

}
