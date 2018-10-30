package com.longwang.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具类
 * @author 竹林听雨
 * @date 2018年9月12日 下午9:16:47
 */
public class CryptographyUtil {

	public final static String SALT = "longwang"; // 加密的盐

	/**
	 * Md5加密
	 * @param str  需要加密的字符串
	 * @param salt  加密盐
	 * @return
	 */
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}

	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		String password="123456";
		System.out.println("Md5加密："+ CryptographyUtil.md5(password, SALT));
	}
}
