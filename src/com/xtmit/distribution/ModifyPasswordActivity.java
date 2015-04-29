package com.xtmit.distribution;

import java.util.LinkedHashMap;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xtmit.distribution.app.MyApplication;
import com.xtmit.distribution.model.ResultModel;
import com.xtmit.distribution.model.ResultType;
import com.xtmit.distribution.view.ClearEditText;
import com.xtmit.distribution.webservice.WebService;
import com.xtmit.distributionsystem.R;
import com.xtmit.zxing.client.android.constans.Constant;
import com.xtmit.zxing.client.android.constans.Constant.WebServiceMethod;
import com.xtmit.zxing.client.android.util.PreferencesUtils;
import com.xtmit.zxing.client.android.util.StringUtils;

public class ModifyPasswordActivity extends BaseActivity {

	Button btn_Confirm;
	
	ClearEditText pass_old, pass_new, pass_confirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		setContentView(R.layout.modifypwd_layout);

		((TextView) getActionBar().getCustomView().findViewById(
				R.id.actionbar_back)).setText(getResources().getString(
				R.string.modify_pass));
		TextView view = (TextView) getActionBar().getCustomView().findViewById(
				R.id.actionbar_back);
		view.setVisibility(View.VISIBLE);
		view.setText(getResources().getString(R.string.actionbar_back));
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		btn_Confirm = (Button) findViewById(R.id.btn_submit);
		pass_confirm = (ClearEditText) findViewById(R.id.et_makeSurepass);
		pass_new = (ClearEditText) findViewById(R.id.et_newpass);
		pass_old = (ClearEditText) findViewById(R.id.et_oldpass);

		btn_Confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (StringUtils.get32MD5Str(pass_old.getText().toString())
						.equals(Constant.gloableUserModel
								.getCompanyUserPassword())) {

					if (!pass_confirm.getText().toString()
							.equals(pass_new.getText().toString())) {
						Toast.makeText(ModifyPasswordActivity.this,
								"两次输入的密码不一致", Toast.LENGTH_LONG).show();
					} else {
						LinkedHashMap<String, Object> datas = new LinkedHashMap<String, Object>(); 
						datas.put(Constant.WebServiceMethod.WEB_PARAM_ModifycompanyID, Constant.gloableUserModel.getCompanyID());
						datas.put(Constant.WebServiceMethod.WEB_PARAM_CompanyUserPassword,StringUtils.get32MD5Str(pass_confirm.getText().toString()));
						datas.put(Constant.WebServiceMethod.WEB_PARAM_CompanyUserID, Constant.gloableUserModel.getCompanyUserID());
						JSONObject json = WebService.stratWebServices(
								WebServiceMethod.WEB_MOTHED_modifypassword,
								datas);
						ResultModel rm = Constant.gson.fromJson(
								json.toString(), ResultModel.class);

						if (rm.getResult().equals(ResultType.Failed.name())) {
							Toast.makeText(ModifyPasswordActivity.this, "设置失败",
									Toast.LENGTH_LONG).show();
						} else if (rm.getResult().equals(
								ResultType.Success.name())) {
							PreferencesUtils.PREFERENCE_NAME = Constant.LOGIN_CACHE;
							Constant.gloableUserModel
									.setCompanyUserPassword(StringUtils
											.get32MD5Str(pass_confirm.getText()
													.toString()));
							PreferencesUtils.putString(MyApplication.context,
									Constant.CACHE_PASS,
									Constant.gloableUserModel
											.getCompanyUserPassword());
							Toast.makeText(ModifyPasswordActivity.this, "设置成功",
									Toast.LENGTH_LONG).show();
							ModifyPasswordActivity.this.finish();
						}

					}
				} else {
					Toast.makeText(ModifyPasswordActivity.this, "旧密码错误", Toast.LENGTH_LONG)
							.show();
				}
			}
		});
	}
}
