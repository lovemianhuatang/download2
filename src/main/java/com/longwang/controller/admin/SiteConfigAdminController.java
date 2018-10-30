package com.longwang.controller.admin;

import com.longwang.common.Result;
import com.longwang.entity.SiteConfig;
import com.longwang.service.ISiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理员 -- 站点配置控制器
 * @author longwang
 * @create 2018-10-25 17:52
 */
@Controller
@RequestMapping("/admin/siteConfig")
public class SiteConfigAdminController {

    @Autowired
    private ISiteConfigService iSiteConfigService;

    /**
     * 内容页
     * @return
     */
    @RequestMapping("/index")
    public String index(ModelMap modelMap){
        modelMap.put("data", iSiteConfigService.findById(1L));
        return "admin/siteConfig/index";
    }

    /**
     * 内容页
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result<String> save(SiteConfig siteConfig){
        return iSiteConfigService.save(siteConfig);
    }

}
