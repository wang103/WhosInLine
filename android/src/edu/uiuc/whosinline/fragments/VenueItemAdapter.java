package edu.uiuc.whosinline.fragments;

import edu.uiuc.whosinline.R;
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
	public void bindView(View arg0, Context arg1, Cursor arg2) {

	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = mLayoutInflator.inflate(R.layout.list_cell_venue, parent, false);
		return view;
	}
}