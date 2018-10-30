package com.longwang.service.impl;

import com.longwang.common.Result;
import com.longwang.dao.SiteConfigMapper;
import com.longwang.entity.SiteConfig;
import com.longwang.service.ISiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 站点配置Service接口实现类
 * @author longwang
 * @create 2018-10-25 11:10
 */
@Service
public class SiteConfigServiceImpl implements ISiteConfigService {

    @Autowired
    private SiteConfigMapper siteConfigMapper;

    @Override
    public Result<String> save(SiteConfig siteConfig) {
        if(siteConfig.getId() == null)
            siteConfigMapper.insertSelective(siteConfig);
        else
            siteConfigMapper.updateByPrimaryKeySelective(siteConfig);
        return Result.createBySuccess("操作成功！");
    }

    @Override
    public Result<SiteConfig> findById(Long id) {
        return Result.createBySuccess(siteConfigMapper.selectByPrimaryKey(id));
    }
}
