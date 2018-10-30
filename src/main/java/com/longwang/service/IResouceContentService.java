package com.longwang.service;

import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.entity.ResouceContent;

import java.util.List;

/**
 * 文章Service接口
 * @author longwang
 * @create 2018-10-25 11:09
 */
public interface IResouceContentService {

    /**
     * 查询所有带条件
     * @param title  标题
     * @param siteType  类别
     * @param pageNum  当前页
     * @param pageSize  //每页记录数
     * @return
     */
    Result<PageInfo<ResouceContent>> findAll(String title, Integer siteType, Integer pageNum, Integer pageSize);

    /**
     * 根据Id获取实体
     * @param id  主键
     * @return
     */
    Result<ResouceContent> findById(Long id);

    /**
     * 添加或修改文章
     * @param resouceContent  文章实体
     * @return
     */
    Result<ResouceContent> save(ResouceContent resouceContent);

    /**
     * 删除文章
     * @param id  主键
     * @return
     */
    Result<String> delete(Long id);

    /**
     * 根据分类Id查询资源文章
     * @param catalogId  分类Id
     * @return
     */
    Result<List<ResouceContent>> findAllByCatalogId(Long catalogId);

}
