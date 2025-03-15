package com.fakestoreapi.runners;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/api_test.feature",
        //tags = "@Test1",
        plugin = {"pretty", "json:target/cucumber-report.json"},
        glue = "com/fakestoreapi/stepDefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE

)
public class ApisTestRunner {

}
