package com.longwang.controller.admin;

import com.longwang.common.ResponseCode;
import com.longwang.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员 -- 留言控制器
 * @author longwang
 * @create 2018-10-25 17:52
 */
@Controller
@RequestMapping("/admin/message")
public class MessageAdminController {

    @Autowired
    private IMessageService iMessageService;

    /**
     * 内容页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "admin/message/index";
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
        modelMap.put("data", iMessageService.findAll(pageNum,pageSize));
        return "admin/message/list";
    }

    /**
     * 查看详情页面
     * @param id  主键
     * @param modelMap
     * @return
     */
    @RequestMapping("/details")
    public String details(Long id, ModelMap modelMap){
        modelMap.put("data", iMessageService.findById(id));
        return "admin/message/details";
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
            iMessageService.delete(Long.valueOf(idsStr[i]));

        resultMap.put("status", ResponseCode.SUCCESS.getCode());
        resultMap.put("msg", "删除成功！");
        return resultMap;
    }



}
