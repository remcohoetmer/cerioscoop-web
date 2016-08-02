package nl.cerios.cerioscoop.jsp;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class RegisterCustomerTest extends SeleniumTest {

	/**
	 * @throws InterruptedException
	 * 
	 * TODO
	 * -
	 */
	@Test
	public void test() throws InterruptedException {
		if (driver == null) {
			return;
		}
		
		// Open index.jsp
		driver.get(BASE_URL + "/index.jsp");

		// Find the register button and click it
		driver.findElement(By.id("navbar-login")).click();
		driver.findElement(By.id("register-button")).click();

		// Fill the register form
		driver.findElement(By.id("firstname")).sendKeys("Selenium");
		driver.findElement(By.id("lastname")).sendKeys("London");
		driver.findElement(By.id("username")).sendKeys("Sel");
		driver.findElement(By.id("password")).sendKeys("london");
		driver.findElement(By.id("email")).sendKeys("selenium@london.com");
		// Submit registration
		driver.findElement(By.id("submit")).click();

		// Goto index.jsp
		driver.get(BASE_URL + "/index.jsp");

		// Fill the username and password of the registered customer
		driver.findElement(By.id("navbar-login")).click();
		driver.findElement(By.className("login-menu")).findElement(By.id("loginUsername")).sendKeys("Sel");
		driver.findElement(By.className("login-menu")).findElement(By.id("loginPassword")).sendKeys("london");
		// Click Login
		driver.findElement(By.id("login-button")).click();

		// Check username Sel in the page
		Assert.assertEquals("Hello customer!", driver.getTitle());
		Assert.assertEquals("Login Successful!\nHello Sel!", driver.findElement(By.id("helloCustomer")).getText());
	}
}