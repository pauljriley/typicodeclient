package uk.me.paulriley.typicodeclient.cucumber.pages;

import uk.me.paulriley.typicodeclient.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class DetailsPage extends BasePage {
    protected static final String SCREENSHOT_TAG = "DetailPage";

    public DetailsPage() {
        onView(withId(R.id.activity_detail)).check(matches(isDisplayed()));
    }
}
