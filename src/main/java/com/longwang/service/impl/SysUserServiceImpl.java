package com.longwang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.dao.SysUserMapper;
import com.longwang.entity.SysUser;
import com.longwang.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员Service接口实现类
 * @author longwang
 * @create 2018-10-25 11:10
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Result<SysUser> login(String username) {
        SysUser sysUser = sysUserMapper.login(username);
        if(sysUser == null)
            return  Result.createByError("此用户不存在！");
        return Result.createBySuccess("登录成功！", sysUser);
    }

    @Override
    public Result<PageInfo<SysUser>> findAll(String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUserMapper.findAll(username));
        return Result.createBySuccess(pageInfo);
    }

    @Override
    public Result<SysUser> findById(Long id) {
        return Result.createBySuccess(sysUserMapper.selectByPrimaryKey(id));
    }

    @Override
    public Result<String> save(SysUser sysUser) {
        if(sysUser == null)
            return Result.createByError("不好意思，出了点小问题，稍后重试！");

        int rowResult = 0;
        if(sysUser.getId() == null)
            rowResult = sysUserMapper.insertSelective(sysUser);
        else
            rowResult = sysUserMapper.updateByPrimaryKeySelective(sysUser);

        if(rowResult > 0)
            return Result.createBySuccess("操作成功！");

        return Result.createByError("操作失败！");
    }

    @Override
    public Result<String> delete(Long id) {
        int rowResult = sysUserMapper.deleteByPrimaryKey(id);
        if(rowResult > 0)
            return Result.createBySuccess("删除成功！");
        return Result.createByError("删除失败！");
    }

    @Override
    public Result checkUsername(String username) {
        int rowResult = sysUserMapper.checkUsername(username);
        if(rowResult > 0)
            return Result.createByError("当前用户已经存在，请重新输入！");
        return Result.createBySuccess("当前可以正常使用！");
    }

}
