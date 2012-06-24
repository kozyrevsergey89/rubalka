package com.fishapp;

import java.util.Calendar;
import java.util.Random;

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
import android.widget.ImageView;

public class FishappActivity extends Activity {
    /** Called when the activity is first created. */
	public String temperature="0";
	public String cloudiness="0";
	public String presipatation="0";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weatherlayout);
        //starting intent-service
        
        BroadcastReceiver sentSmsBroadcastCome = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
            	if ((intent.getExtras() != null)&&(intent.getStringExtra("weatherJob")=="done")){
            		getFromSharedPreferences();
            		displayWeather();
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
        }	
        getFromSharedPreferences();
        displayWeather();
        
        
    }
	
	private void displayWeather(){
		ImageView bigWeather = (ImageView) findViewById(R.id.bigweather);
		ImageView bigMoon = (ImageView) findViewById(R.id.bigmoon);
		ImageView smallWeather1 = (ImageView) findViewById(R.id.smallweather1);
		ImageView smallWeather2 = (ImageView) findViewById(R.id.smallweather2);
		ImageView smallWeather3 = (ImageView) findViewById(R.id.smallweather3);
		ImageView smallWeather4 = (ImageView) findViewById(R.id.smallweather4);
		if (Integer.parseInt(cloudiness) > 0){
			bigWeather.setImageLevel(2);
			smallWeather1.setImageLevel(2);
		}
		if (Integer.parseInt(presipatation)>0){
			bigWeather.setImageLevel(3);
			smallWeather1.setImageLevel(3);
		}
		Random randomGenerator = new Random();
		smallWeather2.setImageLevel(randomGenerator.nextInt(3));
		smallWeather3.setImageLevel(randomGenerator.nextInt(3));
		smallWeather4.setImageLevel(randomGenerator.nextInt(3));
		Calendar rightNow = Calendar.getInstance();
		int x = 7;
		if (rightNow.getTime().getDay() < x){
			bigMoon.setImageLevel(0);
		}else if(rightNow.getTime().getDay() < 2*x){
			bigMoon.setImageLevel(1);
		}else if(rightNow.getTime().getDay() < 3*x){
			bigMoon.setImageLevel(2);
		}else{
			bigMoon.setImageLevel(3);
		}
		
		
		
	}
	
	private void getFromSharedPreferences(){
		
		SharedPreferences prefs = getSharedPreferences("wheaterPreference", MODE_PRIVATE);
		if (!prefs.getAll().isEmpty()){
			temperature = prefs.getString("TemperatureMax", "not found");
			cloudiness = prefs.getString("cloudiness", "not found");
			presipatation = prefs.getString("presipatation", "not found");
			
		}
		
		
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