package no.hvl.dat153.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import no.hvl.dat153.R;
import no.hvl.dat153.classes.Database;
import no.hvl.dat153.classes.Person;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Person> database = ((Database) this.getApplication()).getDatabase();

        if (database.isEmpty()) {
            ((Database) this.getApplication()).addStudent(new Person(getDrawable(R.drawable.bendik), "Bendik"));
            ((Database) this.getApplication()).addStudent(new Person(getDrawable(R.drawable.jon), "Jon"));
            ((Database) this.getApplication()).addStudent(new Person(getDrawable(R.drawable.thomas), "Thomas"));
        }
    }

    public void showDatabase(View view) {
        Intent i = new Intent(this, DatabaseActivity.class);
        startActivity(i);
    }

    public void startQuiz(View view) {
        Intent i = new Intent(this, QuizActivity.class);
        startActivity(i);
    }

    public void addNewStudent(View view) {
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }
}