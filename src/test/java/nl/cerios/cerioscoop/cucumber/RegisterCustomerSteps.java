package nl.cerios.cerioscoop.cucumber;

import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.cerios.testutil.SeleniumTest;

public class RegisterCustomerSteps extends SeleniumTest {

	@Given("^I am on the cerioscoop site$")
	public void navigateToCerioscoopSite() throws InterruptedException {
		getWebDriver().navigate().to(BASE_URL + "/index.jsp");
	}

	@When("^I navigate to register$")
	public void clickOnRegisterButton() throws InterruptedException {
		getWebDriver().findElement(By.id("navbar-login")).click();
		getWebDriver().findElement(By.id("register-button")).click();
	}

	@When("^I fill in the registerform with valid data and submit it$")
	public void fillInRegisterFormAndSubmit() throws InterruptedException {
		getWebDriver().findElement(By.id("firstname")).sendKeys("Selenium");
		getWebDriver().findElement(By.id("lastname")).sendKeys("London");
		getWebDriver().findElement(By.id("username")).sendKeys("Sel");
		getWebDriver().findElement(By.id("password")).sendKeys("london");
		getWebDriver().findElement(By.id("email")).sendKeys("selenium@london.com");
		getWebDriver().findElement(By.id("submit")).click();
	}

	@When("^I navigate to the cerioscoop site$")
	public void navigateToCerioscoopPage() throws InterruptedException {
		getWebDriver().navigate().to(BASE_URL + "/index.jsp");
	}

	@When("^I click on login$")
	public void clickOnLoginButton() throws InterruptedException {
		getWebDriver().findElement(By.id("navbar-login")).click();
	}

	@When("^I fill in the username and password$")
	public void loginWithUsernamePassword() throws InterruptedException {
		getWebDriver().findElement(By.className("login-menu")).findElement(By.id("loginUsername")).sendKeys("Sel");
		getWebDriver().findElement(By.className("login-menu")).findElement(By.id("loginPassword")).sendKeys("london");
		getWebDriver().findElement(By.id("login-button")).click();
	}

	@Then("^I check that the customer has been registered$")
	public void checkIfCustomerIsRegistered() throws InterruptedException {
		Assert.assertEquals("Hello customer!", getWebDriver().getTitle());
		Assert.assertEquals("Login Successful!\nHello Sel (customer)!",
				getWebDriver().findElement(By.className("login-message")).getText());
	}
}
