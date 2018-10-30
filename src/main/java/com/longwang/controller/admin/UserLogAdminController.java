package com.longwang.controller.admin;

import com.longwang.service.IUserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员 -- 登录日志控制器
 * @author longwang
 * @create 2018-10-25 17:52
 */
@Controller
@RequestMapping("/admin/userLog")
public class UserLogAdminController {

    @Autowired
    private IUserLogService iUserLogService;

    /**
     * 内容页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "admin/userLog/index";
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
        modelMap.put("data", iUserLogService.findAll(pageNum,pageSize));
        return "admin/userLog/list";
    }

}
