package com.example.test;

 
import java.util.Timer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class AlarmService  extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	   Timer timer = new Timer();  
	   timer.schedule(new AlarmTimerTask(), 1000, 60000); 
	   return super.onStartCommand(intent, START_STICKY, startId);
	}

    
}
