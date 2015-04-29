package com.xtmit.distribution.webservice;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;

import com.xtmit.distribution.net.IMNetManager;
import com.xtmit.zxing.client.android.constans.Constant;
 
/**
 * 负责解析webservice
 * @author xumin
 *
 */
public class WebService {
	public enum ToastType{
		E_ProDlg,//显示不稳定进度条
		E_Toast,//显示提示
		E_HandleData,//处理网络数据
		E_HandleMsg;//在外部处理
	}
	
//	private static CustomProgressDialog _ProDlg;
	private static JSONObject _resultObj;

 	public static Context mContext;
	public static Context mselfContext;
	
	public static JSONObject stratWebServices(String methodName,
			LinkedHashMap<String, Object> datas) {
		try {
			return Executors.newCachedThreadPool()
					.submit(new MyThread(methodName, datas)).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			 
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			// TODO: handle exception
			 
			return null;
		}
	}

	public static JSONObject stratWebServices(String methodName,
			LinkedHashMap<String, Object> datas, String wsdl) {
		try {
			return Executors.newCachedThreadPool()
					.submit(new MyThread(methodName, datas, wsdl)).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	public static void sendCommand(String command,Object object){
		Message message = new Message();
		message.what = ToastType.E_HandleMsg.ordinal();
		message.arg1 = command.hashCode();
		message.obj = object;
		WebService._Handler.sendMessage(message);
	}*/

	public static class MyThread implements Callable<JSONObject> {
		private String _methodName;
		private LinkedHashMap<String, Object> _datas;
		private String _wsdl;

		public MyThread(String methodName, LinkedHashMap<String, Object> datas,
				String wsdl) {
			_methodName = methodName;
			_datas = datas;
			_wsdl = wsdl;
		}

		public MyThread(String methodName, LinkedHashMap<String, Object> datas) {
			_methodName = methodName;
			_datas = datas;
		}

		@Override
		public JSONObject call() throws Exception {
			SoapObject soapObject = new SoapObject(Constant.NameSpace,_methodName);
			// 传递方法中的参数
			Iterator<Entry<String, Object>> it = _datas.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				soapObject.addProperty(entry.getKey(), entry.getValue());
			}

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = false;
			envelope.setOutputSoapObject(soapObject);// envelope.bodyOut=request;

			if (IMNetManager.instance().isConnectNet()) {
				HttpTransportSE httpTranstation;
				if (_wsdl != null) {
					httpTranstation = new HttpTransportSE(_wsdl, 10000);
				} else {
					httpTranstation = new HttpTransportSE(Constant.WSDLTest,
							10000);
				}
				try {
					httpTranstation.call(null, envelope);
					Object result = envelope.getResponse();
					_resultObj = new JSONObject(result.toString());
					return _resultObj;
				} catch (IOException e) { 
					e.printStackTrace();
					return null;
				} catch (XmlPullParserException e) { 
					e.printStackTrace();
					return  null;
				} catch (JSONException e) { 
					e.printStackTrace();
					return  null;
				}
			} else {
				return null;
			}
		}
	}
	
	public static void clear(){
		if(_resultObj!=null){
			_resultObj=null;
		}
	}
 
}
