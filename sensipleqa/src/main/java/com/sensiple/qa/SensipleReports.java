package com.sensiple.qa;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.sensiple.qa.reports.CaptureScreen;
import com.sensiple.qa.utils.Attributes;
import com.sensiple.qa.utils.Directory;
import com.google.common.io.Files;
import com.sensiple.qa.enums.LogAs;
import com.sensiple.qa.exceptions.SensipleReporterStepFailedException;
import com.sensiple.qa.utils.AuthorDetails;
import com.sensiple.qa.utils.Platform;
import com.sensiple.qa.utils.Steps;

public class SensipleReports {
	private static WebDriver driver;
	public static final int MAX_BAR_REPORTS = 10;
	public static final String MESSAGE = "Sensiple Reporter: Preparing Reports";
	public static String indexPageDescription = "Reports Description";
	public static String currentRunDescription = "";
	private static String screenShotNumber;
	private static long lastExecutionTime = 0L;
	private static long currentExecutionTime;
	public static final String EMPTY = "";
	public static final String STEP_NUM = "STEP";
	public static final String PASSED_BUT_FAILED = "passedButFailed";

	public static void setWebDriver(WebDriver paramWebDriver)  {
		driver = paramWebDriver;
		Platform.prepareDetails(driver);
	}

	public static WebDriver getWebDriver()  {
		return driver;
	}

	public static void setAuthorInfo(String paramString1, String paramString2, String paramString3)  {
		AuthorDetails localAuthorDetails = new AuthorDetails();
		localAuthorDetails.setAuthorName(paramString1);
		localAuthorDetails.setCreationDate(paramString2);
		localAuthorDetails.setVersion(paramString3);
		Reporter.getCurrentTestResult().setAttribute("authorInfo", localAuthorDetails);
	}

	public static void setAuthorInfoAtClassLevel(String authorName, String creationDate, String version)  {
		String str = java.lang.Thread.currentThread().getStackTrace()[2].getClassName();
		AuthorDetails localAuthorDetails = new AuthorDetails();
		localAuthorDetails.setAuthorName(authorName);
		localAuthorDetails.setCreationDate(creationDate);
		localAuthorDetails.setVersion(version);
		Attributes.setClassLevelAuthors(str, localAuthorDetails);
	}

	public static void setTestCaseReqCoverage(String coverage)  {
		Reporter.getCurrentTestResult().setAttribute("reqCoverage", coverage);
	}

	private static void stepFailureHandler(ITestResult ITestResult, Steps Steps, LogAs logAs)  {
		if (logAs == LogAs.FAILED)
		{
			buildReportData(Steps);
			if (Directory.continueExecutionAfterStepFailed) {
				ITestResult.setAttribute("passedButFailed", "passedButFailed");
			} else {
				throw new SensipleReporterStepFailedException();
			}
			return;
		}
		buildReportData(Steps);
	}

	public static void add(String desc, LogAs logAs, CaptureScreen captureScreen)  {
		if (captureScreen != null) {
			if (captureScreen.isCaptureBrowserPage()) {
				takeBrowserPageScreenShot();
			} else if (captureScreen.isCaptureDesktop()) {
				takeDesktopScreenshot();
			} else if (captureScreen.isCaptureWebElement()) {
				takeWebElementScreenShot(captureScreen.getElement());
			}
		}
		Steps localSteps = new Steps();
		localSteps.setDescription(desc);
		localSteps.setInputValue("");
		localSteps.setExpectedValue("");
		localSteps.setActualValue("");
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(logAs);
		stepFailureHandler(Reporter.getCurrentTestResult(), localSteps, logAs);
	}

	public static void add(String description, String inputValue, LogAs logAs, CaptureScreen captureScreen)  {
		if (captureScreen != null) {
			if (captureScreen.isCaptureBrowserPage()) {
				takeBrowserPageScreenShot();
			} else if (captureScreen.isCaptureDesktop()) {
				takeDesktopScreenshot();
			} else if (captureScreen.isCaptureWebElement()) {
				takeWebElementScreenShot(captureScreen.getElement());
			}
		}
		Steps localSteps = new Steps();
		localSteps.setDescription(description);
		localSteps.setInputValue(StringUtils.trimToEmpty(inputValue));
		localSteps.setExpectedValue("");
		localSteps.setActualValue("");
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(logAs);
		stepFailureHandler(Reporter.getCurrentTestResult(), localSteps, logAs);
	}

