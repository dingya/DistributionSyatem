package com.xtmit.distribution.service;

import android.content.BroadcastReceiver;
import android.content.Intent;

public interface IServiceHandler {

	
	/** 没有优先级*/
	int INTENT_NO_PRIORITY = -1;
	
	/** 注册登陆失败事件*/
	String LOGIN_FAILED = "login_failed";
	
	/** 注册登陆成功事件*/
	String LOGIN_SUCCESS = "login_success";
	/** 注册 事件*/
	void registe();
	/**action 改变以往 的string  效率略好*/
	void action(String action,Intent in,BroadcastReceiver b);

	
}
