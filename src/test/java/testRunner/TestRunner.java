package testRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "E:\\java\\cucumberproject\\src\\test\\resourses\\housesale.feature"
        ,glue={"stepdefinition"}
)

public class TestRunner {

}

