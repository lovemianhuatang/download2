package com.longwang.dao;

import com.longwang.entity.LinkConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 友情链接Dao接口
 */
public interface LinkConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LinkConfig record);

    int insertSelective(LinkConfig record);

    LinkConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LinkConfig record);

    int updateByPrimaryKeyWithBLOBs(LinkConfig record);

    int updateByPrimaryKey(LinkConfig record);

    /**
     * 查询所有带条件
     * @param name  链接名称
     * @return
     */
    List<LinkConfig> findAll(@Param(value = "name") String name);
}