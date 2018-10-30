package com.longwang.dao;

import com.longwang.entity.ResouceVideo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源视频Dao接口
 */
public interface ResouceVideoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResouceVideo record);

    int insertSelective(ResouceVideo record);

    ResouceVideo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResouceVideo record);

    int updateByPrimaryKey(ResouceVideo record);

    /**
     * 查询所有带条件
     * @param title  标题
     * @return
     */
    List<ResouceVideo> findAll(@Param(value = "title") String title);

    /**
     * 根据文章Id查询视频
     * @param contentId  文章Id
     * @return
     */
    List<ResouceVideo> findAllByContentId(@Param(value = "contentId") Long contentId);

    /**
     * 根据contentId删除资源视频
     * @param contentId  文章Id
     * @return
     */
    int deleteByContentId(Long contentId);
}