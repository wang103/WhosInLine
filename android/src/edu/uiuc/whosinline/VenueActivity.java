package edu.uiuc.whosinline;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.uiuc.whosinline.database.DatabaseAccessObjReview;
import edu.uiuc.whosinline.database.DatabaseAccessObjVenue;
import edu.uiuc.whosinline.data.Review;
import edu.uiuc.whosinline.data.Venue;
import edu.uiuc.whosinline.listeners.MenuItemListener;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class VenueActivity extends Activity {

	private DatabaseAccessObjVenue dbAccessObjVenue;
	private DatabaseAccessObjReview dbAccessObjReview;
	private Venue venue;
	private int tableNum;

	private void insertTestReviewData() {
		Review review;
		
		review = new Review(0, "Cravings Restaurant", "Good", "Yes, yes, yes! These people aren't goofing around! Although this restaurant is lacking in atmosphere, this is fully made up for in quality and quantity of food. It is by far the best tasting Chinese I have had on campus, for a bargain deal too! Bang for your buck, get some takeout and give it  a try.", 5.0f, "Daniel R.");
		dbAccessObjReview.insertReview(review);
		
		review = new Review(0, "Cravings Restaurant", "Great", "Cravings really caters to poor college students--the prices are cheap and the portions are decently sized. And it also helps that the food is actually good here.", 4.0f, "Connie L.");
		dbAccessObjReview.insertReview(review);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venue);

		// Get content from intent.
		Bundle extras = getIntent().getExtras();
		tableNum = extras.getInt(HomeActivity.INTENT_TABLE_NUM);
		long venueId = extras.getLong(HomeActivity.INTENT_VENUE_ID);

		// Get the database object.
		dbAccessObjVenue = new DatabaseAccessObjVenue(this);
		dbAccessObjVenue.open();
		
		dbAccessObjReview = new DatabaseAccessObjReview(this);
		dbAccessObjReview.open();

		if (dbAccessObjReview.getTableRowCount() == 0) {
			insertTestReviewData();
		}
		
		// Get all the info about this venue.
		venue = dbAccessObjVenue.getVenue(tableNum, venueId);

		// Set the name of the venue as the title of the action bar.
		setTitle(venue.getName());

		setUI(venue);

		// Give the action bar a back button.
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	private void setUI(Venue venue) {
		
		TextView venueInfoName = (TextView) findViewById(R.id.venue_info_name);
		venueInfoName.setText(venue.getName());

		ImageView venueInfoImage = (ImageView) findViewById(R.id.venue_info_image);
		venueInfoImage.setImageResource(venue.getImageResource());

		RatingBar venueInfoRating = (RatingBar) findViewById(R.id.venue_info_rating);
		venueInfoRating.setRating(venue.getRating());

		TextView venueInfoRatingNumber = (TextView) findViewById(R.id.venue_info_rating_number);
		venueInfoRatingNumber.setText(venue.getRating() + "/5.0");

		TextView venueInfoReviewNumber = (TextView) findViewById(R.id.venue_info_review_number);
		venueInfoReviewNumber.setText("(" + dbAccessObjReview.getTableRowCountForVenue(venue.getName()) + " reviews)");

		TextView venueInfoWait = (TextView) findViewById(R.id.venue_info_wait);
		venueInfoWait.setText("Wait: " + venue.getWaitMinutes() + " minutes");

		TextView venueInfoAddress = (TextView) findViewById(R.id.venue_info_address);
		venueInfoAddress.setText(venue.getAddress());

		TextView venueInfoDistance = (TextView) findViewById(R.id.venue_info_distance);
		venueInfoDistance.setText("Distance: " + venue.getDistance() + " miles");

		TextView venueInfoPhone = (TextView) findViewById(R.id.venue_info_phone);
		venueInfoPhone.setText("Phone: " + venue.getPhoneNumber());

		TextView venueInfoHours = (TextView) findViewById(R.id.venue_info_hours);
		String todayHours = null;
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.US);
		Date d = new Date();
		String dayOfTheWeek = sdf.format(d);
		if (dayOfTheWeek.equals("Monday")) {
			todayHours = venue.getHours().getHoursMonday();
		} else if (dayOfTheWeek.equals("Tuesday")) {
			todayHours = venue.getHours().getHoursTuesday();
		} else if (dayOfTheWeek.equals("Wednesday")) {
			todayHours = venue.getHours().getHoursWednesday();
		} else if (dayOfTheWeek.equals("Thursday")) {
			todayHours = venue.getHours().getHoursThursday();
		} else if (dayOfTheWeek.equals("Friday")) {
			todayHours = venue.getHours().getHoursFriday();
		} else if (dayOfTheWeek.equals("Saturday")) {
			todayHours = venue.getHours().getHoursSaturday();
		} else {
			todayHours = venue.getHours().getHoursSunday();
		}
		venueInfoHours.setText("Hours today: " + todayHours);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.venue, menu);
		
		MenuItemListener mil = new MenuItemListener(this, venue, tableNum);
		menu.findItem(R.id.venue_submit_wait).setOnMenuItemClickListener(mil);
		menu.findItem(R.id.venue_chat).setOnMenuItemClickListener(mil);
		menu.findItem(R.id.venue_review).setOnMenuItemClickListener(mil);
		menu.findItem(R.id.venue_favorite).setOnMenuItemClickListener(mil);
		menu.findItem(R.id.venue_request).setOnMenuItemClickListener(mil);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		// back button press
		case android.R.id.home:
			finish();
			return true;
		case R.id.menu_settings:
			Intent i = new Intent(this, SettingsActivity.class);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onResume() {
		dbAccessObjVenue.open();
		dbAccessObjReview.open();
		super.onResume();
	}

	@Override
	public void onPause() {
		dbAccessObjVenue.close();
		dbAccessObjReview.close();
		super.onPause();
	}
}