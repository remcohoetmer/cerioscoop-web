package nl.cerios.cerioscoop.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
	private WebDriver driver;

	@FindBy(id = "navbar-login")
	private WebElement loginNavigationLink;

	@FindBy(id = "loginUsername")
	private WebElement usernameTextfield;

	@FindBy(id = "loginPassword")
	private WebElement passwordTextfield;

	@FindBy(id = "login-button")
	private WebElement loginButton;

	// constructor
	public IndexPage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://localhost:9080/cerioscoop-web");
		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}

	// gedrag van de pagina
	public EmployeePage loginToEmployeePage(String username, String password) {
		loginNavigationLink.click();
		usernameTextfield.sendKeys(username);
		passwordTextfield.sendKeys(password);
		loginButton.click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		return new EmployeePage(driver);
	}
	public CustomerPage loginToCustomerPage(String username, String password) {
		loginNavigationLink.click();
		usernameTextfield.sendKeys(username);
		passwordTextfield.sendKeys(password);
		loginButton.click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		return new CustomerPage(driver);
	}
}
