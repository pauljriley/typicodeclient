package uk.me.paulriley.typicodeclient.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import com.squareup.spoon.Spoon;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.io.File;

import uk.me.paulriley.typicodeclient.TypicodeApplication;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

public final class SpoonScreenshotAction implements ViewAction {

    private final String mTag;
    private final String mTestClass;
    private final String mTestMethod;
    private static File lastScreenshot;

    public static File getLastScreenshot() {
        return lastScreenshot;
    }

    private SpoonScreenshotAction(final String tag, final String testClass, final String testMethod) {
        mTag = tag;
        mTestClass = testClass;
        mTestMethod = testMethod;
    }

    @Override
    public Matcher<View> getConstraints() {
        return Matchers.any(View.class);
    }

    @Override
    public String getDescription() {
        return "Taking a screenshot using spoon.";
    }

    @Override
    public void perform(final UiController uiController, final View view) {
        lastScreenshot = Spoon.screenshot(getActivity(view), mTag, mTestClass, mTestMethod);
    }

    private static Activity getActivity(final View view) {
        Context context = view.getContext();
        while (!(context instanceof Activity)) {
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            } else {
                throw new IllegalStateException("Got a context of class "
                        + context.getClass() + " and I don't know " +
                        "how to get the Activity from it");
            }
        }
        return (Activity) context;
    }

    public static void perform(final String tag) {
        final StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        final String testClass = trace[3].getClassName();
        final String testMethod = trace[3].getMethodName();
        onView(isRoot()).perform(new SpoonScreenshotAction(tag, testClass, testMethod));
    }
}
