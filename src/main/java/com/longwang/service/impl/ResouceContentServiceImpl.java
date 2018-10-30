package com.longwang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.dao.ResouceContentMapper;
import com.longwang.entity.ResouceContent;
import com.longwang.service.IResouceContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章Service接口实现类
 * @author longwang
 * @create 2018-10-25 11:10
 */
@Service
public class ResouceContentServiceImpl implements IResouceContentService {

    @Autowired
    private ResouceContentMapper resouceContentMapper;

    @Override
    public Result<PageInfo<ResouceContent>> findAll(String title, Integer siteType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<ResouceContent> pageInfo = new PageInfo<>(resouceContentMapper.findAll(title,siteType));
        return Result.createBySuccess(pageInfo);
    }

    @Override
    public Result<ResouceContent> findById(Long id) {
        return Result.createBySuccess(resouceContentMapper.selectByPrimaryKey(id));
    }

    @Override
    public Result<ResouceContent> save(ResouceContent resouceContent) {
        if(resouceContent == null)
            return Result.createByError("不好意思，出了点小问题，稍后重试！");

        int rowResult = 0;
        if(resouceContent.getId() == null)
            rowResult = resouceContentMapper.insertSelective(resouceContent);
        else
            rowResult = resouceContentMapper.updateByPrimaryKeySelective(resouceContent);

        if(rowResult > 0)
            return Result.createBySuccess("操作成功！",resouceContent);

        return Result.createByError("操作失败！");
    }

    @Override
    public Result<String> delete(Long id) {
        int rowResult = resouceContentMapper.deleteByPrimaryKey(id);
        if(rowResult > 0)
            return Result.createBySuccess("删除成功！");
        return Result.createByError("删除失败！");
    }

    @Override
    public Result<List<ResouceContent>> findAllByCatalogId(Long catalogId) {
        return Result.createBySuccess(resouceContentMapper.findAllByCatalogId(catalogId));
    }

}
