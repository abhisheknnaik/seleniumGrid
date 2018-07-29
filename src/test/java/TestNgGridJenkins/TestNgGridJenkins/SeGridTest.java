package TestNgGridJenkins.TestNgGridJenkins;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeGridTest {
	WebDriver driver = null;
	private StringBuffer verificationErrors = new StringBuffer();

	@Parameters({ "platform", "browser", "version", "url" })
	@BeforeTest(alwaysRun = true)
	public void setup(String platform, String browser, String version,
			String url) throws MalformedURLException, InterruptedException {
		File strFile = new File("Lib");
		String driverPath = strFile.getAbsolutePath() + "\\";
		DesiredCapabilities caps = new DesiredCapabilities();
		// Platforms
		if (platform.equalsIgnoreCase("Windows"))
			caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		if (platform.equalsIgnoreCase("MAC"))
			caps.setPlatform(org.openqa.selenium.Platform.MAC);
		// Browsers
		
		if (browser.equalsIgnoreCase("InternetExplorer")) {
			caps = DesiredCapabilities.internetExplorer();
			  
		//	caps.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
			caps.setCapability(InternetExplorerDriver.
			  INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			System.setProperty("webdriver.ie.driver", driverPath
					+ "IEDriverServer.exe");

		}
		if (browser.equalsIgnoreCase("Firefox"))
			caps = DesiredCapabilities.firefox();
		if (browser.equalsIgnoreCase("iPad"))
			caps = DesiredCapabilities.ipad();
		if (browser.equalsIgnoreCase("Android"))
			caps = DesiredCapabilities.android();
		// Version
		caps.setVersion(version);
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
				caps);
		// Open the BMI Calculator Application
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}

	@Test(description = "Test Bmi Calculator")
	public void testBmiCalculator() throws InterruptedException {
	}

	@AfterTest
	public void afterTest() {
		// Close the browser
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private void fail(String verificationErrorString) {
		// TODO Auto-generated method stub

	}
}
