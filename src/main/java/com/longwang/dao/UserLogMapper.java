package com.longwang.dao;

import com.longwang.entity.UserLog;

import java.util.List;

/**
 * 登录日志Dao接口
 */
public interface UserLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLog record);

    int insertSelective(UserLog record);

    UserLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLog record);

    int updateByPrimaryKeyWithBLOBs(UserLog record);

    int updateByPrimaryKey(UserLog record);

    /**
     * 分页查询所有
     * @return
     */
    List<UserLog> findAll();
}