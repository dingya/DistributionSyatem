package com.xtmit.distribution.model;

/**
 * ����ҵ���߼��ķ���ģ��
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
	// ������Ϣ
	private Object msg;
	public Object getMsg(){
		return msg;
	}
	public void setMsg(Object msg){
		this.msg =msg;
	}
	/**********************/
}
