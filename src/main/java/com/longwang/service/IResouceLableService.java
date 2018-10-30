package com.longwang.service;

import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.entity.ResouceLable;

/**
 * 标签Service接口
 * @author longwang
 * @create 2018-10-25 11:09
 */
public interface IResouceLableService {

    /**
     * 查询所有带条件
     * @param resouceLable  标签实体
     * @param pageNum  当前页
     * @param pageSize  //每页记录数
     * @return
     */
    Result<PageInfo<ResouceLable>> findAll(ResouceLable resouceLable, Integer pageNum, Integer pageSize);

    /**
     * 根据Id获取实体
     * @param id  主键
     * @return
     */
    Result<ResouceLable> findById(Long id);

    /**
     * 添加或修改标签
     * @param resouceLable  标签实体
     * @return
     */
    Result<String> save(ResouceLable resouceLable);

    /**
     * 删除标签
     * @param id  主键
     * @return
     */
    Result<String> delete(Long id);

}
