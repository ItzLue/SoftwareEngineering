package acceptance_tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "use_cases",
	plugin = { "pretty", "html:target/cucumber-reports"},
	monochrome=true,
	snippets = SnippetType.CAMELCASE,
	glue = { "acceptance_tests"},
	strict = true)
public class AcceptanceTest {

}
