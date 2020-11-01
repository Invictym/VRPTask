package framework.utils;

import framework.logger.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestStepsListener implements ITestListener {

    private String divider = "-------------------=========================================-------------------";

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(divider);
        Log.info("TEST [" +iTestResult.getTestClass().getName() + "." + iTestResult.getName() + "]");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info(divider);
    }
}
