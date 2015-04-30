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
		// 创建状态栏的管理实例  
	    SystemBarTintManager tintManager = new SystemBarTintManager(this);  
	    // 激活状态栏设置  
	    tintManager.setStatusBarTintEnabled(true);  
	    // 激活导航栏设置  
	    tintManager.setNavigationBarTintEnabled(true);  
	 
	    // 设置一个颜色给系统栏  
	    tintManager.setTintColor(getResources().getColor(R.color.color_bg));  
	    //Color.parseColor(
	    // 设置一个样式背景给导航栏  
	    //tintManager.setNavigationBarTintResource(R.drawable.my_tint);  
	    // 设置一个状态栏资源  
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
