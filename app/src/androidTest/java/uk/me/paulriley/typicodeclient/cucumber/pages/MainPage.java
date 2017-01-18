package uk.me.paulriley.typicodeclient.cucumber.pages;

import java.util.List;

import uk.me.paulriley.typicodeclient.R;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainPage extends BasePage {
    protected static final String SCREENSHOT_TAG = "MainPage";

    public MainPage() {
        onView(withId(R.id.activity_main)).check(matches(isDisplayed()));
    }

    public void checkPostsList(List<PostResultsModel> postResultsModels) {
        onView(withId(R.id.posts_list)).check(matches(isDisplayed()));

        for (int i = 0; i < postResultsModels.size(); i++) {
            onView(withId(R.id.posts_list)).check(matches(atPosition(i + 1, withText(postResultsModels.get(i).getTitle()))));
        }
    }

    public void selectAPostFromList() {
        onView(withId(R.id.posts_list)).perform(actionOnItemAtPosition(0, click()));
    }
}
