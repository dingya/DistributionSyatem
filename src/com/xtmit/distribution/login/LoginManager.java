package com.xtmit.distribution.login;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;

import com.xtmit.distribution.BaseManager;
import com.xtmit.distribution.model.CommandCode;
import com.xtmit.distribution.model.CompanyUserModel;
import com.xtmit.distribution.model.ResultModel;
import com.xtmit.distribution.model.ResultType;
import com.xtmit.distribution.service.IServiceHandler;
import com.xtmit.distribution.webservice.WebService;
import com.xtmit.zxing.client.android.constans.Constant;

public class LoginManager extends BaseManager {

	public static LoginManager instance() {
 		return SingleTonLoginHolder.intance;
	}
	private boolean isLogin=false;
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	
	public boolean isLogin() {
		return isLogin;
	}

	private LoginManager(){
		
	}
	private static class  SingleTonLoginHolder{
  		public static LoginManager intance= new LoginManager();
	}
	public void login(String name, String mm) {
		
		String methodName = Constant.WebServiceMethod.WEB_MOTHED_LoginByDistribution;
		LinkedHashMap<String, Object> datas=  new LinkedHashMap<String, Object>();
		datas.put(Constant.WebServiceMethod.WEB_PARAM_userName, name);
		datas.put(Constant.WebServiceMethod.WEB_PARAM_pwd, mm);
		datas.put(Constant.WebServiceMethod.WEB_PARAM_userType, Constant.userType);
		datas.put(Constant.WebServiceMethod.WEB_PARAM_companyID, Constant.companyId);
		JSONObject json = WebService.stratWebServices(methodName, datas);
		if(json==null){
			Intent intent =new Intent(IServiceHandler.LOGIN_FAILED);
			Map<String, Serializable> map= new LinkedHashMap<String, Serializable>();
			
			map.put(Constant.FAILED_RESION, "·þÎñÆ÷´íÎó");
			sendBroadcast( intent,map);
			return;
		}
		ResultModel model=Constant.gson.fromJson(json.toString(), ResultModel.class);
		WebService.clear();
		String res = model.getResult().toString();
		if(res.equals(ResultType.Failed.name())){
			//CommandCode code = Constant.gson.fromJson(model.getMsg().toString(), CommandCode.class);
			//String msg = code.getMsg();
			//((CommandCode)model.getMsg()).name();
			Intent intent =new Intent(IServiceHandler.LOGIN_FAILED);
			Map<String, Serializable> map= new LinkedHashMap<String, Serializable>();
			map.put(Constant.FAILED_RESION, model.getMsg().toString());
			sendBroadcast( intent,map);
		}else if(res.equals(ResultType.Success.name())){
			isLogin=true;
			Constant.gloableUserModel=Constant.gson.fromJson(model.getMsg().toString(), CompanyUserModel.class);
			Intent intent =new Intent(IServiceHandler.LOGIN_SUCCESS);
			Map<String, Serializable> map= new LinkedHashMap<String, Serializable>();
 			sendBroadcast(intent,map);
		}
	}

	
}
