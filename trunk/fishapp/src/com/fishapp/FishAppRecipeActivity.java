package com.fishapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FishAppRecipeActivity extends Activity {
    /** Called when the activity is first created. */
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fish_app_recipe_layout);
		
		TextView logo = (TextView) findViewById(R.id.action_bar_logo);
		logo.setText(getString(R.string.cook_book));
		final Button button1 = (Button) findViewById(R.id.recipebutton1);
		final Button button2 = (Button) findViewById(R.id.recipebutton2);
		final Button button3 = (Button) findViewById(R.id.recipebutton3);
		 
		
		Button.OnClickListener btnclick = new Button.OnClickListener(){

		    @Override
		    public void onClick(View v) {
		        // TODO Auto-generated method stub

		        Button button = (Button)v;
		        Intent startRecipeWebActivity = new Intent(getBaseContext(), RecipeWebActivity.class);
		        startRecipeWebActivity.putExtra("recipeNumber", button.getText().charAt(button.getText().length()));
		        startActivity(startRecipeWebActivity);
		        
		    //Toast.makeText(getApplicationContext(), button.getText().toString(),2).show();  
		    }

		};
		}
	}