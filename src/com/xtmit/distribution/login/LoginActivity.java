package com.xtmit.distribution.login;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xtmit.distribution.service.IServiceHandler;
import com.xtmit.distribution.service.ServiceHandler;
import com.xtmit.distribution.view.ClearEditText;
import com.xtmit.distributionsystem.R;
import com.xtmit.zxing.client.android.constans.Constant;
import com.xtmit.zxing.client.android.util.PreferencesUtils;
import com.xtmit.zxing.client.android.util.StringUtils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity implements IServiceHandler{
	
	Button btn_login;
	ClearEditText cet_name,cet_pass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getActionBar().hide();
		registe();
		cet_name= (ClearEditText) findViewById(R.id.editText_username);
		cet_pass= (ClearEditText) findViewById(R.id.editText_password);

		btn_login=(Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
 				LoginManager.instance().login(cet_name.getText().toString(), StringUtils.get32MD5Str(cet_pass.getText().toString()));
			}
		});
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
			
		}else if(action.equals(IServiceHandler.LOGIN_SUCCESS)){
			startActivity(new Intent("mainActivity"));
			
			String cacheName=Constant.LOGIN_CACHE;
			Map<String, String> map= new LinkedHashMap<String, String>();
			map.put(Constant.CACHE_NAME, Constant.gloableUserModel.getCompanyUserName());
			map.put(Constant.CACHE_PASS, Constant.gloableUserModel.getCompanyUserPassword());
			saveCache(cacheName,map);
			finish();
		}
	}
	private void saveCache(String cacheName, Map<String, String> map) {
 		PreferencesUtils.PREFERENCE_NAME=cacheName;
 		Set<String> keys = map.keySet();
 		for (String string : keys) {
			PreferencesUtils.putString(getApplicationContext(), string, map.get(string));
		}
	}
}
