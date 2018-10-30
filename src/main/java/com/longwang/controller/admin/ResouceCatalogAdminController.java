package com.longwang.controller.admin;

import com.longwang.common.Result;
import com.longwang.entity.ResouceCatalog;
import com.longwang.service.IResouceCatalogService;
import com.longwang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 管理员 -- 资源分类控制器
 * @author longwang
 * @create 2018-10-25 17:52
 */
@Controller
@RequestMapping("/admin/resouceCatalog")
public class ResouceCatalogAdminController {

    @Autowired
    private IResouceCatalogService iResouceCatalogService;

    /**
     * 内容页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "admin/resouceCatalog/index";
    }

    /**
     * 带条件查询
     * @param siteType  资源类型
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(Integer siteType){
        Map<String, Object> resultMap = new HashMap<>();
        Set<Map<String, Object>> set = new HashSet();
        Result<List<ResouceCatalog>> result = iResouceCatalogService.findAll(siteType);
        List<ResouceCatalog> list = result.getData();
        for (ResouceCatalog catalog : list) {
            Result<List<ResouceCatalog>> allByClassPid = iResouceCatalogService.findAllByClassPid(catalog.getId().intValue());
            List<ResouceCatalog> data = allByClassPid.getData();
            for (ResouceCatalog datum : data) {
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("site_type", datum.getSiteType());
                dataMap.put("remark", datum.getRemark());
                if(datum.getpResouceCatalog() == null) {
                    dataMap.put("pid", -1);
                    dataMap.put("pNmae", "无");
                } else{
                    dataMap.put("pid", datum.getpResouceCatalog().getId());
                    dataMap.put("pNmae", datum.getpResouceCatalog().getName());
                }
                dataMap.put("name", datum.getName());
                dataMap.put("id", datum.getId());
                dataMap.put("add_time", datum.getAddTime());
                set.add(dataMap);
            }
        }
        resultMap.put("msg", "ok");
        resultMap.put("code", 0);
        resultMap.put("count", set.size());
        resultMap.put("data", set);
        return resultMap;
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
        Long currentId=0L;
        if(id != null) {
            Result<ResouceCatalog> result = iResouceCatalogService.findById(id);
            if(result.getData().getpResouceCatalog() == null)
                currentId = -1L;
            else
                currentId = result.getData().getpResouceCatalog().getId();
            modelMap.put("data",  result);
            if(result.getData().getpResouceCatalog() != null)
                modelMap.put("oldPid", result.getData().getpResouceCatalog().getId());
            else
                modelMap.put("oldPid", -1);
        }else{
            modelMap.put("data", Result.createBySuccess(new ResouceCatalog()));
        }

        Result<List<ResouceCatalog>> result = iResouceCatalogService.findAll(siteType);
        List<ResouceCatalog> data = result.getData();
        StringBuffer sb = new StringBuffer();
        int index = 1;
        sb.append("<option value='-1'");
        if(-1 == currentId)
            sb.append("selected");
        sb.append(">" + StringUtil.getNbsp(0) + "|-顶级菜单</option>");

        for (ResouceCatalog datum : data) {
            int tempIndex = index + 3;
            sb.append("<option value='" + datum.getId() + "' ");
            if(datum.getId() == currentId)
                sb.append("selected");
            sb.append(">" + StringUtil.getNbsp(tempIndex) + "|-" + datum.getName() + "</option>");
            tempIndex += 3;
            selectChilder(datum.getId(), currentId, tempIndex,sb);
        }
        modelMap.put("selectHtml", sb.toString());
        return "admin/resouceCatalog/save";
    }

    /**
     * 添加或修改
     * @param resouceCatalog  实体
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result save(ResouceCatalog resouceCatalog,Long oldPid){
        if(resouceCatalog.getId() == null){
            resouceCatalog.setAddTime(new Date());
        }
        Result<ResouceCatalog> result = iResouceCatalogService.save(resouceCatalog);
        if (result.isSuccess()){
            ResouceCatalog data = result.getData();
            if(data.getpResouceCatalog().getId() == -1){
                data.setClassPid("," + data.getId() + ",");
            }else{
                Result<ResouceCatalog> byId = iResouceCatalogService.findById(data.getpResouceCatalog().getId());
                ResouceCatalog data1 = byId.getData();
                data.setClassPid(data1.getClassPid() + data.getId() + ",");
            }
            Result<ResouceCatalog> save = iResouceCatalogService.save(data);
            if (save.isSuccess()) {
                ResouceCatalog data2 = save.getData();
                if(oldPid != null && oldPid != data2.getpResouceCatalog().getId()){
                    Result<List<ResouceCatalog>> allByClassPid = iResouceCatalogService.findAllByClassPid(data2.getId().intValue());
                    List<ResouceCatalog> data1 = allByClassPid.getData();
                    updateClassPid(data1, data2);
                }
            }
            return Result.createBySuccess("操作成功！");
        }else {
            return Result.createByError("操作失败！");
        }
    }

    /**
     * 修改子类ClassPid
     * @param data
     * @param resouceCatalog
     */
    private void updateClassPid(List<ResouceCatalog> data,ResouceCatalog resouceCatalog){
        for (ResouceCatalog catalog : data) {
            if(resouceCatalog.getId() != catalog.getId()){
                if(catalog.getpResouceCatalog().getId() == resouceCatalog.getId()){
                    catalog.setClassPid(resouceCatalog.getClassPid() + catalog.getId() + ",");
                    iResouceCatalogService.save(catalog);
                    updateClassPid(data, catalog);
                }
            }
        }
    }


    /**
     * 删除
     * @param id  主键
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result<String> delete(Long id){
        return iResouceCatalogService.delete(id);
    }

    /**
     * 分类下拉框Html代码拼接
     * @param pid  父id
     * @param currentId  当前选中Id
     * @param index  空格数目
     * @param sb  拼接的字符
     * @return
     */
    private String selectChilder(Long pid, Long currentId, Integer index,StringBuffer sb) {
        Result<List<ResouceCatalog>> allByPid = iResouceCatalogService.findAllByPid(pid);
        List<ResouceCatalog> data1 = allByPid.getData();
        if(data1 != null && data1.size() > 0){
            index += 3;
            for (ResouceCatalog resouceCatalog : data1) {
                sb.append("<option value='" + resouceCatalog.getId() + "' ");
                if(resouceCatalog.getId() == currentId)
                    sb.append("selected");
                sb.append(">" + StringUtil.getNbsp(index) + "|-" + resouceCatalog.getName() + "</option>");
                selectChilder(resouceCatalog.getId(),currentId,index,sb);
            }
        }
        return sb.toString();
    }


}
