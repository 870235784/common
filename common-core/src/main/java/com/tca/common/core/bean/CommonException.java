package com.tca.common.core.bean;

/**
 * 通用异常类
 *
 * @author zhoua
 */
public class CommonException extends RuntimeException {

    private Integer errorCode;

    public CommonException() {
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }


    public CommonException(Integer errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
