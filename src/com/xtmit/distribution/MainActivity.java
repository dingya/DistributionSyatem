package com.xtmit.distribution;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xtmit.distribution.app.MyApplication;
import com.xtmit.distribution.model.BasicGoods;
import com.xtmit.distribution.model.BasicInformation;
import com.xtmit.distribution.model.BusinessUnitModel;
import com.xtmit.distribution.model.CacheRecordModel;
import com.xtmit.distribution.model.DepartmentModel;
import com.xtmit.distribution.model.GoodsModel;
import com.xtmit.distribution.model.MaterialRequisitionModel;
import com.xtmit.distribution.model.ProductionLineModel;
import com.xtmit.distribution.model.ResultModel;
import com.xtmit.distribution.model.ResultType;
import com.xtmit.distribution.model.SettlementAccountModel;
import com.xtmit.distribution.webservice.WebService;
import com.xtmit.distributionsystem.R;
import com.xtmit.zxing.client.android.constans.ButtonType;
import com.xtmit.zxing.client.android.constans.Constant;
import com.xtmit.zxing.client.android.constans.Constant.WebServiceMethod;
import com.xtmit.zxing.client.android.constans.ProductionLineStatus;

public class MainActivity extends BaseActivity {

	PullToRefreshListView listView;
	Button btnOrder, btnOutStorage, btnOver, btnOrderRecoder,
			btnOutStorageRecoder;
	private AlertDialog dialog;
	private AlertDialog dialogError;

	// private String times;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnOrder = (Button) findViewById(R.id.btn_order);
		btnOutStorage = (Button) findViewById(R.id.btn_outStore);
		btnOver = (Button) findViewById(R.id.btn_distributionOver);
		btnOrderRecoder = (Button) findViewById(R.id.btn_toOrderlist);
		btnOutStorageRecoder = (Button) findViewById(R.id.btn_toOutStoreRecord);
		btnOrderRecoder.setOnClickListener(l);
		btnOutStorageRecoder.setOnClickListener(l);

		btnOver.setOnClickListener(l);
		btnOutStorage.setOnClickListener(l);
		btnOrder.setOnClickListener(l);

