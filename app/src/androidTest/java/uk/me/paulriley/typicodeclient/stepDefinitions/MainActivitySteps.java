package uk.me.paulriley.typicodeclient.stepDefinitions;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import uk.me.paulriley.typicodeclient.MainActivity;
import uk.me.paulriley.typicodeclient.pages.MainPage;
import uk.me.paulriley.typicodeclient.util.ActivityFinisher;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivitySteps {

    @Rule
    public ActivityTestRule<?> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    private MainPage mMainPage;

    @Before
    public void setUp() throws Exception {
        mActivityRule.launchActivity(null);
        mActivityRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        ActivityFinisher.finishOpenActivities();
    }

    @Given("I am launching the application")
    public void i_am_launching_the_application() throws Throwable {
        mMainPage = new MainPage();
        mMainPage.waitForPageVisible();
    }

    @When("The Main Activity has loaded")
    public void the_main_activity_has_loaded() throws Throwable {

    }

    @Then("I should see a list of posts")
    public void i_should_see_a_list_of_posts() throws Throwable {

    }

}
