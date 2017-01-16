package uk.me.paulriley.typicodeclient.cucumber.pages;

import uk.me.paulriley.typicodeclient.cucumber.steps.HelperSteps;

public class BasePage {
    protected static final String SCREENSHOT_TAG = "invalid-page";

    public <T extends BasePage> T is(Class<T> type) {
        if (type.isInstance(this)) {
            return type.cast(this);
        } else {
            HelperSteps.takeScreenshot(SCREENSHOT_TAG);

            throw new InvalidPageException("Invalid page type. Expected: " + type.getSimpleName() +
                ", but got: " + this.getClass().getSimpleName());
        }
    }

    private class InvalidPageException extends RuntimeException {
        public InvalidPageException(final String message) {
            super(message);
        }
    }
}
