package com.example.yourpantry;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    MainActivity activity1;
    String messageToDisplay;

    @Before
    public void setUp() throws Exception {
        activity1 = mainActivityActivityTestRule.getActivity();
        messageToDisplay = "test message";

    }

    @After
    public void tearDown() throws Exception {
        activity1 = null;
    }

    @Test
    public void isActivityInView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }

    @Test
    public void test_visibility_title_button() {
        onView(withId(R.id.editText_username)).check(matches(isDisplayed()));
        onView(withId(R.id.editText_password)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
        onView(withId(R.id.textView_register)).check(matches(isDisplayed()));
        onView(withId(R.id.textView_Attempts)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
    }

    @Test
    public void testUserInputScenario() {
        onView(withId(R.id.editText_username)).perform(typeText(messageToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.editText_password)).perform(typeText(messageToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());


    }

    @Test
    public void navRegisterActivity() {
        onView(withId(R.id.textView_register)).perform(click());
        onView(withId(R.id.register)).check(matches(isDisplayed()));
    }
}
