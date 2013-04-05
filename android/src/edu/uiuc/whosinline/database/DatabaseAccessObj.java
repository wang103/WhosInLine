package edu.uiuc.whosinline.database;

import java.util.ArrayList;
import java.util.List;

import edu.uiuc.whosinline.data.Venue;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAccessObj {

	private int dbNum;
	private SQLiteDatabase database;

	private SQLiteHelperVenueNearby dbHelperNearby;
	private SQLiteHelperVenueRecent dbHelperRecent;
	private SQLiteHelperVenueFavorite dbHelperFavorite;

	private String[] columnNames = {SQLiteHelperBase.COLUMN_ID,
			SQLiteHelperBase.COLUMN_VENUE_NAME,
			SQLiteHelperBase.COLUMN_VENUE_IMAGE_PATH,
			SQLiteHelperBase.COLUMN_VENUE_TYPE,
			SQLiteHelperBase.COLUMN_VENUE_RATING,
			SQLiteHelperBase.COLUMN_VENUE_WAIT_MIN};

	public DatabaseAccessObj(Context context) {
		dbNum = -1;
		dbHelperNearby = new SQLiteHelperVenueNearby(context);
		dbHelperRecent = new SQLiteHelperVenueRecent(context);
		dbHelperFavorite = new SQLiteHelperVenueFavorite(context);
	}

	public void open(int dbNum) {
		this.dbNum = dbNum;
		if (dbNum == 0) {
			database = dbHelperNearby.getWritableDatabase();
		} else if (dbNum == 2) {
			database = dbHelperRecent.getWritableDatabase();
		} else {
			database = dbHelperFavorite.getWritableDatabase();
		}
	}

	public void close(int dbNum) {
		if (dbNum == 0) {
			dbHelperNearby.close();
		} else if (dbNum == 2) {
			dbHelperRecent.close();
		} else {
			dbHelperFavorite.close();
		}
		this.dbNum = -1;
	}

	private String getCurrentTableName() {
		if (this.dbNum == 0) {
			return SQLiteHelperVenueNearby.TABLE_NAME;
		} else if (this.dbNum == 1) {
			return SQLiteHelperVenueRecent.TABLE_NAME;
		}
		return SQLiteHelperVenueFavorite.TABLE_NAME;
	}

	/**
	 * Insert a new venue into the SQLite database.
	 * 
	 * @param venue a {@link Venue} object.
	 */
	public void insertVenue(Venue venue) {
		ContentValues values = new ContentValues();

		values.put(SQLiteHelperBase.COLUMN_ID, venue.getId());
		values.put(SQLiteHelperBase.COLUMN_VENUE_NAME, venue.getName());
		values.put(SQLiteHelperBase.COLUMN_VENUE_IMAGE_PATH, venue.getImagePath());
		values.put(SQLiteHelperBase.COLUMN_VENUE_TYPE, venue.getType());
		values.put(SQLiteHelperBase.COLUMN_VENUE_RATING, venue.getRating());
		values.put(SQLiteHelperBase.COLUMN_VENUE_WAIT_MIN, venue.getWaitMinutes());

		String tableName = getCurrentTableName();

		database.insert(tableName, null, values);
	}

	/**
	 * Delete a venue from the SQLite database.
	 * 
	 * @param venue a {@link Venue} object.
	 */
	public void deleteVenue(Venue venue) {
		int id = venue.getId();

		String tableName = getCurrentTableName();

		database.delete(tableName, SQLiteHelperBase.COLUMN_ID + "=" + id, null);
	}

	public List<Venue> getAllVenues() {
		List<Venue> venues = new ArrayList<Venue>();

		String tableName = getCurrentTableName();

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