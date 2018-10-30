package com.longwang.common;

/**
 * 一些常用得响应数据
 */
public enum ResponseCode {

    SUCCESS(1, "SUCCESS"),                      //成功
    ERROR(0, "ERROR"),                          //失败
    NEED_LOGIN(10, "NEED LOGIN"),               //需要登录
    SERVICE_ERROR(500, "SERVICE ERROR");        //内部错误

    private int code;
    private String describe;

    private ResponseCode(int code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public int getCode() {
        return this.code;
    }

    public String getDescribe() {
        return this.describe;
    }
}
