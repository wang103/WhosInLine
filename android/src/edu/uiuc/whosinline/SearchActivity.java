package edu.uiuc.whosinline;

import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;

import edu.uiuc.whosinline.database.DatabaseAccessObj;
import edu.uiuc.whosinline.fragments.VenueItemAdapter;
import edu.uiuc.whosinline.windows.ChatWindow;
import edu.uiuc.whosinline.windows.FavoriteWindow;
import edu.uiuc.whosinline.windows.SubmitWaitTimeWindow;
import edu.uiuc.whosinline.windows.WriteReviewWindow;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class SearchActivity extends ListActivity {

	private int tableNum;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		setTitle("Search Results");

		// Give the action bar a back button.
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		setupListForResults(getIntent().getExtras());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
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

	private void setupListForResults(Bundle bundle) {
		tableNum = bundle.getInt(HomeActivity.INTENT_TABLE_NUM);
		String query = bundle.getString(HomeActivity.INTENT_QUERY);

		// Search the database and display the results.
		DatabaseAccessObj dao = new DatabaseAccessObj(this);
		dao.open();

		Cursor cursor = dao.getSearchResults(tableNum, query);

		dao.close();

		VenueItemAdapter tempAdapter = new VenueItemAdapter(this, cursor, false);

		SlideExpandableListAdapter adapter = new SlideExpandableListAdapter(
				tempAdapter, R.id.cell_id, R.id.expandable);

		setListAdapter(adapter);
	}
	
	public void onProfileButtonClick(View v) {
		ListView lv = getListView();
		int position = lv.getPositionForView(v);
		SQLiteCursor cursor = (SQLiteCursor) lv.getItemAtPosition(position);
		long venueID = DatabaseAccessObj.cursorToVenue(cursor).getId();

		Intent intent = new Intent(this, VenueActivity.class);
		Bundle extras = new Bundle();
		extras.putInt(HomeActivity.INTENT_TABLE_NUM, tableNum);
		extras.putLong(HomeActivity.INTENT_VENUE_ID, venueID);
		intent.putExtras(extras);
		startActivity(intent);
	}
	
	public void onSubmitWaitButtonClick(View v) {
		ListView lv = getListView();
		int position = lv.getPositionForView(v);
		SQLiteCursor cursor = (SQLiteCursor) lv.getItemAtPosition(position);
		long venueID = DatabaseAccessObj.cursorToVenue(cursor).getId();

		SubmitWaitTimeWindow windowFrag = new SubmitWaitTimeWindow();
		Bundle extras = new Bundle();
		extras.putInt(HomeActivity.INTENT_TABLE_NUM, tableNum);
		extras.putLong(HomeActivity.INTENT_VENUE_ID, venueID);
		windowFrag.setArguments(extras);
		windowFrag.show(getFragmentManager(), HomeActivity.TAG_SUBMIT_WAIT_TIME_WINDOW);
	}

	public void onChatButtonClick(View v) {
		ListView lv = getListView();
		int position = lv.getPositionForView(v);
		SQLiteCursor cursor = (SQLiteCursor) lv.getItemAtPosition(position);
		long venueID = DatabaseAccessObj.cursorToVenue(cursor).getId();

		ChatWindow windowFrag = new ChatWindow();
		Bundle extras = new Bundle();
		extras.putBoolean(HomeActivity.INTENT_CHAT_TYPE, true);
		extras.putInt(HomeActivity.INTENT_TABLE_NUM, tableNum);
		extras.putLong(HomeActivity.INTENT_VENUE_ID, venueID);
		windowFrag.setArguments(extras);
		windowFrag.show(getFragmentManager(), HomeActivity.TAG_CHAT_WINDOW);
	}

	public void onWriteReviewButtonClick(View v) {
		ListView lv = getListView();
		int position = lv.getPositionForView(v);
		SQLiteCursor cursor = (SQLiteCursor) lv.getItemAtPosition(position);
		long venueID = DatabaseAccessObj.cursorToVenue(cursor).getId();

		WriteReviewWindow windowFrag = new WriteReviewWindow();
		Bundle extras = new Bundle();
		extras.putInt(HomeActivity.INTENT_TABLE_NUM, tableNum);
		extras.putLong(HomeActivity.INTENT_VENUE_ID, venueID);
		windowFrag.setArguments(extras);
		windowFrag.show(getFragmentManager(), HomeActivity.TAG_WRITE_REVIEW_WINDOW);
	}

	public void onFavoriteButtonClick(View v) {
		ListView lv = getListView();
		int position = lv.getPositionForView(v);
		SQLiteCursor cursor = (SQLiteCursor) lv.getItemAtPosition(position);
		long venueID = DatabaseAccessObj.cursorToVenue(cursor).getId();

		FavoriteWindow windowFrag = new FavoriteWindow();
		Bundle extras = new Bundle();
		extras.putInt(HomeActivity.INTENT_TABLE_NUM, tableNum);
		extras.putLong(HomeActivity.INTENT_VENUE_ID, venueID);
		windowFrag.setArguments(extras);
		windowFrag.show(getFragmentManager(), HomeActivity.TAG_FAVORITE_WINDOW);
	}
}