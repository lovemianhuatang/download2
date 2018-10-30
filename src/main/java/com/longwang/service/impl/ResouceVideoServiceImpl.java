package com.longwang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.dao.ResouceVideoMapper;
import com.longwang.entity.ResouceVideo;
import com.longwang.service.IResouceVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 视频Service接口实现类
 * @author longwang
 * @create 2018-10-25 11:10
 */
@Service
public class ResouceVideoServiceImpl implements IResouceVideoService {

    @Autowired
    private ResouceVideoMapper resouceVideoMapper;

    @Override
    public Result<PageInfo<ResouceVideo>> findAll(String title, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<ResouceVideo> pageInfo = new PageInfo<>(resouceVideoMapper.findAll(title));
        return Result.createBySuccess(pageInfo);
    }

    @Override
    public Result<ResouceVideo> findById(Long id) {
        return Result.createBySuccess(resouceVideoMapper.selectByPrimaryKey(id));
    }

    @Override
    public Result<String> save(ResouceVideo resouceVideo) {
        if(resouceVideo == null)
            return Result.createByError("不好意思，出了点小问题，稍后重试！");

        int rowResult = 0;
        if(resouceVideo.getId() == null)
            rowResult = resouceVideoMapper.insertSelective(resouceVideo);
        else
            rowResult = resouceVideoMapper.updateByPrimaryKeySelective(resouceVideo);

        if(rowResult > 0)
            return Result.createBySuccess("操作成功！");

        return Result.createByError("操作失败！");
    }

    @Override
    public Result<String> deleteByContentId(Long contentId) {
        resouceVideoMapper.deleteByContentId(contentId);
        return Result.createBySuccess("删除成功！");
    }

    @Override
    public Result<List<ResouceVideo>> findAllByContentId(Long contentId) {
        return Result.createBySuccess(resouceVideoMapper.findAllByContentId(contentId));
    }

}
