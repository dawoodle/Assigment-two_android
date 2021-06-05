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

public class AddFoodActivityTest {

    @Rule
    public ActivityTestRule<AddFoodActivity> mainActivityActivityTestRule4 = new ActivityTestRule<>(AddFoodActivity.class);
    AddFoodActivity activity4;
    String messageToDisplay;
    String dateToDisplay;

    @Before
    public void setUp() throws Exception {
        activity4 = mainActivityActivityTestRule4.getActivity();
        messageToDisplay = "test message";
        dateToDisplay = "05/20/2021";
    }

    @After
    public void tearDown() throws Exception {
        activity4 = null;
    }

    @Test
    public void isActivityInView() {
        onView(withId(R.id.addFood)).check(matches(isDisplayed()));
    }

    @Test
    public void test_visibility_title_button() {
        onView(withId(R.id.editText_foodName)).check(matches(isDisplayed()));
        onView(withId(R.id.editText_foodType)).check(matches(isDisplayed()));
        onView(withId(R.id.editText_foodExpiry)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_add)).check(matches(isDisplayed()));
    }

    @Test
    public void calenderTest() {
        onView(withId(R.id.btn_calender)).perform(click());
    }

    @Test
    public void testUserInputScenario() {
        onView(withId(R.id.editText_foodName)).perform(typeText(messageToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.editText_foodType)).perform(typeText(messageToDisplay));
        closeSoftKeyboard();
        onView(withId(R.id.editText_foodExpiry)).perform(typeText(dateToDisplay));
        closeSoftKeyboard();

    }
    @Test
    public void navHomePageActivity(){
        onView(withId(R.id.btn_add)).perform(click());
    }


}