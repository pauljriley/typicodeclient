package uk.me.paulriley.typicodeclient.cucumber.steps;

import android.support.test.espresso.EspressoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import uk.me.paulriley.typicodeclient.util.SpoonScreenshotAction;

public class HelperSteps {

    private static Scenario scenario;

    @Before
    public static void before(final Scenario scenario) {
        HelperSteps.scenario = scenario;
    }

    @After
    public static void after() {
        if ((HelperSteps.scenario != null) && (HelperSteps.scenario.isFailed())) {
            takeScreenshot("failed");
        }
    }

    @Given("^I take a screenshot$")
    public void i_take_a_screenshot() {
        takeScreenshot("screenshot");
    }

    public static void takeScreenshot(final String tag) throws ScreenshotException {
        if (scenario == null) {
            throw new ScreenshotException("Error taking screenshot: I'm missing a valid test " +
                    "scenario to attach the screenshot to");
        }

        SpoonScreenshotAction.perform(tag);

        final File screenshot = SpoonScreenshotAction.getLastScreenshot();
        if (screenshot == null) {
            throw new ScreenshotException("Screenshot was not taken correctly, " +
                    "check for failures in screenshot library");
        }

        FileInputStream screenshotStream = null;

        try {
            screenshotStream = new FileInputStream(screenshot);
            final byte fileContent[] = new byte[(int) screenshot.length()];
            final int readImageBytes = screenshotStream.read(fileContent);

            if (readImageBytes != -1) {
                scenario.embed(fileContent, "image/png");
            }
        } catch (final IOException exception) {
            throw new ScreenshotException("Exception while reading file " + exception);
        } finally {
            try {
                if (screenshotStream != null) screenshotStream.close();
            } catch (final IOException exception) {
                throw new ScreenshotException("Error while closing screenshot stream: "
                        + exception);
            }
        }
    }

    private static class ScreenshotException extends RuntimeException implements EspressoException {
        private static final long serialVersionUID = -2017013131052L;

        public ScreenshotException(final String message) {
            super(message);
        }
    }
}
