package edu.uiuc.whosinline.fragments;

import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.data.HoursSchedule;
import edu.uiuc.whosinline.data.Venue;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecentFragment extends BaseFragment {
	
	private int tableNum = 1;
	
	private void insertTestData() {
		HoursSchedule hs = new HoursSchedule("11:00 AM to 10:00 PM",
				"11:00 AM to 10:00 PM", "11:00 AM to 10:00 PM",
				"11:00 AM to 10:00 PM", "11:00 AM to 10:00 PM",
				"11:00 AM to 10:00 PM", "11:00 AM to 10:00 PM");
		
		Venue venue;
		
		venue = new Venue(0, "Cravings Restaurant", "", R.drawable.ic_type_asian,
				"Restaurant", 4.0f, 0.15f, 15,
				"603 S Wright St, Champaign, IL", "(111) 111-1111", hs);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(1, "Chipotle Mexican Grill", "", R.drawable.ic_type_mexican,
				"Restaurant", 5.0f, 0.12f, 10,
				"528 E Green St #101, Champaign, IL", "(111) 111-1111", hs);
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