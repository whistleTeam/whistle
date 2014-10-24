package com.example.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FavoriteTable {
	 // Database table
	  public static final String TABLE_FAVORITE = "favorite";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_PLAYER_ID = "player_id";
	  public static final String COLUMN_SPORT_ID = "sport_id";

	  // Database creation SQL statement
	  private static final String DATABASE_CREATE = "CREATE TABLE "  
	      + TABLE_FAVORITE
	      + "(" 
	      + COLUMN_ID + " INTEGER PRIMARY KEY," 
	      + COLUMN_PLAYER_ID + " INTEGER," + COLUMN_SPORT_ID + " INTEGER"
	      + ")";

	  public static void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) {
	    Log.w(GameTable.class.getName(), "Upgrading database from version "
	        + oldVersion + " to " + newVersion
	        + ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);
	    onCreate(database);
	  }


}
