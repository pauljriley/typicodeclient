package uk.me.paulriley.typicodeclient.util;

import android.support.test.espresso.idling.CountingIdlingResource;

import uk.me.paulriley.typicodeclient.support.util.CountingIdlingResourceListener;

public class CountingIdlingResourceListenerImpl implements CountingIdlingResourceListener {

    private final CountingIdlingResource mCountingIdlingResource;

    public CountingIdlingResourceListenerImpl(final String idlingResourceName) {
        mCountingIdlingResource = new CountingIdlingResource(idlingResourceName);
    }

    public CountingIdlingResource getCountingIdlingResource() {
        return mCountingIdlingResource;
    }

    @Override
    public void increment() {
        mCountingIdlingResource.increment();
    }

    @Override
    public void decrement() {
        mCountingIdlingResource.decrement();
    }
}
