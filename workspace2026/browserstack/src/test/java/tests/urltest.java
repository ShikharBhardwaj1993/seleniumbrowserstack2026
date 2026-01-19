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
	
   
    //Next step to integrate to browser stack configuration
    //    🔑 Add BrowserStack credentials in Jenkins
    //    🧪 Run tests on BrowserStack from Jenkins
    //    🏷️ Show build name, project name in BrowserStack
    // I will go thorugh all code tomorrow https://chatgpt.com/c/696b8b3a-13ac-8322-bcf7-20d82a49ffb2
    //Also i will go through browser stack 
    // How to run parallely on multiple threads
	
}
