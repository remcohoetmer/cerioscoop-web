package nl.cerios.cerioscoop.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import nl.cerios.testutil.SeleniumTest;

public class RegisterCustomerTest extends SeleniumTest {

	/**
	 *             TODO -
	 */
	@Test
	public void test() {
		// Open index.jsp
		getWebDriver().get(BASE_URL + "/index.jsp");

		// Find the register button and click it
		getWebDriver().findElement(By.id("navbar-login")).click();
		getWebDriver().findElement(By.id("register-button")).click();

		// Fill the register form
		getWebDriver().findElement(By.id("firstname")).sendKeys("Selenium");
		getWebDriver().findElement(By.id("lastname")).sendKeys("London");
		getWebDriver().findElement(By.id("username")).sendKeys("Sel");
		getWebDriver().findElement(By.id("password")).sendKeys("london");
		getWebDriver().findElement(By.id("password2")).sendKeys("london");
		getWebDriver().findElement(By.id("email")).sendKeys("selenium@london.com");
		// Submit registration
		getWebDriver().findElement(By.id("submit")).click();

		// Goto index.jsp
		getWebDriver().get(BASE_URL + "/index.jsp");

		// Fill the username and password of the registered customer
		getWebDriver().findElement(By.id("navbar-login")).click();
		getWebDriver().findElement(By.className("login-menu")).findElement(By.id("loginUsername")).sendKeys("Sel");
		getWebDriver().findElement(By.className("login-menu")).findElement(By.id("loginPassword")).sendKeys("london");
		// Click Login
		getWebDriver().findElement(By.id("login-button")).click();

		// Check username Sel in the page
		Assert.assertEquals("Hello customer!", getWebDriver().getTitle());
		final String loginMessage = getWebDriver().findElement(By.className("login-message")).getText();
		Assert.assertEquals("Login Successful!\nHello Sel (customer)!", loginMessage);
	}
}