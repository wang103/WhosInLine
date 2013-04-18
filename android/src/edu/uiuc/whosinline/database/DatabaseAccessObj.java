package edu.uiuc.whosinline.database;

import java.util.ArrayList;
import java.util.List;

import edu.uiuc.whosinline.data.HoursSchedule;
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
			SQLiteHelperVenues.COLUMN_VENUE_IMAGE_RESOURCE,
			SQLiteHelperVenues.COLUMN_VENUE_TYPE,
			SQLiteHelperVenues.COLUMN_VENUE_RATING,
			SQLiteHelperVenues.COLUMN_VENUE_DISTANCE,
			SQLiteHelperVenues.COLUMN_VENUE_WAIT_MIN,
			SQLiteHelperVenues.COLUMN_VENUE_ADDRESS,
			SQLiteHelperVenues.COLUMN_VENUE_PHONE,
			SQLiteHelperVenues.COLUMN_VENUE_HOURS_MONDAY,
			SQLiteHelperVenues.COLUMN_VENUE_HOURS_TUESDAY,
			SQLiteHelperVenues.COLUMN_VENUE_HOURS_WEDNESDAY,
			SQLiteHelperVenues.COLUMN_VENUE_HOURS_THURSDAY,
			SQLiteHelperVenues.COLUMN_VENUE_HOURS_FRIDAY,
			SQLiteHelperVenues.COLUMN_VENUE_HOURS_SATURDAY,
			SQLiteHelperVenues.COLUMN_VENUE_HOURS_SUNDAY};

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

	public Venue getVenue(int tableNum, long id) {
		String tableName = getTableName(tableNum);
		String statement = "SELECT * FROM " + tableName +
				" WHERE " + SQLiteHelperVenues.COLUMN_ID + "=" + id;
		Cursor cursor = database.rawQuery(statement, null);
		if (cursor.moveToFirst()) {
			return cursorToVenue(cursor);
		}
		return null;
	}
	
	public Venue getVenue(int tableNum, String name) {
		String tableName = getTableName(tableNum);
		String statement = "SELECT * FROM " + tableName +
				" WHERE " + SQLiteHelperVenues.COLUMN_VENUE_NAME + "='" +
				name + "'";
		Cursor cursor = database.rawQuery(statement, null);
		if (cursor.moveToFirst()) {
			return cursorToVenue(cursor);
		}
		return null;
	}
	
	public int getTableRowCount(int tableNum) {
		String tableName = getTableName(tableNum);
		String statement = "SELECT Count(*) FROM " + tableName;
		Cursor cursor = database.rawQuery(statement, null);
		cursor.moveToFirst();
		return cursor.getInt(0);
	}
	
	/**
	 * Insert a new venue into the SQLite database.
	 * 
	 * @param tableNum the number representing a table.
	 * @param venue a {@link Venue} object.
	 */
	public void insertVenue(int tableNum, Venue venue) {
		ContentValues values = new ContentValues();

		values.put(SQLiteHelperVenues.COLUMN_VENUE_NAME, venue.getName());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_IMAGE_PATH, venue.getImagePath());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_IMAGE_RESOURCE, venue.getImageResource());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_TYPE, venue.getType());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_RATING, venue.getRating());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_DISTANCE, venue.getDistance());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_WAIT_MIN, venue.getWaitMinutes());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_ADDRESS, venue.getAddress());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_PHONE, venue.getPhoneNumber());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_HOURS_MONDAY, venue.getHours().getHoursMonday());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_HOURS_TUESDAY, venue.getHours().getHoursTuesday());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_HOURS_WEDNESDAY, venue.getHours().getHoursWednesday());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_HOURS_THURSDAY, venue.getHours().getHoursThursday());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_HOURS_FRIDAY, venue.getHours().getHoursFriday());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_HOURS_SATURDAY, venue.getHours().getHoursSaturday());
		values.put(SQLiteHelperVenues.COLUMN_VENUE_HOURS_SUNDAY, venue.getHours().getHoursSunday());

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

	public Cursor getCursor(int tableNum) {
		String tableName = getTableName(tableNum);

		Cursor cursor = database.query(tableName, columnNames, null, null,
				null, null, null);
		
		cursor.moveToFirst();
		
		return cursor;
	}
	
	public List<Venue> getAllVenues(int tableNum) {
		List<Venue> venues = new ArrayList<Venue>();

		Cursor cursor = getCursor(tableNum);

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
	public static Venue cursorToVenue(Cursor cursor) {
		HoursSchedule hs = new HoursSchedule(cursor.getString(10),
				cursor.getString(11), cursor.getString(12), cursor.getString(13),
				cursor.getString(14), cursor.getString(15), cursor.getString(16));
		
		Venue venue = new Venue(cursor.getInt(0), cursor.getString(1),
					cursor.getString(2), cursor.getInt(3), cursor.getString(4),
					cursor.getFloat(5), cursor.getFloat(6), cursor.getInt(7),
					cursor.getString(8), cursor.getString(9), hs);
		return venue;
	}
}