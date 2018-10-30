package com.longwang.dao;

import com.longwang.common.Result;
import com.longwang.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员Dao接口
 */
public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKeyWithBLOBs(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 验证用户名
     * @param username  用户名
     * @return
     */
    int checkUsername(String username);

    /**
     * 登录验证
     * @param username  用户名
     * @return
     */
    SysUser login(@Param("username") String username);

    /**
     * 查询所有带条件
     * @param username  用户名
     * @return
     */
    List<SysUser> findAll(@Param(value = "username") String username);

}