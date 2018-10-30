package com.longwang.service;

import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.entity.AdConfig;

/**
 * 广告Service接口
 * @author longwang
 * @create 2018-10-25 11:09
 */
public interface IAdConfigService {

    /**
     * 查询所有带条件
     * @param name  广告名称
     * @param pageNum  当前页
     * @param pageSize  //每页记录数
     * @return
     */
    Result<PageInfo<AdConfig>> findAll(String name, Integer pageNum, Integer pageSize);

    /**
     * 根据Id获取实体
     * @param id  主键
     * @return
     */
    Result<AdConfig> findById(Long id);

    /**
     * 添加或修改用户
     * @param adConfig  用户实体
     * @return
     */
    Result<String> save(AdConfig adConfig);

    /**
     * 删除用户
     * @param id  主键
     * @return
     */
    Result<String> delete(Long id);

}
