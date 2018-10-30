package com.longwang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.dao.LinkConfigMapper;
import com.longwang.entity.LinkConfig;
import com.longwang.service.ILinkConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 友情链接Service接口实现类
 * @author longwang
 * @create 2018-10-25 11:10
 */
@Service
public class LinkConfigServiceImpl implements ILinkConfigService {

    @Autowired
    private LinkConfigMapper linkConfigMapper;

    @Override
    public Result<PageInfo<LinkConfig>> findAll(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<LinkConfig> pageInfo = new PageInfo<>(linkConfigMapper.findAll(name));
        return Result.createBySuccess(pageInfo);
    }

    @Override
    public Result<LinkConfig> findById(Long id) {
        return Result.createBySuccess(linkConfigMapper.selectByPrimaryKey(id));
    }

    @Override
    public Result<String> save(LinkConfig linkConfig) {
        if(linkConfig == null)
            return Result.createByError("不好意思，出了点小问题，稍后重试！");

        int rowResult = 0;
        if(linkConfig.getId() == null)
            rowResult = linkConfigMapper.insertSelective(linkConfig);
        else
            rowResult = linkConfigMapper.updateByPrimaryKeySelective(linkConfig);

        if(rowResult > 0)
            return Result.createBySuccess("操作成功！");

        return Result.createByError("操作失败！");
    }

    @Override
    public Result<String> delete(Long id) {
        int rowResult = linkConfigMapper.deleteByPrimaryKey(id);
        if(rowResult > 0)
            return Result.createBySuccess("删除成功！");
        return Result.createByError("删除失败！");
    }

}
