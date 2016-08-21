package nl.cerios.cerioscoop.selenium;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.selenium.pages.CustomerPage;
import nl.cerios.cerioscoop.selenium.pages.EmployeePage;
import nl.cerios.cerioscoop.selenium.pages.IndexPage;
import nl.cerios.testutil.SeleniumTest;

public class LoginTest extends SeleniumTest {
	
	@Test
	public void shouldLoginCustomerSuccessfully() {
	    CustomerPage customerPage = new IndexPage(getWebDriver())
	      .loginToCustomerPage("ron","SuperRon");
	    
	    Assert.assertNotNull(customerPage);
	    Assert.assertEquals("Login Successful!\nHello ron!", customerPage.getWelcomeMessage());
	  }
	
	@Test
	public void shouldLoginEmployeeSuccessfully() {
	    EmployeePage employeePage = new IndexPage(getWebDriver())
	      .loginToEmployeePage("A","A");
	    
	    Assert.assertNotNull(employeePage);
	    Assert.assertEquals("Login Successful!\nHello A!", employeePage.getWelcomeMessage());
	  }
}
