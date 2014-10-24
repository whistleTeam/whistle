package com.example.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GameTable {
	 // Database table
	  public static final String TABLE_GAME = "game";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_TIME = "time";
	  public static final String COLUMN_DATE = "date";
	  public static final String COLUMN_LOCATION = "location";
	  public static final String COLUMN_DESCRIPTION = "description";


	  // Database creation SQL statement
	  private static final String DATABASE_CREATE = "CREATE TABLE " 
	      + TABLE_GAME
	      + "(" 
	      + COLUMN_ID + " INTEGER PRIMARY KEY,"  
	      + COLUMN_TIME + " TEXT," 
	      + COLUMN_DATE + " TEXT,"
	      + COLUMN_LOCATION + " TEXT," 
	      + COLUMN_DESCRIPTION + " TEXT" 
	      + ")";

	  public static void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) {
	    Log.w(GameTable.class.getName(), "Upgrading database from version "
	        + oldVersion + " to " + newVersion
	        + ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME);
	    onCreate(database);
	  }

}
