package com.xtmit.distribution.model;

public enum CommandCode{
	Login_IncorrectedPsw("对不起,密码不正确!"),
	Login_MaterialUser("对不起,您不是企业领料人!"),
	Login_OccupiedUser("对不起,该用户名与其他用户冲突!"),
	Login_NonexistentUser("对不起,不存在该用户!"),
	Login_Logined("用户已登录其它设备，不允许登录!"),
	Login_ValidationFailed("用户名,密码验证失败！"),
	Login_NonwhiteList("不在白名单内，无法登录！"),
	Submit_FAILED_NUM("提交失败提交库存和实际库存数量不符"),
	Submit_FAILED_DELETE("提交的货物已经被删除了");
	private String name;
	CommandCode(String name){
		this.name = name;
	}
	public String getMsg(){
		return this.name;
	}
}
