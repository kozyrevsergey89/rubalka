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
import android.widget.TextView;

public class FishappActivity extends Activity {
    /** Called when the activity is first created. */
	public String tempMax="0";
	public String tempMin="0";
	public int cloudiness=0;
	public int presipatation=0;
	private WhetherReceiver whetherReceiver;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weatherlayout);
        //starting intent-service
        ImageView actionBaView = (ImageView) findViewById(R.id.action_bar_logo);
        actionBaView.setImageResource(R.drawable.weather_logo);
    }
	
	@Override
	protected void onStart() {
		super.onStart();
		whetherReceiver = new WhetherReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("toActivity");
		registerReceiver(whetherReceiver, intentFilter);
        if (getConnInfo()){
        	//starting service with xml parsing
        	startService(new Intent(this, ParsingService.class));
        	
            
        }	
        getFromSharedPreferences();
        displayWeather();

	}
	
	private class WhetherReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context arg0, Intent intent) {
			// TODO Auto-generated method stub
			getFromSharedPreferences();
	        displayWeather();
		}}
	
	@Override
	protected void onStop() {
		if(whetherReceiver != null) {
			unregisterReceiver(whetherReceiver);
		}
		super.onStop();
	}
	
	public void displayWeather(){
		ImageView bigWeather = (ImageView) findViewById(R.id.bigweather);
		ImageView bigMoon = (ImageView) findViewById(R.id.bigmoon);
		ImageView smallWeather1 = (ImageView) findViewById(R.id.smallweather1);
		ImageView smallWeather2 = (ImageView) findViewById(R.id.smallweather2);
		ImageView smallWeather3 = (ImageView) findViewById(R.id.smallweather3);
		ImageView smallWeather4 = (ImageView) findViewById(R.id.smallweather4);
		TextView weatherInfo = (TextView) findViewById(R.id.weathertext);
		weatherInfo.setText(R.string.temperature_ad+"от "+tempMin+" до "+tempMax);
		if (cloudiness > 0){
			bigWeather.setImageLevel(2);
			smallWeather1.setImageLevel(2);
		}
		if (presipatation>0){
			bigWeather.setImageLevel(3);
			smallWeather1.setImageLevel(3);
		}
		Random randomGenerator = new Random();
		smallWeather2.setImageLevel(randomGenerator.nextInt(3));
		smallWeather3.setImageLevel(randomGenerator.nextInt(3));
		smallWeather4.setImageLevel(randomGenerator.nextInt(3));
		Integer x = new Integer(7);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH); 
		if (day < x){
			bigMoon.setImageLevel(0);
		}else if(day < 2*x){
			bigMoon.setImageLevel(1);
		}else if(day < 3*x){
			bigMoon.setImageLevel(2);
		}else{
			bigMoon.setImageLevel(3);
		}
	}
	
	private void getFromSharedPreferences(){
		SharedPreferences prefs = getSharedPreferences("wheaterPreference", MODE_PRIVATE);
		if (!prefs.getAll().isEmpty()){
			tempMax = prefs.getString("MAX", "not found");
			tempMin = prefs.getString("MIN", "not found");
			int def = 0;
			cloudiness = prefs.getInt("cloudiness", def);
			presipatation = prefs.getInt("precipitation", def);
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