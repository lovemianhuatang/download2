package com.longwang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.dao.MessageMapper;
import com.longwang.entity.Message;
import com.longwang.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 留言Service接口实现类
 * @author longwang
 * @create 2018-10-25 11:10
 */
@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Result<PageInfo<Message>> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Message> pageInfo = new PageInfo<>(messageMapper.findAll());
        return Result.createBySuccess(pageInfo);
    }

    @Override
    public Result<Message> findById(Long id) {
        return Result.createBySuccess(messageMapper.selectByPrimaryKey(id));
    }

    @Override
    public Result<String> delete(Long id) {
        int rowResult = messageMapper.deleteByPrimaryKey(id);
        if(rowResult > 0)
            return Result.createBySuccess("删除成功！");
        return Result.createByError("删除失败！");
    }

}