	public static void add(String desc, String expectedValue, String actualValue, LogAs logAs, CaptureScreen captureScreen)  {
		if (captureScreen != null) {
			if (captureScreen.isCaptureBrowserPage()) {
				takeBrowserPageScreenShot();
			} else if (captureScreen.isCaptureDesktop()) {
				takeDesktopScreenshot();
			} else if (captureScreen.isCaptureWebElement()) {
				takeWebElementScreenShot(captureScreen.getElement());
			}
		}
		Steps localSteps = new Steps();
		localSteps.setDescription(desc);
		localSteps.setInputValue("");
		localSteps.setExpectedValue(expectedValue);
		localSteps.setActualValue(actualValue);
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(logAs);
		stepFailureHandler(Reporter.getCurrentTestResult(), localSteps, logAs);
	}
/**
 * Add inputs for generating reports
 * @param desc
 * @param inputValue
 * @param expectedValue
 * @param actualValue
 * @param paramLogAs
 * @param paramCaptureScreen
 */
	public static void add(String action,String desc, String inputValue, String expectedValue, String actualValue, LogAs paramLogAs, CaptureScreen paramCaptureScreen)  {
		if (paramCaptureScreen != null) {
			if (paramCaptureScreen.isCaptureBrowserPage()) {
				takeBrowserPageScreenShot();
			} else if (paramCaptureScreen.isCaptureDesktop()) {
				takeDesktopScreenshot();
			} else if (paramCaptureScreen.isCaptureWebElement()) {
				takeWebElementScreenShot(paramCaptureScreen.getElement());
			}
		}
		Steps localSteps = new Steps();
		localSteps.setActions(StringUtils.trimToEmpty(action));
		localSteps.setDescription(StringUtils.trimToEmpty(desc));
		localSteps.setInputValue(StringUtils.trimToEmpty(inputValue));
		localSteps.setExpectedValue(StringUtils.trimToEmpty(expectedValue));
		localSteps.setActualValue(StringUtils.trimToEmpty(actualValue));
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(paramLogAs);
		stepFailureHandler(Reporter.getCurrentTestResult(), localSteps, paramLogAs);
	}
/**
 * Get and build current test results
 * @param steps
 */
	private static void buildReportData(Steps steps)  {
		screenShotNumber = null;
		int i = Reporter.getOutput().size() + 1;
		Reporter.getCurrentTestResult().setAttribute("STEP" + i, steps);
		Reporter.log("STEP" + i);
	}

	private static String getExecutionTime()  {
		currentExecutionTime = System.currentTimeMillis();
		long l = currentExecutionTime - lastExecutionTime;
		if (l > 1000L)
		{
			l /= 1000L;
			lastExecutionTime = currentExecutionTime;
			//return l + " Sec";
			return l + " s";
		}
		lastExecutionTime = currentExecutionTime;
		//return l + " Milli Sec";
		return l + " ms";
	}

