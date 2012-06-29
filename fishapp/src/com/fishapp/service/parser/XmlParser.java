package com.fishapp.service.parser;

import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;
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
		String min = null;
	    String max = null;
	    String pheno = null;
	    String cloud = null;
	    int cloudiness=0;
	    int precipitation=0;
		
	    //preparing to write data to shared preferences
	    SharedPreferences settings = context.getSharedPreferences("wheaterPreference", Context.MODE_PRIVATE);
	    SharedPreferences.Editor prefEditor = settings.edit();
	    
	    try {

	        final URL currentUrl = new URL(weatherUrl);

	        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        final DocumentBuilder builder = factory.newDocumentBuilder();

	        final Document doc = builder.parse(new InputSource(currentUrl.openStream()));
	        doc.getDocumentElement().normalize();

	        final NodeList nodeList = doc.getElementsByTagName("TEMPERATURE");
	        final Node node = nodeList.item(2);

	        final NodeList phenoList = doc.getElementsByTagName("PHENOMENA");
	        final Node pheNode = phenoList.item(2);

	        final NamedNodeMap attribute = node.getAttributes();
	        final Node value = attribute.getNamedItem("min");

	        final NamedNodeMap map = pheNode.getAttributes();
	        final Node mapVal = map.getNamedItem("cloudiness");

	        min = value.getNodeValue();
	        cloud = mapVal.getNodeValue();

	        final NamedNodeMap maxAttr = node.getAttributes();
	        final Node maxValue = maxAttr.getNamedItem("max");

	        final NamedNodeMap pre = pheNode.getAttributes();
	        final Node preVal = pre.getNamedItem("precipitation");

	        max = maxValue.getNodeValue();
	        pheno = preVal.getNodeValue();

	        cloudiness = Integer.parseInt(cloud);
	        precipitation = Integer.parseInt(pheno);
	        
	      } catch (final ParserConfigurationException e) {
	        Log.e("XMLParser", "parser exception", e);
	      } catch (final SAXException e) {
	        Log.e("XMLParser", "SAXException", e);
	      } catch (final IOException e) {
	        Log.e("XMLParser", "IOException", e);
	      }
	    
	    //putting data to shared prefs
	    
	    prefEditor.putString("MAX", max);
	    prefEditor.putString("MIN", min);
	    prefEditor.putInt("cloudiness", cloudiness);
	    prefEditor.putInt("precipitation", precipitation);
	    prefEditor.commit();
	    
	    
	    //sending intent extras back to activity
	    Intent toActivity = new Intent(context, FishappActivity.class);
	    toActivity.setAction("toActivity");
	    context.sendBroadcast(toActivity);
		}

	}
