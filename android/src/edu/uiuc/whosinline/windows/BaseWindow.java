package edu.uiuc.whosinline.windows;

import edu.uiuc.whosinline.HomeActivity;
import edu.uiuc.whosinline.data.Venue;
import edu.uiuc.whosinline.database.DatabaseAccessObjVenue;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

public class BaseWindow extends android.app.DialogFragment {

	protected Button positiveButton;
	protected Button negativeButton;
	
	protected Venue getVenue(Bundle extras, Context context) {
		// Get content from intent.
		int tableNum = extras.getInt(HomeActivity.INTENT_TABLE_NUM);
		long venueId = extras.getLong(HomeActivity.INTENT_VENUE_ID);

		// Get the database object.
		DatabaseAccessObjVenue dbAccessObj = new DatabaseAccessObjVenue(context);
		dbAccessObj.open();

		// Get all the info about this venue.
		Venue venue = dbAccessObj.getVenue(tableNum, venueId);
		
		dbAccessObj.close();
		
		return venue;
	}
}