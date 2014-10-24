package com.example.database;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.whistle.Game;
import com.example.whistle.Person;
import com.example.whistle.Sport;

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
	      values.put("name", person.getName()); 
	      values.put("phonenumber", person.getPhoneNumber());      
	      values.put("location", person.getLocation()); 
	      values.put("username", person.getUserName());      

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
	        p.setId(c.getInt(c.getColumnIndex(PersonTable.COLUMN_ID)));
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
	            	p.setId(c.getInt(c.getColumnIndex(PersonTable.COLUMN_ID)));
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
	        values.put("name", p.getName()); 
		    values.put("phonenumber", p.getPhoneNumber());      
		    values.put("location", p.getLocation()); 
		    values.put("username", p.getUserName());      

	 
	        // updating row
	        return db.update(PersonTable.TABLE_PERSON, values, PersonTable.COLUMN_ID + " = ?",
	                new String[] { String.valueOf(p.getId()) });
	    }
	    
	    /**
	     * Deleting a person without the persons games
	     */
	    public void deletePerson(long person_id) {
	        SQLiteDatabase db = this.getWritableDatabase();
	        db.delete(PersonTable.TABLE_PERSON, PersonTable.COLUMN_ID + " = ?",
	                new String[] { String.valueOf(person_id) });
	    }
	    
	    
	    /**
	     * Creating a game
	     */
	  public long createGame(Game game)
	  {
		  SQLiteDatabase db = this.getWritableDatabase();

		  ContentValues values = new ContentValues();
	      values.put("description", game.getDescription()); 
	      values.put("time", game.getTime());      
	      values.put("location", game.getLocation()); 
	      values.put("date", game.getDate());      

	        long game_id = db.insert(GameTable.TABLE_GAME, null, values);
     
	        return game_id;
	
	  }
	  
	  
	   /**
	     * get a game
	     */
	    public Game getGame(long game_id) {
	        SQLiteDatabase db = this.getReadableDatabase();
	 
	        String selectQuery = "SELECT  * FROM " + GameTable.TABLE_GAME + " WHERE "
	                + GameTable.COLUMN_ID + " = " + game_id;
	 
	        Log.e(LOG, selectQuery);
	 
	        Cursor c = db.rawQuery(selectQuery, null);
	 
	        if (c != null)
	            c.moveToFirst();
	 
	        Game g = new Game();
	        g.setId(c.getInt(c.getColumnIndex(GameTable.COLUMN_ID)));
	        g.setDate(c.getString(c.getColumnIndex(GameTable.COLUMN_DATE)));
	        g.setTime(c.getString(c.getColumnIndex(GameTable.COLUMN_TIME)));
	        g.setDescription(c.getString(c.getColumnIndex(GameTable.COLUMN_DESCRIPTION)));
	        g.setLocation(c.getString(c.getColumnIndex(GameTable.COLUMN_LOCATION)));
	        return g;
	    }
	 
	    /**
	     * getting all Games
	     * */
	    public List<Game> getAllGames() {
	        List<Game> games = new ArrayList<Game>();
	        String selectQuery = "SELECT  * FROM " + GameTable.TABLE_GAME;
	 
	        Log.e(LOG, selectQuery);
	 
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor c = db.rawQuery(selectQuery, null);
	 
	        // looping through all rows and adding to list
	        if (c.moveToFirst()) {
	            do {
	            	Game g = new Game();
	                g.setId(c.getInt(c.getColumnIndex(GameTable.COLUMN_ID)));
	    	        g.setDate(c.getString(c.getColumnIndex(GameTable.COLUMN_DATE)));
	    	        g.setTime(c.getString(c.getColumnIndex(GameTable.COLUMN_TIME)));
	    	        g.setDescription(c.getString(c.getColumnIndex(GameTable.COLUMN_DESCRIPTION)));
	    	        g.setLocation(c.getString(c.getColumnIndex(GameTable.COLUMN_LOCATION)));
	    	  	 
	                // adding to games list
	                games.add(g);
	            } while (c.moveToNext());
	        }
	 
	        return games;
	    }
	  
	    /**
	     * getting Game count
	     */
	    public int getGameCount() {
	        String countQuery = "SELECT  * FROM " + GameTable.TABLE_GAME;
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(countQuery, null);
	 
	        int count = cursor.getCount();
	        cursor.close();
	 
	        // return count
	        return count;
	    }
	 
	    /**
	     * Updating a Game
	     */
	    public int updateGame(Game g) {
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        ContentValues values = new ContentValues();
	        values.put("description", g.getDescription()); 
		      values.put("time", g.getTime());      
		      values.put("location", g.getLocation()); 
		      values.put("date", g.getDate());      

	 
	        // updating row
	        return db.update(GameTable.TABLE_GAME, values, GameTable.COLUMN_ID + " = ?",
	                new String[] { String.valueOf(g.getId()) });
	    }
	    
	    /**
	     * Deleting a Game
	     */
	    public void deleteGame(long game_id) {
	        SQLiteDatabase db = this.getWritableDatabase();
	        db.delete(GameTable.TABLE_GAME, GameTable.COLUMN_ID + " = ?",
	                new String[] { String.valueOf(game_id) });
	    }
	    
	    /**
	     * Creating a sport
	     */
	  public long createSport(Sport sport)
	  {
		  SQLiteDatabase db = this.getWritableDatabase();

		  ContentValues values = new ContentValues();
	      values.put("name", sport.getName());
	   
	        long sport_id = db.insert(SportTable.TABLE_SPORT, null, values);
     
	        return sport_id;
	
	  }
	  
	  
	   /**
	     * get a sport
	     */
	    public Sport getSport(long sport_id) {
	        SQLiteDatabase db = this.getReadableDatabase();
	 
	        String selectQuery = "SELECT  * FROM " + SportTable.TABLE_SPORT + " WHERE "
	                + SportTable.COLUMN_ID + " = " + sport_id;
	 
	        Log.e(LOG, selectQuery);
	 
	        Cursor c = db.rawQuery(selectQuery, null);
	 
	        if (c != null)
	            c.moveToFirst();
	 
	        Sport s = new Sport();
	        s.setId(c.getInt(c.getColumnIndex(SportTable.COLUMN_ID)));
	        s.setName(c.getString(c.getColumnIndex(SportTable.COLUMN_NAME)));
	        return s;
	    }
	 
	    /**
	     * getting all Sports
	     * */
	    public List<Sport> getAllSports() {
	        List<Sport> sports = new ArrayList<Sport>();
	        String selectQuery = "SELECT  * FROM " + SportTable.TABLE_SPORT;
	 
	        Log.e(LOG, selectQuery);
	 
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor c = db.rawQuery(selectQuery, null);
	 
	        // looping through all rows and adding to list
	        if (c.moveToFirst()) {
	            do {
	                Sport s = new Sport();
	    	        s.setId(c.getInt(c.getColumnIndex(SportTable.COLUMN_ID)));
	    	        s.setName(c.getString(c.getColumnIndex(SportTable.COLUMN_NAME)));
	    	 	    	  	 
	                // adding to people list
	                sports.add(s);
	            } while (c.moveToNext());
	        }
	 
	        return sports;
	    }
	  
	    /**
	     * getting Sport count
	     */
	    public int getSportCount() {
	        String countQuery = "SELECT  * FROM " + SportTable.TABLE_SPORT;
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(countQuery, null);
	 
	        int count = cursor.getCount();
	        cursor.close();
	 
	        // return count
	        return count;
	    }
	 
	    /**
	     * Updating a Sport
	     */
	    public int updateSport(Sport s) {
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        ContentValues values = new ContentValues();
		      values.put("name", s.getName());

	 
	        // updating row
	        return db.update(SportTable.TABLE_SPORT, values, SportTable.COLUMN_ID + " = ?",
	                new String[] { String.valueOf(s.getId()) });
	    }
	    
	    /**
	     * Deleting a Sport
	     */
	    public void deleteSport(long sport_id) {
	        SQLiteDatabase db = this.getWritableDatabase();
	        db.delete(SportTable.TABLE_SPORT, SportTable.COLUMN_ID + " = ?",
	                new String[] { String.valueOf(sport_id) });
	    }
	    
	    
	    /*
	     * getting all games a player owns
	     * */
	    public List<Game> getAllGamesOwnedByPlayer(String player_name) {
	        List<Game> games = new ArrayList<Game>();
	     
	        String selectQuery = "SELECT  * FROM " + GameTable.TABLE_GAME + " game, "
	                + PersonTable.TABLE_PERSON + " player, " + OwnsTable.TABLE_OWNS + " ownstab WHERE player."
	                + PersonTable.TABLE_PERSON + " = '" + player_name + "'" + " AND player." + OwnsTable.COLUMN_ID
	                + " = " + "ownstab." + OwnsTable.COLUMN_PLAYER_ID + " AND game." + OwnsTable.COLUMN_ID + " = "
	                + "ownstab." + OwnsTable.COLUMN_GAME_ID;
	     
	        Log.e(LOG, selectQuery);
	     
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor c = db.rawQuery(selectQuery, null);
	     
	        // looping through all rows and adding to list
	        if (c.moveToFirst()) {
	            do {
	                Game g = new Game();
	                g.setId(c.getInt(c.getColumnIndex(GameTable.COLUMN_ID)));
	    	        g.setDate(c.getString(c.getColumnIndex(GameTable.COLUMN_DATE)));
	    	        g.setTime(c.getString(c.getColumnIndex(GameTable.COLUMN_TIME)));
	    	        g.setDescription(c.getString(c.getColumnIndex(GameTable.COLUMN_DESCRIPTION)));
	    	        g.setLocation(c.getString(c.getColumnIndex(GameTable.COLUMN_LOCATION)));
	 
	                // adding to games list
	                games.add(g);
	            } while (c.moveToNext());
	        }
	     
	        return games;
	    }
	    
	    
	    /*
	     * getting all games a player has playedin
	     * */
	    public List<Game> getAllGamesPlayedIn(String player_name) {
	        List<Game> games = new ArrayList<Game>();
	     
	        String selectQuery = "SELECT  * FROM " + GameTable.TABLE_GAME + " game, "
	                + PersonTable.TABLE_PERSON + " player, " + PlayedInTable.TABLE_PLAYEDIN + " playedin WHERE player."
	                + PersonTable.TABLE_PERSON + " = '" + player_name + "'" + " AND player." + PlayedInTable.COLUMN_ID
	                + " = " + "playedin." + PlayedInTable.COLUMN_PLAYER_ID + " AND game." + PlayedInTable.COLUMN_ID + " = "
	                + "playedin." + PlayedInTable.COLUMN_GAME_ID;
	     
	        Log.e(LOG, selectQuery);
	     
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor c = db.rawQuery(selectQuery, null);
	     
	        // looping through all rows and adding to list
	        if (c.moveToFirst()) {
	            do {
	                Game g = new Game();
	                g.setId(c.getInt(c.getColumnIndex(GameTable.COLUMN_ID)));
	    	        g.setDate(c.getString(c.getColumnIndex(GameTable.COLUMN_DATE)));
	    	        g.setTime(c.getString(c.getColumnIndex(GameTable.COLUMN_TIME)));
	    	        g.setDescription(c.getString(c.getColumnIndex(GameTable.COLUMN_DESCRIPTION)));
	    	        g.setLocation(c.getString(c.getColumnIndex(GameTable.COLUMN_LOCATION)));
	 
	                // adding to games list
	                games.add(g);
	            } while (c.moveToNext());
	        }
	     
	        return games;
	    }
	    
	    
	    /*
	     * getting all the favorite sports of a person
	     * */
	    public List<Sport> getAllFavoriteSports(String player_name) {
	        List<Sport> sports = new ArrayList<Sport>();
	     
	        String selectQuery = "SELECT  * FROM " + SportTable.TABLE_SPORT + " game, "
	                + PersonTable.TABLE_PERSON + " player, " + PlayedInTable.TABLE_PLAYEDIN + " playedin WHERE player."
	                + PersonTable.TABLE_PERSON + " = '" + player_name + "'" + " AND player." + PlayedInTable.COLUMN_ID
	                + " = " + "playedin." + PlayedInTable.COLUMN_PLAYER_ID + " AND game." + PlayedInTable.COLUMN_ID + " = "
	                + "playedin." + PlayedInTable.COLUMN_GAME_ID;
	     
	        Log.e(LOG, selectQuery);
	     
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor c = db.rawQuery(selectQuery, null);
	     
	        // looping through all rows and adding to list
	        if (c.moveToFirst()) {
	            do {
	            	Sport s = new Sport();
	                s.setId(c.getInt(c.getColumnIndex(SportTable.COLUMN_ID)));
	    	        s.setName(c.getString(c.getColumnIndex(SportTable.COLUMN_NAME)));
	    	       	 
	                // adding to games list
	    	        sports.add(s);
	            } while (c.moveToNext());
	        }
	     
	        return sports;
	    }
	    
	 // closing database
	    public void closeDB() {
	        SQLiteDatabase db = this.getReadableDatabase();
	        if (db != null && db.isOpen())
	            db.close();
	    }
	    
	    
	    /*
	     * Deleting a player including owned games
	     */
	    public void deletePersonAndOwnedGames(Person person, boolean should_delete_all_owned_games) {
	        SQLiteDatabase db = this.getWritableDatabase();
	     
	        // before deleting tag
	        // check if games under this player should also be deleted
	        if (should_delete_all_owned_games) {
	            // get all games under this player
	            List<Game> allOwnedGames = getAllGamesOwnedByPlayer(person.getName());
	     
	            // delete all games
	            for (Game game : allOwnedGames) {
	                // delete game
	                deleteGame(game.getId());
	            }
	        }
	     
	        // now delete the person
	        db.delete(PersonTable.TABLE_PERSON, PersonTable.COLUMN_ID + " = ?",
	                new String[] { String.valueOf(person.getId()) });
	    }
	    
	    
	    /*
	     * Creating owned game
	     */
	    public long createOwnedGame(long game_id, long player_id) {
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        ContentValues values = new ContentValues();
	        values.put(GameTable.COLUMN_ID, game_id);
	        values.put(PersonTable.COLUMN_ID, player_id);
	 
	        long id = db.insert(OwnsTable.TABLE_OWNS, null, values);
	 
	        return id;
	    }
	    
	    /*
	     * Creating favorites
	     */
	    public long createFavoriteSport(long sport_id, long player_id) {
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        ContentValues values = new ContentValues();
	        values.put(SportTable.COLUMN_ID, sport_id);
	        values.put(PersonTable.COLUMN_ID, player_id);
	 
	        long id = db.insert(FavoriteTable.TABLE_FAVORITE, null, values);
	 
	        return id;
	    }

	    /*
	     * Creating game played in
	     */
	    public long createPlayedIn(long game_id, long player_id) {
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        ContentValues values = new ContentValues();
	        values.put(GameTable.COLUMN_ID, game_id);
	        values.put(PersonTable.COLUMN_ID, player_id);
	 
	        long id = db.insert(PlayedInTable.TABLE_PLAYEDIN, null, values);
	 
	        return id;
	    }
	    
	    /**
	     removing player and game from the OwnsTable and PlayedInTable
	     query to find player_id and game_id and remove the row
	     // Updating a  favorite
	    // Following method will remove the player assigned to a favorite
	     ///
	    public int updateFavorite(long id, long player_id) {
	        SQLiteDatabase db = this.getWritableDatabase();
	     
	        ContentValues values = new ContentValues();
	        values.put(PersonTable.TABLE_PERSON, player_id);
	     
	        // updating row
	        return db.update(SportTable.TABLE_SPORT, values, SportTable.COLUMN_ID + " = ?",
	                new String[] { String.valueOf(id) });
	    }
	    
	    //
	     // Updating an owned game
	     // Following method will remove the player from owning the game
	     //
	    public int updateOwnedGame(long id, long player_id) {
	        SQLiteDatabase db = this.getWritableDatabase();
	     
	        ContentValues values = new ContentValues();
	        values.put(PersonTable.TABLE_PERSON, player_id);
	     
	        // updating row
	        return db.update(GameTable.TABLE_GAME, values, GameTable.COLUMN_ID + " = ?",
	                new String[] { String.valueOf(id) });
	    }
	    
	    //
	     // Updating an game playedin
	     // Following method will remove the player from playing in game
	     //
	    public int updatePlayedIn(long id, long player_id) {
	        SQLiteDatabase db = this.getWritableDatabase();
	     
	        ContentValues values = new ContentValues();
	        values.put(PersonTable.TABLE_PERSON, player_id);
	     
	        // updating row
	        return db.update(GameTable.TABLE_GAME, values, GameTable.COLUMN_ID + " = ?",
	                new String[] { String.valueOf(id) });
	    }
	    **/
	    
	    
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
