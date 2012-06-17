package com.fishapp.service;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import com.fishapp.R;
import com.fishapp.service.parser.XmlParser;

import android.app.IntentService;
import android.content.Intent;

public class ParsingService extends IntentService {

	public ParsingService(){
		super("parsingService");
	} 

	@Override
	protected void onHandleIntent(final Intent intent) {
		XmlParser parser = new XmlParser(this);
		try {
			parser.parseXml(getString(R.string.weather_url));
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
