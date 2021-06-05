package com.example.yourpantry;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class WebviewActivityTest {
    @Rule
    public ActivityTestRule<WebviewActivity> mainActivityActivityTestRule6 = new ActivityTestRule<>(WebviewActivity.class);
    WebviewActivity activity6;

    @Before
    public void setUp() throws Exception {
        activity6 = mainActivityActivityTestRule6.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        activity6 = null;
    }

    @Test
    public void isActivityInView() {
        onView(withId(R.id.webView)).check(matches(isDisplayed()));
    }

}
