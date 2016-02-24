package com.example.commet.booker;
import android.app.Application;
import android.content.ComponentName;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.matchers.*;
import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.*;
//import static java.util.regex.Pattern.matches;

/**
 * Created by Jesse and Michael
 * Class to implement basic system testing of the various activities and actions in the
 * application.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ApplicationTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule(MainActivity.class);

    /*
    * Test for the load of MainActivity. Checks that text in MainActivity is correctly
    * displayed, indicating that the activity has loaded correctly.
    */
    @Test
    public void testForCorrectTitle() {
        onView(withText("Barter Books")).check(matches(withText("Barter Books")));
    }

    /*
    * Test of login button in main activity. Tests to see if book_list activity is
    * loaded on login button click. Sets up text fields with valid data.
    */
    @Test
    public void testButtonClickLogin() {
        onView(withId(R.id.username)).perform(clearText(), typeText("jesse@"));
        onView(withId(R.id.password)).perform(clearText(), typeText("134567892"));
        onView(withText("Barter Books")).perform(closeSoftKeyboard());
        onView(withText("Login")).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), book_list.class)));
    }

    /*
    * Test of Signup button in main activity. Tests to see if SignupActivity is
    * loaded on Signup button click.
    */
    @Test
    public void testButtonClickSignUp() {
//        onView(withId(R.id.username)).perform(clearText(), typeText("jesse@"));
//        onView(withId(R.id.password)).perform(clearText(), typeText("134567892"));
        onView(withText("Barter Books")).perform(closeSoftKeyboard());
        onView(withText("Signup")).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), SignupActivity.class)));

    }
}