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
	private boolean _conncetState = false;// ����״̬�洢
	private boolean _hasChanged = false;// ����״̬�Ƿ��иı�

	public MyThread myThread = new MyThread();

	public Net(Context context) {
		_context = context;
		_conncetState = isConnectNet();
	}

	/**
	 * �ж��Ƿ���ͨ����
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

	// ����״̬�ı�֮��ͨ���˽ӿڵ�ʵ��֪ͨ��ǰ�����״̬���˽ӿ���Activity��ע��ʵ������
	private GetConnectState onGetConnectState = new GetConnectState() { // ��ӽӿ�ʵ����ȡ����״̬
		@Override
		public void GetState(boolean isConnected) {
			if (_conncetState != isConnected) { // �����ǰ����״̬��㲥���񷵻ص�״̬��ͬ�Ž���֪ͨ��ʾ
				_hasChanged = true;
				_conncetState = isConnected;
				if (_conncetState) {// ������
					_handler.sendEmptyMessage(1);
				} else {// δ����
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
	 * ���õ�ǰ������ڵ㣬����Ҫ��ע����ĵط��̳�INet�ӿڣ��Ϳ��������Զ��������ͨ�ϴ�����
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
			case 1:// ������
				_iNet.DoSomethingsCon();
				break;
			case 2:// δ����
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
				onGetConnectState.GetState(isContected); // ֪ͨ����״̬�ı�
			}
		}
	}
}
