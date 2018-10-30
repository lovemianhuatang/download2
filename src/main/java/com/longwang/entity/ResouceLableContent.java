package com.longwang.entity;

import java.io.Serializable;

/**
 * 标签和资源内容关系实体
 */
public class ResouceLableContent implements Serializable {

    private Long id;  // 编号

    private ResouceContent resouceContent;  // 内容

    private ResouceLable resouceLable;  // 标签

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResouceContent getResouceContent() {
        return resouceContent;
    }

    public void setResouceContent(ResouceContent resouceContent) {
        this.resouceContent = resouceContent;
    }

    public ResouceLable getResouceLable() {
        return resouceLable;
    }

    public void setResouceLable(ResouceLable resouceLable) {
        this.resouceLable = resouceLable;
    }
}