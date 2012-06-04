package com.fishapp.service;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import com.fishapp.service.parser.XmlParser;

import android.app.IntentService;
import android.content.Intent;

public class ParsingService extends IntentService {

	public ParsingService(final String name) {
		super(name);
	}
	
	public ParsingService(){
		super("parsingService");
	} 

	@Override
	protected void onHandleIntent(final Intent intent) {
		XmlParser parser = new XmlParser(this);
		try {
			parser.parseXml("http://informer.gismeteo.ru/xml/33345_1.xml");
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
