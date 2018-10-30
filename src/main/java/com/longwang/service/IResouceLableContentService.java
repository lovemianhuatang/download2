package com.longwang.service;

import com.longwang.common.Result;
import com.longwang.entity.ResouceLableContent;

import java.util.List;

/**
 * 文章与标签关联Service接口
 * @author longwang
 * @create 2018-10-25 11:09
 */
public interface IResouceLableContentService {

    /**
     * 添加或修改关联
     * @param resouceLableContent  关联实体
     * @return
     */
    Result<String> save(ResouceLableContent resouceLableContent);

    /**
     * 删除关联
     * @param contentId  文章Id
     * @return
     */
    Result<String> deleteByContentId(Long contentId);

    /**
     * 根据文章Id查询关联
     * @param contentId  文章Id
     * @return
     */
    Result<List<ResouceLableContent>> findAllByContentId(Long contentId);

}
