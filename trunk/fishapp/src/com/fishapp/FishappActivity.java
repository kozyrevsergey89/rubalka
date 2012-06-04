package com.fishapp;

import com.fishapp.service.ParsingService;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.AbsListView.RecyclerListener;

public class FishappActivity extends Activity {
    /** Called when the activity is first created. */
	public String temperature;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //starting intent-service
        startService(new Intent(this, ParsingService.class));
        
        BroadcastReceiver sentSmsBroadcastCome = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                //Toast.makeText(context, "SMS SENT!!", Toast.LENGTH_SHORT).show();
            	if (intent.getExtras() != null){
            		temperature = intent.getStringExtra("weather"); 
            	}
            }
        };
        IntentFilter filterSend = new IntentFilter();
        filterSend.addAction("toActivity");
        registerReceiver(sentSmsBroadcastCome, filterSend);
        
    }
    
    
    
    
}