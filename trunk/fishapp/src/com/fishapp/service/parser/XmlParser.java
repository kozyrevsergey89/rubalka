package com.fishapp.service.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.fishapp.FishappActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
		//preparing to write data to shared preferences
	    SharedPreferences settings = context.getSharedPreferences("wheaterPreference", Context.MODE_PRIVATE);
	    SharedPreferences.Editor prefEditor = settings.edit();
	    //use preferences to prefEditor.putString("TemperatureMax", temperatureMax);

	    while (!"FORECAST".equals(parser.getName()) && parser.getEventType() != XmlPullParser.START_TAG) {
	        parser.next();
	    }
	    //writing date to pref
	    prefEditor.putString("forecastDay", parser.getAttributeValue(null, "day"));
	    prefEditor.putString("forecastMonth", parser.getAttributeValue(null, "month"));
	    while (!"TEMPERATURE".equals(parser.getName()) && parser.getEventType() != XmlPullParser.START_TAG) {
	        parser.next();
	    }
	    prefEditor.putString("temperatureMax", parser.getAttributeValue(null, "max"));
	    prefEditor.putString("temperaturemin", parser.getAttributeValue(null, "min"));
	    
	    
	    
	    //sending intent extras back to activity
	    Intent toActivity = new Intent(context, FishappActivity.class);
	    toActivity.putExtra("weatherJob", "done" );
	    context.sendBroadcast(toActivity);
		}

	}
