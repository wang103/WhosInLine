package edu.uiuc.whosinline.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class SQLiteHelperBase extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "whosinline.db";
	private static final int DATABASE_VERSION = 1;

	public SQLiteHelperBase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
}