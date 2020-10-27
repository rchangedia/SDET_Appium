package appiumtest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Login_1 {
	AppiumDriver<MobileElement> driver = null;
	WebDriverWait wait;
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceId", "fe0ee678");
		caps.setCapability("deviceName", "OnePlus 7T");
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "com.google.android.keep");
		caps.setCapability("appActivity", ".activities.BrowseActivity");
		caps.setCapability("noReset", true);
		
		// Instantiate Appium Driver
				URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
				driver = new AndroidDriver<MobileElement>(appServer, caps);
				wait = new WebDriverWait(driver, 5);

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				driver.get("https://www.training-support.net/selenium");
			}

			@Test
			public void loginForm() {

				driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Login Form\").instance(0))"));

				driver.findElementByXPath("//android.widget.TextView[@text='Login Form']").click();

				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.EditText[@text='']")));

				driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").click();
				driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").sendKeys("admin");
				
				driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").click();
				driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").sendKeys("password");
				
				driver.findElementByXPath("//android.widget.Button[@text='Log in']").click();
				
				String loginSuccess = driver.findElementByXPath("//android.view.View[@text='Welcome Back, admin']").getText();
				
				Assert.assertEquals(loginSuccess, "Welcome Back, admin");
				
				driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").click();
				driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").clear();
				driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").sendKeys("admin1");
				
				driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").click();
				driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").clear();
				driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").sendKeys("password1");
				
				driver.findElementByXPath("//android.widget.Button[@text='Log in']").click();
				
				String loginFailure = driver.findElementByXPath("//android.view.View[@text='Invalid Credentials']").getText();
				
				Assert.assertEquals(loginFailure, "Invalid Credentials");

			}
			
			@AfterClass
		    public void afterClass() {
		        driver.quit();
		    }
			
		}


