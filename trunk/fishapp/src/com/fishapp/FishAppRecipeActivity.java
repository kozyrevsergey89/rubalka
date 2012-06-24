package com.fishapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FishAppRecipeActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	
	public static final String GOULYASH = "goulyash";
	public static final String FISH_SOUP = "fish_soup";
	public static final String FISH_BURGER = "fish_burger";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fish_app_recipe_layout);
		
		TextView logo = (TextView) findViewById(R.id.action_bar_logo);
		logo.setText(getString(R.string.cook_book));
		final Button button1 = (Button) findViewById(R.id.recipebutton1);
		final Button button2 = (Button) findViewById(R.id.recipebutton2);
		final Button button3 = (Button) findViewById(R.id.recipebutton3);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		 
	}

	@Override
	public void onClick(View v) {
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
	}
}