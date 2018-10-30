package com.longwang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.dao.AdConfigMapper;
import com.longwang.entity.AdConfig;
import com.longwang.service.IAdConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 广告Service接口实现类
 * @author longwang
 * @create 2018-10-25 11:10
 */
@Service
public class AdConfigServiceImpl implements IAdConfigService {

    @Autowired
    private AdConfigMapper adConfigMapper;

    @Override
    public Result<PageInfo<AdConfig>> findAll(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<AdConfig> pageInfo = new PageInfo<>(adConfigMapper.findAll(name));
        return Result.createBySuccess(pageInfo);
    }

    @Override
    public Result<AdConfig> findById(Long id) {
        return Result.createBySuccess(adConfigMapper.selectByPrimaryKey(id));
    }

    @Override
    public Result<String> save(AdConfig adConfig) {
        if(adConfig == null)
            return Result.createByError("不好意思，出了点小问题，稍后重试！");

        int rowResult = 0;
        if(adConfig.getId() == null)
            rowResult = adConfigMapper.insertSelective(adConfig);
        else
            rowResult = adConfigMapper.updateByPrimaryKeySelective(adConfig);

        if(rowResult > 0)
            return Result.createBySuccess("操作成功！");

        return Result.createByError("操作失败！");
    }

    @Override
    public Result<String> delete(Long id) {
        int rowResult = adConfigMapper.deleteByPrimaryKey(id);
        if(rowResult > 0)
            return Result.createBySuccess("删除成功！");
        return Result.createByError("删除失败！");
    }

}
