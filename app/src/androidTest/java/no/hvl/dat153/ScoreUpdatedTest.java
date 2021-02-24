package no.hvl.dat153;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import no.hvl.dat153.activities.QuizActivity;
import no.hvl.dat153.classes.Person;
import no.hvl.dat153.classes.PersonDao;
import no.hvl.dat153.classes.PersonDatabase;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ScoreUpdatedTest {

    private QuizActivity quizActivity;

    @Rule
    public ActivityScenarioRule<QuizActivity> activityRule =
            new ActivityScenarioRule<>(QuizActivity.class);

    @BeforeClass
    public static void beforeTest() {
        List<Person> database;
        PersonDao personDao;

        Context context = ApplicationProvider.getApplicationContext();
        PersonDatabase personDatabase = PersonDatabase.getInstance(context);
        personDao = personDatabase.personDao();
        database = personDao.getDb();

        if (database.isEmpty()) {
            personDao.addStudent(new Person(ContextCompat.getDrawable(context,
                    R.drawable.bendik), "Bendik"));
        }
    }

    // Test score after submitting correct answer
    @Test
    public void scoreIsCorrect() {
        activityRule.getScenario().onActivity(x -> quizActivity = x);

        assertFalse(quizActivity.shuffledDb.isEmpty());
        String name = quizActivity.shuffledDb.get(0).getName();

        onView(withId(R.id.score)).check(matches(withSubstring("0/0")));
        onView(withId(R.id.studentName)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.button8)).perform(click());
        onView(withId(R.id.score)).check(matches(withSubstring("1/1")));
    }

    // Test score after submitting wrong answer
    @Test
    public void scoreIsNotCorrect() {
        activityRule.getScenario().onActivity(x -> quizActivity = x);

        assertFalse(quizActivity.shuffledDb.isEmpty());
        onView(withId(R.id.score)).check(matches(withSubstring("0/0")));
        onView(withId(R.id.studentName)).perform(typeText("Wrong"), closeSoftKeyboard());
        onView(withId(R.id.button8)).perform(click());
        onView(withId(R.id.score)).check(matches(withSubstring("0/1")));
    }
}