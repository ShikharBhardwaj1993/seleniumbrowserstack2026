package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.DriverFactory;

public class urltest {

	WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.initDriver();
    }

    @Test
    public void verifyGoogleTitle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Google"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
	
    //https://chatgpt.com/c/696372da-fa4c-8322-99bb-42a7980ef28a
	
}
