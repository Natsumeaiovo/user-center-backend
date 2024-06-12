package com.serein.usercenter.common;

import lombok.Data;

import javax.management.openmbean.TabularData;
import java.io.Serializable;

/**
 * 通用返回类
 * @param <T>
 * @author serein
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String msessage;

    private String description;

    public BaseResponse(int code, T data, String msessage, String description) {
        this.code = code;
        this.data = data;
        this.msessage = msessage;
        this.description = description;
    }

    public BaseResponse(int code, T data, String message) {
        this(code, data, message, "");
    }

    public BaseResponse(int code, T data) {
        this(code, data, "", "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDescription());
    }
}
