package uk.me.paulriley.typicodeclient.cucumber.runner;

import android.os.Bundle;

import uk.me.paulriley.typicodeclient.BuildConfig;

import cucumber.api.android.CucumberInstrumentationCore;

public class CucumberTestRunner extends android.support.test.runner.AndroidJUnitRunner {

    private static final String CUCUMBER_TAGS_KEY = "tags";
    private final CucumberInstrumentationCore instrumentationCore
            = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);

        final String tags = BuildConfig.TEST_TAGS;
        if (!tags.isEmpty()) {
            bundle.putString(CUCUMBER_TAGS_KEY, tags.replaceAll(",", "--").replaceAll("\\s",""));
        }

        instrumentationCore.create(bundle);
    }

    @Override
    public void onStart() {
        waitForIdleSync();
        instrumentationCore.start();
    }
}
