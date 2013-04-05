package edu.uiuc.whosinline.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteHelperVenueNearby extends SQLiteHelperBase {

	public static final String TABLE_NAME = "venue_nearby";

	public SQLiteHelperVenueNearby(Context context) {
		super(context);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		onCreateHelper(db, TABLE_NAME);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgradeHelper(db, TABLE_NAME, oldVersion, newVersion);
	}
}