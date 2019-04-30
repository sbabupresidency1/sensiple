package com.sensiple.qa.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class MethodListener implements IInvokedMethodListener {

	public void afterInvocation(IInvokedMethod paramIInvokedMethod, ITestResult paramITestResult) {}

	public void beforeInvocation(IInvokedMethod paramIInvokedMethod, ITestResult testResult)  {
		if ((!paramIInvokedMethod.isConfigurationMethod()) || (paramIInvokedMethod.isTestMethod()))    {
			SensipleReportsListener.createReportDir(testResult);
		}
	}
}

