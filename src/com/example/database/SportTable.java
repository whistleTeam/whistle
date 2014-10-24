package com.example.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SportTable {
	 // Database table
	  public static final String TABLE_SPORT = "sport";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_NAME = "name";
	
	  // Database creation SQL statement
	  private static final String DATABASE_CREATE = "CREATE TABLE " 
	      + TABLE_SPORT
	      + "(" 
	      + COLUMN_ID + " INTEGER PRIMARY KEY,"   
	      + COLUMN_NAME + " TEXT" 
	      + ")";

	  public static void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) {
	    Log.w(GameTable.class.getName(), "Upgrading database from version "
	        + oldVersion + " to " + newVersion
	        + ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_SPORT);
	    onCreate(database);
	  }

}
