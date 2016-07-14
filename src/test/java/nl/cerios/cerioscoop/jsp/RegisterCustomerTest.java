package nl.cerios.cerioscoop.jsp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterCustomerTest {	

	public static void main(String[] args) throws InterruptedException {
	//Create chrome driver to drive the browser
	System.setProperty("webdriver.chrome.driver", 
	"C:\\LocalDevelopment\\dev\\ChromeDriver\\chromedriver.exe");	
	WebDriver driver = new ChromeDriver();
	
	//Open index.jsp
	driver.get("http://localhost:9080/cerioscoop-web/index.jsp");
	
	//Find the register button and click it	
	driver.findElement(By.id("register")).click();
	
	//Fill the register form
	driver.findElement(By.id("firstname")).sendKeys("Selenium");
	Thread.sleep(2000);
	driver.findElement(By.id("lastname")).sendKeys("London");
	Thread.sleep(2000);
	driver.findElement(By.id("username")).sendKeys("Sel");
	Thread.sleep(2000);
	driver.findElement(By.id("password")).sendKeys("london");
	Thread.sleep(2000);
	driver.findElement(By.id("email")).sendKeys("selenium@london.com");
	Thread.sleep(2000);
	//Submit registration
	driver.findElement(By.id("submit")).click();
	
	//Goto index.jsp
	driver.get("http://localhost:9080/cerioscoop-web/index.jsp");
	
	//Fill the username and password of the registered customer
	driver.findElement(By.id("loginUsername")).sendKeys("Sel");
	Thread.sleep(2000);
	driver.findElement(By.id("loginPassword")).sendKeys("london");
	Thread.sleep(2000);
	//Click Login
	driver.findElement(By.id("login")).click();
	
	//Check username Sel in the page
	System.out.println(driver.getTitle());
	System.out.println(driver.findElement(By.id("helloCustomer")).getText());
	}
}