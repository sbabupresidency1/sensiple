package com.sensiple.qa.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Babu on 2017-02-09.
 */
public class ConsoleLoggingListener implements ITestListener {
    private final Logger log = Logger.getLogger(ConsoleLoggingListener.class.getName());

    private static String getTestId(final ITestResult result) {
        final Object[] parameters = result.getParameters();
        final String id;
        if (parameters != null && parameters.length > 0) {
            id = parameters[0].toString();
        } else {
            id = result.getName();
        }
        return id;
    }

    @Override
    public void onTestStart(final ITestResult result) {
        this.log.info(String.format("%s: starting", getTestId(result)));
    }

    @Override
    public void onTestSuccess(final ITestResult result) {
        this.log.info(String.format("%s: success", getTestId(result)));
    }

    @Override
    public void onTestFailure(final ITestResult result) {
        this.log.log(Level.WARNING, String.format("%s: FAILED", getTestId(result)), result.getThrowable());
    }

    @Override
    public void onTestSkipped(final ITestResult result) {
        this.log.info(String.format("%s: skipped", getTestId(result)));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
        onTestFailure(result);
    }

    @Override
    public void onStart(final ITestContext context) {
        // NOP
    }

    @Override
    public void onFinish(final ITestContext context) {
        // NOP
    }
}
