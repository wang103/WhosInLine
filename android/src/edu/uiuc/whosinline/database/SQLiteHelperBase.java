package edu.uiuc.whosinline.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class SQLiteHelperBase extends SQLiteOpenHelper {

	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_VENUE_NAME = "name";
	private static final String COLUMN_VENUE_IMAGE_PATH = "image_path";
	private static final String COLUMN_VENUE_TYPE = "type";
	private static final String COLUMN_VENUE_RATING = "rating";
	private static final String COLUMN_VENUE_WAIT_MIN = "wait_minutes";

	private static final String DATABASE_NAME = "whosinline.db";
	private static final int DATABASE_VERSION = 1;

	public SQLiteHelperBase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	protected void onCreateHelper(SQLiteDatabase db, String tableName) {
		// Database creation SQL statement.
		String databaseCreateStat = "create table " + tableName + "(" 
				+ COLUMN_ID + " integer primary key, "
				+ COLUMN_VENUE_NAME + " tinytext not null, "
				+ COLUMN_VENUE_IMAGE_PATH + " text not null, "
				+ COLUMN_VENUE_TYPE + " tinytext not null, "
				+ COLUMN_VENUE_RATING + " tinyint(1) not null, "
				+ COLUMN_VENUE_WAIT_MIN + " int not null);";

		db.execSQL(databaseCreateStat);
	}

	protected void onUpgradeHelper(SQLiteDatabase db, String tableName, int oldVersion, int newVersion) {
		Log.w(SQLiteHelperBase.class.getName(), "Upgrading database from version " + oldVersion
				+ " to " + newVersion + ", which will destroy all old data.");
		db.execSQL("DROP TABLE IF EXISTS " + tableName);
		onCreate(db);
	}
}