package com.example.database;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.whistle.Person;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	   // Logcat tag
     private static final String LOG = MySQLiteHelper.class.getName();
    
	  private static final String DATABASE_NAME = "mydb.db";
	  private static final int DATABASE_VERSION = 1;

	  public MySQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  // Method is called during creation of the database
	  @Override
	  public void onCreate(SQLiteDatabase database) {
		PersonTable.onCreate(database);
		GameTable.onCreate(database);
	    SportTable.onCreate(database);
	    PlayedInTable.onCreate(database);
	    OwnsTable.onCreate(database);
	    FavoriteTable.onCreate(database);

	  }

	  // Method is called during an upgrade of the database,
	  // e.g. if you increase the database version
	  @Override
	  public void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) {
		  PersonTable.onUpgrade(database, oldVersion, newVersion);
		  GameTable.onUpgrade(database, oldVersion, newVersion);
		  SportTable.onUpgrade(database, oldVersion, newVersion);
		  PlayedInTable.onUpgrade(database, oldVersion, newVersion);
		  GameTable.onUpgrade(database, oldVersion, newVersion);
		  FavoriteTable.onUpgrade(database, oldVersion, newVersion);

	  }
	  
	  // ------------------------ "person" table methods ----------------//
	  
	     /**
	     * Creating a person
	     */
	  public long createPerson(Person person)
	  {
		  SQLiteDatabase db = this.getWritableDatabase();

		  ContentValues values = new ContentValues();
	      values.put("name", person.getName()); // Contact Name
	      values.put("phonenumber", person.getPhoneNumber()); // Contact Phone
	      values.put("location", person.getLocation()); // Contact Name
	      values.put("username", person.getUserName()); // Contact Phone

	        long person_id = db.insert(PersonTable.TABLE_PERSON, null, values);
     
	        return person_id;
	
	  }
	  
	  
	   /**
	     * get a person
	     */
	    public Person getPerson(long person_id) {
	        SQLiteDatabase db = this.getReadableDatabase();
	 
	        String selectQuery = "SELECT  * FROM " + PersonTable.TABLE_PERSON + " WHERE "
	                + PersonTable.COLUMN_ID + " = " + person_id;
	 
	        Log.e(LOG, selectQuery);
	 
	        Cursor c = db.rawQuery(selectQuery, null);
	 
	        if (c != null)
	            c.moveToFirst();
	 
	        Person p = new Person();
	        p.setID(c.getInt(c.getColumnIndex(PersonTable.COLUMN_ID)));
	        p.setName((c.getString(c.getColumnIndex(PersonTable.COLUMN_NAME))));
	        p.setUserName(c.getString(c.getColumnIndex(PersonTable.COLUMN_USERNAME)));
	        p.setLocation(c.getString(c.getColumnIndex(PersonTable.COLUMN_LOCATION)));
	        p.setPhoneNumber(c.getString(c.getColumnIndex(PersonTable.COLUMN_PHONENUMBER)));
	        return p;
	    }
	 
	    /**
	     * getting all persons
	     * */
	    public List<Person> getAllPersons() {
	        List<Person> people = new ArrayList<Person>();
	        String selectQuery = "SELECT  * FROM " + PersonTable.TABLE_PERSON;
	 
	        Log.e(LOG, selectQuery);
	 
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor c = db.rawQuery(selectQuery, null);
	 
	        // looping through all rows and adding to list
	        if (c.moveToFirst()) {
	            do {
	            	Person p = new Person();
	            	p.setID(c.getInt(c.getColumnIndex(PersonTable.COLUMN_ID)));
	      	        p.setName((c.getString(c.getColumnIndex(PersonTable.COLUMN_NAME))));
	      	        p.setUserName(c.getString(c.getColumnIndex(PersonTable.COLUMN_USERNAME)));
	      	        p.setLocation(c.getString(c.getColumnIndex(PersonTable.COLUMN_LOCATION)));
	      	        p.setPhoneNumber(c.getString(c.getColumnIndex(PersonTable.COLUMN_PHONENUMBER)));
	 
	                // adding to people list
	                people.add(p);
	            } while (c.moveToNext());
	        }
	 
	        return people;
	    }
	  
	    /**
	     * getting person count
	     */
	    public int getPersonCount() {
	        String countQuery = "SELECT  * FROM " + PersonTable.TABLE_PERSON;
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(countQuery, null);
	 
	        int count = cursor.getCount();
	        cursor.close();
	 
	        // return count
	        return count;
	    }
	 
	    /**
	     * Updating a person
	     */
	    public int updatePerson(Person p) {
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        ContentValues values = new ContentValues();
	        values.put("name", p.getName()); // Contact Name
		    values.put("phonenumber", p.getPhoneNumber()); // Contact Phone
		    values.put("location", p.getLocation()); // Contact Name
		    values.put("username", p.getUserName()); // Contact Phone

	 
	        // updating row
	        return db.update(PersonTable.TABLE_PERSON, values, PersonTable.COLUMN_ID + " = ?",
	                new String[] { String.valueOf(p.getId()) });
	    }
	    
	    /**
	     * Deleting a person
	     */
	    public void deletePerson(long person_id) {
	        SQLiteDatabase db = this.getWritableDatabase();
	        db.delete(PersonTable.TABLE_PERSON, PersonTable.COLUMN_ID + " = ?",
	                new String[] { String.valueOf(person_id) });
	    }
	    
	 // closing database
	    public void closeDB() {
	        SQLiteDatabase db = this.getReadableDatabase();
	        if (db != null && db.isOpen())
	            db.close();
	    }
	 
	    /**
	     * get datetime
	     * */
	    private String getDateTime() {
	        SimpleDateFormat dateFormat = new SimpleDateFormat(
	                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
	        Date date = new Date();
	        return dateFormat.format(date);
	    }


}
