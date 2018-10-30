package com.longwang.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源内容实体
 */
public class ResouceContent implements Serializable {

    private Long id;  // 编号

    private String title;  // 标题名称

    private Date addTime;  // 创建时间

    private String seoKeyword;  // seo关键字

    private String seoDescription;  // seo描述信息

    private String thumbnailImg;  // 缩略图

    private Integer clickCount;  // 阅读次数

    private String author;  // 作者

    private Integer isShow;  // 是否显示，0表示不显示，1表示显示

    private Integer isHome;  // 是否在首页显示，0表示不显示，1表示显示

    private Integer isNews;  // 是否最新内容，0表示否，1表示是

    private ResouceCatalog resouceCatalog;  // 资源分类

    private Integer siteType;  // 标记资源类型,1表示文章，2表示视频，3表示软件

    private Integer isRecommend;  // 是否推荐，0表示否，1表示是

    private String classPid;  // 父级分类集，存储方式：1，2，3

    private String summary;  // 摘要

    private String description;  // 详细描述

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getSeoKeyword() {
        return seoKeyword;
    }

    public void setSeoKeyword(String seoKeyword) {
        this.seoKeyword = seoKeyword;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getThumbnailImg() {
        return thumbnailImg;
    }

    public void setThumbnailImg(String thumbnailImg) {
        this.thumbnailImg = thumbnailImg;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getIsHome() {
        return isHome;
    }

    public void setIsHome(Integer isHome) {
        this.isHome = isHome;
    }

    public Integer getIsNews() {
        return isNews;
    }

    public void setIsNews(Integer isNews) {
        this.isNews = isNews;
    }

    public ResouceCatalog getResouceCatalog() {
        return resouceCatalog;
    }

    public void setResouceCatalog(ResouceCatalog resouceCatalog) {
        this.resouceCatalog = resouceCatalog;
    }

    public Integer getSiteType() {
        return siteType;
    }

    public void setSiteType(Integer siteType) {
        this.siteType = siteType;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getClassPid() {
        return classPid;
    }

    public void setClassPid(String classPid) {
        this.classPid = classPid;
    }
}