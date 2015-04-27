package com.xtmit.distribution.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;

public class Net {
	private boolean isContected = true;
	private Context _context;
	private Handler _handler = new MyHandler();
	private INet _iNet;
	private boolean _conncetState = false;// 网络状态存储
	private boolean _hasChanged = false;// 网络状态是否有改变

	public MyThread myThread = new MyThread();

	public Net(Context context) {
		_context = context;
		_conncetState = isConnectNet();
	}

	/**
	 * 判断是否连通网络
	 * 
	 * @return
	 */
	public Boolean isConnectNet() {
		boolean netSataus = false;
		ConnectivityManager cwjManager = (ConnectivityManager) _context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cwjManager != null) {
			cwjManager.getActiveNetworkInfo();
			if (cwjManager.getActiveNetworkInfo() != null) {
				netSataus = cwjManager.getActiveNetworkInfo().isAvailable();
			}
		}
		return netSataus;
	}

	// 网络状态改变之后，通过此接口的实例通知当前网络的状态，此接口在Activity中注入实例对象
	private GetConnectState onGetConnectState = new GetConnectState() { // 添加接口实例获取连接状态
		@Override
		public void GetState(boolean isConnected) {
			if (_conncetState != isConnected) { // 如果当前连接状态与广播服务返回的状态不同才进行通知显示
				_hasChanged = true;
				_conncetState = isConnected;
				if (_conncetState) {// 已连接
					_handler.sendEmptyMessage(1);
				} else {// 未连接
					_handler.sendEmptyMessage(2);
				}
			} else {
				_hasChanged = false;
			}
		}
	};

	public boolean hasChanged() {
		return _hasChanged;
	}

	/**
	 * 设置当前的网络节点，在需要关注网络的地方继承INet接口，就可以设置自定义的网络通断处理方法
	 * 
	 * @param iNet
	 */
	public void SetNetHandler(INet iNet) {
		_iNet = iNet;
	}

	private class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:// 已连接
				_iNet.DoSomethingsCon();
				break;
			case 2:// 未连接
				_iNet.DoSomethingsDiscon();
				break;
			default:
				break;
			}
		}
	}

	public interface GetConnectState {
		public void GetState(boolean isConnected);
	}

	public class MyThread implements Runnable {
		public MyThread() {
		}

		@Override
		public void run() {
			if (isConnectNet()) {
				isContected = true;
			} else {
				isContected = false;
			}
			if (onGetConnectState != null) {
				onGetConnectState.GetState(isContected); // 通知网络状态改变
			}
		}
	}
}
