package com.xtmit.distribution;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonParser;
import com.xtmit.distribution.app.MyApplication;
import com.xtmit.zxing.client.android.constans.Constant;

import android.content.Intent;

public class BaseManager {

	protected void sendBroadcast(Intent intent, Map<String, Serializable> map) {
		// TODO Auto-generated method stub
		Set<String> keys = map.keySet();
		for (String string : keys) {
			intent.putExtra(string, map.get(string));
		}
		MyApplication.context.sendBroadcast(intent);
	}

	protected <T> T getGsonObject(String gson, String key, Class<T> obj) {
		return Constant.gson.fromJson(new JsonParser().parse(gson).getAsJsonObject().get(key), obj);
	}
}
