package edu.uiuc.whosinline.fragments;

import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.database.SQLiteHelperVenues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

public class VenueItemAdapter extends CursorAdapter {

	private Context mContext;
	private LayoutInflater mLayoutInflator;
	
	public VenueItemAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
		
		this.mContext = context;
		this.mLayoutInflator = LayoutInflater.from(mContext);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		int imageResource = cursor.getInt(cursor.getColumnIndex(
				SQLiteHelperVenues.COLUMN_VENUE_IMAGE_RESOURCE));
		String venueName = cursor.getString(cursor.getColumnIndex(
				SQLiteHelperVenues.COLUMN_VENUE_NAME));
		
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
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = mLayoutInflator.inflate(R.layout.list_cell_venue, parent, false);
		return view;
	}
}