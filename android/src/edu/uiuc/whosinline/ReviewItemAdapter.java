package edu.uiuc.whosinline;

import edu.uiuc.whosinline.database.SQLiteHelperReview;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class ReviewItemAdapter extends CursorAdapter {

	private Context mContext;
	private LayoutInflater mLayoutInflator;
	private Cursor cursor;
	
	public ReviewItemAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
		
		this.mContext = context;
		this.mLayoutInflator = LayoutInflater.from(mContext);
		this.cursor = c;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		String title = cursor.getString(cursor.getColumnIndex(
				SQLiteHelperReview.COLUMN_REVIEW_TITLE));
		String content = cursor.getString(cursor.getColumnIndex(
				SQLiteHelperReview.COLUMN_REVIEW_CONTENT));
		float rating = cursor.getFloat(cursor.getColumnIndex(
				SQLiteHelperReview.COLUMN_REVIEW_RATING));
		String userName = cursor.getString(cursor.getColumnIndex(
				SQLiteHelperReview.COLUMN_REVIEW_USER_NAME));
		
		TextView titleTextView = (TextView) view.findViewById(R.id.row_title);
		titleTextView.setText(title);
		
		TextView contentTextView = (TextView) view.findViewById(R.id.row_review);
		contentTextView.setText(content);
		
		RatingBar ratingBar = (RatingBar) view.findViewById(R.id.row_ratingbar);
		ratingBar.setRating(rating);
		
		TextView userTextView = (TextView) view.findViewById(R.id.row_user_name);
		userTextView.setText("By: " + userName);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (cursor.moveToPosition(position) == false) {
			throw new IllegalStateException("Couldn't move cursor to the right position");
		}
		
		View view;
		
		if(convertView == null){
			view = mLayoutInflator.inflate(R.layout.list_cell_review, parent, false);
		} else {
			view = convertView;
		}
		
		bindView(view, mContext, cursor);
		return view;
	}
	
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = mLayoutInflator.inflate(R.layout.list_cell_review, parent, false);
		return view;
	}
}