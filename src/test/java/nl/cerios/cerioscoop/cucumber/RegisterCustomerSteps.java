package nl.cerios.cerioscoop.cucumber;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.cerios.cerioscoop.jsp.SeleniumTest;

public class RegisterCustomerSteps extends SeleniumTest {

	@Given("^I am on the cerioscoop site$")
	public void navigateToCerioscoopSite() throws InterruptedException {
		driver = new ChromeDriver();
		driver.navigate().to(BASE_URL + "/index.jsp");
	}

	@When("^I navigate to register$")
	public void clickOnRegisterButton() throws InterruptedException {
		driver.findElement(By.id("navbar-login")).click();
		driver.findElement(By.id("register-button")).click();
	}

	@When("^I fill in the registerform with valid data and submit it$")
	public void fillInRegisterFormAndSubmit() throws InterruptedException {
		driver.findElement(By.id("firstname")).sendKeys("Selenium");
		driver.findElement(By.id("lastname")).sendKeys("London");
		driver.findElement(By.id("username")).sendKeys("Sel");
		driver.findElement(By.id("password")).sendKeys("london");
		driver.findElement(By.id("email")).sendKeys("selenium@london.com");
		driver.findElement(By.id("submit")).click();
	}

	@When("^I navigate to the cerioscoop site$")
	public void navigateToCerioscoopPage() throws InterruptedException {
		driver.navigate().to(BASE_URL + "/index.jsp");
	}

	@When("^I click on login$")
	public void clickOnLoginButton() throws InterruptedException {
		driver.findElement(By.id("navbar-login")).click();
				
	}

	@When("^I fill in the username and password$")
	public void loginWithUsernamePassword() throws InterruptedException {
		driver.findElement(By.className("login-menu")).findElement(By.id("loginUsername")).sendKeys("Sel");
		driver.findElement(By.className("login-menu")).findElement(By.id("loginPassword")).sendKeys("london");
		driver.findElement(By.id("login-button")).click();
	}

	@Then("^I check that the customer has been registered$")
	public void checkIfCustomerIsRegistered() throws InterruptedException {
		Assert.assertEquals("Hello customer!", driver.getTitle());
		Assert.assertEquals("Login Successful!\nHello Sel!", driver.findElement(By.className("login-message")).getText());
		driver.quit();
	}
}
