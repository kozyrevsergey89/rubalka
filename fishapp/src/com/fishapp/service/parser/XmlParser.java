package com.fishapp.service.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.fishapp.FishappActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.util.Log;

public class XmlParser {
	
	
	
	public XmlParser(Context context){
		this.context=context;
	}
	
	public Context context;
	public String forecastDay,forecastMonth,temperatureMax,temperatureMin;
	
	public void parseXml(String weatherUrl) throws XmlPullParserException, IOException
	{
		
		      
			  XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		      XmlPullParser parser = factory.newPullParser();

		      InputStream in;
		      try {
		        URL u = new URL(weatherUrl);
		        in = u.openStream();
		      }
		      catch (MalformedURLException ex) {
		          // Maybe it's a file name :)
		          in = new FileInputStream(weatherUrl);
		      }
		      parser.setInput(in, null);

	    while (!"FORECAST".equals(parser.getName()) && parser.getEventType() != XmlPullParser.START_TAG) {
	        parser.next();
	    }
	    forecastDay = parser.getAttributeValue(null, "day");
	    forecastMonth = parser.getAttributeValue(null, "month");
	    Log.d("FORECAST date-->", forecastDay+forecastMonth);
	    while (!"TEMPERATURE".equals(parser.getName()) && parser.getEventType() != XmlPullParser.START_TAG) {
	        parser.next();
	    }
	    temperatureMax= parser.getAttributeValue(null, "max");
	    temperatureMin = parser.getAttributeValue(null, "min");
	    Log.d("Temperature max and min-->", "max = "+ temperatureMax+ "min = "+ temperatureMin);
	    
	    //writing weather data to shared preferences
	    
	    
	    //sending intent extras back to activity
	    Intent toActivity = new Intent(context, FishappActivity.class);
	    toActivity.putExtra("weather", temperatureMax );
	    context.sendBroadcast(toActivity);
	    
	    
	    
		}

	}
