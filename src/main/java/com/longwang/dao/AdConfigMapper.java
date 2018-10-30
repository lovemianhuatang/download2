package com.longwang.dao;

import com.longwang.entity.AdConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 广告Dao接口
 */
public interface AdConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdConfig record);

    int insertSelective(AdConfig record);

    AdConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdConfig record);

    int updateByPrimaryKey(AdConfig record);

    /**
     * 查询所有带条件
     * @param name  广告名称
     * @return
     */
    List<AdConfig> findAll(@Param(value = "name") String name);
}