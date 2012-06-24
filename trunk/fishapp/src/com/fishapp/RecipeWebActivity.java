package com.fishapp;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class RecipeWebActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.id.recipe_web_view);
		Intent startingIntent = getIntent();
		String numberOfWebViewRecipe = startingIntent.getStringExtra("recipeNumber");
		String webViewToStart = "recipe_web_view"+numberOfWebViewRecipe;
		WebView recipeWebView = (WebView) findViewById(R.id.recipe_web_view);
		recipeWebView.loadUrl(webViewToStart);
		
		
		
	}
	

}
