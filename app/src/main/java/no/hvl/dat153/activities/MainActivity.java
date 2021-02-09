package no.hvl.dat153.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import no.hvl.dat153.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDatabase(View view) { // Start database activity
        Intent i = new Intent(this, DatabaseActivity.class);
        startActivity(i);
    }

    public void startQuiz(View view) { // Start quiz activity
        Intent i = new Intent(this, QuizActivity.class);
        startActivity(i);
    }

    public void addNewStudent(View view) { // Start add activity
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }
}