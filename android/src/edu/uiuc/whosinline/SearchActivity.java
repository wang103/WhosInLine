package edu.uiuc.whosinline;

import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;

import edu.uiuc.whosinline.database.DatabaseAccessObj;
import edu.uiuc.whosinline.fragments.VenueItemAdapter;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;

public class SearchActivity extends ListActivity {

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
		int tableNum = bundle.getInt(HomeActivity.INTENT_TABLE_NUM);
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
}