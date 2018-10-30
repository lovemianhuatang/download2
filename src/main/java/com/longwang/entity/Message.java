package com.longwang.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 留言实体
 */
public class Message implements Serializable {

    private Long id;  // 编号

    private String ip;  // ip地址

    private String address;  // 省市区地址

    private Date addTime;  // 留言时间

    private String content;  // 内容

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}