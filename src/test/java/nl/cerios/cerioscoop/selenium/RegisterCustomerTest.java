package nl.cerios.cerioscoop.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import nl.cerios.cerioscoop.service.GeneralService;
import nl.cerios.testutil.SeleniumTest;

public class RegisterCustomerTest extends SeleniumTest {

	private GeneralService generalService = new GeneralService();
	private String userName = generalService.generateRandomUsername();
	
	/**
	 *  TODO Delete the customer after registering!
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
		getWebDriver().findElement(By.id("lastname")).sendKeys("SelLondon");
		getWebDriver().findElement(By.id("username")).sendKeys(userName);
		System.out.println(userName);
		getWebDriver().findElement(By.id("password")).sendKeys("Sellondon");
		getWebDriver().findElement(By.id("password2")).sendKeys("Sellondon");
		getWebDriver().findElement(By.id("email")).sendKeys("selenium@london.com");
		// Submit registration
		getWebDriver().findElement(By.id("submit")).click();

		//Tijdelijk object met een null waarde voor het slagen van de Seleniumtest
		String dummy = null;
		
		// Check username Sel in the page
		Assert.assertEquals("Hello customer!", getWebDriver().getTitle());
		final String loginMessage = getWebDriver().findElement(By.className("login-message")).getText().toString();
		Assert.assertEquals("Welcome, your registry has been processed!"+dummy+"\nHello "+userName+" (customer)!", loginMessage);
	}
}