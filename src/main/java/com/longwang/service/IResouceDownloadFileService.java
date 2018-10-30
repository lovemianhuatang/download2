package com.longwang.service;

import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.entity.ResouceDownloadFile;

/**
 * 软件Service接口
 * @author longwang
 * @create 2018-10-25 11:09
 */
public interface IResouceDownloadFileService {

    /**
     * 查询所有带条件
     * @param title  标题
     * @param pageNum  当前页
     * @param pageSize  //每页记录数
     * @return
     */
    Result<PageInfo<ResouceDownloadFile>> findAll(String title, Integer pageNum, Integer pageSize);

    /**
     * 根据Id获取实体
     * @param id  主键
     * @return
     */
    Result<ResouceDownloadFile> findById(Long id);

    /**
     * 添加或修改软件
     * @param resouceDownloadFile  软件实体
     * @return
     */
    Result<String> save(ResouceDownloadFile resouceDownloadFile);

    /**
     * 删除软件
     * @param id  主键
     * @return
     */
    Result<String> delete(Long id);

    /**
     * 根据文章Id查询软件
     * @param contentId  文章Id
     * @return
     */
    Result<ResouceDownloadFile> findAllByContentId(Long contentId);

    /**
     * 根据contentId删除资源软件
     * @param contentId  文章Id
     * @return
     */
    Result<String> deleteByContentId(Long contentId);

}
