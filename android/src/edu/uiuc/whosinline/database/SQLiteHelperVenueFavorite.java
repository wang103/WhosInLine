package edu.uiuc.whosinline.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteHelperVenueFavorite extends SQLiteHelperBase {

	private static final String TABLE_NAME = "venue_favorite";

	public SQLiteHelperVenueFavorite(Context context) {
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