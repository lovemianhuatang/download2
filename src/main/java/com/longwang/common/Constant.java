package com.longwang.common;

/**
 * 封装所有的常量
 */
public class Constant {

    public static final String CURRENT_USER = "currentUser"; //表示session中登录的用户
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String PHONE = "phone";

    public interface Role {
        int ROLE_ORDINARY = 0;  //普通用户
        int ROLE_ADMIN = 1;     //管理员
    }

    public interface State {
        int STATE_BAN = 0;      //用户被禁用
        int STATE_NORMAL = 1;   //用户正常
    }

    public interface SiteType {
        int WEN_ZHANG = 1;      //文章
        int SHI_PIN = 2;   //视频
        int RUAN_JIAN = 3;   //软件
    }

}
