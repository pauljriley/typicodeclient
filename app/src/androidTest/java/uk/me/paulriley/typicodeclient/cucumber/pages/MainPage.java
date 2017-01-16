package uk.me.paulriley.typicodeclient.cucumber.pages;

import uk.me.paulriley.typicodeclient.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class MainPage extends BasePage {
    protected static final String SCREENSHOT_TAG = "MainPage";

    public MainPage() {
        onView(withId(R.id.activity_main)).check(matches(isDisplayed()));
    }

    public void checkPostsList() {
        onView(withId(R.id.posts_list)).check(matches(isDisplayed()));
    }

    public void selectAPostFromList() {

    }
}
