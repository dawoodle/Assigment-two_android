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

public class UpdateDeleteActivityTest {

    @Rule
    public ActivityTestRule<UpdateDeleteActivity> mainActivityActivityTestRule5 = new ActivityTestRule<>(UpdateDeleteActivity.class);
    UpdateDeleteActivity activity5;
    String messageToDisplay;
    String dateToDisplay;

    @Before
    public void setUp() throws Exception {

        activity5 = mainActivityActivityTestRule5.getActivity();
        messageToDisplay = "test message";
        dateToDisplay = "05/20/2021";
    }

    @After
    public void tearDown() throws Exception {
        activity5 = null;
    }

    @Test
    public void isActivityInView() {
        onView(withId(R.id.update_delete)).check(matches(isDisplayed()));
    }

    @Test
    public void test_visibility_title_button() {
        onView(withId(R.id.editText_foodName2)).check(matches(isDisplayed()));
        onView(withId(R.id.editText_foodType2)).check(matches(isDisplayed()));
        onView(withId(R.id.editText_foodExpiry2)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_update)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_delete)).check(matches(isDisplayed()));
    }

    @Test
    public void calenderTest() {
        onView(withId(R.id.btn_calender)).perform(click());
    }

    @Test
    public void testUserInputScenario() {
        onView(withId(R.id.editText_foodName2)).perform(typeText(messageToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.editText_foodType2)).perform(typeText(messageToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.editText_foodExpiry2)).perform(typeText(dateToDisplay));
        closeSoftKeyboard();

    }

    @Test
    public void navHomepageActivity(){
        onView(withId(R.id.btn_update)).perform(click());
        onView(withId(R.id.btn_delete)).perform(click());
    }
}