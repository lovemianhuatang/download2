package com.longwang.service.impl;

import com.longwang.common.Result;
import com.longwang.dao.ResouceCatalogMapper;
import com.longwang.dao.ResouceContentMapper;
import com.longwang.entity.ResouceCatalog;
import com.longwang.entity.ResouceContent;
import com.longwang.service.IResouceCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源分类Service接口实现类
 * @author longwang
 * @create 2018-10-25 11:10
 */
@Service
public class ResouceCatalogServiceImpl implements IResouceCatalogService {

    @Autowired
    private ResouceCatalogMapper resouceCatalogMapper;

    @Autowired
    private ResouceContentMapper resouceContentMapper;

    @Override
    public Result<List<ResouceCatalog>> findAll(Integer siteType) {
        return Result.createBySuccess(resouceCatalogMapper.findAll(siteType));
    }

    @Override
    public Result<ResouceCatalog> findById(Long id) {
        return Result.createBySuccess(resouceCatalogMapper.selectByPrimaryKey(id));
    }

    @Override
    public Result<ResouceCatalog> save(ResouceCatalog resouceCatalog) {
        if(resouceCatalog == null)
            return Result.createByError("不好意思，出了点小问题，稍后重试！");

        int rowResult = 0;
        if(resouceCatalog.getId() == null)
            rowResult = resouceCatalogMapper.insertSelective(resouceCatalog);
        else
            rowResult = resouceCatalogMapper.updateByPrimaryKeySelective(resouceCatalog);

        if(rowResult > 0)
            return Result.createBySuccess("操作成功！",resouceCatalog);

        return Result.createByError("操作失败！");
    }

    @Override
    public Result<String> delete(Long id) {
        List<ResouceCatalog> allByPid = resouceCatalogMapper.findAllByPid(id);
        if(allByPid != null && allByPid.size() > 0)
            return Result.createByError("该分类下有子分类，不能删除！");
        List<ResouceContent> allByCatalogId = resouceContentMapper.findAllByCatalogId(id);
        if(allByCatalogId != null && allByCatalogId.size() > 0)
            return Result.createByError("该分类下存在资源，不能删除！");

        int rowResult = resouceCatalogMapper.deleteByPrimaryKey(id);
        if(rowResult > 0)
            return Result.createBySuccess("删除成功！");
        return Result.createByError("删除失败！");
    }

    @Override
    public Result<List<ResouceCatalog>> findAllByClassPid(Integer classPid) {
        return Result.createBySuccess(resouceCatalogMapper.findAllByClassPid(classPid));
    }

    @Override
    public Result<List<ResouceCatalog>> findAllByPid(Long pid) {
        return Result.createBySuccess(resouceCatalogMapper.findAllByPid(pid));
    }

}
