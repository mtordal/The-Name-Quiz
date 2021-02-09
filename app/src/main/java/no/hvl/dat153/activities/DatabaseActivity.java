package no.hvl.dat153.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import no.hvl.dat153.R;
import no.hvl.dat153.adapters.DatabaseAdapter;
import no.hvl.dat153.classes.PersonDatabase;
import no.hvl.dat153.classes.Person;

public class DatabaseActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        listView = findViewById(R.id.listView); // Get listview

        // Get database
        PersonDatabase db = PersonDatabase.getInstance(this);
        List<Person> database = db.personDao().getDb();

        DatabaseAdapter databaseAdapter = new DatabaseAdapter(this, R.layout.database_view, database); // Create adapter on database view layout

        listView.setAdapter(databaseAdapter); // Set adapter to the listview
    }

    public void addNewStudent(View view) { // Start add activity
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }
}