package com.longwang.entity;

import java.io.Serializable;

/**
 * 资源标签实体
 */
public class ResouceLable implements Serializable {

    private Long id;  // 编号

    private String name;  // 标签名称

    private Integer siteType;  // 标记资源类型,1表示文章，2表示视频，3表示软件

    private String color;  // 标签颜色

    private Integer isNews;  // 热门

    private String remark;  // 备注

    public ResouceLable(Long id) {
        this.id = id;
    }

    public ResouceLable() {
    }

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

    public Integer getSiteType() {
        return siteType;
    }

    public void setSiteType(Integer siteType) {
        this.siteType = siteType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getIsNews() {
        return isNews;
    }

    public void setIsNews(Integer isNews) {
        this.isNews = isNews;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}