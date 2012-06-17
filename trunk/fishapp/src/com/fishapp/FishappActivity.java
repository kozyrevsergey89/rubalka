package com.fishapp;

import com.fishapp.service.ParsingService;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

public class FishappActivity extends Activity {
    /** Called when the activity is first created. */
	public String temperature;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //starting intent-service
        
        BroadcastReceiver sentSmsBroadcastCome = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
            	if ((intent.getExtras() != null)&&(intent.getStringExtra("weatherJob")=="done")){
            		//displayWeather(); TO DO
            		getFromSharedPreferences(); 
            	}
            }
        };
        
        if (getConnInfo()){
        	//starting service with xml parsing
        	startService(new Intent(this, ParsingService.class));
            //registering receiver
        	IntentFilter filterSend = new IntentFilter();
            filterSend.addAction("toActivity");
            registerReceiver(sentSmsBroadcastCome, filterSend);
        	//SharedPreferences settings = getSharedPreferences("wheaterPreference", MODE_PRIVATE);
    	    //temperature = settings.getString("TemperatureMax", "not found");
        }else{
        	//displayWeather();
        	getFromSharedPreferences();
        }
    }
	
	private void displayWeather(){
		
	}
	
	private void getFromSharedPreferences(){
		SharedPreferences prefs = getSharedPreferences("wheaterPreference", MODE_PRIVATE);
		temperature = prefs.getString("TemperatureMax", "not found");
	}
	
	public Boolean getConnInfo(){
        ConnectivityManager conMgr =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo i = conMgr.getActiveNetworkInfo();
		  if (i == null)
		    return false;
		  if (!i.isConnected())
		    return false;
		  if (!i.isAvailable())
		    return false;
		  return true;
		
	}
    
}