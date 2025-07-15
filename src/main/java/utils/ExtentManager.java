package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports createInstance() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/extent/extent-report.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("SauceDemo Tests");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static void setTest(ExtentTest testLogger) {
        test.set(testLogger);
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}