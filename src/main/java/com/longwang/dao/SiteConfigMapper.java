package com.longwang.dao;

import com.longwang.entity.SiteConfig;

/**
 * 站点配置Dao接口
 */
public interface SiteConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SiteConfig record);

    int insertSelective(SiteConfig record);

    SiteConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SiteConfig record);

    int updateByPrimaryKey(SiteConfig record);
}