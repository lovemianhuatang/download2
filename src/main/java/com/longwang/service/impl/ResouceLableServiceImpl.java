package com.longwang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.dao.ResouceLableMapper;
import com.longwang.entity.ResouceLable;
import com.longwang.service.IResouceLableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 标签Service接口实现类
 * @author longwang
 * @create 2018-10-25 11:10
 */
@Service
public class ResouceLableServiceImpl implements IResouceLableService {

    @Autowired
    private ResouceLableMapper resouceLableMapper;


    @Override
    public Result<PageInfo<ResouceLable>> findAll(ResouceLable resouceLable, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<ResouceLable> pageInfo = new PageInfo<>(resouceLableMapper.findAll(resouceLable));
        return Result.createBySuccess(pageInfo);
    }

    @Override
    public Result<ResouceLable> findById(Long id) {
        return Result.createBySuccess(resouceLableMapper.selectByPrimaryKey(id));
    }

    @Override
    public Result<String> save(ResouceLable resouceLable) {
        if(resouceLable == null)
            return Result.createByError("不好意思，出了点小问题，稍后重试！");

        int rowResult = 0;
        if(resouceLable.getId() == null)
            rowResult = resouceLableMapper.insertSelective(resouceLable);
        else
            rowResult = resouceLableMapper.updateByPrimaryKeySelective(resouceLable);

        if(rowResult > 0)
            return Result.createBySuccess("操作成功！");

        return Result.createByError("操作失败！");
    }

    @Override
    public Result<String> delete(Long id) {
        int rowResult = resouceLableMapper.deleteByPrimaryKey(id);
        if(rowResult > 0)
            return Result.createBySuccess("删除成功！");
        return Result.createByError("删除失败！");
    }

}
