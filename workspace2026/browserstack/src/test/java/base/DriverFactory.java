package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.MutableCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver driver;

    public static WebDriver initDriver() {

        String runOn = System.getProperty("runOn", "local");
        System.out.println("RUN MODE : " + runOn);

        try {

            // ===== LOCAL =====
            if ("local".equalsIgnoreCase(runOn)) {
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                System.out.println("Running tests locally");
                return driver;
            }

            // ===== BROWSERSTACK =====
            if ("browserstack".equalsIgnoreCase(runOn)) {

                String username = System.getenv("BROWSERSTACK_USERNAME");
                String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

                if (username == null || accessKey == null) {
                    throw new RuntimeException("BrowserStack credentials not found in Jenkins environment variables");
                }

                MutableCapabilities caps = new MutableCapabilities();
                caps.setCapability("browserName", "Chrome");
                caps.setCapability("browserVersion", "latest");

                Map<String, Object> bstackOptions = new HashMap<String, Object>();
                bstackOptions.put("os", "Windows");
                bstackOptions.put("osVersion", "11");
                bstackOptions.put("projectName", "Selenium Java Framework");
                bstackOptions.put("buildName", "Jenkins Build #" + System.getenv("BUILD_NUMBER"));
                bstackOptions.put("sessionName", "Google Title Test");

                caps.setCapability("bstack:options", bstackOptions);

                String hubURL = "https://" + username + ":" + accessKey +
                        "@hub-cloud.browserstack.com/wd/hub";

                driver = new RemoteWebDriver(new URL(hubURL), caps);

                System.out.println("Running tests on BrowserStack");
                System.out.println("Hub URL: " + hubURL);

                return driver;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Driver initialization failed");
    }
}