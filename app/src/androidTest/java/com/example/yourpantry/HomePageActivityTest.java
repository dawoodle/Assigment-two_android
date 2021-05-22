package com.example.yourpantry;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class HomePageActivityTest {

    @Rule
    public ActivityTestRule<HomePageActivity> mainActivityActivityTestRule3 = new ActivityTestRule<>(HomePageActivity.class);
    HomePageActivity activity3;

    @Before
    public void setUp() throws Exception {
        activity3 = mainActivityActivityTestRule3.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        activity3 = null;
    }
    @Test
    public void isActivityInView() {
        onView(withId(R.id.home)).check(matches(isDisplayed()));
    }

    @Test
    public void test_visibility_FabButton() { onView(withId(R.id.add_button)).check(matches(isDisplayed()));}

    @Test
    public void NavAddFoodActivity(){
        onView(withId(R.id.add_button)).perform(click());
    }

    @Test
    public void TestDeleteButton(){
        onView(withId(R.id.delete_all)).perform(click());
    }

}
