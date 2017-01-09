package uk.me.paulriley.typicodeclient.stepDefinitions;

import cucumber.api.CucumberOptions;
import uk.me.paulriley.typicodeclient.BuildConfig;

@CucumberOptions(
        plugin = {
                "pretty",
                "html:" + Runner.REPORT_PATH + "cucumber-html-report",
                "pretty:" + Runner.REPORT_PATH + "cucumber-report.json",
                "junit:" + Runner.REPORT_PATH + "cucumber.xml"
        },
        features = "features",
        tags = "@default"
)
public class Runner {
    public static final String REPORT_PATH = "data/data"
            + BuildConfig.APPLICATION_ID + "/cucumber-reports/";
}
