package com.longwang.entity;

import java.io.Serializable;

/**
 * 站点配置实体
 */
public class SiteConfig implements Serializable {

    private Long id;  // 编号

    private String siteName;  // 站点名称

    private String seoKeyword;  // seo关键字

    private String seoDescription;  // seo描述

    private String siteQuotation;  // 每日语录

    private String aboutSummary;  // 个人签名

    private String aboutDashang;  // 打赏二维码

    private String aboutErweima;  // 二维码

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
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

    public String getSiteQuotation() {
        return siteQuotation;
    }

    public void setSiteQuotation(String siteQuotation) {
        this.siteQuotation = siteQuotation;
    }

    public String getAboutSummary() {
        return aboutSummary;
    }

    public void setAboutSummary(String aboutSummary) {
        this.aboutSummary = aboutSummary;
    }

    public String getAboutDashang() {
        return aboutDashang;
    }

    public void setAboutDashang(String aboutDashang) {
        this.aboutDashang = aboutDashang;
    }

    public String getAboutErweima() {
        return aboutErweima;
    }

    public void setAboutErweima(String aboutErweima) {
        this.aboutErweima = aboutErweima;
    }
}