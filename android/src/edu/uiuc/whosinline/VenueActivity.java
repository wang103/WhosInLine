package edu.uiuc.whosinline;

import edu.uiuc.whosinline.database.DatabaseAccessObj;
import edu.uiuc.whosinline.data.Venue;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class VenueActivity extends Activity {

	private DatabaseAccessObj dbAccessObj;
	private Venue venue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Get content from intent.
		Bundle extras = getIntent().getExtras();
		int tableNum = extras.getInt(HomeActivity.INTENT_TABLE_NUM);
		long venueId = extras.getLong(HomeActivity.INTENT_VENUE_ID);
		
		// Get the database object.
		dbAccessObj = new DatabaseAccessObj(this);
		dbAccessObj.open();
		
		// Get all the info about this venue.
		venue = dbAccessObj.getVenue(tableNum, venueId);
		
		// Set the name of the venue as the title of the action bar.
		setTitle(venue.getName());
		
		// Give the action bar a back button.
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
	
	@Override
	public void onResume() {
		dbAccessObj.open();
		super.onResume();
	}
	
	@Override
	public void onPause() {
		dbAccessObj.close();
		super.onPause();
	}
}