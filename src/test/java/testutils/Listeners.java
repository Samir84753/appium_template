package testutils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.android.AndroidDriver;
import samir.appiumecommerce.andriodBaseTest;
import samir.appiumecommerce.utils.Actions_util;

public class Listeners implements ITestListener {

    ExtentTest test;

    ExtentReports extent = ExtentReporterNG.getReporterObject();
    Actions_util action;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
        ITestListener.super.onStart(context);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // TODO Auto-generated method stub
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        AndroidDriver driver = andriodBaseTest.getDriver();
        action = new Actions_util(driver);
        // get error message in case of failure.
        String errorMessage = result.getThrowable().getMessage();
        // log error message,status on test report.
        test.log(Status.FAIL, "Failed" + errorMessage);

        try {
            action.getscreenShot(result.getMethod().getMethodName());
            String path = System.getProperty("user.dir") + "/reports/screenshots/" + result.getMethod().getMethodName()
                    + ".jpg";
            test.addScreenCaptureFromPath(path, result.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Skipped");
    }

    @Override
    public void onTestStart(ITestResult result) {
        Test testAnnotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
        String testDescription = (testAnnotation != null) ? testAnnotation.description() : " ";

        test = extent.createTest(result.getMethod().getMethodName(), testDescription);

        // test.assignCategory("Android");
        // test.assignDevice("Samsung Galaxy A53");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Passed");
    }

}
