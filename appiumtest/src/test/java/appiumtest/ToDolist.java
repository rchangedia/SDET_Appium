package appiumtest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

public class ToDolist {
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
	public void toDoList() {

		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"To-Do List\").instance(0))"));

		driver.findElementByXPath("//android.widget.TextView[@text='To-Do List']").click();

		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.EditText[@text='']")));

		ArrayList<String> taskLists = new ArrayList<String>();
		taskLists.add("Add tasks to list");
		taskLists.add("Get number of tasks");
		taskLists.add("Clear the list");

		for (String tasklist : taskLists) {

			driver.findElementByXPath("//android.widget.EditText[@text='']").click();
			driver.findElementByXPath("//android.widget.EditText[@text='']").sendKeys(tasklist);

			driver.findElementByXPath("//android.widget.Button[@text='Add Task']").click();

			List<MobileElement> taskNames = driver.findElementsByXPath("//android.view.View[@text='" + tasklist + "']");

			for (MobileElement taskName : taskNames) {

				Assert.assertEquals(taskName.getText(), tasklist);

				taskName.click();

			}

		}

		driver.findElementByXPath("//android.view.View[@text='']").click();

	}
	
	@AfterClass
    public void afterClass() {
        driver.quit();
    }
}

  

