package com.tca.common.core.utils;


import com.tca.common.core.bean.Page;
import com.tca.common.core.bean.Result;
import com.tca.common.utils.BeanUtils;

import java.util.List;

/**
 * 返回结果操作帮助工具
 * @author Richard
 * @since 1.14
 */
public class ResultHelper {
	
	private ResultHelper() {
    }


    /**
     * 新返回结果
     * @param result
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Result<T> newResult(Result result, Class<T> clazz){
	    if(!result.isSuccess()){
	        return Result.failure(result.getMsg());
        }
        return Result.success(BeanUtils.copyProperties(result.getData(),clazz));
    }


    /**
     * 新返回Page结果
     * @param result
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Result<Page<T>> newPageResult(Result<Page> result, Class<T> clazz){
        if(!result.isSuccess()){
            return Result.failure(result.getMsg());
        }
        Page page = result.getData();
        return Result.success(PageHelper.newPage(page,clazz));
    }
    
    /**
     * 新返回list结果
     * @param result
     * @param clazz
     * @return
     */
    public static <T> Result<List<T>> newListResult(Result<List> result, Class<T> clazz) {
    	if(!result.isSuccess()){
            return Result.failure(result.getMsg());
        }
    	return Result.success(BeanUtils.deepCopyListProperties(result.getData(), clazz));
    }

    /**
     * 新返回Page结果,会将page对象里的List做深拷贝
     * @param result
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Result<Page<T>> newDeepPageResult(Result<Page<?>> result, Class<T> clazz){
        if(!result.isSuccess()){
            return Result.failure(result.getMsg());
        }
        Page page = result.getData();
        return Result.success(PageHelper.newDeepPage(page,clazz));
    }
    
}
