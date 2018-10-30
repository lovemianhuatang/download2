package com.longwang.dao;

import com.longwang.entity.ResouceDownloadFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源文件下载Dao接口
 */
public interface ResouceDownloadFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResouceDownloadFile record);

    int insertSelective(ResouceDownloadFile record);

    ResouceDownloadFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResouceDownloadFile record);

    int updateByPrimaryKey(ResouceDownloadFile record);

    /**
     * 查询所有带条件
     * @param title  标题
     * @return
     */
    List<ResouceDownloadFile> findAll(@Param(value = "title") String title);

    /**
     * 根据文章Id查询软件
     * @param contentId  文章Id
     * @return
     */
    ResouceDownloadFile findAllByContentId(@Param(value = "contentId") Long contentId);

    /**
     * 根据contentId删除资源软件
     * @param contentId  文章Id
     * @return
     */
    int deleteByContentId(Long contentId);
}