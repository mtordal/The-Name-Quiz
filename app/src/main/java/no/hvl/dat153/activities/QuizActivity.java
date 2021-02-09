package no.hvl.dat153.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import no.hvl.dat153.R;
import no.hvl.dat153.classes.Person;
import no.hvl.dat153.classes.PersonDatabase;

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

        // Find views
        title = findViewById(R.id.title);
        image = findViewById(R.id.studentImage);
        name = findViewById(R.id.studentName);
        button = findViewById(R.id.button8);
        scoreView = findViewById(R.id.score);

        // Track score
        score = 0;
        total = 0;

        button.setOnClickListener(v -> guessSubmitted()); // Listener on submit

        // Get database
        PersonDatabase db = PersonDatabase.getInstance(this);

        // Randomize database
        List<Person> database = db.personDao().getDb();
        Collections.shuffle(database);
        iter = database.iterator();

        newStudent(); // Show student
    }

    public void newStudent() {
        if (iter.hasNext()) { // If there are more students in the list
            student = iter.next();
            image.setImageDrawable(student.getImage());
        } else { // No more students
            title.setText(R.string.resultTitle);
            image.setImageDrawable(null);
            name.setHint("");
            name.setBackgroundResource(android.R.color.transparent);
            name.clearFocus();
            button.setText(R.string.endBtn);
            button.setOnClickListener(v -> endQuiz());
        }

        name.setText("");
        name.onEditorAction(EditorInfo.IME_ACTION_DONE);
    }

    public void guessSubmitted() {
        total++;
        String guess = name.getText().toString();
        String answer = student.getName();

        if (guess.toLowerCase().equals(answer.toLowerCase())) { // Correct guess
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show();
        } else { // Incorrect guess
            Toast.makeText(this, "Incorrect, correct answer was " + answer + ".", Toast.LENGTH_LONG).show();
        }

        scoreView.setText(MessageFormat.format("{0}/{1}", score, total)); // Update score
        name.onEditorAction(EditorInfo.IME_ACTION_DONE); // Hide keyboard
        newStudent();
    }

    public void endQuiz() { // Start main activity
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}