package appiumtest;

import java.net.MalformedURLException;
import java.net.URL;

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

public class GoogleKeep_reminder{
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
	}

	@Test
	public void createNote() {

		driver.findElementById("new_note_button").click();

		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("editable_title")));

		driver.findElementById("editable_title").click();
		driver.findElementById("editable_title").sendKeys("Test Title");

		driver.findElementById("edit_note_text").click();
		driver.findElementById("edit_note_text").sendKeys("This is a test note.");
		
		driver.findElementById("menu_reminder").click();
		
		driver.findElementById("save").click();

		driver.findElementByXPath("//android.widget.ImageButton[@content-desc='Navigate up']").click();

		String noteTitle = driver.findElementById("index_note_title").getText();

		Assert.assertEquals(noteTitle, "Test Title");

	}

	@Test
	public void deleteNote() {

		driver.findElementById("browse_note_interior_content").click();

		driver.findElementById("bs_action_button").click();

		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.presenceOfElementLocated(MobileBy.xpath("//android.widget.TextView[@text='Delete']")));

		driver.findElementByXPath("//android.widget.TextView[@text='Delete']").click();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}



