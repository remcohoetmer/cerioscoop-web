package nl.cerios.cerioscoop.jsp;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class SeleniumTest {

	public static final String BASE_URL = "http://localhost:9080/cerioscoop-web";

	protected static WebDriver driver;

	@BeforeClass
	public static void checkHttpServer() {
		// Test if the web server is running and the home page can be reached.
		try {
			new URL(BASE_URL).openConnection().connect();
		} catch (IOException e) {
			throw new IllegalStateException("The URL '" + BASE_URL + "' cannot be accessed."
					+ " Please check if the web server is running.", e);
		}
	}

	@BeforeClass
	public static void openBrowser() {
		// Create driver to communicate with the (Chrome!) browser
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterClass
	public static void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}