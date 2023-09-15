package testutils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    static ExtentReports extent;

    public static ExtentReports getReporterObject() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        // reporter config
        String path = System.getProperty("user.dir") + "/reports/Appium_Report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Ecommerce Test report");
        reporter.config().setDocumentTitle("Test Result");

        extent = new ExtentReports();
        extent.attachReporter(reporter);

        // System Information
        // extent.setSystemInfo("Tester", "Samir Maharjan");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Build Version", "1.0");
        extent.setSystemInfo("Execution Date", formattedDate);
        extent.setSystemInfo("Device", "Samsung Galaxy A53");
        extent.setSystemInfo("Android", "v13");

        return extent;
    }
}
