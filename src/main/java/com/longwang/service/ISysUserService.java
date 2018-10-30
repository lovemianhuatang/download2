package com.longwang.service;

import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.entity.SysUser;

/**
 * 管理员Service接口
 * @author longwang
 * @create 2018-10-25 11:09
 */
public interface ISysUserService {

    /**
     * 管理员登录
     * @param username  用户名
     * @return
     */
    Result<SysUser> login(String username);

    /**
     * 查询所有带条件
     * @param username  用户名
     * @param pageNum  当前页
     * @param pageSize  //每页记录数
     * @return
     */
    Result<PageInfo<SysUser>> findAll(String username, Integer pageNum, Integer pageSize);

    /**
     * 根据Id获取实体
     * @param id  主键
     * @return
     */
    Result<SysUser> findById(Long id);

    /**
     * 添加或修改用户
     * @param sysUser  用户实体
     * @return
     */
    Result<String> save(SysUser sysUser);

    /**
     * 删除用户
     * @param id  主键
     * @return
     */
    Result<String> delete(Long id);

    /**
     * 验证用户名是否存在
     * @param username  用户名
     * @return
     */
    Result checkUsername(String username);

}
