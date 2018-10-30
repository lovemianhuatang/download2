package com.longwang.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * 封装响应数据集
 * @param <T>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Result<T> implements Serializable {

    private int status;     //响应状态吗
    private String msg;     //消息
    private T data;         //数据

    private Result(int status) {
        this.status = status;
    }

    private Result(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private Result(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private Result(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private Result(CodeAndMsg codeAndMsg) {
        this.status = codeAndMsg.getCode();
        this.msg = codeAndMsg.getMsg();
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    /**
     * 判断响应码是否时成功
     * @return
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    /**
     * 成功
     */
    public static <T> Result<T> createBySuccess() {
        return new Result<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> Result<T> createBySuccess(String msg) {
        return new Result<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> createBySuccess(T data) {
        return new Result<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> Result<T> createBySuccess(String msg, T data) {
        return new Result<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 失败
     */
    public static <T> Result<T> createByError() {
        return new Result<T>(ResponseCode.ERROR.getCode());
    }

    public static <T> Result<T> createByError(String errorMsg) {
        return new Result<T>(ResponseCode.ERROR.getCode(), errorMsg);
    }

    public static <T> Result<T> createByError(int errorCode, String errorMsg) {
        return new Result<T>(errorCode, errorMsg);
    }

    public static <T> Result<T> createByError(CodeAndMsg codeAndMsg) {
        return new Result<T>(codeAndMsg);
    }
}
