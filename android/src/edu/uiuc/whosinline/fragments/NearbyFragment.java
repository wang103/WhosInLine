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
		
		venue = new Venue(0, "Cravings Restaurant", "", R.drawable.ic_type_asian,
				"Restaurant", R.drawable.stars_4, 0.15f, 15);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(1, "Chipotle Mexican Grill", "", R.drawable.ic_type_mexican,
				"Restaurant", R.drawable.stars_5, 0.12f, 10);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(2, "Noodles & Company", "", R.drawable.ic_type_restaurant,
				"Restaurant", R.drawable.stars_3, 0.09f, 5);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(3, "Papa Johns", "", R.drawable.ic_type_pizza,
				"Restaurant", R.drawable.stars_4, 0.24f, 5);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(4, "Legends", "", R.drawable.ic_type_sportsbar,
				"Sports Bar", R.drawable.stars_4, 0.31f, 2);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(5, "Pizza Hut", "", R.drawable.ic_type_pizza,
				"Restaurant", R.drawable.stars_1, 0.37f, 5);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(6, "Joe's Brewery", "", R.drawable.ic_type_bar,
				"Bar", R.drawable.stars_5, 0.37f, 20);
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