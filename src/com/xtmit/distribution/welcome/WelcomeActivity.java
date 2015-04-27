package com.xtmit.distribution.welcome;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.xtmit.distribution.login.LoginManager;
import com.xtmit.distribution.service.IServiceHandler;
import com.xtmit.distribution.service.ServiceHandler;
import com.xtmit.distributionsystem.R;
import com.xtmit.zxing.client.android.constans.Constant;
import com.xtmit.zxing.client.android.util.PreferencesUtils;
 
public class WelcomeActivity extends Activity implements IServiceHandler {
 	private static final int WHAT_WELCOME_OVER = 555;
	private static final int WHAT_LOGIN_HANDLE = 666;
	Handler handle=new Handler(){
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case WHAT_WELCOME_OVER:
				startActivity(new Intent("ditributionLogin"));
				break;
			case WHAT_LOGIN_HANDLE:
				String name = getCacheName();
				String mm = getCachePass();
				LoginManager.instance().login(name,mm);
				break;
			default:
				break;
			}
		};
	};
	
	/**
	 * 第一次登陆【进入登陆界面】 否则【后台取 数据 自己登陆进入主页】
	 */
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
	}
	
	
	@Override
	protected void onResume() {
 		super.onResume();
		registe();
		Message msg;
		if(isFirst(Constant.LOGIN_CACHE)){
			msg= handle.obtainMessage(WHAT_LOGIN_HANDLE);
		}else{
			msg= handle.obtainMessage(WHAT_WELCOME_OVER);
		}
		handle.sendMessageAtTime(msg, 5000);
	}
	/**
	 * 
	 * @return 缓存密码 【MD5】
	 */
	protected String getCachePass() {
 		return PreferencesUtils.getString(getApplicationContext(), Constant.CACHE_PASS);
	}

	/**
	 * 
	 * @return 缓存用户名
	 */
	protected String getCacheName() {
 		return PreferencesUtils.getString(getApplicationContext(), Constant.CACHE_NAME);
	}

	/**
	 * 
	 * @param loginCache  缓存 文件名
	 * @return true 已经登陆过 有缓存false 第一次登陆
	 */
	private boolean isFirst(String loginCache) {
		PreferencesUtils.PREFERENCE_NAME=loginCache;
		String name = PreferencesUtils.getString(getApplicationContext(), Constant.CACHE_NAME);
 		return name!=null&&!name.isEmpty()&&name.length()>0;
	}

	@Override
	public void registe() {
		List<String> actions=new ArrayList<String>();
		actions.add(IServiceHandler.LOGIN_FAILED);
		actions.add(IServiceHandler.LOGIN_SUCCESS);
		ServiceHandler.instance().registAction(this, actions, IServiceHandler.INTENT_NO_PRIORITY, this);
	}

	@Override
	public void action(String action, Intent in, BroadcastReceiver b) {
 		if (action.equals(IServiceHandler.LOGIN_FAILED)) {
			Toast.makeText(getApplicationContext(), in.getStringExtra(Constant.FAILED_RESION), 3000).show();
		}else if(action.equals(IServiceHandler.LOGIN_SUCCESS)){
			startActivity(new Intent("mainActivity"));
			finish();
		}
	}
	
	
}
