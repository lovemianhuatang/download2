package com.longwang.service;

import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.entity.ResouceVideo;

import java.util.List;

/**
 * 视频Service接口
 * @author longwang
 * @create 2018-10-25 11:09
 */
public interface IResouceVideoService {

    /**
     * 查询所有带条件
     * @param title  标题
     * @param pageNum  当前页
     * @param pageSize  //每页记录数
     * @return
     */
    Result<PageInfo<ResouceVideo>> findAll(String title, Integer pageNum, Integer pageSize);

    /**
     * 根据Id获取实体
     * @param id  主键
     * @return
     */
    Result<ResouceVideo> findById(Long id);

    /**
     * 添加或修改视频
     * @param resouceVideo  视频实体
     * @return
     */
    Result<String> save(ResouceVideo resouceVideo);

    /**
     * 根据contentId删除资源视频
     * @param contentId  文章Id
     * @return
     */
    Result<String> deleteByContentId(Long contentId);

    /**
     * 根据文章Id查询视频
     * @param contentId  文章Id
     * @return
     */
    Result<List<ResouceVideo>> findAllByContentId(Long contentId);

}
