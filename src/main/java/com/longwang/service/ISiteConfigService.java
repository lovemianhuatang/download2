package com.longwang.service;

import com.longwang.common.Result;
import com.longwang.entity.SiteConfig;

/**
 * 站点配置Service接口
 * @author longwang
 * @create 2018-10-25 11:09
 */
public interface ISiteConfigService {

    /**
     * 添加或修改站点配置
     * @param siteConfig  站点配置实体
     * @return
     */
    Result<String> save(SiteConfig siteConfig);

    /**
     * 根据Id获取实体
     * @param id  主键
     * @return
     */
    Result<SiteConfig> findById(Long id);

}
