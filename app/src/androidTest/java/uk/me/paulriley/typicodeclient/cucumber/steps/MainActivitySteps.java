package uk.me.paulriley.typicodeclient.cucumber.steps;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dagger.ObjectGraph;
import rx.Observable;
import uk.me.paulriley.typicodeclient.TypicodeApplication;
import uk.me.paulriley.typicodeclient.cucumber.pages.BasePage;
import uk.me.paulriley.typicodeclient.cucumber.pages.DetailsPage;
import uk.me.paulriley.typicodeclient.cucumber.pages.MainPage;
import uk.me.paulriley.typicodeclient.injection.modules.PresenterModule;
import uk.me.paulriley.typicodeclient.util.ActivityFinisher;
import uk.me.paulriley.typicodeclient.util.CountingIdlingResourceListenerImpl;
import uk.me.paulriley.typicodeclient.view.home.HomeActivity;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.me.paulriley.typicodeclient.cucumber.steps.TestModule.allPosts;

@SuppressWarnings("JUnitTestCaseWithNoTests")
@RunWith(AndroidJUnit4.class)
public class MainActivitySteps {

    private HomeActivity mActivity;
    private CountingIdlingResourceListenerImpl mCountingIdlingResourceListener;
    private PowerManager.WakeLock mFullWakeUpLock;
    private BasePage mCurrentPage;
    private TestModule testModule;

    @Rule
    private ActivityTestRule<HomeActivity> mActivityRule
            = new ActivityTestRule<>(HomeActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        testModule = new TestModule();

        Context testContext = InstrumentationRegistry.getTargetContext()
                .getApplicationContext();

        TypicodeApplication application
                = (TypicodeApplication) testContext;

        application.setObjectGraph(ObjectGraph.create(
                testModule,
                new PresenterModule(testContext)));

        registerIdlingResources();
        mActivity = mActivityRule.launchActivity(new Intent());
        assertNotNull(mActivity);
        turnOnScreenOfTestDevice();
    }

    private void registerIdlingResources() {
        mCountingIdlingResourceListener = new CountingIdlingResourceListenerImpl("MainActivityStarter");
        HomeActivity.setIdlingNotificationListener(mCountingIdlingResourceListener);
        Espresso.registerIdlingResources(mCountingIdlingResourceListener.getCountingIdlingResource());
    }

    private void turnOnScreenOfTestDevice() {
        final PowerManager powerManager = (PowerManager) mActivity.getSystemService(Context.POWER_SERVICE);
        mFullWakeUpLock = powerManager.newWakeLock((PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "Do Not Dim Screen");
        mFullWakeUpLock.acquire();
    }

    @After
    public void tearDown() throws Exception {
        HomeActivity.setIdlingNotificationListener(null);
        if (mCountingIdlingResourceListener != null) {
            Espresso.unregisterIdlingResources(mCountingIdlingResourceListener.getCountingIdlingResource());
        }
        ActivityFinisher.finishOpenActivities();
        letScreenOfTestDeviceTurnOff();
    }

    private void letScreenOfTestDeviceTurnOff() {
        if (mFullWakeUpLock != null) {
            mFullWakeUpLock.release();
        }
    }

    @Given("^I see the main page$")
    public void i_see_the_main_page() {
        when(testModule.mockTypicodeResults.getPosts()).thenReturn(Observable.just(allPosts()));
        mCurrentPage = new MainPage();
    }

    @Then("^I should see a list of posts$")
    public void i_should_see_list_of_posts() {
        mCurrentPage.is(MainPage.class).checkPostsList(allPosts());
    }

    @When("^I select a post$")
    public void i_select_a_post() {
        mCurrentPage.is(MainPage.class).selectAPostFromList();
    }

    @Then("^I should see the posts details$")
    public void i_should_see_the_posts_details() {
        mCurrentPage.is(DetailsPage.class);
    }
}
