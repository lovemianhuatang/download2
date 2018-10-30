package com.longwang.controller.admin;

import com.longwang.common.Constant;
import com.longwang.common.Result;
import com.longwang.entity.SysUser;
import com.longwang.entity.UserLog;
import com.longwang.service.ISysUserService;
import com.longwang.service.IUserLogService;
import com.longwang.util.CryptographyUtil;
import com.longwang.util.DateUtil;
import com.longwang.util.IPUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员 -- 后台登录及其他请求处理
 * @author longwang
 * @create 2018-10-25 11:41
 */
@Controller
@RequestMapping("/admin")
public class IndexAdminController {

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private IUserLogService iUserLogService;

    @Value("${uploadFilePath}")
    private String uploadFilePath;  // 图片上传路径

    /**
     * 管理员登录
     * @param username  用户名
     * @param password  密码
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result<SysUser> login(String username, String password, HttpServletRequest request){

        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username, CryptographyUtil.md5(password, CryptographyUtil.SALT));
        try{
            subject.login(token); // 登录验证
            String userName=(String) SecurityUtils.getSubject().getPrincipal();
            Result<SysUser> result = iSysUserService.login(userName);
            SysUser currentUser = result.getData();
            currentUser.setLastLoginTime(new Date());
            iSysUserService.save(currentUser);
            UserLog userLog = new UserLog();
            userLog.setSysUser(currentUser);
            userLog.setAddTime(currentUser.getLastLoginTime());
            userLog.setIp(IPUtil.getIpAddr(request));
            userLog.setAddress(IPUtil.getAdd(request));
            userLog.setContent(currentUser.getUsername() + "用户在" + DateUtil.formatDate(currentUser.getLastLoginTime(), "yyyy年MM月dd日 hh时mm分ss秒") + "登录,ip地址【" + userLog.getIp() +"】,登录区域在" + userLog.getAddress());
            iUserLogService.add(userLog);
            subject.getSession().setAttribute(Constant.CURRENT_USER, currentUser);
            return result;
        }catch(Exception e){
            return Result.createByError("用户名或密码不匹配！");
        }
    }

    /**
     * 首页框架页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "admin/index";
    }

    /**
     * 首页右边内容页
     * @return
     */
    @RequestMapping("/wrok")
    public String wrok(){
        return "admin/wrok";
    }

    /**
     * 图片上传
     * @param fileImg
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String,Object> upload(@RequestParam(name = "fileImg",required = false)MultipartFile fileImg) throws IOException {
        Map<String,Object> resultMap=new HashMap<>();
        if(!fileImg.isEmpty()){
            String mkdirName = DateUtil.getCurrentDateStr();
            File file = new File(uploadFilePath + mkdirName);
            if(!(file.exists()))
                file.mkdirs();

            // 获取文件名
            String fileName = fileImg.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //新文件名
            String newFileName= DateUtil.getCurrentDateTimeStr()+suffixName;
            fileImg.transferTo(new File(uploadFilePath + mkdirName + "/" + newFileName));
            resultMap.put("url", "/upload/" + mkdirName + "/" + newFileName);
        }
        return resultMap;
    }

}
