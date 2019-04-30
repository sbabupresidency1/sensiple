package com.sensiple.qa.listeners;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.IExecutionListener;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.sensiple.qa.exceptions.SensipleReporterStepFailedException;
import com.sensiple.qa.utils.Platform;
import com.sensiple.qa.utils.SettingsFile;
import com.sensiple.qa.utils.TestParameters;
import com.sensiple.qa.writers.CurrentRunPageWriter;
import com.sensiple.qa.writers.HTMLDesignFilesJSWriter;
import com.sensiple.qa.writers.IndexPageWriter;
import com.sensiple.qa.writers.TestCaseReportsPageWriter;
import com.sensiple.qa.SensipleReports;
import com.sensiple.qa.exceptions.SensipleReporterException;
import com.sensiple.qa.utils.Attributes;
import com.sensiple.qa.utils.Directory;
import com.sensiple.qa.writers.ConsolidatedReportsPageWriter;

public class SensipleReportsListener implements ITestListener, IExecutionListener, IReporter, ISuiteListener {

	static Logger log = Logger.getLogger(SensipleReportsListener.class.getName());
	int runCount = 0;
	List<ITestResult> passedTests = new ArrayList<ITestResult>();
	List<ITestResult> failedTests = new ArrayList<ITestResult>();
	List<ITestResult> skippedTests = new ArrayList<ITestResult>();
	
	public static boolean suiteStarted = false;

	public void onStart(ITestContext paramITestContext) {}

	public void onFinish(ITestContext paramITestContext) {}

	public void onTestFailedButWithinSuccessPercentage(ITestResult paramITestResult) {}

	public void onTestFailure(ITestResult testResult)  {
		this.failedTests.add(testResult);
		setPlatfromBrowserDetails(testResult);
	}

	public void onTestSkipped(ITestResult testResult)  {
		if ((testResult.getThrowable() instanceof SkipException))  {
			this.skippedTests.add(testResult);
			return;
		}
		createReportDir(testResult);
		this.skippedTests.add(testResult);
		setPlatfromBrowserDetails(testResult);
	}

	public void onTestStart(ITestResult paramITestResult) {}

	public void onTestSuccess(ITestResult result)  {
		try
		{
			if (result.getAttribute("passedButFailed").equals("passedButFailed"))
			{
				result.setStatus(2);
				result.setThrowable(new SensipleReporterStepFailedException());
				this.failedTests.add(result);
				setPlatfromBrowserDetails(result);
				return;
			}
		}
		catch (NullPointerException localNullPointerException) {}
		this.passedTests.add(result);
		setPlatfromBrowserDetails(result);
	}

	public static void setPlatfromBrowserDetails(ITestResult paramITestResult)  {
		Platform.prepareDetails(SensipleReports.getWebDriver());
		paramITestResult.setAttribute(Platform.BROWSER_NAME_PROP, Platform.BROWSER_NAME);
		paramITestResult.setAttribute(Platform.BROWSER_VERSION_PROP, Platform.BROWSER_VERSION);
	}

	public static void createReportDir(ITestResult paramITestResult)  {
		String str = getReportDir(paramITestResult);
		Directory.mkDirs(str);
		Directory.mkDirs(str + Directory.SEP + Directory.SCREENSHOT_DIRName);
	}

	public static String getRelativePathFromSuiteLevel(ITestResult iTestResult)  {
		String suiteName = iTestResult.getTestContext().getSuite().getName();
		String testName = iTestResult.getTestContext().getCurrentXmlTest().getName();
		String className = iTestResult.getTestClass().getName().replace(".", Directory.SEP);
		String methodName = iTestResult.getMethod().getMethodName();
		methodName = methodName + "_Iteration" + (iTestResult.getMethod().getCurrentInvocationCount() + 1);
		return suiteName + Directory.SEP + testName + Directory.SEP + className + Directory.SEP + methodName;
	}

	public static String getReportDir(ITestResult testResult)  {
		String reportDir = getRelativePathFromSuiteLevel(testResult);
		testResult.setAttribute("relativeReportDir", reportDir);
		String str2 = Directory.RUNDir + Directory.SEP + reportDir;
		testResult.setAttribute("iteration", Integer.valueOf(testResult.getMethod().getCurrentInvocationCount() + 1));
		testResult.setAttribute("reportDir", str2);
		return str2;
	}

	public void setTickInterval(List<ITestResult> paramList1, List<ITestResult> paramList2, List<ITestResult> paramList3)
			throws SensipleReporterException  {
		int i = SettingsFile.getHighestTestCaseNumber();
		int j = SettingsFile.getBiggestNumber(new int[] { i, paramList1.size(), paramList2.size(), paramList3.size() });
		int k = j / 10;
		if (k > 1) {
			HTMLDesignFilesJSWriter.TICK_INTERVAL = k;
		}
	}

