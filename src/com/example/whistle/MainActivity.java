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
		
		//test database(Person)
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
