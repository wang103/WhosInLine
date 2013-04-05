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
		
		venue = new Venue(0, "Cravings Restaurant", "", R.drawable.cravings,
				"Restaurant", R.drawable.stars_4, 0.15f, 15);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(1, "Chipotle Mexican Grill", "", R.drawable.chipotle,
				"Restaurant", R.drawable.stars_5, 0.12f, 10);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(2, "Noodles & Company", "", R.drawable.noddles,
				"Restaurant", R.drawable.stars_3, 0.09f, 5);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(3, "Papa Johns", "", R.drawable.papa_johns,
				"Restaurant", R.drawable.stars_4, 0.24f, 5);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(4, "Pizza Hut", "", R.drawable.pizza_hut,
				"Restaurant", R.drawable.stars_1, 0.37f, 5);
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
		for (Venue venue : venues) {
			dbAccessObj.deleteVenue(tableNum, venue);
		}
		insertTestData();
		
		
		// Fields from the database.
		String[] from = new String[] {SQLiteHelperVenues.COLUMN_VENUE_NAME,
				SQLiteHelperVenues.COLUMN_VENUE_IMAGE_RESOURCE,
				SQLiteHelperVenues.COLUMN_VENUE_TYPE,
				SQLiteHelperVenues.COLUMN_VENUE_RATING,
				SQLiteHelperVenues.COLUMN_VENUE_DISTANCE,
				SQLiteHelperVenues.COLUMN_VENUE_WAIT_MIN};
		
		// Fields on the UI to map.
		int[] to = new int[] {R.id.cell_venue_name, R.id.cell_venue_image,
				R.id.cell_venue_type, R.id.cell_rating_image,
				R.id.cell_distance, R.id.cell_wait_minutes};
		
		adapter = new SimpleCursorAdapter(getActivity(),
				R.layout.list_cell_venue, dbAccessObj.getCursor(tableNum),
				from, to, 0);

		setListAdapter(adapter);
	}
}