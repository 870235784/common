package com.tca.common.core.bean;


import com.alibaba.fastjson.JSONObject;
import com.tca.common.core.enums.CommonResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * 通用返回结果
 *
 * @author zhoua
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回提示语
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 构造器
     * @param code
     * @param msg
     */
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构造器
     * @param code
     * @param msg
     * @param data
     */
    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    /**
     * 操作成功
     * @param data
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result(CommonResultEnum.SUCCESS.getCode(), CommonResultEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 操作成功
     * @return
     */
    public static Result success(){
        return new Result(CommonResultEnum.SUCCESS.getCode(), CommonResultEnum.SUCCESS.getMsg());
    }

    /**
     * 操作失败
     * @return
     */
    public static Result failure(String msg){
        return new Result(CommonResultEnum.ERROR.getCode(), msg);
    }

    /**
     * 操作失败
     * @return
     */
    public static Result failure(){
        return new Result(CommonResultEnum.ERROR.getCode(), CommonResultEnum.ERROR.getMsg());
    }


    /**
     * 判断是否成功
     * @return
     */
    public boolean isSuccess(){
        return CommonResultEnum.SUCCESS.getCode().equals(this.code);
    }


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
