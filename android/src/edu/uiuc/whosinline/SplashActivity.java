package edu.uiuc.whosinline;

import android.os.Bundle;
import android.os.Handler;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashActivity extends Activity {

	private final int SPLASH_DURATION = 1000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		// hide the action bar
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		// wait here for half a second
		new Handler().postDelayed(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent i = new Intent(SplashActivity.this, HomeActivity.class);
				startActivity(i);
				finish();
			}
			
		}, SPLASH_DURATION);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
