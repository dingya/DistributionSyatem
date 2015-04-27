package com.xtmit.distribution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hrw.framework.ahibernate.dao.AhibernateDao;
import com.xtmit.distribution.model.CacheRecordModel;
import com.xtmit.zxing.client.android.util.db.CacheRecordDBHelper;

public class DBManager {

	// private static AssetDatabaseOpenHelper dbHelper;
	/*
	 * private static SQLiteDatabase db; private static String DB_NAME="Record";
	 */
	private static Context mContext;

	private AhibernateDao<CacheRecordModel> dao;

	CacheRecordDBHelper dbHelper;
	private SQLiteDatabase sqlDB;

	private DBManager(Context context) {
		mContext = context;
		dbHelper = new CacheRecordDBHelper(mContext);
		sqlDB = dbHelper.getWritableDatabase();
		dao = new AhibernateDao<CacheRecordModel>(sqlDB);
	}

	public static DBManager instance(Context context) {

		return instance != null ? instance : new DBManager(context);
	}

	private static DBManager instance;

	public List<CacheRecordModel> getRecordByWhere(Map<String, String> where) {
		List<CacheRecordModel> models;
		models = dao.queryList(CacheRecordModel.class, where);
		//dao.
		return models;
	}

	public List<CacheRecordModel> getRecordByModel(CacheRecordModel where) {
		List<CacheRecordModel> models = new ArrayList<CacheRecordModel>();
		models = dao.queryList(where);
		
		return models;
	}
	
	public int insertToSql(CacheRecordModel model) {

		return dao.insert(model);

	}

	public void deleteRecord(Map<String, String> where) {
		dao.delete(CacheRecordModel.class, where);
	}

	public void updateRecord(CacheRecordModel model, Map<String, String> where) {
		dao.update(model, where);
	}

}
