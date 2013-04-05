package edu.uiuc.whosinline.fragments;

import java.util.List;

import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.data.Venue;
import edu.uiuc.whosinline.database.SQLiteHelperVenues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

public class NearbyFragment extends BaseFragment {
	
	final private int tableNum = 0;
	
	private void insertTestData() {
		Venue venue;
		
		venue = new Venue(0, "Cravings Restaurant", "cravings.png", "Restaurant", 4, 15);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(1, "Chipotle Mexican Grill", "chipotle.png", "Restaurant", 5, 10);
		dbAccessObj.insertVenue(tableNum, venue);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		fillData();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the layout for this fragment.
		return inflater.inflate(R.layout.fragment_nearby, container, false);
	}
	
	/**
	 * Populate the list view.
	 */
	private void fillData() {
		
		List<Venue> venues = dbAccessObj.getAllVenues(tableNum);
		
		if (venues.size() == 0) {
			insertTestData();
		}
		
		// Fields from the database.
		String[] from = new String[] {SQLiteHelperVenues.COLUMN_VENUE_NAME,
				SQLiteHelperVenues.COLUMN_VENUE_TYPE,
				SQLiteHelperVenues.COLUMN_VENUE_WAIT_MIN};
		
		// Fields on the UI to map.
		int[] to = new int[] {R.id.cell_venue_name, R.id.cell_venue_type,
				R.id.cell_wait_minutes};
		
		adapter = new SimpleCursorAdapter(getActivity(),
				R.layout.list_cell_venue, dbAccessObj.getCursor(tableNum),
				from, to, 0);

		setListAdapter(adapter);
	}
}