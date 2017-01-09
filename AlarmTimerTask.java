package com.example.test;

import java.io.File;
import java.util.TimerTask;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.os.Environment;
import android.util.Log;

public class AlarmTimerTask extends TimerTask {

	public static final String DATABASE_NAME = "Hui.db";
	public static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "HuiTable";
	public static final String TITLE = "Latitude";
	public static final String BODY = "Longitude";
	private Context mContext;
	public LocationTimerTask(Context mContext) {
	       this.mContext = mContext;
	 }

	@Override
	public void run() {
		
		try {
			String path = Environment.getExternalStorageDirectory().getPath() + "/Hui/";
			File dir = new File(path);
			if(dir.exists() == false)
				dir.mkdir();
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path + DATABASE_NAME, null); 
			String sql = "CREATE TABLE  if not exists " + TABLE_NAME + " (" + TITLE
					+ " text not null, " + BODY + " text not null, Timestamp DATETIME DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime')) " + ");";
			
			db.execSQL(sql); 
			Location loc = this.getLocation();
			if(loc != null)
			{
				sql = "insert into " + TABLE_NAME + " (" + TITLE + ", " + BODY
						+ ") values('" + loc.getLatitude() + "', '" + loc.getLongitude() + "');";
				db.execSQL(sql); 
			}
			db.close();
 
		}
		catch(Exception e)
		{
			Log.i("LocationTimerTask", e.toString());
		}

	}  
	
	public Location getLocation() {
	    LocationManager locationManager = (LocationManager) this.mContext.getSystemService(Context.LOCATION_SERVICE);        
	    if (locationManager != null) {          
	        Location lastKnownLocationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	        if (lastKnownLocationGPS != null) {             
	            return lastKnownLocationGPS;
	        } else {                
	            Location loc =  locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
	            return loc;
	        }
	    } else {            
	        return null;
	    }
	}

}

