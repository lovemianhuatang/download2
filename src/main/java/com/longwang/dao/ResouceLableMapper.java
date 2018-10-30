package com.longwang.dao;

import com.longwang.entity.ResouceLable;

import java.util.List;

/**
 * 标签Dao接口
 */
public interface ResouceLableMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResouceLable record);

    int insertSelective(ResouceLable record);

    ResouceLable selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResouceLable record);

    int updateByPrimaryKeyWithBLOBs(ResouceLable record);

    int updateByPrimaryKey(ResouceLable record);

    /**
     * 查询所有带条件
     * @param resouceLable  标签实体
     * @return
     */
    List<ResouceLable> findAll(ResouceLable resouceLable);
}