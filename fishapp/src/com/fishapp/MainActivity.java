package com.fishapp;

import com.fishapp.calendar.CalendarActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button weather, calendar, cook_book, wiki;
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		weather = (Button) findViewById(R.id.weather);
		calendar = (Button) findViewById(R.id.calendar);
		cook_book = (Button) findViewById(R.id.cook_book);
		wiki = (Button) findViewById(R.id.wiki);
		
		weather.setOnClickListener(this);
		calendar.setOnClickListener(this);
		cook_book.setOnClickListener(this);
		wiki.setOnClickListener(this);
	}

	@Override
	public void onClick(final View v) {
		switch (v.getId()) {
		case R.id.weather:
			startActivity(new Intent(this, FishappActivity.class));
			break;
		case R.id.calendar:
			startActivity(new Intent(Intent.ACTION_VIEW).setDataAndType(null, com.fishapp.calendar.CalendarActivity.MIME_TYPE));
			//Toast.makeText(this, getString(R.string.calendar_toast), Toast.LENGTH_SHORT).show();
			break;
		case R.id.cook_book:
			startActivity(new Intent(this, FishAppRecipeActivity.class));
			break;
		case R.id.wiki:
			Intent intent = new Intent(this, WebActivity.class);
			intent.putExtra("wiki", "wiki");
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
}
