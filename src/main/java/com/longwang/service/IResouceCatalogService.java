package com.longwang.service;

import com.longwang.common.Result;
import com.longwang.entity.ResouceCatalog;

import java.util.List;

/**
 * 资源分类Service接口
 * @author longwang
 * @create 2018-10-25 11:09
 */
public interface IResouceCatalogService {

    /**
     * 查询所有带条件
     * @param siteType  资源类型
     * @return
     */
    Result<List<ResouceCatalog>> findAll(Integer siteType);

    /**
     * 根据Id获取实体
     * @param id  主键
     * @return
     */
    Result<ResouceCatalog> findById(Long id);

    /**
     * 添加或修改资源分类
     * @param resouceCatalog  资源分类实体
     * @return
     */
    Result<ResouceCatalog> save(ResouceCatalog resouceCatalog);

    /**
     * 删除资源分类
     * @param id  主键
     * @return
     */
    Result<String> delete(Long id);

    /**
     * 根据Id查询一级分类下的分类
     * @param classPid  父Id
     * @return
     */
    Result<List<ResouceCatalog>> findAllByClassPid(Integer classPid);

    /**
     * 根据父Id查询分类
     * @param pid  父Id
     * @return
     */
    Result<List<ResouceCatalog>> findAllByPid(Long pid);

}
