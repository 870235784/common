package com.tca.common.core.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhoua
 *
 * 返回结果枚举
 */
@Getter
@AllArgsConstructor
public enum CommonResultEnum {

    /**
     * 成功
     */
    SUCCESS(200,"操作成功"),

    /**
     * 失败
     */
    ERROR(500,"操作失败"),
    ;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String msg;


}
