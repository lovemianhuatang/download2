package com.longwang.controller.admin;

import com.longwang.common.ResponseCode;
import com.longwang.common.Result;
import com.longwang.entity.AdConfig;
import com.longwang.service.IAdConfigService;
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
 * 管理员 -- 广告控制器
 * @author longwang
 * @create 2018-10-25 17:52
 */
@Controller
@RequestMapping("/admin/adConfig")
public class AdConfigAdminController {

    @Autowired
    private IAdConfigService iAdConfigService;

    /**
     * 内容页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "admin/adConfig/index";
    }

    /**
     * 分页带条件查询
     * @param name  广告名称
     * @param pageNum  当前页
     * @param pageSize  每页记录数
     * @return
     */
    @RequestMapping("/list")
    public String list(String name, Integer pageNum, Integer pageSize, ModelMap modelMap){
        modelMap.put("data", iAdConfigService.findAll(name,pageNum,pageSize));
        return "admin/adConfig/list";
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
            modelMap.put("data", iAdConfigService.findById(id));
        else
            modelMap.put("data", Result.createBySuccess(new AdConfig()));
        return "admin/adConfig/save";
    }

    /**
     * 添加或修改
     * @param adConfig  实体
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result save(AdConfig adConfig){
        if(adConfig.getId() == null) {
            adConfig.setAddTime(new Date());
            if (StringUtil.isEmpty(adConfig.getAdImg()))
                adConfig.setAdImg("/upload/jzytp.jpg");
        }
        return iAdConfigService.save(adConfig);
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
            iAdConfigService.delete(Long.valueOf(idsStr[i]));

        resultMap.put("status", ResponseCode.SUCCESS.getCode());
        resultMap.put("msg", "删除成功！");
        return resultMap;
    }

}
