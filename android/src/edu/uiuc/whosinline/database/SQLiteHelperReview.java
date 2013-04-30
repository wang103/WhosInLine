package edu.uiuc.whosinline.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelperReview extends SQLiteOpenHelper {

	public static final String TABLE_NAME_REVIEW = "review";

	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_REVIEW_VENUE_NAME = "venue_name";
	public static final String COLUMN_REVIEW_TITLE = "title";
	public static final String COLUMN_REVIEW_CONTENT = "content";
	public static final String COLUMN_REVIEW_RATING = "rating";
	public static final String COLUMN_REVIEW_USER_NAME = "user_name";

	private static final String DATABASE_NAME = "whosinline_review.db";
	private static final int DATABASE_VERSION = 1;
	
	public SQLiteHelperReview(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Database creation SQL statement.
		String databaseCreateStat = "create table " + TABLE_NAME_REVIEW + "("
				+ COLUMN_ID + " integer primary key AUTOINCREMENT, "
				+ COLUMN_REVIEW_VENUE_NAME + " text not null, "
				+ COLUMN_REVIEW_TITLE + " text not null, "
				+ COLUMN_REVIEW_CONTENT + " text not null, "
				+ COLUMN_REVIEW_RATING + " float(2,1) not null, "
				+ COLUMN_REVIEW_USER_NAME + " text not null);";

		db.execSQL(databaseCreateStat);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteHelperReview.class.getName(), "Upgrading database from version " + oldVersion
				+ " to " + newVersion + ", which will destroy all old data.");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_REVIEW);
		onCreate(db);
	}
}