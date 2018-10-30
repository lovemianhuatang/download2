package com.longwang.controller.admin;

import com.longwang.common.Constant;
import com.longwang.common.ResponseCode;
import com.longwang.common.Result;
import com.longwang.entity.*;
import com.longwang.service.*;
import com.longwang.util.DateUtil;
import com.longwang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员 -- 文章控制器
 * @author longwang
 * @create 2018-10-25 17:52
 */
@Controller
@RequestMapping("/admin/resouceContent")
public class ResouceContentAdminController {

    @Autowired
    private IResouceContentService iResouceContentService;

    @Autowired
    private IResouceCatalogService iResouceCatalogService;

    @Autowired
    private IResouceVideoService iResouceVideoService;

    @Autowired
    private IResouceDownloadFileService iResouceDownloadFileService;

    @Autowired
    private IResouceLableService iResouceLableService;

    @Autowired
    private IResouceLableContentService iResouceLableContentService;

    @Value("${uploadFilePath}")
    private String uploadFilePath;  // 图片上传路径


    /**
     * 内容页
     * @return
     */
    @RequestMapping("/index")
    public String index(Integer siteType, ModelMap modelMap){
        modelMap.put("siteType", siteType);
        if(siteType == Constant.SiteType.WEN_ZHANG)
            modelMap.put("navName", "文章");
        if(siteType == Constant.SiteType.SHI_PIN)
            modelMap.put("navName", "视频");
        if(siteType == Constant.SiteType.RUAN_JIAN)
            modelMap.put("navName", "软件");
        return "admin/resouceContent/index";
    }

    /**
     * 分页带条件查询
     * @param title  文章名
     * @param siteType  类别
     * @param pageNum  当前页
     * @param pageSize  每页记录数
     * @return
     */
    @RequestMapping("/list")
    public String list(String title,Integer siteType , Integer pageNum, Integer pageSize, ModelMap modelMap){
        modelMap.put("data", iResouceContentService.findAll(title,siteType,pageNum,pageSize));
        return "admin/resouceContent/list";
    }

    /**
     * 新增或修改页面
     * @param id  主键
     * @param modelMap
     * @return
     */
    @RequestMapping("/toSave")
    public String toSave(@RequestParam(name = "id",required = false)Long id, Integer siteType, ModelMap modelMap){
        modelMap.put("siteType", siteType);
        Long currentId = -1L;
        if(id != null) {
            Result<ResouceContent> result = iResouceContentService.findById(id);
            modelMap.put("data",  result);
            currentId = result.getData().getResouceCatalog().getId();

            if (siteType == Constant.SiteType.SHI_PIN)
                modelMap.put("videoData", iResouceVideoService.findAllByContentId(id));
            if(siteType == Constant.SiteType.RUAN_JIAN)
                modelMap.put("fileData", iResouceDownloadFileService.findAllByContentId(id));

            Result<List<ResouceLableContent>> allByContentId = iResouceLableContentService.findAllByContentId(id);
            modelMap.put("lableList",  allByContentId);
        }else{
            modelMap.put("data", Result.createBySuccess(new ResouceContent()));
        }

        Result<List<ResouceCatalog>> result = iResouceCatalogService.findAll(siteType);
        List<ResouceCatalog> data = result.getData();
        StringBuffer sb = new StringBuffer();
        for (ResouceCatalog datum : data) {
            int tempIndex = 1;
            sb.append("<option value='" + datum.getId() + "' ");
            if(datum.getId() == currentId)
                sb.append("selected");
            sb.append(">" + StringUtil.getNbsp(tempIndex) + "|-" + datum.getName() + "</option>");
            tempIndex += 3;
            selectChilder(datum.getId(), currentId, tempIndex,sb);
        }
        modelMap.put("selectHtml", sb.toString());

        return "admin/resouceContent/save";
    }

