package com.longwang.common;

/**
 * 封装一些常用的响应状态码和消息，在ResponseCode类得基础之上
 */
public class CodeAndMsg {
	
	public static final CodeAndMsg SERVICE_ERROR = 
		   new CodeAndMsg(ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getDescribe());
	
	public static final CodeAndMsg NEED_LOGIN = 
		   new CodeAndMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDescribe());
		
	public static final CodeAndMsg ADD_ERROR = new CodeAndMsg(20, "ADD THE ERROR");
	
	public static final CodeAndMsg DELETE_ERROR = new CodeAndMsg(30, "DELETE THE ERROR");
		
		
	private int code;
	private String msg;

	private CodeAndMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() { return code; }
	
	public String getMsg() { return msg; }
}
