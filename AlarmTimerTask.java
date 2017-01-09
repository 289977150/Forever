package com.example.test;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.telephony.SmsManager;
import android.text.format.Time;
import android.util.Log;

public class AlarmTimerTask extends java.util.TimerTask{

	public static DatabaseHelper mOpenHelper;
	public static final String DATABASE_NAME = "dbForTest.db";
	public static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "diary";
	public static final String TITLE = "title";
	public static final String BODY = "body";
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			String sql = "CREATE TABLE  if not exists " + TABLE_NAME + " (" + TITLE
					+ " text not null, " + BODY + " text not null, Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP " + ");";
			
			db.execSQL(sql);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}

	private static void insertItem() {
		if(mOpenHelper == null)
			mOpenHelper = new DatabaseHelper(null);
		
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		String sql = "insert into " + TABLE_NAME + " (" + TITLE + ", " + BODY
				+ ") values('this is title', 'this is body');";
 
		try {
	 
			db.execSQL(sql);
 
			 
		} catch (SQLException e) 
		{
			 Log.i("", "");
		}
	}
 
	
	@Override
	public void run() {
		
		try {
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/mnt/sdcard/books/" + DATABASE_NAME, null); 
		String sql = "CREATE TABLE  if not exists " + TABLE_NAME + " (" + TITLE
				+ " text not null, " + BODY + " text not null, Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP " + ");";
		
		db.execSQL(sql); 
		sql = "insert into " + TABLE_NAME + " (" + TITLE + ", " + BODY
				+ ") values('this is title', 'this is body');";
		db.execSQL(sql); 
		db.close();
		Log.i("", "");
		//insertItem();
		}
		catch(Exception e)
		{
			Log.i("", "");
		}

	}  

}
