package com.tca.common.core.utils;

import com.tca.common.core.bean.Page;
import com.tca.common.utils.BeanUtils;
import com.tca.common.utils.ValidateUtils;

import java.util.List;

/**
 * @author Richard
 * @date 2019/7/29 10:54
 */
public class PageHelper {

    private PageHelper() {
    }


    /**
     * 组装新分页page
     * @param rawPage  源page
     * @param list     分页数据
     * @param <T>      新page 数据类型
     * @return
     */
    public static <T> Page<T> newPage(Page rawPage, List<T> list){
        Page<T> newPage = new Page<>(rawPage.getCurrent(), rawPage.getSize(), rawPage.getTotal());
        newPage.setRecords(list);
        return newPage;
    }






    /**
     * 组装新分页page
     * @param rawPage  源page
     * @param clazz    转换后的DO
     * @param <T>      新page 数据类型
     * @return
     */
    public static <T> Page<T> newPage(Page rawPage, Class<T> clazz){
        List records = rawPage.getRecords();
        if(ValidateUtils.isEmpty(records)){
            return new Page<>(rawPage.getCurrent(), rawPage.getSize(), rawPage.getTotal());
        }
        return newPage(rawPage, BeanUtils.copyListProperties(records,clazz));
    }



    /**
     * 组装新分页page
     * @param rawPage  源page
     * @param clazz    转换后的DO
     * @param <T>      新page 数据类型
     * @return
     */
    public static <T> Page<T> newDeepPage(Page rawPage, Class<T> clazz){
        List records = rawPage.getRecords();
        if(ValidateUtils.isEmpty(records)){
            return new Page<>(rawPage.getCurrent(), rawPage.getSize(), rawPage.getTotal());
        }
        return newPage(rawPage, BeanUtils.deepCopyListProperties(records,clazz));
    }


    /**
     * 空分页
     * @param current
     * @param size
     * @return
     */
    public static Page emptyPage(long current, long size){
        return new Page<>(current, size, 0);
    }


    /**
     * 空分页
     * @return
     */
    public static Page emptyPage(){
        return new Page<>(1, 10, 0);
    }

}
