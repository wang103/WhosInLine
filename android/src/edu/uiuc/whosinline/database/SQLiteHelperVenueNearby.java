package edu.uiuc.whosinline.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteHelperVenueNearby extends SQLiteHelperBase {

	public SQLiteHelperVenueNearby(Context context) {
		super(context);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}