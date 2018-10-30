package com.longwang.dao;

import com.longwang.entity.ResouceCatalog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源分类Dao接口
 */
public interface ResouceCatalogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResouceCatalog record);

    int insertSelective(ResouceCatalog record);

    ResouceCatalog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResouceCatalog record);

    int updateByPrimaryKeyWithBLOBs(ResouceCatalog record);

    int updateByPrimaryKey(ResouceCatalog record);

    /**
     * 查询所有带条件
     * @param siteType  资源类型
     * @return
     */
    List<ResouceCatalog> findAll(@Param(value = "siteType") Integer siteType);

    /**
     * 根据Id查询一级分类下的分类
     * @param classPid  父Id
     * @return
     */
    List<ResouceCatalog> findAllByClassPid(@Param(value = "classPid") Integer classPid);

    /**
     * 根据父Id查询分类
     * @param pid  父Id
     * @return
     */
    List<ResouceCatalog> findAllByPid(@Param(value = "pid") Long pid);



}