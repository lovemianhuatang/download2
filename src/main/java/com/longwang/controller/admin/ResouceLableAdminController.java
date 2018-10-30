package com.longwang.controller.admin;

import com.longwang.common.ResponseCode;
import com.longwang.common.Result;
import com.longwang.entity.ResouceLable;
import com.longwang.service.IResouceLableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员 -- 标签控制器
 * @author longwang
 * @create 2018-10-25 17:52
 */
@Controller
@RequestMapping("/admin/resouceLable")
public class ResouceLableAdminController {

    @Autowired
    private IResouceLableService iResouceLableService;

    /**
     * 内容页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "admin/resouceLable/index";
    }

    /**
     * 分页带条件查询
     * @param resouceLable  标签实体
     * @param pageNum  当前页
     * @param pageSize  每页记录数
     * @return
     */
    @RequestMapping("/list")
    public String list(ResouceLable resouceLable, Integer pageNum, Integer pageSize, ModelMap modelMap){
        modelMap.put("data", iResouceLableService.findAll(resouceLable,pageNum,pageSize));
        return "admin/resouceLable/list";
    }

    /**
     * 新增或修改页面
     * @param id  主键
     * @param modelMap
     * @return
     */
    @RequestMapping("/toSave")
    public String toSave(@RequestParam(name = "id",required = false)Long id,Integer siteType, ModelMap modelMap){
        modelMap.put("siteType", siteType);
        if(id != null)
            modelMap.put("data", iResouceLableService.findById(id));
        else
            modelMap.put("data", Result.createBySuccess(new ResouceLable()));
        return "admin/resouceLable/save";
    }

    /**
     * 添加或修改
     * @param resouceLable  实体
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result save(ResouceLable resouceLable){
        return iResouceLableService.save(resouceLable);
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
            iResouceLableService.delete(Long.valueOf(idsStr[i]));

        resultMap.put("status", ResponseCode.SUCCESS.getCode());
        resultMap.put("msg", "删除成功！");
        return resultMap;
    }

}
