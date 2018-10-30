package com.longwang.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源分类实体
 */
public class ResouceCatalog implements Serializable {

    private Long id;  // 编号

    private String name;  // 分类名称

    private Date addTime;  // 创建时间

    private ResouceCatalog pResouceCatalog;  // 父级分类

    private Integer siteType;  // 标记资源类型,1表示文章，2表示视频，3表示软件

    private String classPid;  // 父级分类集，存储方式：1，2，3

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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public ResouceCatalog getpResouceCatalog() {
        return pResouceCatalog;
    }

    public void setpResouceCatalog(ResouceCatalog pResouceCatalog) {
        this.pResouceCatalog = pResouceCatalog;
    }

    public Integer getSiteType() {
        return siteType;
    }

    public void setSiteType(Integer siteType) {
        this.siteType = siteType;
    }

    public String getClassPid() {
        return classPid;
    }

    public void setClassPid(String classPid) {
        this.classPid = classPid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}