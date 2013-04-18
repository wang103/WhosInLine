package edu.uiuc.whosinline.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelperVenues extends SQLiteOpenHelper {

	public static final String TABLE_NAME_NEARBY = "venue_nearby";
	public static final String TABLE_NAME_RECENT = "venue_recent";
	public static final String TABLE_NAME_FAVORITE = "venue_favorite";
	
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_VENUE_NAME = "name";
	public static final String COLUMN_VENUE_IMAGE_PATH = "image_path";
	public static final String COLUMN_VENUE_IMAGE_RESOURCE = "image_resource";
	public static final String COLUMN_VENUE_TYPE = "type";
	public static final String COLUMN_VENUE_RATING = "rating";
	public static final String COLUMN_VENUE_DISTANCE = "distance";
	public static final String COLUMN_VENUE_WAIT_MIN = "wait_minutes";
	public static final String COLUMN_VENUE_ADDRESS = "address";
	public static final String COLUMN_VENUE_PHONE = "phone";
	public static final String COLUMN_VENUE_HOURS_MONDAY = "hours_monday";
	public static final String COLUMN_VENUE_HOURS_TUESDAY = "hours_tuesday";
	public static final String COLUMN_VENUE_HOURS_WEDNESDAY = "hours_wednesday";
	public static final String COLUMN_VENUE_HOURS_THURSDAY = "hours_thursday";
	public static final String COLUMN_VENUE_HOURS_FRIDAY = "hours_friday";
	public static final String COLUMN_VENUE_HOURS_SATURDAY = "hours_saturday";
	public static final String COLUMN_VENUE_HOURS_SUNDAY = "hours_sunday";
	
	private static final String DATABASE_NAME = "whosinline.db";
	private static final int DATABASE_VERSION = 1;
	
	public SQLiteHelperVenues(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	private void onCreateHelper(SQLiteDatabase db, String tableName) {
		// Database creation SQL statement.
		String databaseCreateStat = "create table " + tableName + "(" 
				+ COLUMN_ID + " integer primary key AUTOINCREMENT, "
				+ COLUMN_VENUE_NAME + " tinytext not null, "
				+ COLUMN_VENUE_IMAGE_PATH + " text not null, "
				+ COLUMN_VENUE_IMAGE_RESOURCE + " int not null, "
				+ COLUMN_VENUE_TYPE + " tinytext not null, "
				+ COLUMN_VENUE_RATING + " float(2,1) not null, "
				+ COLUMN_VENUE_DISTANCE + " float not null, "
				+ COLUMN_VENUE_WAIT_MIN + " int not null, "
				+ COLUMN_VENUE_ADDRESS + " text not null, "
				+ COLUMN_VENUE_PHONE + " text not null, "
				+ COLUMN_VENUE_HOURS_MONDAY + " text not null, "
				+ COLUMN_VENUE_HOURS_TUESDAY + " text not null, "
				+ COLUMN_VENUE_HOURS_WEDNESDAY + " text not null, "
				+ COLUMN_VENUE_HOURS_THURSDAY + " text not null, "
				+ COLUMN_VENUE_HOURS_FRIDAY + " text not null, "
				+ COLUMN_VENUE_HOURS_SATURDAY + " text not null, "
				+ COLUMN_VENUE_HOURS_SUNDAY + " text not null);";

		db.execSQL(databaseCreateStat);
	}

	private void onUpgradeHelper(SQLiteDatabase db, String tableName, int oldVersion, int newVersion) {
		Log.w(SQLiteHelperVenues.class.getName(), "Upgrading database from version " + oldVersion
				+ " to " + newVersion + ", which will destroy all old data.");
		db.execSQL("DROP TABLE IF EXISTS " + tableName);
		onCreate(db);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		onCreateHelper(db, TABLE_NAME_NEARBY);
		onCreateHelper(db, TABLE_NAME_RECENT);
		onCreateHelper(db, TABLE_NAME_FAVORITE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgradeHelper(db, TABLE_NAME_NEARBY, oldVersion, newVersion);
		onUpgradeHelper(db, TABLE_NAME_RECENT, oldVersion, newVersion);
		onUpgradeHelper(db, TABLE_NAME_FAVORITE, oldVersion, newVersion);
	}
}