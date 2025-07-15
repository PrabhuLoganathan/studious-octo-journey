package base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import utils.ConfigReader;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private final int maxRetryCount = Integer.parseInt(ConfigReader.getProperty("retry.count"));


    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}