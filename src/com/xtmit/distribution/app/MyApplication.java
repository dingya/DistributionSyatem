package com.xtmit.distribution.app;

import com.xtmit.distribution.DBManager;
import com.xtmit.distribution.net.IMNetManager;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

	public static Context context;

	public static DBManager dbManager;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context=getApplicationContext();
		IMNetManager.instance().initNet(context);
		dbManager=DBManager.instance(context);
	}
}
