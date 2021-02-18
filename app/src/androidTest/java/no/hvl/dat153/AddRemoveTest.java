package no.hvl.dat153;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import no.hvl.dat153.activities.AddActivity;

import static android.app.Instrumentation.ActivityResult;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddRemoveTest {

    @Rule
    public IntentsTestRule<AddActivity> activityRule =
            new IntentsTestRule<>(AddActivity.class);

    // Test for adding student
    @Test
    public void addStudent() {
        int sizeBefore = activityRule.getActivity().students;

        Intent intent = new Intent();
        Uri uri = Uri.parse("android.resource://no.hvl.dat153/drawable/" + R.drawable.bendik);
        intent.setData(uri);
        onView(withId(R.id.nameTextView)).perform(typeText("Bendik"),
                closeSoftKeyboard());
        ActivityResult result = new ActivityResult(Activity.RESULT_OK, intent);
        intending(hasAction(Intent.ACTION_PICK)).respondWith(result);
        onView(withId(R.id.button6)).perform(click());
        onView(withId(R.id.button7)).perform(click());
        assertEquals(activityRule.getActivity().students, sizeBefore + 1);
    }
}