	public void onFinish()  {
		try
		{
			String passedTest = SettingsFile.get("passedList") + this.passedTests.size() + ';';
			String failedTest = SettingsFile.get("failedList") + this.failedTests.size() + ';';
			String skippedTest = SettingsFile.get("skippedList") + this.skippedTests.size() + ';';
			SettingsFile.set("passedList", passedTest);
			SettingsFile.set("failedList", failedTest);
			SettingsFile.set("skippedList", skippedTest);
			setTickInterval(this.passedTests, this.failedTests, this.skippedTests);
			HTMLDesignFilesJSWriter.lineChartJS(passedTest, failedTest, skippedTest, this.runCount);
			HTMLDesignFilesJSWriter.barChartJS(passedTest, failedTest, skippedTest, this.runCount);
			HTMLDesignFilesJSWriter.pieChartJS(this.passedTests.size(), this.failedTests.size(), this.skippedTests.size(), this.runCount);
			generateIndexPage();
			long l = ((Long)Attributes.getAttribute("startExecution")).longValue();
			generateConsolidatedPage();
			generateCurrentRunPage(this.passedTests, this.failedTests, this.skippedTests, l, System.currentTimeMillis());
			startReportingForPassed(this.passedTests);
			startReportingForFailed(this.failedTests);
			startReportingForSkipped(this.skippedTests);
			/* if (Directory.generateExcelReports) {
        ExcelReports.generateExcelReport(Directory.RUNDir + Directory.SEP + "(" + Directory.REPORTSDIRName + ") " + Directory.RUNName + this.runCount + ".xlsx", this.passedTests, this.failedTests, this.skippedTests);
      }*/
			if (Directory.generateConfigReports) {
				ConfigurationListener.startConfigurationMethodsReporting(this.runCount);
			}
		}
		catch (Exception localException)
		{
			localException.printStackTrace();
			throw new IllegalStateException(localException);
		}
	}

	public void startCreatingDirs(ISuite paramISuite)  {
		Directory.mkDirs(Directory.RUNDir + Directory.SEP + paramISuite.getName());
		Iterator<?> localIterator = paramISuite.getXmlSuite().getTests().iterator();
		while (localIterator.hasNext())
		{
			XmlTest localXmlTest = (XmlTest)localIterator.next();
			Directory.mkDirs(Directory.RUNDir + Directory.SEP + paramISuite.getName() + Directory.SEP + localXmlTest.getName());
		}
	}

	public void generateIndexPage()  {
		PrintWriter localPrintWriter = null;
		try
		{
			localPrintWriter = new PrintWriter(Directory.REPORTSDir + Directory.SEP + "index.html");
			IndexPageWriter.header(localPrintWriter);
			IndexPageWriter.content(localPrintWriter, SensipleReports.indexPageDescription);
			IndexPageWriter.footer(localPrintWriter);
			return;
		}
		catch (FileNotFoundException localFileNotFoundException)
		{
			localFileNotFoundException.printStackTrace();
		}
		finally
		{
			try
			{
				localPrintWriter.close();
			}
			catch (Exception localException3)
			{
				localPrintWriter = null;
			}
		}
	}

	public void generateCurrentRunPage(List<ITestResult> passedList, List<ITestResult> failedList, List<ITestResult> skippedList, long execution, long executionTime)  {
		PrintWriter localPrintWriter = null;
		try
		{
			localPrintWriter = new PrintWriter(Directory.RUNDir + Directory.SEP + "CurrentRun.html");
			CurrentRunPageWriter.header(localPrintWriter);
			CurrentRunPageWriter.menuLink(localPrintWriter, 0);
			CurrentRunPageWriter.content(localPrintWriter, passedList, failedList, skippedList, ConfigurationListener.passedConfigurations, ConfigurationListener.failedConfigurations, ConfigurationListener.skippedConfigurations, this.runCount, execution, executionTime);
			CurrentRunPageWriter.footer(localPrintWriter);
			return;
		}
		catch (FileNotFoundException localFileNotFoundException)
		{
			localFileNotFoundException.printStackTrace();
		}
		finally
		{
			try
			{
				localPrintWriter.close();
			}
			catch (Exception localException3)
			{
				localPrintWriter = null;
			}
		}
	}

	public void generateConsolidatedPage()  {
		PrintWriter localPrintWriter = null;
		try
		{
			localPrintWriter = new PrintWriter(Directory.RESULTSDir + Directory.SEP + "ConsolidatedPage.html");
			ConsolidatedReportsPageWriter.header(localPrintWriter);
			ConsolidatedReportsPageWriter.menuLink(localPrintWriter, this.runCount);
			ConsolidatedReportsPageWriter.content(localPrintWriter);
			ConsolidatedReportsPageWriter.footer(localPrintWriter);
			return;
		}
		catch (FileNotFoundException localFileNotFoundException)
		{
			localFileNotFoundException.printStackTrace();
		}
		finally
		{
			try
			{
				localPrintWriter.close();
			}
			catch (Exception localException3)
			{
				localPrintWriter = null;
			}
		}
	}

	public static String getTestCaseName(ITestResult testResult)  {
		TestParameters params = ((TestParameters)testResult.getParameters()[0]);
		return params.getTestCaseName();
	}

