package com.longwang.dao;

import com.longwang.entity.ResouceLableContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标签和资源内容关系Dao接口
 */
public interface ResouceLableContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResouceLableContent record);

    int insertSelective(ResouceLableContent record);

    ResouceLableContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResouceLableContent record);

    int updateByPrimaryKey(ResouceLableContent record);

    /**
     * 根据文章Id查询标签
     * @param contentId  文章Id
     * @return
     */
    List<ResouceLableContent> findAllByContentId(@Param(value = "contentId") Long contentId);

    /**
     * 根据contentId删除关联
     * @param contentId  文章Id
     * @return
     */
    int deleteByContentId(Long contentId);

}