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
	/**���� �ļ��� key*/
	public static final String LOGIN_CACHE = "login_cache";
	/**���� �û��� key*/
	public static final String CACHE_NAME = "cache_name";
	/**���� ���� MD5 key*/
	public static final String CACHE_PASS = "cache_pass";

	/**�û�����  5-������*/
	public static final int userType = 5;
	/**��˾id 3-������*/
	public static final int companyId = 3;
	
	/**ע�� �¼���ǰ׺*/
	public static final String PRE_ACTION = "action_";
	public static final String FAILED_RESION = "failed_resion";
	
	public static Gson gson= new Gson();
	public static CompanyUserModel gloableUserModel;
	
	
	//extra �� ����key
	public static final String actionBarTitle = "actionBarTitle";
	public static final String WhichRecord = "WhichRecord";
 
	public static final String tableName_OrderList = "table_Record";
	public static final String DB_NAME = "Distribute_DB_Record";
 	/**
	 * webService ����������
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
		
		/** ɨ�赽�� ��ά����Ϣ����ά���ţ�*/
		public static final String WEB_PARAM_MaterialQRNum="MaterialQRNum";
		
		/** ��ť����
		 * 1 ���� {@link ButtonType}  2 ���� 3 ���
		 */
		public static final String WEB_PARAM_ButtonType="ButtonType";
		/**
		 * �ı� ����״̬
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
		 * �������ϵ�״̬ 20 Ϊ30;
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

		/**���ϵ���*/
		public static final String KEY_MaterialResquitionNum = "";
		/**��������*/
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
		
		//������ ���� ������ ��һ�������� ���µ�
		//�µ��� ��˭ �������ҵ�����
		public static final String KEY_TrueNameHandler = "TrueNameHandler";
		
		/***/
		/***/
		/***/
		/***/
		/***/
		
	}
}
