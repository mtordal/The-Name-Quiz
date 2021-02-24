package no.hvl.dat153.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import no.hvl.dat153.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.quiz_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.menu_home:
                finish();
                return true;

            case R.id.menu_quiz:
                finish();
                i = new Intent(this, QuizActivity.class);
                startActivity(i);
                return true;

            case R.id.menu_database:
                finish();
                i = new Intent(this, DatabaseActivity.class);
                startActivity(i);
                return true;

            case R.id.menu_add:
                finish();
                i = new Intent(this, AddActivity.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}