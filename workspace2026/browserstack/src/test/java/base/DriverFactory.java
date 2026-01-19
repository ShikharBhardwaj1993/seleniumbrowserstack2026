package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.MutableCapabilities;

import java.net.URL;

public class DriverFactory {

    public static WebDriver driver;

    public static WebDriver initDriver() {

        String runOn = System.getProperty("runOn", "local");

        try {

            if (runOn.equalsIgnoreCase("local")) {
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                return driver;
            }

            if (runOn.equalsIgnoreCase("browserstack")) {

                String username = System.getenv("BROWSERSTACK_USERNAME");
                String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

                if (username == null || accessKey == null) {
                    throw new RuntimeException("BrowserStack credentials not found in environment variables");
                }

                MutableCapabilities caps = new MutableCapabilities();
                caps.setCapability("browserName", "Chrome");
                caps.setCapability("browserVersion", "latest");

                MutableCapabilities bstackOptions = new MutableCapabilities();
                bstackOptions.setCapability("os", "Windows");
                bstackOptions.setCapability("osVersion", "11");
                bstackOptions.setCapability("userName", username);
                bstackOptions.setCapability("accessKey", accessKey);
                bstackOptions.setCapability("projectName", "Selenium Java Framework");
                bstackOptions.setCapability("buildName", "Jenkins Build");
                bstackOptions.setCapability("sessionName", "Google Title Test");

                caps.setCapability("bstack:options", bstackOptions);

                driver = new RemoteWebDriver(
                        new URL("https://hub.browserstack.com/wd/hub"),
                        caps
                );

                return driver;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Driver initialization failed");
    }
}