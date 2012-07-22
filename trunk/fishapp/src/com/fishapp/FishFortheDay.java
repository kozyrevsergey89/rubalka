package com.fishapp;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class FishFortheDay extends Activity implements OnClickListener {
	
	public Button button1, button2, button3, button4, button5, button6, button7;
	
	/*public static final String FISH1 = "karas";
	public static final String FISH2 = "karp";
	public static final String FISH3 = "shuka";*/
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fish_for_the_day_layout);
		
		ImageView actionBaView = (ImageView) findViewById(R.id.action_bar_logo);
        actionBaView.setImageResource(R.drawable.logo);
        
		button1 = (Button) findViewById(R.id.recipebutton1);
		button2 = (Button) findViewById(R.id.recipebutton2);
		button3 = (Button) findViewById(R.id.recipebutton3);
		button4 = (Button) findViewById(R.id.recipebutton4);
		button5 = (Button) findViewById(R.id.recipebutton5);
		button6 = (Button) findViewById(R.id.recipebutton6);
		button7 = (Button) findViewById(R.id.recipebutton7);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
		button6.setOnClickListener(this);
		button7.setOnClickListener(this);
		
		//getting extras (date) from intent
		//
		//
		//setting percentage per fish based on WTF???
		setPercantage();
		 
	}
	
	private void setPercantage(){
		//logic emulation
		int min = 65;
		int max = 80;
		Random r = new Random();
		button1.append("   -   "+ new Integer(r.nextInt(max - min + 1) + min).toString()+" %");
		button2.append("   -   "+ new Integer(r.nextInt(max - min + 1) + min).toString()+" %");
		button3.append("   -   "+ new Integer(r.nextInt(max - min + 1) + min).toString()+" %");
		button4.append("   -   "+ new Integer(r.nextInt(max - min + 1) + min).toString()+" %");
		button5.append("   -   "+ new Integer(r.nextInt(max - min + 1) + min).toString()+" %");
		button6.append("   -   "+ new Integer(r.nextInt(max - min + 1) + min).toString()+" %");
		button7.append("   -   "+ new Integer(r.nextInt(max - min + 1) + min).toString()+" %");
	}

	@Override
	public void onClick(View v) {
		//Toast.makeText(this, "тут выводим инфу из либы по рыбкам", Toast.LENGTH_SHORT);
		/*
        Intent startRecipeWebActivity = new Intent(getBaseContext(), WebActivity.class);
        switch (v.getId()) {
		case R.id.recipebutton1:
			startRecipeWebActivity.putExtra("recipeNumber", FISH_SOUP);
			break;
		case R.id.recipebutton2:
			startRecipeWebActivity.putExtra("recipeNumber", FISH_BURGER);
			break;
		case R.id.recipebutton3:
			startRecipeWebActivity.putExtra("recipeNumber", GOULYASH);
			break;	
		default:
			break;
		}
        startActivity(startRecipeWebActivity);
	*/
		 Intent startRecipeWebActivity = new Intent(getBaseContext(), WebActivity.class);
		 startRecipeWebActivity.putExtra("wiki", "wiki");
		 startActivity(startRecipeWebActivity);
	}

}
