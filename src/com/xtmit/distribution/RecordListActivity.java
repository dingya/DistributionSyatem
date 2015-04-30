package com.xtmit.distribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xtmit.distribution.app.MyApplication;
import com.xtmit.distribution.model.CacheRecordModel;
import com.xtmit.distribution.model.ResultModel;
import com.xtmit.distribution.model.ResultType;
import com.xtmit.distribution.webservice.WebService;
import com.xtmit.distributionsystem.R;
import com.xtmit.zxing.client.android.constans.Constant;
import com.xtmit.zxing.client.android.constans.Constant.WebServiceMethod;

public class RecordListActivity extends BaseActivity {

	PullToRefreshListView pullRefreshList;
	private BaseAdapter adapter;
	private List<CacheRecordModel> listRcord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_record);
		pullRefreshList = (PullToRefreshListView) findViewById(R.id.listView_needDistribution);
		
		String actionBarTitle = getIntent().getStringExtra(
				Constant.actionBarTitle);
		TextView title = (TextView) getActionBar().getCustomView()
				.findViewById(R.id.actionbar_title);
		title.setText(actionBarTitle);
		TextView tv_back = (TextView) getActionBar().getCustomView().findViewById(R.id.actionbar_back);
		tv_back.setVisibility(View.VISIBLE);
		tv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		//pullRefreshList.setMode(Mode.BOTH);
		
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 
		int record = getIntent().getIntExtra(Constant.WhichRecord,
				Constant.RECORD_TYPE_ORDER_RECORD);
		listRcord = initData(record);
		listRcord = UpdateSql(listRcord,record);
		
		
		// getList();
		if (listRcord != null) {
			adapter = new RecordAdapter( this,listRcord);
			pullRefreshList.setAdapter(adapter);
		}
		pullRefreshList.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				RecordListActivity.this.onResume();
				//pullRefreshList.onRefreshComplete(); 
				//adapter.notifyDataSetChanged();
				//pullRefreshList.setRefreshing();
				 new FinishRefresh().execute();
				adapter.notifyDataSetInvalidated();
			}
		} );

	}
	
	 private class FinishRefresh extends AsyncTask<Void, Void, Void>{    
	        @Override    
	        protected Void doInBackground(Void... params) {    
	             try {    
	                 Thread.sleep(1500);    
	             } catch (InterruptedException e) {    
	             }    
	            return null;    
	        }    
	     
	        @Override    
	        protected void onPostExecute(Void result){    
//	          adapter.notifyDataSetChanged();  
	        	pullRefreshList.onRefreshComplete();    
	        }    
	    }   

	private List<CacheRecordModel> UpdateSql(List<CacheRecordModel> listRcord2,int recordType) {
		LinkedHashMap<String, Object> datas = null;
		List<String> MQnum =new ArrayList<String>();
		List<CacheRecordModel> collection = new ArrayList<CacheRecordModel>();
		for (CacheRecordModel cacheRecordModel : listRcord2) {
			datas = new LinkedHashMap<>();
			datas.put(WebServiceMethod.WEB_PARAM_companyID, Constant.gloableUserModel.getCompanyID());
			datas.put(WebServiceMethod.WEB_PARAM_userID, Constant.gloableUserModel.getCompanyUserID());
			datas.put(WebServiceMethod.WEB_PARAM_RunningNum, cacheRecordModel.getRunningNumber());
			datas.put(WebServiceMethod.WEB_PARAM_ProductionLineID, cacheRecordModel.getProductionId());
			datas.put(WebServiceMethod.WEB_PARAM_MQStatus, recordType==Constant.RECORD_TYPE_ORDER_RECORD?30:40);
			datas.put(WebServiceMethod.WEB_PARAM_MaterialRequisitionNum, cacheRecordModel.getMaterialResquitionNum());
			JSONObject json = WebService.stratWebServices(WebServiceMethod.WEB_MOTHED_getMyOrderAlreadyOutStorage, datas);
			if (json==null) {
				return null;
			}
			ResultModel resultModel=Constant.gson.fromJson(json.toString(), ResultModel.class);
			if (resultModel==null) {
				return null;
			}
			if(resultModel.getResult().equals(ResultType.Success.name())){
				MQnum.add(cacheRecordModel.getMaterialResquitionNum());
				collection.add(cacheRecordModel);
			}
		}
 		
		for (String string : MQnum) {
			Map<String, String> where= new LinkedHashMap<String, String>();
			where.put("MaterialResquitionNum", string);
			where.put("userID", String.valueOf(Constant.gloableUserModel.getCompanyUserID()));
			where.put("recordType", String.valueOf(recordType));
			MyApplication.dbManager.deleteRecord(where);
		}
		listRcord2.removeAll(collection);
		//我的下料 有没有被出库；出库 则删掉 我的 记录;
		//我的出库有没有被配送完成，完成则删掉
		return listRcord2;
	}

	private List<CacheRecordModel> initData(int record) {
			Map<String, String> where = new HashMap<String, String>();  
			where.put("recordType", record+ "");
		return MyApplication.dbManager.getRecordByWhere(where); 
	}
	
	
	private class RecordAdapter extends BaseAdapter{
		private Context mContext;
		private List<CacheRecordModel> mlist;

		public RecordAdapter(Context recordListActivity,
				List<CacheRecordModel> plistRcord) {
			// TODO Auto-generated constructor stub
			mContext = recordListActivity;
			mlist=plistRcord;
		}

		@Override
		public View getView(int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder vh = null;
			if (convertView == null) {
				convertView = View.inflate(mContext,
						R.layout.item_list_record, null);
				vh = new ViewHolder();
				vh.tv_materialReqNum = (TextView) convertView
						.findViewById(R.id.tv_MaterialRequistionNum);
				vh.tv_ProductLineName = (TextView) convertView
						.findViewById(R.id.tv_productionName);
				vh.tv_time = (TextView) convertView
						.findViewById(R.id.tv_time);
				convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}

			vh.tv_materialReqNum.setText(listRcord.get(position)
					.getMaterialResquitionNum());
			vh.tv_ProductLineName.setText(listRcord.get(position)
					.getProductionName());
			vh.tv_time.setText(listRcord.get(position).getTime());
			return convertView;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mlist.size();
		}

		class ViewHolder {
			public TextView tv_time, tv_materialReqNum,tv_ProductLineName;
		}
		
	}
}
