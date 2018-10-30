package com.longwang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.dao.ResouceDownloadFileMapper;
import com.longwang.entity.ResouceDownloadFile;
import com.longwang.service.IResouceDownloadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 软件Service接口实现类
 * @author longwang
 * @create 2018-10-25 11:10
 */
@Service
public class ResouceDownloadFileServiceImpl implements IResouceDownloadFileService {

    @Autowired
    private ResouceDownloadFileMapper resouceDownloadFileMapper;

    @Override
    public Result<PageInfo<ResouceDownloadFile>> findAll(String title, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<ResouceDownloadFile> pageInfo = new PageInfo<>(resouceDownloadFileMapper.findAll(title));
        return Result.createBySuccess(pageInfo);
    }

    @Override
    public Result<ResouceDownloadFile> findById(Long id) {
        return Result.createBySuccess(resouceDownloadFileMapper.selectByPrimaryKey(id));
    }

    @Override
    public Result<String> save(ResouceDownloadFile resouceDownloadFile) {
        if(resouceDownloadFile == null)
            return Result.createByError("不好意思，出了点小问题，稍后重试！");

        int rowResult = 0;
        if(resouceDownloadFile.getId() == null)
            rowResult = resouceDownloadFileMapper.insertSelective(resouceDownloadFile);
        else
            rowResult = resouceDownloadFileMapper.updateByPrimaryKeySelective(resouceDownloadFile);

        if(rowResult > 0)
            return Result.createBySuccess("操作成功！");

        return Result.createByError("操作失败！");
    }

    @Override
    public Result<String> delete(Long id) {
        int rowResult = resouceDownloadFileMapper.deleteByPrimaryKey(id);
        if(rowResult > 0)
            return Result.createBySuccess("删除成功！");
        return Result.createByError("删除失败！");
    }

    @Override
    public Result<ResouceDownloadFile> findAllByContentId(Long contentId) {
        return Result.createBySuccess(resouceDownloadFileMapper.findAllByContentId(contentId));
    }

    @Override
    public Result<String> deleteByContentId(Long contentId) {
        resouceDownloadFileMapper.deleteByContentId(contentId);
        return Result.createBySuccess("删除成功！");
    }

}