		TextView title = (TextView) getActionBar().getCustomView()
				.findViewById(R.id.actionbar_title);
		title.setText(getResources().getString(R.string.title_scan));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data == null) {
			return;
		}
		int button = data.getIntExtra(WebServiceMethod.WEB_PARAM_ButtonType, 0);
		if (resultCode == Constant.RESULTCODE_CAPTURE_ACTIVITY) {
			LinkedHashMap<String, Object> datas = new LinkedHashMap<String, Object>();
			datas.put(WebServiceMethod.WEB_PARAM_companyID, Constant.companyId);
			datas.put(WebServiceMethod.WEB_PARAM_MaterialQRNum,
					data.getStringExtra(Constant.SCAN_RESUALT_TEXT));
			datas.put(WebServiceMethod.WEB_PARAM_ButtonType, button);
			JSONObject json = WebService
					.stratWebServices(
							WebServiceMethod.WEB_MOTHED_scanQRMakeSureIsHaveMaterialNum,
							datas);
			if (json == null) {
				showError("请求服务器失败");
				return;
			}

			ResultModel res = Constant.gson.fromJson(json.toString(),
					ResultModel.class);
			WebService.clear();
			if (res.getResult().equals(ResultType.Failed.name())) {
				showError(res.getMsg().toString());
			} else if (res.getResult().equals(ResultType.Success.name())) {
				// int status = 0;
				if (button == ButtonType.BUTTON_TYPE_ORDER) {
					status = ProductionLineStatus.BeOrderMiss;
				} else if (button == ButtonType.BUTTON_TYPE_DISTRUBUTION_OVER) {
					status = ProductionLineStatus.DistributeOverBeNomal;
				} else if (button == ButtonType.BUTTON_TYPE_OUT_STORAGE) {
					status = -1;
				}
				fromJson = Constant.gson.fromJson(res.getMsg().toString(),
						ProductionLineModel.class);
				// Constant.gson.fromJson(res.getMsg().toString(),
				// ProductionLineModel.class),status
				if (fromJson != null) {
					showInfo();
				}
			}
		}
	}

	ProductionLineModel fromJson;
	int status;

	/**
	 * 扫描成功 弹出的提示 确认信息
	 * 
	 * @param fromJson
	 * @param status
	 */
	// final ProductionLineModel fromJsons,final int statuss
	private void showInfo() {
		View customTitleView = null;
		View view = View.inflate(this, R.layout.view_dialog, null);
		Button btnPositive = (Button) view
				.findViewById(R.id.btn_diologPositive);
		Button btnCancel = (Button) view.findViewById(R.id.btn_diologCancel);
		TextView tv_lineName = (TextView) view
				.findViewById(R.id.tv_productLineName);
		TextView tv_bomNum = (TextView) view.findViewById(R.id.tv_bomNum);

		tv_bomNum.setText(fromJson.getMaterialName());
		tv_lineName.setText(fromJson.getProductionLineName());

		btnCancel.setOnClickListener(l);

		btnPositive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				if (status > 0) {
					LinkedHashMap<String, Object> datas = new LinkedHashMap<String, Object>();
					datas.put(WebServiceMethod.WEB_PARAM_companyID,
							Constant.companyId);
					datas.put(WebServiceMethod.WEB_PARAM_ProductionLineNum,
							fromJson.getProductionLineNum());
					datas.put(WebServiceMethod.WEB_PARAM_ProductionLineID,
							fromJson.getProductionLineID());
					datas.put(WebServiceMethod.WEB_PARAM_status, status);

					JSONObject gson = WebService
							.stratWebServices(
									WebServiceMethod.WEB_MOTHED_changeProductionLineStatus,
									datas);
					ResultModel res = Constant.gson.fromJson(gson.toString(),
							ResultModel.class);
					WebService.clear();
					if (res.getResult().equals(ResultType.Failed.name())) {
						showError(res.getMsg().toString());
					} else if (res.getResult()
							.equals(ResultType.Success.name())) {
						if (status == ProductionLineStatus.BeOrderMiss) {
							// 下料
							LinkedHashMap<String, Object> datas2 = new LinkedHashMap<String, Object>();
							datas2.put(WebServiceMethod.WEB_PARAM_companyID, 3);
							datas2.put(WebServiceMethod.WEB_PARAM_BomNum,
									fromJson.getBomNum());

							JSONObject json = WebService.stratWebServices(
									WebServiceMethod.WEB_MOTHED_getGoodsModel,
									datas2);
							if (json == null) {
								showError("服务器出错!\n请及时反馈json=null");
								return;
							}
							ResultModel ressult = Constant.gson.fromJson(
									json.toString(), ResultModel.class);
							WebService.clear();
							if (ressult == null) {
								showError("服务器出错!\n请及时反馈:ressult=null");
								return;
							}
							if (ressult.getResult().equals(
									ResultType.Failed.name())) {
								showError(ressult.getMsg().toString());
							} else if (ressult.getResult().equals(
									ResultType.Success.name())) {
								GoodsModel model = Constant.gson.fromJson(
										ressult.getMsg().toString(),
										GoodsModel.class);

								LinkedHashMap<String, Object> datas3 = new LinkedHashMap<String, Object>();
								datas3.put(
										WebServiceMethod.WEB_PARAM_MaterialRequisitionModel,
										Constant.gson.toJson(initModel(model),
												MaterialRequisitionModel.class));
								datas3.put(
										WebServiceMethod.WEB_PARAM_ProductionLineID,
										fromJson.getProductionLineID());
								JSONObject json2 = WebService.stratWebServices(
										WebServiceMethod.WEB_MOTHED_GenerateMR,
										datas3);
								if (json2 == null)
									showError("json=null");
								ResultModel resm = Constant.gson.fromJson(
										json2.toString(), ResultModel.class);
								WebService.clear();
								if (resm == null)
									showError("ResultModel=null");

								if (resm.getResult().equals(
										ResultType.Failed.name())) {

									showError(resm.getMsg() == null ? "服务器出错!\n请及时反馈"
											: resm.getMsg().toString());
								} else if (resm.getResult().equals(
										ResultType.Success.name())) {
									/*
									 * SpannableString str = new
									 * SpannableString(
									 * resm.getMsg().toString());
									 * str.setSpan(new RelativeSizeSpan(2.5f),
									 * 3, 4,
									 * Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
									 * str.setSpan(new
									 * ForegroundColorSpan(Color.GRAY), 0,
									 * str.length
									 * (),Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
									 */
									showError("已生成领料单\n单号:"
											+ resm.getMsg().toString());
									insertLoacalRecord(
											resm.getMsg().toString(), fromJson,
											status);
								}

							}

						} else if (status == ProductionLineStatus.DistributeOverBeNomal) {
							// 陪送完成
							LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
							params.put(WebServiceMethod.WEB_PARAM_companyID,
									Constant.companyId);
							params.put(
									WebServiceMethod.WEB_PARAM_ProductionLineID,
									fromJson.getProductionLineID());
							params.put(WebServiceMethod.WEB_PARAM_RunningNum,
									fromJson.getRunningNumber());
							JSONObject resJson = WebService
									.stratWebServices(
											WebServiceMethod.WEB_MOTHED_updateMaterialRequisationStatusDistributionOver,
											params);
							ResultModel DisOverRes = Constant.gson.fromJson(
									resJson.toString(), ResultModel.class);
							if (DisOverRes.getResult().equals(
									ResultType.Failed.name())) {
								showError(DisOverRes.getMsg().toString());
							} else if (DisOverRes.getResult().equals(
									ResultType.Success.name())) {
								showError(DisOverRes.getMsg().toString());
							}
						}
					}
				} else if (status < 0) {
					// 出库 改 配送人 信息;
					LinkedHashMap<String, Object> Maps = new LinkedHashMap<String, Object>();
					Maps.put(WebServiceMethod.WEB_PARAM_companyID,
							Constant.companyId);
					Maps.put(WebServiceMethod.WEB_PARAM_ProductionLineID,
							fromJson.getProductionLineID());
					Maps.put(WebServiceMethod.WEB_PARAM_RunningNum,
							fromJson.getRunningNumber());
					Maps.put(WebServiceMethod.WEB_PARAM_userID,
							Constant.gloableUserModel.getCompanyUserID());
					Maps.put(WebServiceMethod.WEB_PARAM_userName,
							Constant.gloableUserModel.getCompanyUserName());
					Maps.put(WebServiceMethod.WEB_PARAM_trueName,
							Constant.gloableUserModel.getCompanyUserTrueName());

					// TODO 改变领料单状态改为30【待领用】
					JSONObject json = WebService
							.stratWebServices(
									WebServiceMethod.WEB_MOTHED_updateMaterialRequisationStatus,
									Maps);
					if (json == null)
						return;
					ResultModel res = Constant.gson.fromJson(json.toString(),
							ResultModel.class);
					WebService.clear();

					if (res == null)
						return;
					if (res.getResult().equals(ResultType.Failed.name())) {
						showError(res.getMsg().toString());
					} else if (res.getResult()
							.equals(ResultType.Success.name())) {
						new AlertDialog.Builder(MainActivity.this)
								.setTitle("确认领料单单号")
								.setMessage(res.getMsg().toString()).show();
						insertLoacalRecord(res.getMsg().toString(), fromJson,
								status);
					}

				}

			}

			private void insertLoacalRecord(String Num,
					ProductionLineModel fromJsons, int status) {
				SimpleDateFormat dateFormat1 = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				CacheRecordModel model = new CacheRecordModel(Num,
						fromJsons.getProductionLineName(), 
						fromJsons.getProductionLineID() + 0l,
						fromJsons.getMaterialName(),
						fromJsons.getRunningNumber(),
						fromJsons.getProductionLineStatus() + 0l,
						Constant.gloableUserModel.getCompanyUserName(),
						Constant.gloableUserModel.getCompanyUserID() + 0l,
						Constant.gloableUserModel.getCompanyUserTrueName(),
						dateFormat1.format(new Date()),
						status > 0 ? Constant.RECORD_TYPE_ORDER_RECORD + 0l
								: Constant.RECORD_TYPE_OUT_STORAGE_RECORD + 0l);

				MyApplication.dbManager.insertToSql(model);
			}
		});

		dialog = new AlertDialog.Builder(this).setTitle("确认信息")
				.setCustomTitle(customTitleView).setView(view).show();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				status = 0;
				fromJson = null;
			}
		});
	}

	private void showError(String string) {
		View layout = View.inflate(this, R.layout.custom, null);
		TextView text = (TextView) layout.findViewById(R.id.tvTextToast);
		ImageView cross=  (ImageView) layout.findViewById(R.id.img_cross);
		text.setText(string);
		cross.setOnClickListener(l);
		//Toast toast = new Toast(getApplicationContext());
		dialogError = new AlertDialog.Builder(this).setView(layout).show();
		  
		//toast.setGravity(Gravity.RIGHT | Gravity.TOP, 12, 40);
		//toast.setDuration(Toast.LENGTH_LONG);
		//toast.setView(layout);
		//toast.show();

	}

	private MaterialRequisitionModel initModel(GoodsModel Goods) {

		MaterialRequisitionModel requisitionModel = new MaterialRequisitionModel();

		BasicInformation basicInformation = new BasicInformation();

		// depart
		DepartmentModel department = new DepartmentModel();
		department.setCompanyID(3);
		department.setDepartmentEnName("Engineering");
		department.setDepartmentID(0);
		department.setDepartmentName("工程部");
		department.setDepartmentNumber("1000");
		// TODO 唯一部门信息 伪造
		basicInformation.setDepartmentModel(department);
		basicInformation.setDepartmentModelInCompany(department);

		// secc
		SettlementAccountModel sa = new SettlementAccountModel();
		sa.setSettlementAccountEnName("Temp Help Expense");
		sa.setSettlementAccountName("仅用于记录临时工费用如工资等");
		sa.setSettlementNumber("13200000");
		basicInformation.setSettlementAccountModel(sa);

		// Bu
		BusinessUnitModel bu = new BusinessUnitModel();
		bu.setBusinessUnitID(0);
		bu.setBusinessUnitName("Shared Service");
		bu.setBusinessUnitNumber("000");
		bu.setBusinessUnitShortName("SHA");
		bu.setCompanyID(3);
		bu.setRemark("");
		basicInformation.setBusinessUnitModel(bu);

		basicInformation.setCompanyID(Constant.gloableUserModel.getCompanyID());
		basicInformation.setCompanyUserID(Constant.gloableUserModel
				.getCompanyUserID());
		basicInformation.setCompanyUserTrueName(Constant.gloableUserModel
				.getCompanyUserTrueName());

		basicInformation.setCompanyUserName(Constant.gloableUserModel
				.getCompanyUserName());
		basicInformation.setMaterialRequisitionNum(getResources().getString(
				R.string.order_get));
		basicInformation.setCompanyEnShortName("FI");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		basicInformation.setMaterialRequisitionDate(dateFormat1
				.format(new Date()));
		Double totals = 0.00;
		for (GoodsModel model : getGoodsValues(Goods)) {
			totals = totals + model.getUnitPrice() * 1;
		}
		basicInformation.setTotalPrice(totals);
		// 需要在出库时 更新这些信息
		basicInformation.setAuditorID(0);
		basicInformation.setAuditorName("未知,待出库更新");
		basicInformation.setAuditorTrueName("未知,待出库更新");

		requisitionModel.setBasicInformation(basicInformation);

		// ---
		List<BasicGoods> basicGoods = new ArrayList<BasicGoods>();
		for (GoodsModel model : getGoodsValues(Goods)) {
			BasicGoods basicGood = new BasicGoods();
			basicGood.setCompanyMateriaID(model.getCompanyMateriaID());
			basicGood.setMaterialRequisitionNum("");

			// 一次只扫一次 只能下单一次
			basicGood.setMRQuantity(1);
			basicGood.setCompanyMateriaName(model.getCompanyMateriaName());
			basicGood.setCompanyMateriaNameEn(model.getCompanyMaterialNameEn());
			basicGood.setBrand(model.getBrand());
			basicGood.setBrandEn(model.getBrandEn());
			basicGood.setModel(model.getModel());
			basicGood.setModelEn(model.getModelEn());
			basicGood.setCompanyMateriaNum(model.getCompanyMateriaNum());
			basicGood.setUnit(model.getUnit());
			basicGood.setUnitEn(model.getUnitEn());
			basicGood.setSpecifications(model.getSpecifications());
			basicGood.setSpecificationsEn(model.getSpecificationsEn());
			basicGood.setUnitPrice(model.getUnitPrice());

			basicGood.setRunningNumber(model.getRunningNumber());
			basicGood.setMaterialType(model.getMaterialType());
			basicGood.setMGRunningNumber(model.getMGRunningNumber());
			basicGood.setQuantity(model.getQuantity());
			List<GoodsModel> child = model.getChildGoodsmodels();
			basicGood.setChildGoodsmodels(child);
			basicGoods.add(basicGood);
		}
		requisitionModel.setBasicGoods(basicGoods);

		return requisitionModel;
	}

	private List<GoodsModel> getGoodsValues(GoodsModel object) {
		List<GoodsModel> basicGoods = new ArrayList<GoodsModel>();
		basicGoods.add(object);
		return basicGoods;
	}

