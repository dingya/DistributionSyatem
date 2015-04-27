package com.xtmit.distribution.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.xtmit.zxing.client.android.constans.Constant;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ServiceHandler {

	Map<String,List<IServiceHandler>> actionMaps = new LinkedHashMap<String, List<IServiceHandler>>();
	private BroadcastReceiver receiver = new ActionBroadcastReceiver();
	
	private static  class SingleTonHolder{
		public static final ServiceHandler instance= new ServiceHandler();
	}
	
	public static ServiceHandler instance(){
		return SingleTonHolder.instance;
	}
	private ServiceHandler(){
	}

	public void registAction(Context context,List<String> actions,int priority,IServiceHandler iServiceHandler){
		if(!actions.isEmpty()){
			IntentFilter filter = new IntentFilter();
			if(priority!= IServiceHandler.INTENT_NO_PRIORITY){
				filter.setPriority(priority);
			}
			for (String key : actions) {
 				filter.addAction(key);
				if(actionMaps.containsKey(key)){
					actionMaps.get(key).add(iServiceHandler);
				}else{
					List<IServiceHandler> list= new ArrayList<IServiceHandler>();
					list.add(iServiceHandler);
					actionMaps.put(key, list);
				}
			}
			context.registerReceiver(receiver , filter);
		}
		
	}
	
	private class  ActionBroadcastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			
			String action = intent.getAction();
			List<IServiceHandler> IServiceHandlers  = actionMaps.get(action);
		
			for (IServiceHandler iServiceHandler : IServiceHandlers) {
				iServiceHandler.action(action, intent, this);
			}
			
		}
		
	}
}
