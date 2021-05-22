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

public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> mainActivityActivityTestRule2 = new ActivityTestRule<>(RegisterActivity.class);
    RegisterActivity activity2;
    String messageToDisplay;
    String passwordToDisplay;

    @Before
    public void setUp() throws Exception {
        activity2 = mainActivityActivityTestRule2.getActivity();
        messageToDisplay = "test message";
        passwordToDisplay = "12345678";
    }

    @After
    public void tearDown() throws Exception {
        activity2 = null;
    }

    @Test
    public void isActivityInView() {
        onView(withId(R.id.register)).check(matches(isDisplayed()));
    }

    @Test
    public void test_visibility_title_button() {
        onView(withId(R.id.editText_firstName)).check(matches(isDisplayed()));
        onView(withId(R.id.editText_lastName)).check(matches(isDisplayed()));
        onView(withId(R.id.editText_username)).check(matches(isDisplayed()));
        onView(withId(R.id.editText_password)).check(matches(isDisplayed()));
        onView(withId(R.id.editText_cnf_password)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_register)).check(matches(isDisplayed()));
    }

    @Test
    public void testUserInputScenario() {
        onView(withId(R.id.editText_firstName)).perform(typeText(messageToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.editText_lastName)).perform(typeText(messageToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.editText_username)).perform(typeText(messageToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.editText_password)).perform(typeText(passwordToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.editText_cnf_password)).perform(typeText(passwordToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.btn_register)).perform(click());
    }
    @Test
    public void navMainActivity() {
        onView(withId(R.id.editText_firstName)).perform(typeText(messageToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.editText_lastName)).perform(typeText(messageToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.editText_username)).perform(typeText(messageToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.editText_password)).perform(typeText(passwordToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.editText_cnf_password)).perform(typeText(passwordToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.btn_register)).perform(click());
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }
}