//	private int i = 0;
	/** 所有点击 事件 */
	private OnClickListener l = new OnClickListener() {

		@SuppressLint("ShowToast")
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_diologCancel:
				dialog.dismiss();
				/*i++;
				if (i > 1) {
					dialog.dismiss();
				} else {
					Toast.makeText(getApplicationContext(), "双击取消", 2000)
							.show();
				}
				h.sendEmptyMessageDelayed(1, 2000);*/
				break;
			case R.id.btn_order:
				// 通知下料
				startActivityForResult(new Intent("capture").putExtra(
						WebServiceMethod.WEB_PARAM_ButtonType,
						ButtonType.BUTTON_TYPE_ORDER),
						Constant.REQUESTCODE_MAIN_ACTIVITY);
				break;
			case R.id.btn_outStore:
				// 出库
				startActivityForResult(new Intent("capture").putExtra(
						WebServiceMethod.WEB_PARAM_ButtonType,
						ButtonType.BUTTON_TYPE_OUT_STORAGE),
						Constant.REQUESTCODE_MAIN_ACTIVITY);
				break;

			case R.id.btn_distributionOver:
				// 完成配送
				startActivityForResult(new Intent("capture").putExtra(
						WebServiceMethod.WEB_PARAM_ButtonType,
						ButtonType.BUTTON_TYPE_DISTRUBUTION_OVER),
						Constant.REQUESTCODE_MAIN_ACTIVITY);
				break;

			case R.id.btn_toOrderlist:
				// 查看下料记录
				startActivity(new Intent("recordList").putExtra(
						Constant.actionBarTitle, "下料记录")
						.putExtra(Constant.WhichRecord,
								Constant.RECORD_TYPE_ORDER_RECORD));
				break;
			case R.id.btn_toOutStoreRecord:
				// 查看出库记录
				startActivity(new Intent("recordList").putExtra(
						Constant.actionBarTitle, "出库记录").putExtra(
						Constant.WhichRecord,
						Constant.RECORD_TYPE_OUT_STORAGE_RECORD));
				break;
				
			case R.id.img_cross:
				dialogError.dismiss();
				break;
			default:
				break;
			}
		}
	};

	Handler h = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				//i = 0;
				break;

			default:
				break;
			}
		};
	};

	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.main, menu);

		return true;

	};

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.actionbar_setting:
			startActivity(new Intent("setting"));
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
