package edu.uiuc.whosinline.database;

import java.util.ArrayList;
import java.util.List;

import edu.uiuc.whosinline.data.Venue;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAccessObj {

	private SQLiteDatabase database;
	private SQLiteHelperVenues dbHelper;

	private String[] columnNames = {SQLiteHelperVenues.COLUMN_ID,
			SQLiteHelperVenues.COLUMN_VENUE_NAME,
			SQLiteHelperVenues.COLUMN_VENUE_IMAGE_PATH,
			SQLiteHelperVenues.COLUMN_VENUE_TYPE,
			SQLiteHelperVenues.COLUMN_VENUE_RATING,
			SQLiteHelperVenues.COLUMN_VENUE_WAIT_MIN};

	public DatabaseAccessObj(Context context) {
		dbHelper = new SQLiteHelperVenues(context);
	}

	public void open() {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	private String getTableName(int tableNum) {
		if (tableNum == 0) {
			return SQLiteHelperVenues.TABLE_NAME_NEARBY;
		} else if (tableNum == 1) {
			return SQLiteHelperVenues.TABLE_NAME_RECENT;
		}
		return SQLiteHelperVenues.TABLE_NAME_FAVORITE;
	}

	/**
	 * Insert a new venue into the SQLite database.
	 * 
	 * @param tableNum the number representing a table.
	 * @param venue a {@link Venue} object.
	 */
	public void insertVenue(int tableNum, Venue venue) {
		ContentValues values = new ContentValues();

		values.put(SQLiteHelperVenues.COLUMN_ID, venue.getId());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_NAME, venue.getName());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_IMAGE_PATH, venue.getImagePath());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_TYPE, venue.getType());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_RATING, venue.getRating());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_WAIT_MIN, venue.getWaitMinutes());

		String tableName = getTableName(tableNum);

		database.insert(tableName, null, values);
	}

	/**
	 * Delete a venue from the SQLite database.
	 * 
	 * @param tableNum the number representing a table.
	 * @param venue a {@link Venue} object.
	 */
	public void deleteVenue(int tableNum, Venue venue) {
		int id = venue.getId();

		String tableName = getTableName(tableNum);

		database.delete(tableName, SQLiteHelperVenues.COLUMN_ID + "=" + id, null);
	}

	public List<Venue> getAllVenues(int tableNum) {
		List<Venue> venues = new ArrayList<Venue>();

		String tableName = getTableName(tableNum);

		Cursor cursor = database.query(tableName, columnNames, null, null,
				null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Venue venue = cursorToVenue(cursor);
			venues.add(venue);

			cursor.moveToNext();
		}

		cursor.close();
		return venues;
	}

	/**
	 * Helper method to convert a {@link Cursor} object to a {@link Venue} object.
	 * 
	 * @param cursor a {@link Cursor} object.
	 * @return a {@link Venue} object.
	 */
	private Venue cursorToVenue(Cursor cursor) {
		Venue venue = new Venue(cursor.getInt(0), cursor.getString(1),
					cursor.getString(2), cursor.getString(3), cursor.getInt(4),
					cursor.getInt(5));
		return venue;
	}
}