package com.xtmit.zxing.client.android.util.db;

import com.hrw.framework.ahibernate.table.TableUtils;
import com.xtmit.distribution.model.CacheRecordModel;
import com.xtmit.zxing.client.android.constans.Constant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CacheRecordDBHelper extends SQLiteOpenHelper {

	static String DB_NAME=Constant.DB_NAME;
	public CacheRecordDBHelper(Context context) {
		super(context, DB_NAME, null, 1);
 	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		TableUtils.createTable(db, true, CacheRecordModel.class );
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		TableUtils.dropTable(db, CacheRecordModel.class );
		onCreate(db);
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
	}
}
