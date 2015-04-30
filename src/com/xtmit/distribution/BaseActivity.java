package com.xtmit.distribution;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.xtmit.distributionsystem.R;

public class BaseActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ����״̬���Ĺ���ʵ��  
	    SystemBarTintManager tintManager = new SystemBarTintManager(this);  
	    // ����״̬������  
	    tintManager.setStatusBarTintEnabled(true);  
	    // �����������  
	    tintManager.setNavigationBarTintEnabled(true);  
	 
	    // ����һ����ɫ��ϵͳ��  
	    tintManager.setTintColor(getResources().getColor(R.color.color_bg));  
	    //Color.parseColor(
	    // ����һ����ʽ������������  
	    //tintManager.setNavigationBarTintResource(R.drawable.my_tint);  
	    // ����һ��״̬����Դ  
	    //tintManager.setStatusBarTintDrawable(MyDrawable);  
	    
	    
		initActionbar();
	}

	private void initActionbar() {
 		ActionBar actionbar= getActionBar();
 		View view= View.inflate(getApplicationContext(), R.layout.custom_actionbar, null);
 		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER);
 		actionbar.setCustomView(view, lp);
 		actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
 		actionbar.setDisplayUseLogoEnabled(false);
 		actionbar.setDisplayShowTitleEnabled(false);
 		actionbar.setDisplayShowCustomEnabled(true);
 		actionbar.setDisplayShowHomeEnabled(false);
	}
}
