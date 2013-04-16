package edu.uiuc.whosinline.fragments;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.data.Venue;

public class NearbyFragment extends BaseFragment {
	
	private int tableNum = 0;
	
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
		
		List<Venue> venues = dbAccessObj.getAllVenues(tableNum);
		for (Venue venue : venues) {
			dbAccessObj.deleteVenue(tableNum, venue);
		}
		insertTestData();
		
		fillData(tableNum);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the layout for this fragment.
		return inflater.inflate(R.layout.fragment_nearby, container, false);
	}
}