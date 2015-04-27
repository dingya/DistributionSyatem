package com.xtmit.distribution.net;

 import com.xtmit.distribution.app.MyApplication;

import android.content.Context;

public class IMNetManager {

	Net mNet;
	private IMNetManager(){
		
	}
	public static IMNetManager instance() {
 		return SinglertonHoler.instance;
	}

	private static class SinglertonHoler{
		public static IMNetManager instance=new IMNetManager();
	}

	public void initNet(Context context){
		mNet=new Net(context);
	}
	
	public boolean isConnectNet() {
		if(mNet!=null){
			return mNet.isConnectNet();
		}else{
			return new Net(MyApplication.context).isConnectNet();
		}
	}
}
