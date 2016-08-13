package nl.cerios.cerioscoop.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import nl.cerios.cerioscoop.jsp.SeleniumTest;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/"})
public abstract class RunnerTest extends SeleniumTest {

}
