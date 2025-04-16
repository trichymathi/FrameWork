package main_Class;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;

@CucumberOptions(features = {
		"src/main/resources/Feature_Files" }, dryRun = false, snippets = SnippetType.CAMELCASE, monochrome = true, glue = "step_Defenition_Class"
//				,tags="@regression"

)
public class Runner extends AbstractTestNGCucumberTests {

}