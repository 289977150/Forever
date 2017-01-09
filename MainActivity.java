package com.example.test;



import java.util.ArrayList;




import android.app.Activity;
import android.app.ActivityManager;
 
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
 


public class MainActivity extends Activity {

	public static Context context;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  
        
        context = MainActivity.this;


        
		 
		if (!isServiceWorked("com.example.test.AlarmService")) {
			Intent intent = new Intent(MainActivity.context,
					AlarmService.class);
			MainActivity.context.startService(intent);
		}
  
        
    }

	public boolean isServiceWorked(String serviceName) {
		//Context context = MainActivity.this;
		ActivityManager myManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		ArrayList<RunningServiceInfo> runningService = (ArrayList<RunningServiceInfo>) myManager
				.getRunningServices(30);
		for (int i = 0; i < runningService.size(); i++) {
			if (runningService.get(i).service.getClassName().toString()
					.equals(serviceName)) {
				return true;
			}
		}
		return false;
	}
    

}
