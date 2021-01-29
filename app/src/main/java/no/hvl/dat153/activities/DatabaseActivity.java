package no.hvl.dat153.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import no.hvl.dat153.R;
import no.hvl.dat153.adapters.DatabaseAdapter;
import no.hvl.dat153.classes.Database;
import no.hvl.dat153.classes.Person;

public class DatabaseActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        listView = findViewById(R.id.listView);

        ArrayList<Person> database = ((Database) this.getApplication()).getDatabase();

        DatabaseAdapter databaseAdapter = new DatabaseAdapter(this, R.layout.database_view, database);

        listView.setAdapter(databaseAdapter);
    }

    public void addNewStudent(View view) {
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }
}