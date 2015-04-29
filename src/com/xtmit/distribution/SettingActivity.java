package com.xtmit.distribution;

import com.xtmit.distributionsystem.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SettingActivity extends BaseActivity {

	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_layout);
		TextView title=(TextView) getActionBar().getCustomView().findViewById(R.id.actionbar_title);
		title.setText(R.string.setting);
		TextView back=(TextView) getActionBar().getCustomView().findViewById(R.id.actionbar_back);
		back.setText(R.string.actionbar_back);
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(l);
		
		findViewById(R.id.btn_exit).setOnClickListener(l);
		findViewById(R.id.modifyPass_Login).setOnClickListener(l);
		
	}
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View v) { 
			switch (v.getId()) {
			case R.id.actionbar_back:
				finish();
				break;
			case R.id.modifyPass_Login:
				startActivity(new Intent("modifypassword"));
			case R.id.btn_exit:
				startActivity(new Intent("ditributionLogin"));
				finish();
			default:
				break;
			}
		}
	};
}
