package samir.appiumecommerce;

import java.io.IOException;

import org.openqa.selenium.By;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import samir.appiumecommerce.pageObject_android.LoginPage;
import samir.appiumecommerce.utils.Actions_util;

public class LoginScreen_Test extends andriodBaseTest {
    LoginPage loginPage;
    Actions_util action;

    /**
     * Runs before every method.
     * sets up page object and action class object
     * initializes appactivity to login page before each method.
     *
     */
    @BeforeMethod
    public void preTestSetup() {
        loginPage = new LoginPage(driver);
        action = new Actions_util(driver);

        driver.executeScript("mobile: startActivity", ImmutableMap.of("intent",
                loginPage.loginAppPackage + "/" + loginPage.loginAppActivity));
    }

    @Test(description = "User can Login")
    public void loginSuccess() throws InterruptedException {
        Thread.sleep(2000);
        loginPage.nameField_locator.sendKeys("Samir");
        loginPage.letsshopButton_locator.click();

        // assert the app activity after login.
        Assert.assertEquals(action.getCurrentActivity(), loginPage.afterLoginActivity);
    }

    @Test(description = "User cannot login if name field is empty.")
    public void loginFail() throws InterruptedException, IOException {
        Thread.sleep(2000);
        loginPage.letsshopButton_locator.click();
        Assert.assertEquals(loginPage.nameErrorBox_locator.getText(), "Please enter your name",
                " :Assertion Failed On Locator: " + loginPage.nameErrorBox_locator);

    }

    @Test(description = "User can scroll through all countries and select a country option.")
    public void countryDropdownCheck() throws InterruptedException {
        Thread.sleep(2000);
        loginPage.countryDropdown_locator.click();

        String country = "Bhutan";
        action.scrollToText(country);
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + country +
                "']")).click();

        Assert.assertEquals(loginPage.countryDropdown_locator.getText(), country,
                " :Assertion Failed On Locator: " + loginPage.countryDropdown_locator);

    }
}