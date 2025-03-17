package com.fakestoreapi.runners;


import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features/apitest.feature",
        //tags = "@Test4",
        glue = {"com.fakestoreapi.stepDefinitions"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class ApisTest extends AbstractTestNGCucumberTests  {

}
