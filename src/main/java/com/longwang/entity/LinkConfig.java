package com.longwang.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 友情链接实体
 */
public class LinkConfig implements Serializable {

    private Long id;  // 编号

    private String name;  // 链接名称

    private String url;  // 链接地址

    private Integer isShow;  // 是否显示，0表示不显示，1表示显示

    private Date addTime;  // 添加时间

    private String remark;  // 备注

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}