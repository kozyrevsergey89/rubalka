package com.fishapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;

public class WebActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_web_activity_layout);
		String text = "text/html";
	    String utf = "UTF-8";

		Intent startingIntent = getIntent();
		
		ImageView actionBaView = (ImageView) findViewById(R.id.action_bar_logo);


		WebView recipeWebView = (WebView) findViewById(R.id.recipe_web_view);
		if (startingIntent.getExtras().containsKey("wiki")) {
	        actionBaView.setImageResource(R.drawable.enciclopedia_logo);
			recipeWebView.loadDataWithBaseURL("",parseHtmlFile(R.raw.wiki) , text, utf, "");
		} else {
			actionBaView.setImageResource(R.drawable.recepts_logo);
			String numberOfWebViewRecipe = startingIntent.getStringExtra("recipeNumber");
			if(numberOfWebViewRecipe.equals(FishAppRecipeActivity.FISH_BURGER)) {
				recipeWebView.loadDataWithBaseURL("",parseHtmlFile(R.raw.fish_bourger) , text, utf, "");
			} else if (numberOfWebViewRecipe.equals(FishAppRecipeActivity.FISH_SOUP)) {
				recipeWebView.loadDataWithBaseURL("",parseHtmlFile(R.raw.fish_soup) , text, utf, "");
			} else {
				recipeWebView.loadDataWithBaseURL("",parseHtmlFile(R.raw.goulyash) , text, utf, "");
			}
		}
	}
	
	
	private String parseHtmlFile(final int resource) {
	    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    int i;
	    try {
	      final InputStream inputStream = getResources().openRawResource(resource);
	      i = inputStream.read();
	      while (i != -1) {
	        outputStream.write(i);
	        i = inputStream.read();
	      }
	      inputStream.close();

	    } catch (final IOException e) {
	      Log.e("ERROR", e.getMessage());
	    }
	    return outputStream.toString();
	  }

}
