package edu.uiuc.whosinline.fragments;

import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.database.SQLiteHelperVenues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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
		float rating = cursor.getFloat(cursor.getColumnIndex(
				SQLiteHelperVenues.COLUMN_VENUE_RATING));
		int waitMinutes = cursor.getInt(cursor.getColumnIndex(
				SQLiteHelperVenues.COLUMN_VENUE_WAIT_MIN));
		String venueType = cursor.getString(cursor.getColumnIndex(
				SQLiteHelperVenues.COLUMN_VENUE_TYPE));
		float distance = cursor.getFloat(cursor.getColumnIndex(
				SQLiteHelperVenues.COLUMN_VENUE_DISTANCE));
		
		ImageView venueImageView = (ImageView) view.findViewById(R.id.cell_venue_image);
		venueImageView.setImageResource(imageResource);
		
		TextView nameTextView = (TextView) view.findViewById(R.id.cell_venue_name);
		nameTextView.setText(venueName);
		
		RatingBar ratingBar = (RatingBar) view.findViewById(R.id.cell_rating);
		ratingBar.setRating(rating);
		
		TextView ratingTextView = (TextView) view.findViewById(R.id.cell_rating_number);
		ratingTextView.setText(rating + "/5.0");
		
		TextView waitTextView = (TextView) view.findViewById(R.id.cell_wait_minutes);
		waitTextView.setText(waitMinutes + " minutes wait");
		
		TextView typeTextView = (TextView) view.findViewById(R.id.cell_venue_type);
		typeTextView.setText(venueType);
		
		TextView distanceTextView = (TextView) view.findViewById(R.id.cell_distance);
		distanceTextView.setText(distance + " miles");
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = mLayoutInflator.inflate(R.layout.list_cell_venue, parent, false);
		return view;
	}
}