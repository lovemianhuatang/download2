package com.longwang.service.impl;

import com.longwang.common.Result;
import com.longwang.dao.ResouceLableContentMapper;
import com.longwang.entity.ResouceLableContent;
import com.longwang.service.IResouceLableContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签与文章关联Service接口实现类
 * @author longwang
 * @create 2018-10-25 11:10
 */
@Service
public class ResouceLableContentServiceImpl implements IResouceLableContentService {

    @Autowired
    private ResouceLableContentMapper resouceLableContentMapper;

    @Override
    public Result<String> save(ResouceLableContent resouceLableContent) {
        if(resouceLableContent == null)
            return Result.createByError("不好意思，出了点小问题，稍后重试！");

        int rowResult = 0;
        if(resouceLableContent.getId() == null)
            rowResult = resouceLableContentMapper.insertSelective(resouceLableContent);
        else
            rowResult = resouceLableContentMapper.updateByPrimaryKeySelective(resouceLableContent);

        if(rowResult > 0)
            return Result.createBySuccess("操作成功！");

        return Result.createByError("操作失败！");
    }

    @Override
    public Result<String> deleteByContentId(Long contentId) {
        resouceLableContentMapper.deleteByContentId(contentId);
        return Result.createBySuccess("删除成功！");
    }

    @Override
    public Result<List<ResouceLableContent>> findAllByContentId(Long contentId) {
        return Result.createBySuccess(resouceLableContentMapper.findAllByContentId(contentId));
    }

}
