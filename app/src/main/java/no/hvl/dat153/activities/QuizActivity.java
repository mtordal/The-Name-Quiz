package no.hvl.dat153.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import no.hvl.dat153.R;
import no.hvl.dat153.classes.Database;
import no.hvl.dat153.classes.Person;

public class QuizActivity extends AppCompatActivity {
    TextView title;
    ImageView image;
    TextView name;
    Button button;
    TextView scoreView;
    private Iterator<Person> iter;
    private Person student;
    private int score;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        title = findViewById(R.id.title);
        image = findViewById(R.id.studentImage);
        name = findViewById(R.id.studentName);
        button = findViewById(R.id.button8);
        scoreView = findViewById(R.id.score);
        score = 0;
        total = 0;

        button.setOnClickListener(v -> guessSubmitted());

        ArrayList<Person> database = ((Database) this.getApplication()).getDatabase();
        Collections.shuffle(database);
        iter = database.iterator();

        newStudent();
    }

    public void newStudent() {
        if (iter.hasNext()) {
            student = iter.next();
            image.setImageDrawable(student.getImage());
            name.setText("");
        } else {
            title.setText("Final result");
            image.setImageDrawable(null);
            name.setText("");
            button.setText("End quiz");
            button.setOnClickListener(v -> endQuiz());
            name.onEditorAction(EditorInfo.IME_ACTION_DONE);
        }
    }

    public void guessSubmitted() {
        total++;
        String guess = name.getText().toString();
        String answer = student.getName();

        if (guess.toLowerCase().equals(answer.toLowerCase())) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Incorrect, correct answer was " + answer + ".", Toast.LENGTH_LONG).show();
        }

        scoreView.setText(score + "/" + total);
        name.onEditorAction(EditorInfo.IME_ACTION_DONE);
        newStudent();
    }

    public void endQuiz() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}