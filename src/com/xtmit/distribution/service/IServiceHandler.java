package com.xtmit.distribution.service;

import android.content.BroadcastReceiver;
import android.content.Intent;

public interface IServiceHandler {

	
	/** û�����ȼ�*/
	int INTENT_NO_PRIORITY = -1;
	
	/** ע���½ʧ���¼�*/
	String LOGIN_FAILED = "login_failed";
	
	/** ע���½�ɹ��¼�*/
	String LOGIN_SUCCESS = "login_success";
	/** ע�� �¼�*/
	void registe();
	/**action �ı����� ��string  Ч���Ժ�*/
	void action(String action,Intent in,BroadcastReceiver b);

	
}
