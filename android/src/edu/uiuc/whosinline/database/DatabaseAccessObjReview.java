package edu.uiuc.whosinline.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAccessObjReview {
	
	private SQLiteDatabase database;
	private SQLiteHelperVenues dbHelper;
	
	private String[] columnNames = {SQLiteHelperReview.COLUMN_ID,
			SQLiteHelperReview.COLUMN_REVIEW_VENUE_NAME,
			SQLiteHelperReview.COLUMN_REVIEW_TITLE,
			SQLiteHelperReview.COLUMN_REVIEW_CONTENT,
			SQLiteHelperReview.COLUMN_REVIEW_RATING,
			SQLiteHelperReview.COLUMN_REVIEW_USER_NAME};
	
	public DatabaseAccessObjReview(Context context) {
		dbHelper = new SQLiteHelperVenues(context);
	}

	public boolean isOpen() {
		return database.isOpen();
	}
	
	public void open() {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Cursor getCursor(boolean reverse) {
		String orderBy = SQLiteHelperVenues.COLUMN_ID;
		if (reverse) {
			orderBy += " DESC";
		}
		
		Cursor cursor = database.query(SQLiteHelperReview.TABLE_NAME_REVIEW,
				columnNames, null, null, null, null, orderBy);

		cursor.moveToFirst();

		return cursor;
	}
}