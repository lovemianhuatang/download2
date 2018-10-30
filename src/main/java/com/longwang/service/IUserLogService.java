package com.longwang.service;

import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.entity.UserLog;

/**
 * 登录日志Service接口
 * @author longwang
 * @create 2018-10-25 11:09
 */
public interface IUserLogService {

    /**
     * 分页查询所有
     * @param pageNum  当前页
     * @param pageSize  //每页记录数
     * @return
     */
    Result<PageInfo<UserLog>> findAll(Integer pageNum, Integer pageSize);

    /**
     * 添加登录日志
     * @param userLog  登录日志实体
     * @return
     */
    Result<String> add(UserLog userLog);

}
