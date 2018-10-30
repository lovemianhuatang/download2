package com.longwang.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告实体
 */
public class AdConfig implements Serializable {

    private Long id;  // 编号

    private String name;  // 广告名称

    private String adImg;  // 图片

    private Integer isShow;  // 是否显示，0表示不显示，1表示显示

    private String remark;  // 描述

    private String url;  // 跳转地址

    private Date addTime;  // 创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdImg() {
        return adImg;
    }

    public void setAdImg(String adImg) {
        this.adImg = adImg;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}