package edu.uiuc.whosinline;

import edu.uiuc.whosinline.database.DatabaseAccessObj;
import edu.uiuc.whosinline.data.Venue;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class VenueActivity extends Activity {

	protected DatabaseAccessObj dbAccessObj;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// get content from intent
		Bundle extras = getIntent().getExtras();
		int venue_id = extras.getInt("current_venue_id");
		// get the database object
		dbAccessObj = new DatabaseAccessObj(this);
		dbAccessObj.open();
		// here, call the the get_veneue method to get all the info about this venue (doesn't work yet)
		Venue venue = dbAccessObj.getVenue(venue_id);
		// set the name of the venue as the title of the action bar
		setTitle(venue.getName());
		// give the action bar a back button
		ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_venue);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.venue, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()){
	    	// back button press
	        case android.R.id.home:
	        	finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
