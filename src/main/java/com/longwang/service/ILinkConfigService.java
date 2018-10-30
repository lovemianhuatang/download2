package com.longwang.service;

import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.entity.LinkConfig;

/**
 * 友情链接Service接口
 * @author longwang
 * @create 2018-10-25 11:09
 */
public interface ILinkConfigService {

    /**
     * 查询所有带条件
     * @param name  友情链接名
     * @param pageNum  当前页
     * @param pageSize  //每页记录数
     * @return
     */
    Result<PageInfo<LinkConfig>> findAll(String name, Integer pageNum, Integer pageSize);

    /**
     * 根据Id获取实体
     * @param id  主键
     * @return
     */
    Result<LinkConfig> findById(Long id);

    /**
     * 添加或修改友情链接
     * @param linkConfig  友情链接实体
     * @return
     */
    Result<String> save(LinkConfig linkConfig);

    /**
     * 删除友情链接
     * @param id  主键
     * @return
     */
    Result<String> delete(Long id);

}
