package uk.me.paulriley.typicodeclient.test;

import cucumber.api.CucumberOptions;

@CucumberOptions(features = "features",
        glue = {"uk.me.paulriley.typicodeclient.cucumber.steps"},
        format = {"pretty",
                "html:/data/data/uk.me.paulriley.typicodeclient/cucumber-reports/cucumber-html-report",
                "json:/data/data/uk.me.paulriley.typicodeclient/cucumber-reports/cucumber.json",
                "junit:/data/data/uk.me.paulriley.typicodeclient/cucumber-reports/cucumber.xml"
        },
        tags={"~@manual",
                "@main-scenarios"}
)
class CucumberTestCase {
}
