package no.hvl.dat153;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import no.hvl.dat153.activities.DatabaseActivity;
import no.hvl.dat153.activities.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ActivityButtonTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void databaseBtnStartsDatabaseActivity() {
        Intents.init();
        onView(withId(R.id.button)).perform(ViewActions.click());
        intended(hasComponent(DatabaseActivity.class.getName()));
    }
}
