package com.longwang.util;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author 竹林听雨
 * @date 2018年9月12日 下午9:16:47
 */
public class StringUtil {

	/**
	 * 判断是否是空
	 * @param str 需要判断的字符串
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 判断是否不是空
	 * @param str  需要判断的字符串
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if((str!=null)&&!"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 过滤Html等标签
	 * @param inputString
	 * @return
	 */
	public static String HtmlText(String inputString) {
		// 过滤html标签
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		Pattern p_script;
		java.util.regex.Matcher m_script;
		Pattern p_style;
		java.util.regex.Matcher m_style;
		Pattern p_html;
		java.util.regex.Matcher m_html;
		Pattern p_cont1;
		java.util.regex.Matcher m_cont1;
		Pattern p_cont2;
		java.util.regex.Matcher m_cont2;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_cont1 = "[\\d+\\s*`~!@#$%^&*\\(?~！@#￥%……&*（）——+|{}【】‘：”“’_]"; // 定义HTML标签的正则表达式
			String regEx_cont2 = "[\\w[^\\W]*]"; // 定义HTML标签的正则表达式[a-zA-Z]
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			p_cont1 = Pattern.compile(regEx_cont1, Pattern.CASE_INSENSITIVE);
			m_cont1 = p_cont1.matcher(htmlStr);
			htmlStr = m_cont1.replaceAll(""); // 过滤其它标签
			p_cont2 = Pattern.compile(regEx_cont2, Pattern.CASE_INSENSITIVE);
			m_cont2 = p_cont2.matcher(htmlStr);
			htmlStr = m_cont2.replaceAll(""); // 过滤html标签
			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;// 返回文本字符串
	}


	/**
	 * 去除html标签
	 */
	public static String stripHtml(String content) {
		// <p>段落替换为换行
		content = content.replaceAll("<p .*?>", "\r\n");
		// <br><br/>替换为换行
		content = content.replaceAll("<br\\s*/?>", "\r\n");
		// 去掉其它的<>之间的东西
		content = content.replaceAll("\\<.*?>", "");
		// 去掉空格
		content = content.replaceAll(" ", "");
		return content;
	}

	/**
	 * 生成六位随机数
	 * @return
	 */
	public static String genSixRandomNum(){
		Random random = new Random();
		String result="";
		for (int i=0;i<6;i++)
		{
			result+=random.nextInt(10);
		}
		return result;
	}

	/**
	 * 打印空格
	 * @param index  数目
	 * @return
	 */
	public static String getNbsp(int index) {
		StringBuffer sb = new StringBuffer();
		int tempIndex = index + 2;
		for (int i = 0; i < tempIndex; ++i)
			sb.append("&nbsp;");
		return sb.toString();
	}

}
