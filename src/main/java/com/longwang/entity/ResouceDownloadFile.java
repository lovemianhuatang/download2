package com.longwang.entity;

import java.io.Serializable;

/**
 * 文件下载资源实体
 */
public class ResouceDownloadFile implements Serializable {

    private Long id;  // 编号

    private String fileName; // 文件名称

    private Long fileSize;  // 文件大小

    private String url;  // 储存地址

    private Long downloadCount;  // 下载次数

    private String ext;  // 扩展名

    private ResouceContent resouceContent;  // 内容

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Long downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public ResouceContent getResouceContent() {
        return resouceContent;
    }

    public void setResouceContent(ResouceContent resouceContent) {
        this.resouceContent = resouceContent;
    }
}