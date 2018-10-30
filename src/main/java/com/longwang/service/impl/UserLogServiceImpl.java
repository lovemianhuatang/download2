package com.longwang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.dao.UserLogMapper;
import com.longwang.entity.UserLog;
import com.longwang.service.IUserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录日志Service接口实现类
 * @author longwang
 * @create 2018-10-25 11:10
 */
@Service
public class UserLogServiceImpl implements IUserLogService {

    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public Result<PageInfo<UserLog>> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<UserLog> pageInfo = new PageInfo<>(userLogMapper.findAll());
        return Result.createBySuccess(pageInfo);
    }

    @Override
    public Result<String> add(UserLog userLog) {
        int rowResult = userLogMapper.insertSelective(userLog);
        if(rowResult > 0)
            return Result.createBySuccess("添加成功！");
        return Result.createByError("添加失败！");
    }
}
