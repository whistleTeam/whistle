package com.example.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PersonTable {
	 // Database table
	  public static final String TABLE_PERSON = "person";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_NAME = "name";
	  public static final String COLUMN_USERNAME = "username";
	  public static final String COLUMN_LOCATION = "location";
	  public static final String COLUMN_PHONENUMBER= "phonenumber";
	
	  // Database creation SQL statement
	  private static final String DATABASE_CREATE = "CREATE TABLE " 
	      + TABLE_PERSON
	      + "(" 
	      + COLUMN_ID + " INTEGER PRIMARY KEY," 
	      + COLUMN_NAME + " TEXT," 
	      + COLUMN_USERNAME + " TEXT,"
	      + COLUMN_LOCATION + " TEXT,"
	      + COLUMN_PHONENUMBER + " TEXT" 
	      + ")";

	  public static void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) {
	    Log.w(GameTable.class.getName(), "Upgrading database from version "
	        + oldVersion + " to " + newVersion
	        + ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
	    onCreate(database);
	  }

}
