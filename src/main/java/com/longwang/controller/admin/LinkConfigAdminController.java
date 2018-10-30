package com.longwang.controller.admin;

import com.longwang.common.ResponseCode;
import com.longwang.common.Result;
import com.longwang.entity.LinkConfig;
import com.longwang.service.ILinkConfigService;
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
 * 管理员 -- 友情链接控制器
 * @author longwang
 * @create 2018-10-25 17:52
 */
@Controller
@RequestMapping("/admin/linkConfig")
public class LinkConfigAdminController {

    @Autowired
    private ILinkConfigService iLinkConfigService;

    /**
     * 内容页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "admin/linkConfig/index";
    }

    /**
     * 分页带条件查询
     * @param name  友情链接名称
     * @param pageNum  当前页
     * @param pageSize  每页记录数
     * @return
     */
    @RequestMapping("/list")
    public String list(String name, Integer pageNum, Integer pageSize, ModelMap modelMap){
        modelMap.put("data", iLinkConfigService.findAll(name,pageNum,pageSize));
        return "admin/linkConfig/list";
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
            modelMap.put("data", iLinkConfigService.findById(id));
        else
            modelMap.put("data", Result.createBySuccess(new LinkConfig()));
        return "admin/linkConfig/save";
    }

    /**
     * 添加或修改
     * @param linkConfig  实体
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result save(LinkConfig linkConfig){
        if(linkConfig.getId() == null)
            linkConfig.setAddTime(new Date());

        return iLinkConfigService.save(linkConfig);
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
            iLinkConfigService.delete(Long.valueOf(idsStr[i]));

        resultMap.put("status", ResponseCode.SUCCESS.getCode());
        resultMap.put("msg", "删除成功！");
        return resultMap;
    }

}
