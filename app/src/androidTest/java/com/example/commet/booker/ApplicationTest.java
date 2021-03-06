package com.example.commet.booker;
import android.content.ComponentName;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
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
    * Test of login button in main activity. Tests to see if BookList activity is
    * loaded on login button click. Sets up text fields with valid data.
    */
    @Test
    public void testButtonClickLogin() {
        onView(withId(R.id.username)).perform(clearText(), typeText("jesse@gmail.com"));
        onView(withId(R.id.password)).perform(clearText(), typeText("134567892"));
        onView(withText("Barter Books")).perform(closeSoftKeyboard());
        onView(withText("Login")).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), UserProfile.class)));
    }

    /*
    * Test of Signup button in main activity. Tests to see if SignupActivity is
    * loaded on Signup button click.
    */
    @Test
    public void testButtonClickSignUp() {
        onView(withId(R.id.username)).perform(clearText(), typeText("bencommet@gmail.com"));
        onView(withId(R.id.password)).perform(clearText(), typeText("134567892"));
        onView(withId(R.id.signup_button)).perform(closeSoftKeyboard());
        onView(withId(R.id.signup_button)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), SignupActivity.class)));
    }

    /*Tests for all Activities in app, validates navigation between activities from login*/
    @Test
    public void testButtonClickPostBook() {
        onView(withId(R.id.username)).perform(clearText(), typeText("commetb@mail.gvsu.edu"));
        onView(withId(R.id.password)).perform(clearText(), typeText("134567892"));
        onView(withText("Barter Books")).perform(closeSoftKeyboard());
        onView(withText("Login")).perform(click());
        onView(withId(R.id.userPost)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), QueryForm.class)));
    }

    /*Tests going from main activity to login Activity to the Search Activity*/
    @Test
    public void testButtonClickSearchBook() {
        onView(withId(R.id.username)).perform(clearText(), typeText("brandon@facebook.com"));
        onView(withId(R.id.password)).perform(clearText(), typeText("134567892"));
        onView(withText("Barter Books")).perform(closeSoftKeyboard());
        onView(withText("Login")).perform(click());
        onView(withId(R.id.userSearch)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), SearchForm.class)));
    }

    /*Tests going from main activity to login Activity to the view all books activity*/
    @Test
    public void testButtonClickViewAllBooks() {
        onView(withId(R.id.username)).perform(clearText(), typeText("fish@sea.com"));
        onView(withId(R.id.password)).perform(clearText(), typeText("134567892"));
        onView(withText("Barter Books")).perform(closeSoftKeyboard());
        onView(withText("Login")).perform(click());
        onView(withId(R.id.viewAllUser)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), BookList.class)));
    }

    /*Tests going from main activity to login Activity to the user actvity to the search activity*/
    @Test
    public void testLoginPostSearch(){
        onView(withId(R.id.username)).perform(clearText(), typeText("mike@mike.com"));
        onView(withId(R.id.password)).perform(clearText(), typeText("134567892"));
        onView(withText("Barter Books")).perform(closeSoftKeyboard());
        onView(withText("Login")).perform(click());
        onView(withId(R.id.userPost)).perform(click());
        onView(withId(R.id.querySearchBtn)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), SingleBookAdapter.class)));
    }

    /*Tests going from main activity to login activity to the user activity to the search activity
    then it performs a search
     */
    @Test
    public void testLoginSearchBook(){
        onView(withId(R.id.username)).perform(clearText(), typeText("android@google.com"));
        onView(withId(R.id.password)).perform(clearText(), typeText("134567892"));
        onView(withText("Barter Books")).perform(closeSoftKeyboard());
        onView(withText("Login")).perform(click());
        onView(withId(R.id.userSearch)).perform(click());
        onView(withId(R.id.startSearch)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), SingleBookAdapter.class)));
    }

    /* This test checks to see if the ISBN value is successfully passed
*/
    @Test
    public void testSearchISBNFunction(){
        onView(withId(R.id.username)).perform(clearText(), typeText("android@google.com"));
        onView(withId(R.id.password)).perform(clearText(), typeText("134567892"));
        onView(withText("Barter Books")).perform(closeSoftKeyboard());
        onView(withText("Login")).perform(click());
        onView(withId(R.id.userSearch)).perform(click());
        onView(withId(R.id.startSearch)).perform(click());
        onView(withId(R.id.bookIsbn)).check(matches(withText("ISBN: 9781118983843\n")));
    }

    @Test
    public void testSearchAuthorFunction(){
        onView(withId(R.id.username)).perform(clearText(), typeText("android@google.com"));
        onView(withId(R.id.password)).perform(clearText(), typeText("134567892"));
        onView(withText("Barter Books")).perform(closeSoftKeyboard());
        onView(withText("Login")).perform(click());
        onView(withId(R.id.userSearch)).perform(click());
        onView(withId(R.id.startSearch)).perform(click());
        onView(withId(R.id.bookPubDate)).check(matches(withText("Publishing Date: 2015-01-20")));
    }


}