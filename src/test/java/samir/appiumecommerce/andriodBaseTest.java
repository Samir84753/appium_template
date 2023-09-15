package samir.appiumecommerce;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class andriodBaseTest {
    public static AndroidDriver driver;
    public AppiumDriverLocalService service;

    /**
     * Runs before starting test for each class.
     * 
     * @throws MalformedURLException
     */
    @BeforeClass
    public void config() throws MalformedURLException {
        // To start appium server automatically.
        service = new AppiumServiceBuilder()
                .withAppiumJS(
                        // directory where appium is located on node modules.
                        new File("/home/samir/.nvm/versions/node/v18.17.0/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withLogFile(new File("appium_logs.txt"))
                .build();
        service.start();

        URL url = new URL("http://127.0.0.1:4723/");

        // defining capabilities:
        // https://github.com/appium/appium-uiautomator2-driver#capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("RZCT31CQ8TN");
        options.setApp("src/test/java/samir/resources/General-Store.apk");

        driver = new AndroidDriver(url, options);

        // defining implicit wait timeout.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * Run after completing test of a class.
     */
    @AfterClass
    public void teardown() {
        // driver and service teardown.
        driver.quit();
        service.stop();
    }

    /**
     * Clears screenshots dir on reports folder.
     * 
     * @throws IOException
     */
    @BeforeSuite
    public void beforeSuite() throws IOException {
        String screeshotDir = System.getProperty("user.dir") + "/reports/screenshots/";
        FileUtils.cleanDirectory(new File(screeshotDir));
    }

    public static AndroidDriver getDriver() {
        return driver;
    }

}
