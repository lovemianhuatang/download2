package com.longwang.dao;

import com.longwang.entity.ResouceContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源内容Dao接口
 */
public interface ResouceContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResouceContent record);

    int insertSelective(ResouceContent record);

    ResouceContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResouceContent record);

    int updateByPrimaryKeyWithBLOBs(ResouceContent record);

    int updateByPrimaryKey(ResouceContent record);

    /**
     * 查询所有带条件
     * @param title  标题
     * @param siteType  类别
     * @return
     */
    List<ResouceContent> findAll(@Param(value = "title") String title, @Param(value = "siteType") Integer siteType);

    /**
     * 根据分类Id查询资源文章
     * @param catalogId  分类Id
     * @return
     */
    List<ResouceContent> findAllByCatalogId(@Param(value = "catalogId") Long catalogId);
}