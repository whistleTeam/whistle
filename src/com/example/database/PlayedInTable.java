package com.example.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PlayedInTable {
	 // Database table
	  public static final String TABLE_PLAYEDIN = "playedin";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_PLAYER_ID = "player_id";
	  public static final String COLUMN_GAME_ID = "game_id";

	  // Database creation SQL statement
	  private static final String DATABASE_CREATE = "CREATE TABLE " 
	      + TABLE_PLAYEDIN
	      + "(" 
	      + COLUMN_ID + " INTEGER PRIMARY KEY,"  
	      + COLUMN_PLAYER_ID + " INTEGER," + COLUMN_GAME_ID + " INTEGER"
	      + ")";

	  public static void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) {
	    Log.w(GameTable.class.getName(), "Upgrading database from version "
	        + oldVersion + " to " + newVersion
	        + ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYEDIN);
	    onCreate(database);
	  }

}