	private static String getLineNumDesc()  {
		String str = "" + java.lang.Thread.currentThread().getStackTrace()[3].getLineNumber();
		return str;
	}
/**
 * Screenshot for Desktop
 */
	private static void takeDesktopScreenshot()  {
		if (!Directory.takeScreenshot) {
			return;
		}
		ITestResult localITestResult = Reporter.getCurrentTestResult();
		String str = localITestResult.getAttribute("reportDir").toString() + Directory.SEP + Directory.IMGDIRName;
		screenShotNumber = Reporter.getOutput(Reporter.getCurrentTestResult()).size() + 1 + "";
		File localFile = new File(str + Directory.SEP + screenShotNumber + ".PNG");
		try
		{
			Rectangle localRectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage localBufferedImage = new Robot().createScreenCapture(localRectangle);
			ImageIO.write(localBufferedImage, "PNG", localFile);
		}
		catch (Exception localException)
		{
			screenShotNumber = null;
		}
	}
/**
 * Screenshot for BrowserPage
 */
	private static void takeBrowserPageScreenShot()  {
		if (!Directory.takeScreenshot) {
			return;
		}
		if (getWebDriver() == null)
		{
			screenShotNumber = null;
			return;
		}
		ITestResult localITestResult = Reporter.getCurrentTestResult();
		String str = localITestResult.getAttribute("reportDir").toString() + Directory.SEP + Directory.IMGDIRName;
		screenShotNumber = Reporter.getOutput(Reporter.getCurrentTestResult()).size() + 1 + "";
		File localFile = new File(str + Directory.SEP + screenShotNumber + ".PNG");
		try
		{
			WebDriver localWebDriver;
			if (getWebDriver().getClass().getName().equals("org.openqa.selenium.remote.RemoteWebDriver")) {
				localWebDriver = new Augmenter().augment(getWebDriver());
			} else {
				localWebDriver = getWebDriver();
			}
			if ((localWebDriver instanceof TakesScreenshot))
			{
				byte[] arrayOfByte = (byte[])((TakesScreenshot)localWebDriver).getScreenshotAs(OutputType.BYTES);
				Files.write(arrayOfByte, localFile);
			}
			else
			{
				screenShotNumber = null;
			}
		}
		catch (Exception localException)
		{
			screenShotNumber = null;
		}
	}
/**
 * Screenshot for Webelement
 * @param webElement
 */
	private static void takeWebElementScreenShot(WebElement webElement)  {
		if (!Directory.takeScreenshot) {
			return;
		}
		if (getWebDriver() == null)
		{
			screenShotNumber = null;
			return;
		}
		ITestResult localITestResult = Reporter.getCurrentTestResult();
		String str = localITestResult.getAttribute("reportDir").toString() + Directory.SEP + Directory.IMGDIRName;
		screenShotNumber = Reporter.getOutput(Reporter.getCurrentTestResult()).size() + 1 + "";
		File localFile1 = new File(str + Directory.SEP + screenShotNumber + ".PNG");
		try
		{
			WebDriver localWebDriver;
			if (getWebDriver().getClass().getName().equals("org.openqa.selenium.remote.RemoteWebDriver")) {
				localWebDriver = new Augmenter().augment(getWebDriver());
			} else {
				localWebDriver = getWebDriver();
			}
			if ((localWebDriver instanceof TakesScreenshot))
			{
				File localFile2 = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				BufferedImage localBufferedImage1 = ImageIO.read(localFile2);
				Point localPoint = webElement.getLocation();
				int i = webElement.getSize().getWidth();
				int j = webElement.getSize().getHeight();
				BufferedImage localBufferedImage2 = localBufferedImage1.getSubimage(localPoint.getX(), localPoint.getY(), i, j);
				ImageIO.write(localBufferedImage2, "PNG", localFile1);
			}
			else
			{
				screenShotNumber = null;
			}
		}
		catch (Exception localException)
		{
			screenShotNumber = null;
		}
	}

	public static void takeScreenShot()  {
		if (!Directory.takeScreenshot) {
			return;
		}
		if (getWebDriver() == null)
		{
			screenShotNumber = null;
			return;
		}
		ITestResult localITestResult = Reporter.getCurrentTestResult();
		String str = localITestResult.getAttribute("reportDir").toString() + Directory.SEP + Directory.IMGDIRName;
		screenShotNumber = Reporter.getOutput(Reporter.getCurrentTestResult()).size() + 1 + "";
		File localFile = new File(str + Directory.SEP + screenShotNumber + ".PNG");
		try
		{
			WebDriver localWebDriver;
			if ((getWebDriver().getClass().getName().equals("org.openqa.selenium.remote.RemoteWebDriver")) || ((getWebDriver() instanceof RemoteWebDriver))) {
				localWebDriver = new Augmenter().augment(getWebDriver());
			} else {
				localWebDriver = getWebDriver();
			}
			if ((localWebDriver instanceof TakesScreenshot))
			{
				byte[] arrayOfByte = (byte[])((TakesScreenshot)localWebDriver).getScreenshotAs(OutputType.BYTES);
				Files.write(arrayOfByte, localFile);
			}
			else{
				screenShotNumber = null;
			}
		}
		catch (Exception localException)   {
			screenShotNumber = null;
		}
	}

	static  {
		try
		{
			lastExecutionTime = Reporter.getCurrentTestResult().getStartMillis();
		}
		catch (Exception localException)    {
			lastExecutionTime = ((Long)Attributes.getAttribute("startExecution")).longValue();
		}
	}
}

