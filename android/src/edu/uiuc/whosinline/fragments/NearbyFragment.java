package edu.uiuc.whosinline.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.data.HoursSchedule;
import edu.uiuc.whosinline.data.Venue;

public class NearbyFragment extends BaseFragment {
	
	private int tableNum = 0;
	
	private void insertTestData() {
		HoursSchedule hs = new HoursSchedule("Closed", "Closed", "Closed",
				"Closed", "Closed", "Closed", "Closed");
		
		Venue venue;
		
		venue = new Venue(0, "Cravings Restaurant", "", R.drawable.ic_type_asian,
				"Restaurant", 4.0f, 0.15f, 15,
				"603 S Wright St, Champaign, IL", "111-111-1111", hs);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(1, "Chipotle Mexican Grill", "", R.drawable.ic_type_mexican,
				"Restaurant", 5.0f, 0.12f, 10,
				"528 E Green St #101, Champaign, IL", "111-111-1111", hs);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(2, "Noodles & Company", "", R.drawable.ic_type_restaurant,
				"Restaurant", 3.5f, 0.09f, 5,
				"528 E Green St, Champaign, IL", "111-111-1111", hs);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(3, "Papa Johns", "", R.drawable.ic_type_pizza,
				"Restaurant", 4.0f, 0.24f, 5,
				"106 E Green St, Champaign, IL", "111-111-1111", hs);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(4, "Legends", "", R.drawable.ic_type_sportsbar,
				"Sports Bar", 4.5f, 0.31f, 2,
				"522 E Green St, Champaign, IL", "111-111-1111", hs);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(5, "Pizza Hut", "", R.drawable.ic_type_pizza,
				"Restaurant", 1.5f, 0.37f, 5,
				"411 E Green St, Champaign, IL", "111-111-1111", hs);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(6, "Joe's Brewery", "", R.drawable.ic_type_bar,
				"Bar", 5.0f, 0.37f, 20,
				"706 South 5th Street, Champaign, IL", "111-111-1111", hs);
		dbAccessObj.insertVenue(tableNum, venue);
		
		venue = new Venue(7, "Maize Mexican Grill", "", R.drawable.ic_type_mexican, 
				"Restaurant", 5.0f, 0.37f, 5, 
				"60 E Green St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(8, "The Blind Pig Company", "", R.drawable.ic_type_sportsbar, 
				"Sports Bar", 5.0f, 0.37f, 5, 
				"120 N Walnut St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(9, "Jarling's Custard Cup", "", R.drawable.ic_type_icecream, 
				"Ice Cream", 4.5f, 0.37f, 5, 
				"309 W Kirby Ave Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(10, "Golden Harbor", "", R.drawable.ic_type_asian, 
				"Asian", 4.0f, 0.37f, 5, 
				"505 S Neil St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(11, "Papa Del's", "", R.drawable.ic_type_pizza, 
				"Pizza", 4.0f, 0.37f, 5, 
				"206 E Green St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(12, "Cocomero", "", R.drawable.ic_type_icecream, 
				"Ice Cream", 4.0f, 0.37f, 5, 
				"709 S Wright St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(13, "Mas Amigos", "", R.drawable.ic_type_mexican, 
				"Restaurant", 4.0f, 0.37f, 5, 
				"40 E Springfield Ave Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(14, "Farren's Pub & Eatery", "", R.drawable.ic_type_restaurant, 
				"Restaurant", 4.5f, 0.37f, 5, 
				"308 N Randolph St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(15, "Escobar's", "", R.drawable.ic_type_mexican, 
				"Restaurant", 4.0f, 0.37f, 5, 
				"6 E Columbia Ave Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(16, "Murphy's Pub", "", R.drawable.ic_type_bar, 
				"Bar", 4.5f, 0.37f, 5, 
				"604 E Green St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(17, "Blind Pig Brewery", "", R.drawable.ic_type_bar, 
				"Bar", 4.5f, 0.37f, 5, 
				"120 N Neil St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(18, "Jupiter's Pizzeria & Billiards", "", R.drawable.ic_type_pizza, 
				"Pizza", 4.0f, 0.37f, 5, 
				"39 E Main St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(19, "Mike N Molly's", "", R.drawable.ic_type_bar, 
				"Bar", 3.5f, 0.37f, 5, 
				"105 N Market St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(20, "Firehaus", "", R.drawable.ic_type_bar, 
				"Bar", 5.0f, 0.37f, 5, 
				"708 S 6th St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(21, "Buffalo Wild Wings", "", R.drawable.ic_type_sportsbar, 
				"Sports Bar", 3.0f, 0.37f, 5, 
				"907 West Marketview Drive Champaign, IL 61822", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(22, "Clybourne", "", R.drawable.ic_type_bar, 
				"Bar", 4.0f, 0.37f, 5, 
				"708 S 6th St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(23, "Cowboy Monkey", "", R.drawable.ic_type_restaurant, 
				"Restaurant", 2.5f, 0.37f, 5, 
				"6 E Taylor St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(24, "Geovanti's", "", R.drawable.ic_type_fastfood, 
				"Restaurant", 4.0f, 0.37f, 5, 
				"401 E Green St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(25, "Wedge Tequila Bar & Grill", "", R.drawable.ic_type_bar, 
				"Bar", 3.0f, 0.37f, 5, 
				"415 N Neil St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(26, "The Highdive", "", R.drawable.ic_type_nightclub, 
				"Night Club", 5.0f, 0.37f, 5, 
				"51 East Main St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(27, "KAMS", "", R.drawable.ic_type_nightclub, 
				"Night Club", 2.0f, 0.37f, 5, 
				"618 E Daniel St Champaign, IL 61820", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(28, "Soma Ultralounge", "", R.drawable.ic_type_nightclub, 
				"Night Club", 3.0f, 0.37f, 5, 
				"320 N Neil St Champaign, IL 61821", "111-111-1111", hs); 
		dbAccessObj.insertVenue(tableNum, venue);

		venue = new Venue(29, "Memphis On Main", "", R.drawable.ic_type_bar, 
				"Bar", 2.5f, 0.37f, 5, 
				"55 E Main St Champaign, IL 61820", "111-111-1111", hs); 
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
		return inflater.inflate(R.layout.fragment_nearby, container, false);
	}
}