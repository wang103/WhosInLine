package edu.uiuc.whosinline.fragments;

import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;

import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.database.DatabaseAccessObj;
import edu.uiuc.whosinline.database.SQLiteHelperVenues;
import android.support.v4.app.ListFragment;
import android.widget.SimpleCursorAdapter;
import android.os.Bundle;

public abstract class BaseFragment extends ListFragment {
	
	protected SlideExpandableListAdapter adapter;
	protected DatabaseAccessObj dbAccessObj;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		dbAccessObj = new DatabaseAccessObj(getActivity());
		dbAccessObj.open();
	}
	
	/**
	 * Populate the list view.
	 * 
	 * @param tableNum table number for the database access.
	 */
	public void fillData(int tableNum) {
		
		// Fields from the database.
		String[] from = new String[] {SQLiteHelperVenues.COLUMN_VENUE_NAME,
				SQLiteHelperVenues.COLUMN_VENUE_IMAGE_RESOURCE,
				SQLiteHelperVenues.COLUMN_VENUE_TYPE,
				SQLiteHelperVenues.COLUMN_VENUE_RATING,
				SQLiteHelperVenues.COLUMN_VENUE_DISTANCE,
				SQLiteHelperVenues.COLUMN_VENUE_WAIT_MIN};
		
		// Fields on the UI to map.
		int[] to = new int[] {R.id.cell_venue_name, R.id.cell_venue_image,
				R.id.cell_venue_type, R.id.cell_rating_number,
				R.id.cell_distance, R.id.cell_wait_minutes};
		
		SimpleCursorAdapter tempAdapter = new SimpleCursorAdapter(getActivity(),
				R.layout.list_cell_venue, dbAccessObj.getCursor(tableNum),
				from, to, 0);

		adapter = new SlideExpandableListAdapter(tempAdapter, R.id.cell_id,
				R.id.expandable);

		setListAdapter(adapter);
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