    /**
     * 添加或修改
     * @param resouceContent  实体
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result save(ResouceContent resouceContent, @RequestParam(name = "lableId", required = false)Long[] lableId,ResouceDownloadFile resouceDownloadFile, @RequestParam(name = "videoTitle", required = false)String[] videoTitle, @RequestParam(name = "videoUrl", required = false)String[] videoUrl, @RequestParam(name = "videoOrderSort", required = false)Integer[] videoOrderSort){
        if(resouceContent.getId() == null){
            resouceContent.setAddTime(new Date());
            if(StringUtil.isEmpty(resouceContent.getThumbnailImg()))
                resouceContent.setThumbnailImg("/public/images/nopic.png");
        }
        Result<ResouceContent> save = iResouceContentService.save(resouceContent);
        if(save.isSuccess()) {
            resouceContent = save.getData();

            //标签处理
            if (lableId != null && lableId.length > 0) {
                iResouceLableContentService.deleteByContentId(resouceContent.getId());
                ResouceLableContent resouceLableContent = new ResouceLableContent();
                resouceLableContent.setResouceContent(resouceContent);
                for (int i = 0; i < lableId.length; i++) {
                    resouceLableContent.setResouceLable(new ResouceLable(lableId[i]));
                    iResouceLableContentService.save(resouceLableContent);
                }
            }

            //软件处理
            Result<ResouceDownloadFile> allByContentId = iResouceDownloadFileService.findAllByContentId(resouceContent.getId());
            ResouceDownloadFile data = allByContentId.getData();
            if(data != null && resouceContent.getSiteType() == Constant.SiteType.RUAN_JIAN){
                data.setUrl(resouceDownloadFile.getUrl());
                data.setExt(resouceDownloadFile.getExt());
                data.setResouceContent(resouceContent);
                data.setDownloadCount(resouceDownloadFile.getDownloadCount());
                data.setFileName(resouceDownloadFile.getFileName());
                data.setFileSize(resouceDownloadFile.getFileSize());
                iResouceDownloadFileService.save(data);
            }else {
                if(resouceContent.getSiteType() == Constant.SiteType.RUAN_JIAN && StringUtil.isNotEmpty(resouceDownloadFile.getFileName())){
                    resouceDownloadFile.setResouceContent(resouceContent);
                    iResouceDownloadFileService.save(resouceDownloadFile);
                }
            }

            //视频处理
            iResouceVideoService.deleteByContentId(resouceContent.getId());
            if(resouceContent.getSiteType() == Constant.SiteType.SHI_PIN && videoTitle != null && videoTitle.length > 0){
                ResouceVideo resouceVideo = new ResouceVideo();
                resouceVideo.setResouceContent(resouceContent);
                for (int i = 0; i < videoTitle.length; i++) {
                    resouceVideo.setTitle(videoTitle[i]);
                    resouceVideo.setUrl(videoUrl[i]);
                    resouceVideo.setOrderSort(videoOrderSort[i]);
                    iResouceVideoService.save(resouceVideo);
                }
            }
        }else{
            return Result.createByError("操作失败！");
        }
        return Result.createBySuccess("操作成功！");
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
        for(int i=0;i<idsStr.length;i++){
            Result<ResouceContent> byId = iResouceContentService.findById(Long.valueOf(idsStr[i]));
            ResouceContent data = byId.getData();
            if(data.getSiteType() == Constant.SiteType.SHI_PIN)
                iResouceVideoService.deleteByContentId(Long.valueOf(idsStr[i]));
            if(data.getSiteType() == Constant.SiteType.RUAN_JIAN)
                iResouceDownloadFileService.deleteByContentId(Long.valueOf(idsStr[i]));

            iResouceContentService.delete(Long.valueOf(idsStr[i]));
        }

        resultMap.put("status", ResponseCode.SUCCESS.getCode());
        resultMap.put("msg", "删除成功！");
        return resultMap;
    }

    @RequestMapping("/openResouceLable")
    public String openResouceLable(Integer siteType, ModelMap modelMap){
        modelMap.put("siteType", siteType);
        return "admin/resouceContent/selectedLable";
    }

    /**
     * 分页带条件查询
     * @param resouceLable  标签实体
     * @param pageNum  当前页
     * @param pageSize  每页记录数
     * @return
     */
    @RequestMapping("/resouceLable/list")
    public String list(ResouceLable resouceLable, @RequestParam(name = "pageNum", required = false, defaultValue = "1")Integer pageNum, @RequestParam(name = "pageSize", required = false, defaultValue = "10")Integer pageSize, ModelMap modelMap){
        modelMap.put("data", iResouceLableService.findAll(resouceLable,pageNum,pageSize));
        return "admin/resouceContent/listLable";
    }

    /**
     * 软件上传
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String,Object> upload(@RequestParam(name = "file",required = false) MultipartFile file) throws IOException {
        Map<String,Object> resultMap=new HashMap<>();
        if(!file.isEmpty()){
            String mkdirName = DateUtil.getCurrentDateStr();
            File mkdirFile = new File(uploadFilePath + "download/" + mkdirName);
            if(!(mkdirFile.exists()))
                mkdirFile.mkdirs();

            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //新文件名
            String newFileName= DateUtil.getCurrentDateTimeStr()+suffixName;
            file.transferTo(new File(uploadFilePath + "download/" + mkdirName + "/" + newFileName));
            resultMap.put("url", "/upload/download/" + mkdirName + "/" + newFileName);
            resultMap.put("size",  file.getSize());
            resultMap.put("name",  fileName);
            resultMap.put("ext",  suffixName);
        }
        return resultMap;
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
