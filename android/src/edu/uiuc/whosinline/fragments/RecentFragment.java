package edu.uiuc.whosinline.fragments;

import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.data.Venue;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecentFragment extends BaseFragment {
	
	private int tableNum = 1;
	
	private void insertTestData() {
		Venue venue;
		
		venue = new Venue(0, "Cravings Restaurant", "", R.drawable.ic_type_asian,
				"Restaurant", R.drawable.stars_4, 0.15f, 15,
				"603 S Wright St, Champaign, IL");
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(1, "Chipotle Mexican Grill", "", R.drawable.ic_type_mexican,
				"Restaurant", R.drawable.stars_5, 0.12f, 10,
				"528 E Green St #101, Champaign, IL");
		dbAccessObj.insertVenue(tableNum, venue);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (dbAccessObj.getTableRowCount(tableNum) == 0) {
			insertTestData();
		}
		
		fillData(tableNum);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the layout for this fragment.
		return inflater.inflate(R.layout.fragment_recent, container, false);
	}
}