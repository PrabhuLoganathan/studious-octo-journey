package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import utils.ConfigReader;
import utils.DriverFactory;
import utils.ExtentManager;

public class TestBase {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void startReport() {
        extent = ExtentManager.createInstance();
    }

    @AfterSuite
    public void endReport() {
        extent.flush();
    }

    @BeforeMethod
    public void setup(Method method) {
        DriverFactory.setDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("base.url"));
        test = extent.createTest(method.getName());
        ExtentManager.setTest(test);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentManager.getTest().log(Status.FAIL, result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentManager.getTest().log(Status.PASS, "Test passed");
        } else {
            ExtentManager.getTest().log(Status.SKIP, "Test skipped");
        }
        DriverFactory.closeDriver();
    }
}