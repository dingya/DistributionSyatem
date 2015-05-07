package com.xtmit.zxing.client.android.constans;

import com.google.gson.Gson;
import com.xtmit.distribution.model.CompanyUserModel;

public class Constant {
	public static final int RECORD_TYPE_ORDER_RECORD=1;
	public static final int RECORD_TYPE_OUT_STORAGE_RECORD=2;
	
	
	public static final String SCAN_RESUALT_TEXT = "scan_resualt_text";
	public static final int RESULTCODE_CAPTURE_ACTIVITY = 0x111;
	public static final int REQUESTCODE_MAIN_ACTIVITY = 0x110;
	public static final String NameSpace = "http://service.ws.cxf.com/";
	public static final String WSDL = "http://218.4.217.58:59999/CXF-WS-Server";
	public static final String WSDLTest = "http://192.168.1.100:9999/CXF-WS-Server";
	/**缓存 文件名 key*/
	public static final String LOGIN_CACHE = "login_cache";
	/**缓存 用户名 key*/
	public static final String CACHE_NAME = "cache_name";
	/**缓存 密码 MD5 key*/
	public static final String CACHE_PASS = "cache_pass";

	/**用户类型  5-领料人*/
	public static final int userType = 5;
	/**公司id 3-法克赛*/
	public static final int companyId = 3;
	
	/**注册 事件的前缀*/
	public static final String PRE_ACTION = "action_";
	public static final String FAILED_RESION = "failed_resion";
	
	public static Gson gson= new Gson();
	public static CompanyUserModel gloableUserModel;
	
	
	//extra 的 各种key
	public static final String actionBarTitle = "actionBarTitle";
	public static final String WhichRecord = "WhichRecord";
 
	public static final String tableName_OrderList = "table_Record";
	public static final String DB_NAME = "Distribute_DB_Record";
 	/**
	 * webService 方法、参数
	 */
	public static class WebServiceMethod{
		/**
		 * Api login;
		 */
		public static final String WEB_MOTHED_LoginByDistribution="LoginByDistribution";
		public static final String WEB_PARAM_userName="userName";
		public static final String WEB_PARAM_pwd="pwd";
		public static final String WEB_PARAM_companyID="CompanyID";
		public static final String WEB_PARAM_userType="userType";
		
		/**
		 * Api check is have this QR in sql;
		 */
		public static final String WEB_MOTHED_scanQRMakeSureIsHaveMaterialNum = "scanQRMakeSureIsHaveMaterialNum";
		
		/** 扫描到的 二维码信息（二维码编号）*/
		public static final String WEB_PARAM_MaterialQRNum="MaterialQRNum";
		
		/** 按钮类型
		 * 1 下料 {@link ButtonType}  2 出库 3 完成
		 */
		public static final String WEB_PARAM_ButtonType="ButtonType";
		/**
		 * 改变 产线状态
		 */
		public static final String WEB_MOTHED_changeProductionLineStatus = "changeProductionLineStatus";
		public static final String WEB_PARAM_ProductionLineNum = "ProductionLineNum";
		public static final String WEB_PARAM_ProductionLineID = "ProductionLineID";
		public static final String WEB_PARAM_status = "status";
		public static final String WEB_MOTHED_getGoodsModel = "getGoodsModel";
		public static final String WEB_PARAM_BomNum = "BomNum";
		public static final String WEB_MOTHED_GenerateMR = "GenerateMR";
		public static final String WEB_PARAM_MaterialRequisitionModel = "MaterialRequisitionModel";
		
		/**
		 * 更新领料单状态 20 为30;
		 */
		public static final String WEB_MOTHED_updateMaterialRequisationStatus = "updateMaterialRequisationStatus";
		public static final String WEB_PARAM_RunningNum = "RunningNum";
		public static final String WEB_PARAM_userID = "userID";
		public static final String WEB_PARAM_trueName = "trueName";
		public static final String WEB_MOTHED_updateMaterialRequisationStatusDistributionOver = "updateMaterialRequisationStatusDistributionOver";
		public static final String WEB_MOTHED_getMyOrderAlreadyOutStorage = "getMyOrderAlreadyOutStorage";
		public static final String WEB_PARAM_MQStatus = "MQStatus";
		public static final String WEB_MOTHED_modifypassword = "updateUserPwd";
		public static final String WEB_PARAM_ModifycompanyID="companyID";
		public static final String WEB_PARAM_CompanyUserPassword="CompanyUserPassword";
		public static final String WEB_PARAM_CompanyUserID = "CompanyUserID";
		public static final String WEB_PARAM_MaterialRequisitionNum = "MaterialRequisitionNum";
	}
	
	
	public static class DBConstant{

		/**领料单号*/
		public static final String KEY_MaterialResquitionNum = "";
		/**产线名称*/
		public static final String KEY_ProductionName = "ProductionName";
		/**id*/
		public static final String KEY_ProductionId = "ProductionId";
		/***/
		public static final String KEY_MaterialName = "MaterialName";
		public static final String KEY_RunningNumber = "RunningNumber";
		public static final String KEY_ProductionLineStatus = "ProductionLineStatus";
		public static final String KEY_userTrueName = "userTrueName";
		public static final String KEY_userID = "userID";
		public static final String KEY_userName = "userName";
		public static final String KEY_time = "time";
		
		//出库者 ：我 处理了 哪一个下料人 的下单
		//下单这 ：谁 出库了我的下料
		public static final String KEY_TrueNameHandler = "TrueNameHandler";
		
		/***/
		/***/
		/***/
		/***/
		/***/
		
	}
}
