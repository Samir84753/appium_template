package samir.appiumecommerce.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import samir.appiumecommerce.utils.Actions_util;

public class Actions_util {
    public AndroidDriver driver;

    public Actions_util(AndroidDriver driver) {
        this.driver = driver;
    }

    public String getCurrentActivity() {
        String currentActivity = driver.currentActivity();
        return currentActivity;
    }

    public void scrollToText(String text) {
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollTextIntoView(\"" + text
                                + "\")"));
    }

    public void getscreenShot(String filename) throws IOException {
        // Take a screenshot
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(
                System.getProperty("user.dir") + "/reports/screenshots/" + filename
                        + ".jpg");
        FileUtils.copyFile(srcFile, targetFile);

    }
}