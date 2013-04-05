package edu.uiuc.whosinline.fragments;

import edu.uiuc.whosinline.database.DatabaseAccessObj;
import android.app.ListFragment;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public abstract class BaseFragment extends ListFragment {
	
	protected SimpleCursorAdapter adapter;
	protected DatabaseAccessObj dbAccessObj;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		dbAccessObj = new DatabaseAccessObj(getActivity());
		dbAccessObj.open();
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