	public void startReportingForPassed(List<ITestResult> testResultList)  {
		PrintWriter localPrintWriter = null;
		Iterator<ITestResult> localIterator = testResultList.iterator();
		while (localIterator.hasNext())
		{
			ITestResult testResult = (ITestResult)localIterator.next();
			String str = testResult.getAttribute("reportDir").toString();
			try
			{
				localPrintWriter = new PrintWriter(str + Directory.SEP + getTestCaseName(testResult) + ".html");
				TestCaseReportsPageWriter.header(localPrintWriter, testResult);
				TestCaseReportsPageWriter.menuLink(localPrintWriter, testResult, 0);
				TestCaseReportsPageWriter.content(localPrintWriter, testResult, this.runCount);
				TestCaseReportsPageWriter.footer(localPrintWriter);
				try
				{
					localPrintWriter.close();
				}
				catch (Exception localException1)
				{
					localPrintWriter = null;
				}
			}
			catch (FileNotFoundException localFileNotFoundException)
			{
				localFileNotFoundException.printStackTrace();
			}
			finally
			{
				try
				{
					localPrintWriter.close();
				}
				catch (Exception localException3)
				{
					localPrintWriter = null;
				}
			}
		}
	}

	public void startReportingForFailed(List<ITestResult> testCaseList)  {
		PrintWriter localPrintWriter = null;
		Iterator<ITestResult> localIterator = testCaseList.iterator();
		while (localIterator.hasNext())
		{
			ITestResult testResult = (ITestResult)localIterator.next();
			String str = testResult.getAttribute("reportDir").toString();
			try
			{
				localPrintWriter = new PrintWriter(str + Directory.SEP + getTestCaseName(testResult) + ".html");
				TestCaseReportsPageWriter.header(localPrintWriter, testResult);
				TestCaseReportsPageWriter.menuLink(localPrintWriter, testResult, 0);
				TestCaseReportsPageWriter.content(localPrintWriter, testResult, this.runCount);
				TestCaseReportsPageWriter.footer(localPrintWriter);
				try
				{
					localPrintWriter.close();
				}
				catch (Exception localException1)
				{
					localPrintWriter = null;
				}
			}
			catch (FileNotFoundException localFileNotFoundException) {}finally
			{
				try
				{
					localPrintWriter.close();
				}
				catch (Exception localException3)
				{
					localPrintWriter = null;
				}
			}
		}
	}

	public void startReportingForSkipped(List<ITestResult> testCaseList)  {
		PrintWriter localPrintWriter = null;
		Iterator<ITestResult> localIterator = testCaseList.iterator();
		while (localIterator.hasNext())
		{
			ITestResult testResult = (ITestResult)localIterator.next();
			String str = testResult.getAttribute("reportDir").toString();
			try
			{
				localPrintWriter = new PrintWriter(str + Directory.SEP + getTestCaseName(testResult) + ".html");
				TestCaseReportsPageWriter.header(localPrintWriter, testResult);
				TestCaseReportsPageWriter.menuLink(localPrintWriter, testResult, 0);
				TestCaseReportsPageWriter.content(localPrintWriter, testResult, this.runCount);
				TestCaseReportsPageWriter.footer(localPrintWriter);
				try
				{
					localPrintWriter.close();
				}
				catch (Exception localException1)
				{
					localPrintWriter = null;
				}
			}
			catch (FileNotFoundException localFileNotFoundException)
			{
				localFileNotFoundException.printStackTrace();
			}
			finally
			{
				try
				{
					localPrintWriter.close();
				}
				catch (Exception localException3)
				{
					localPrintWriter = null;
				}
			}
		}
	}

	public void onExecutionFinish()  {
		Attributes.setAttribute("endExecution", Long.valueOf(System.currentTimeMillis()));
		if (Directory.recordSuiteExecution) {
			try
			{
				//this.recorder.stop();
			}
			catch (Throwable localThrowable)
			{
				throw new IllegalStateException(localThrowable);
			}
		}
	}

	private  void  initChecking()  {
		try
		{
			Directory.verifyRequiredFiles();
			SettingsFile.correctErrors();
			this.runCount = (Integer.parseInt(SettingsFile.get("run").trim()) + 1);
			SettingsFile.set("run", "" + this.runCount);
			String setTestRun = SettingsFile.get("testRunDT") + Directory.createTestRunDateTime() + ';';
			SettingsFile.set("testRunDT", setTestRun);
			Directory.RUNDir += this.runCount;
			Directory.mkDirs(Directory.RUNDir);			
		}
		catch (Exception localException)
		{
			throw new IllegalStateException(localException);
		}
	}

	public void onExecutionStart()  {
		Attributes.setAttribute("startExecution", Long.valueOf(System.currentTimeMillis()));
		initChecking();
	}

	public void generateReport(List<XmlSuite> paramList, List<ISuite> paramList1, String paramString)  {
		Iterator<ISuite> localIterator = paramList1.iterator();
		while (localIterator.hasNext())
		{
			ISuite localISuite = (ISuite)localIterator.next();
			Attributes.setSuiteNameMapper(localISuite.getName());
			startCreatingDirs(localISuite);
			onFinish();
		}
	}

	public void onFinish(ISuite paramISuite) {}

	public void onStart(ISuite paramISuite) {}
}

