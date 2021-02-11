package no.hvl.dat153;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import no.hvl.dat153.activities.QuizActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ScoreUpdatedTest {

    @Rule
    public ActivityScenarioRule<QuizActivity> activityRule =
            new ActivityScenarioRule<>(QuizActivity.class);

    // Test score after submitting correct answer
    @Test
    public void scoreIsCorrect() {
        // Only works if database is not empty
        if (!QuizActivity.database.isEmpty()) {
            String name = QuizActivity.database.get(0).getName();

            onView(withId(R.id.score)).check(matches(withSubstring("0/0")));
            onView(withId(R.id.studentName)).perform(typeText(name), closeSoftKeyboard());
            onView(withId(R.id.button8)).perform(click());
            onView(withId(R.id.score)).check(matches(withSubstring("1/1")));
        }
    }

    // Test score after submitting wrong answer
    @Test
    public void scoreIsNotCorrect() {
        // Only works if database is not empty
        if (!QuizActivity.database.isEmpty()) {
            onView(withId(R.id.score)).check(matches(withSubstring("0/0")));
            onView(withId(R.id.studentName)).perform(typeText("Wrong"), closeSoftKeyboard());
            onView(withId(R.id.button8)).perform(click());
            onView(withId(R.id.score)).check(matches(withSubstring("0/1")));
        }
    }
}
