package edu.uiuc.whosinline.fragments;

import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;

import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.database.DatabaseAccessObjVenue;
import android.support.v4.app.ListFragment;
import android.os.Bundle;

public abstract class BaseFragment extends ListFragment {
	
	protected SlideExpandableListAdapter adapter;
	protected DatabaseAccessObjVenue dbAccessObj;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		dbAccessObj = new DatabaseAccessObjVenue(getActivity());
		dbAccessObj.open();
	}
	
	/**
	 * Populate the list view.
	 * 
	 * @param tableNum table number for the database access.
	 */
	public void fillData(int tableNum) {
		
		boolean isOpen = dbAccessObj.isOpen();
		if (isOpen == false) {
			dbAccessObj.open();
		}
		VenueItemAdapter tempAdapter = new VenueItemAdapter(getActivity(),
				dbAccessObj.getCursor(tableNum, tableNum == 1 ? true : false), false);
		
		adapter = new SlideExpandableListAdapter(tempAdapter, R.id.cell_id,
				R.id.expandable);

		setListAdapter(adapter);
		
		if (isOpen == false) {
			dbAccessObj.close();
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