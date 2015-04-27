package com.xtmit.distribution.model;

/**
 * 处理业务逻辑的返回模型
 * @author xumin
 *
 */
public class ResultModel {
	/**********************/
	private String result;
	// Success || Failed
	public String getResult(){
		return result;
	}
	public void setResult(String result){
		this.result =result;
	}
	
	/**********************/
	// 附加信息
	private Object msg;
	public Object getMsg(){
		return msg;
	}
	public void setMsg(Object msg){
		this.msg =msg;
	}
	/**********************/
}
