package uk.me.paulriley.typicodeclient.cucumber.pages;

public class BasePage {
    public <T extends BasePage> T is(Class<T> type) {
        if (type.isInstance(this)) {
            return type.cast(this);
        } else {
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
