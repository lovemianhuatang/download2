package com.longwang.controller.admin;

import com.longwang.common.ResponseCode;
import com.longwang.common.Result;
import com.longwang.entity.SysUser;
import com.longwang.service.ISysUserService;
import com.longwang.util.CryptographyUtil;
import com.longwang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员 -- 用户控制器
 * @author longwang
 * @create 2018-10-25 17:52
 */
@Controller
@RequestMapping("/admin/sysUser")
public class SysUserAdminController {

    @Autowired
    private ISysUserService iSysUserService;

    /**
     * 内容页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "admin/sysUser/index";
    }

    /**
     * 分页带条件查询
     * @param username  用户名
     * @param pageNum  当前页
     * @param pageSize  每页记录数
     * @return
     */
    @RequestMapping("/list")
    public String list(String username, Integer pageNum, Integer pageSize, ModelMap modelMap){
        modelMap.put("data", iSysUserService.findAll(username,pageNum,pageSize));
        return "admin/sysUser/list";
    }

    /**
     * 新增或修改页面
     * @param id  主键
     * @param modelMap
     * @return
     */
    @RequestMapping("/toSave")
    public String toSave(@RequestParam(name = "id",required = false)Long id, ModelMap modelMap){
        if(id != null)
            modelMap.put("data", iSysUserService.findById(id));
        else
            modelMap.put("data", Result.createBySuccess(new SysUser()));
        return "admin/sysUser/save";
    }

    /**
     * 添加或修改
     * @param sysUser  实体
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result save(SysUser sysUser){
        if(sysUser.getId() == null){
            sysUser.setAddTime(new Date());
            if(StringUtil.isEmpty(sysUser.getHeaderImg()))
                sysUser.setHeaderImg("/upload/jzytp.jpg");
        }
        sysUser.setPassword(CryptographyUtil.md5(sysUser.getPassword(),CryptographyUtil.SALT));
        return iSysUserService.save(sysUser);
    }

    /**
     * 删除（批量）
     * @param id  主键（多个以,号隔开）
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String id){
        Map<String, Object> resultMap=new HashMap<>();
        String []idsStr=id.split(",");
        for(int i=0;i<idsStr.length;i++)
            iSysUserService.delete(Long.valueOf(idsStr[i]));

        resultMap.put("status", ResponseCode.SUCCESS.getCode());
        resultMap.put("msg", "删除成功！");
        return resultMap;
    }

    /**
     * 用户名表单验证（是否存在）
     * @param param  用户名
     * @return
     */
    @RequestMapping("/valid")
    @ResponseBody
    public Map<String, Object> valid(String param) {
        Map<String, Object> resultMap = new HashMap<>();
        Result<String> result = iSysUserService.checkUsername(param);
        if (result.isSuccess()) {
            resultMap.put("info", result.getMsg());
            resultMap.put("status", "y");
        } else {
            resultMap.put("info", result.getMsg());
            resultMap.put("status", "n");
        }
        return resultMap;
    }

}
