package com.xiaoma.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult <T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public ResponseResult(){
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
    }

    public ResponseResult(Integer code, T data){
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(String msg, T data){
        this.msg = msg;
        this.data = data;
    }

    public static ResponseResult errorResult(Integer code, String msg){
        ResponseResult result = new ResponseResult();
        return result.error(code, msg);
    }

    public static ResponseResult okResult() {
        ResponseResult result = new ResponseResult();
        return result;
    }

    public static ResponseResult okResult(Integer code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.ok(code, msg, null);
    }

    public static ResponseResult okResult(Object data) {
        ResponseResult result = okResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg());
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static ResponseResult errorResult(AppHttpCodeEnum enums){
        return okResult(enums.getCode(), enums.getMsg());
    }

    public static ResponseResult errorResult(AppHttpCodeEnum enums, String msg){
        return okResult(enums.getCode(), msg);
    }




    public ResponseResult<?> error(Integer code, String msg){
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ResponseResult<?> ok(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data){
        this.code = code;
        this.data = data;
        return this;
    }

    public ResponseResult<?> ok(T data) {
        this.data = data;
        return this;
    }


}
