package com.example.whistle;

import java.util.List;

import com.example.database.MySQLiteHelper;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {
	 // Database Helper
	MySQLiteHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//test database(Person, Game, Sport)
		db = new MySQLiteHelper(getApplicationContext());
		Person p1 = new Person("00", "00", "00", "00");
		Person p2 = new Person("01", "00", "00", "00");
		Person p3 = new Person("02", "00", "00", "00");
		Person p4 = new Person("03", "00", "00", "00");
		Person p5 = new Person("04", "00", "00", "00");

		long p1_id = db.createPerson(p1);
        long p2_id = db.createPerson(p2);
        long p3_id = db.createPerson(p3);
        long p4_id = db.createPerson(p4);
 
        Log.d("Person Count", "Person Count: " + db.getAllPersons().size());
 
     // Getting all persons
        Log.d("Get persons", "Getting All persons");
 
        List<Person> allPersons = db.getAllPersons();
        for (Person person : allPersons) {
            Log.d("Person", person.getName());
        }
        
        // Deleting a Person
        Log.d("Delete Person", "Deleting a Person"); 
        db.deletePerson(p1_id);
 
        
        
        
        Game g1 = new Game("00", "00", "00", "00");
        Game g2 = new Game("01", "00", "00", "00");
        Game g3 = new Game("02", "00", "00", "00");
        Game g4 = new Game("03", "00", "00", "00");
        Game g5 = new Game("04", "00", "00", "00");

		long g1_id = db.createGame(g1);
        long g2_id = db.createGame(g2);
        long g3_id = db.createGame(g3);
        long g4_id = db.createGame(g4);
 
        Log.d("Game Count", "Game Count: " + db.getAllGames().size());
 
     // Getting all persons
        Log.d("Get games", "Getting All games");
 
        List<Game> allGames = db.getAllGames();
        for (Game game : allGames) {
            Log.d("Person", game.getDescription());
        }
        
        // Deleting a Game
        Log.d("Delete Game", "Deleting a Game"); 
        db.deletePerson(g1_id);
 
        
        
        
        
        Sport s1 = new Sport("00");
        Sport s2 = new Sport("01");
        Sport s3 = new Sport("02");
        Sport s4 = new Sport("03");
        Sport s5 = new Sport("04");

		long s1_id = db.createSport(s1);
        long s2_id = db.createSport(s2);
        long s3_id = db.createSport(s3);
        long s4_id = db.createSport(s4);
 
        Log.d("Sport Count", "Sport Count: " + db.getAllSports().size());
 
     // Getting all Sports
        Log.d("Get Sports", "Getting All Sports");
 
        List<Sport> allSports = db.getAllSports();
        for (Sport sport : allSports) {
            Log.d("Sport", sport.getName());
        }
        
        // Deleting a Sport
        Log.d("Delete Sport", "Deleting a Sport"); 
        db.deleteSport(p1_id);
 

        // Don't forget to close database connection
        db.closeDB();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
