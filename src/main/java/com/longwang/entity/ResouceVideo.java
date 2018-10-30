package com.longwang.entity;

import java.io.Serializable;

/**
 * 视频资源实体
 */
public class ResouceVideo implements Serializable {

    private Long id;  // 编号

    private String title;  // 视频名称

    private String url;  // 视频地址

    private Integer orderSort;  // 集数,从小到大

    private ResouceContent resouceContent;  // 内容

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(Integer orderSort) {
        this.orderSort = orderSort;
    }

    public ResouceContent getResouceContent() {
        return resouceContent;
    }

    public void setResouceContent(ResouceContent resouceContent) {
        this.resouceContent = resouceContent;
    }
}