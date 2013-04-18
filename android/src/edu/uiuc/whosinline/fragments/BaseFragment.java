package edu.uiuc.whosinline.fragments;

import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;

import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.database.DatabaseAccessObj;
import android.support.v4.app.ListFragment;
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
		
		VenueItemAdapter tempAdapter = new VenueItemAdapter(getActivity(),
				dbAccessObj.getCursor(tableNum, tableNum == 1 ? true : false), false);
		
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