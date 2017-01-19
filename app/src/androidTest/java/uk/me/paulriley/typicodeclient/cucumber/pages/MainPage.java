package uk.me.paulriley.typicodeclient.cucumber.pages;

import android.support.annotation.NonNull;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.view.View;

import org.hamcrest.Matcher;

import uk.me.paulriley.typicodeclient.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

public class MainPage extends BasePage {
    public MainPage() {
        onView(withId(R.id.activity_main)).check(matches(isDisplayed()));
    }

    public void checkPostsList() {
        onView(withId(R.id.posts_list)).check(matches(isDisplayed()));
        onView(withId(R.id.posts_list)).check(matches(isCompletelyDisplayed()));
    }

    public void selectAPostFromList() {
        onView(getDisplayedPostsRecyclerView())
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    public void checkPostDetails() {
        onView(withId(R.id.post_title)).check(matches(isDisplayed()));
        onView(withId(R.id.post_body)).check(matches(isDisplayed()));
        onView(withId(R.id.post_user_avatar)).check(matches(isDisplayed()));
        onView(withId(R.id.post_user_name)).check(matches(isDisplayed()));
        onView(withId(R.id.comments)).check(matches(isDisplayed()));
        onView(withId(R.id.comments)).check(matches(isCompletelyDisplayed()));
    }

    @NonNull
    private Matcher<View> getDisplayedPostsRecyclerView() {
        return allOf(
                withId(R.id.posts_list),
                isDisplayed());
    }
}
