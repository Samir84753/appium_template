package samir.appiumecommerce.pageObject_android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {
    AndroidDriver driver;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "android:id/text1")
    public WebElement countryDropdown_locator;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    public WebElement nameField_locator;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    public WebElement letsshopButton_locator;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.Toast[1]")
    public WebElement nameErrorBox_locator;

    public String loginAppPackage = "com.androidsample.generalstore";
    public String loginAppActivity = "com.androidsample.generalstore.MainActivity";

    public String afterLoginActivity = ".AllProductsActivity";
}
