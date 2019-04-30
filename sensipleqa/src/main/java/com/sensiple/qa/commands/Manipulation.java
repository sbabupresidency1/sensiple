package com.sensiple.qa.commands;

import java.awt.AWTException;
import net.sourceforge.tess4j.*;
import javax.swing.JOptionPane;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.html5.AddApplicationCache;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.IScreen;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import com.sensiple.qa.SensipleReports;
import com.sensiple.qa.enums.LogAs;
import com.sensiple.qa.util.CommandUtils;
import com.sensiple.qa.utils.Platform;
import com.sensiple.qa.writers.ReportsPage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;

import com.sensiple.qa.utils.Directory;

public class Manipulation extends CommandUtils implements OR {

	static Logger log = Logger.getLogger(Manipulation.class.getName());
	public static String ElementWait=Directory.WaitFor;
	public static int WaitElementSeconds=new Integer(ElementWait);

	public static void click(WebElement webElement) {
		try {
			if(webElement.isDisplayed()) {
				webElement.click();
			}
		}
		catch (StaleElementReferenceException e){ }
	}

	public static void jsClickByXPath(WebDriver driver,String NormalXpath) {
		WebElement element = driver.findElement(By.xpath(NormalXpath));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	public static void jsTypeByXPath(WebDriver driver,String NormalXpath, String InputData) {
		WebElement element = driver.findElement(By.xpath(NormalXpath));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].type ='"+InputData+"';",element);
	}
	//	public static void actionClick(WebDriver driver, WebElement webElement) {
	//		try {
	//			if(webElement.isEnabled()) {
	//				Actions action = new Actions(driver);
	//				action.click(webElement).build().perform();
	//			}
	//		} catch (StaleElementReferenceException e) { }
	//	}
	public static void actionClick(WebDriver driver, WebElement webElement) {
		try {
			if(webElement.isEnabled()) {
				//Actions action = new Actions(driver);
				//action.click(webElement).build().perform();
				webElement.click();
			}
		} catch (StaleElementReferenceException e) { }
	}

	public static void actionType(WebDriver driver,WebElement webElement,CharSequence... keysToSend){
		try {
			if(webElement.isEnabled())
			{
				//Actions action = new Actions(driver);
				//action.sendKeys(webElement, keysToSend).build().perform();
				webElement.sendKeys(keysToSend);
			}
		} catch (StaleElementReferenceException e) { }
	}

	//	public static void actionType(WebDriver driver,WebElement webElement,CharSequence... keysToSend){
	//		try {
	//			if(webElement.isEnabled())
	//			{
	//				Actions action = new Actions(driver);
	//				action.sendKeys(webElement, keysToSend).build().perform();
	//			}
	//		} catch (StaleElementReferenceException e) { }
	//	}

	public static void doubleClick(WebDriver driver, WebElement webElement) {
		try {
			Actions action = new Actions(driver).doubleClick(webElement);
			action.build().perform();
		} catch (StaleElementReferenceException e) {
			log.info("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			log.info("Element " + webElement + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			log.info("Element " + webElement + " was not clickable "
					+ e.getStackTrace());
		}
	}

	public static void clickAt(WebDriver driver, WebElement webElement, int x,int y) {
		try {
			System.out.println("Browser is " + Directory.browser);
			Robot robot = new Robot();
			robot.mouseMove(x,y);
			wait(driver, "3");
			robot.mousePress(InputEvent.BUTTON1_MASK);
			wait(driver, "1");
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			wait(driver, "1");

		} catch (Exception e) {
			log.info("Could not click " + e.getStackTrace());
		}

	}
	public static void clickUnavailabilityRedArea(WebDriver driver, WebElement webElement, int x,int y)
	{
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS"))
		{
			try {
				System.out.println("Browser is " + Directory.browser);
				Robot robot = new Robot();
				robot.mouseMove(x,y);
				wait(driver, "3");
				robot.mousePress(InputEvent.BUTTON1_MASK);
				wait(driver, "1");
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				wait(driver, "1");

			} catch (Exception e) {
				log.info("Could not click " + e.getStackTrace());
			}
		}
		else if(OSName.contains("MAC"))
		{
			if((Directory.browser).equalsIgnoreCase("chrome"))
			{
				Navigate.pageDown(driver);
				Robot robot;
				try {
					robot = new Robot();
					System.out.println("eeeentereed");
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Screen screen = new Screen();
				Pattern logo = new Pattern(Directory.uploadFilePath+"Capture7.JPG");
				wait(driver, "5");

				try {
					screen.wait(logo, 7000);
					screen.click(logo);
					screen.wait(logo, 7000);}
				catch (FindFailed e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}}
			else
			{
				Navigate.pageDown(driver);
				Robot robot;

				Screen screen = new Screen();
				Pattern logo = new Pattern(Directory.uploadFilePath+"Capture7.JPG");
				wait(driver, "5");

				try {
					screen.wait(logo, 7000);
					screen.click(logo);
					screen.wait(logo, 7000);}
				catch(Exception e4)
				{

				}
			}

		}
	}


	public static void clickAndHold(WebDriver driver,WebElement webElement)	{
		try {
			Actions builder = new Actions(driver);
			builder.clickAndHold(webElement).build().perform();
		} catch (StaleElementReferenceException e) {
			log.info("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			log.info("Element " + webElement + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			log.info("Element " + webElement + " was not clickable "
					+ e.getStackTrace());
		}
	}

	public static void clear(WebElement webElement) {
		webElement.clear();

	}
	public static void actionClear(WebDriver driver,WebElement webElement) {
		webElement.click();
		Actions action = new Actions(driver);
		webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), "55");
		action.sendKeys(Keys.DELETE);


	}

	public static String clearAndType(WebElement webElement, String keysToSend) {
		webElement.clear();
		try{Thread.sleep(2000);}catch(InterruptedException e){e.printStackTrace();}
		webElement.sendKeys(keysToSend);
		return keysToSend;
	}

	public static String sendKeys(WebElement webElement,String keysToSend) {
		webElement.sendKeys(keysToSend);
		return keysToSend;
	}

	public static void submit(WebElement webElement) {
		webElement.submit();
	}

	public static void keyDown(Actions actions, Keys keys) {
		actions.keyDown(keys);
	}

	public static void keyUp(Actions actions, Keys keys) {
		actions.keyUp(keys);
	}

	public static String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public static String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public static void mouseOver(WebDriver driver, WebElement webElement) {
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(webElement).build().perform();
		} catch (StaleElementReferenceException e) {
			log.info("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			log.info("Element " + webElement + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			log.info("Element " + webElement + " was not mouseOver "
					+ e.getStackTrace());
		}
	}

	public static void mouseOverAndClick(WebDriver driver, WebElement webElement) {
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(webElement).click().build().perform();
		} catch (StaleElementReferenceException e) {
			log.info("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			log.info("Element " + webElement + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			log.info("Element " + webElement + " was not mouseOver "
					+ e.getStackTrace());
		}
	}

	public static void selectCheckBox(WebElement element) {
		try {
			if (element.isSelected()) {
				log.info("Checkbox: " + element + "is already selected");
			} else {
				element.click();
			}
		} catch (Exception e) {
			log.info("Unable to select the checkbox: " + element);
		}
	}

	public static void deSelectCheckBox(WebElement element) {
		try {
			if (element.isSelected()) {
				element.click();
			} else {
				log.info("Checkbox: " + element + "is already deselected");
			}
		} catch (Exception e) {
			log.info("Unable to deselect checkbox: " + element);
		}
	}

	public static void selectByIndex(WebElement element, String inputData) {
		try {
			Integer index = new Integer(inputData);
			Select selectBox = new Select(element);
			selectBox.selectByIndex(index);
		} catch (Exception e) {
			log.info("Unable to select selectbox: " + element);
		}
	}

	public static void selectByValue(WebElement element, String inputData) {
		try {
			Select selectBox = new Select(element);
			selectBox.selectByValue(inputData);
		} catch (Exception e) {
			log.info("Unable to select selectbox: " + element);
		}
	}

	public static String selectByVisibletext(WebElement element, String inputData) {
		try {
			Select selectBox = new Select(element);
			selectBox.selectByVisibleText(inputData);
		} catch (Exception e) {
			log.info("Unable to select selectbox: " + element);
		}
		return inputData;
	}

	public static void deSelectByVisibletext(WebElement element, String inputData) {
		try {
			Select selectBox = new Select(element);
			selectBox.deselectByVisibleText(inputData);
		} catch (Exception e) {
			log.info("Unable to select selectbox: " + element);
		}
	}

	public static void deSelectByIndex(WebElement element, String inputData) {
		try {
			Integer index = new Integer(inputData);
			Select selectBox = new Select(element);
			selectBox.deselectByIndex(index);
		} catch (Exception e) {
			log.info("Unable to select selectbox: " + element);
		}
	}

	public static void deSelectByValue(WebElement element, String inputData) {
		try {
			Select selectBox = new Select(element);
			selectBox.deselectByValue(inputData);
		} catch (Exception e) {
			log.info("Unable to select selectbox: " + element);
		}
	}

	public static String Main_Window = "";
	public static void getWindow(WebDriver driver, WebElement webElement)
	{
		Navigate.waitTime(driver, "5");
		Main_Window = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		wait(driver, "3");
		click(webElement);
		wait(driver, "3");

		ArrayList<String> tabs2=new ArrayList<String>(driver.getWindowHandles());
		wait(driver, "3");

		driver.switchTo().window(tabs2.get(1));
		wait(driver, "3");
		System.out.println("Entered tabs");
		wait(driver, "3");

		/*try{
			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);
		}
		catch (Exception e)
		{

		}	*/
	}

	public static void getWindowUsingJsclick(WebDriver driver, String xpath) {
		Navigate.waitTime(driver, "5");
		Main_Window = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		wait(driver, "3");
		jsClickByXPath(driver, xpath);
		wait(driver, "2");
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		ArrayList<String> allWindows=new ArrayList<String>(driver.getWindowHandles());
		allWindows.remove(Main_Window);
		driver.switchTo().window(allWindows.get(0));
		if (Platform.BROWSER_NAME.equalsIgnoreCase("UnKnown"))
		{
			try
			{
				if(driver.findElement(By.xpath("//a[@id='overridelink']")).isDisplayed()){
					driver.get("javascript:document.getElementById('overridelink').click();");

				}
			}
			catch(Exception e)
			{

			}
		}
		else
		{

		}

	}

	public static String Main_Window1 ="";
	public static void getwindowandclose(WebDriver driver, WebElement webElement) {
		Navigate.waitTime(driver, "5");
		Main_Window1 = driver.getWindowHandle();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait(driver, "3");
		click(webElement);
		wait(driver, "2");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ArrayList<String> allWindows = new ArrayList<String>(
				driver.getWindowHandles());
		allWindows.remove(Main_Window1);
		driver.switchTo().window(allWindows.get(0));
		wait(driver, "3");
		try{
			com.sensiple.qa.commands.Manipulation.browserURLSecurityException(driver);
		}
		catch (Exception e)
		{

		}
		if (Platform.BROWSER_NAME.equalsIgnoreCase("UnKnown")) {
			try {
				if (driver.findElement(By.xpath("//a[@id='overridelink']"))
						.isDisplayed()) {
					driver.get("javascript:document.getElementById('overridelink').click();");

				}
			} catch (Exception e) {

			}
		} else {

		}
		driver.switchTo().window(Main_Window1).close();
		driver.switchTo().window(allWindows.get(0));

	}

	public static void getSecondWindow(WebDriver driver, WebElement webElement) {
		String Second_Window = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", webElement);
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		wait(driver, "10");
		ArrayList<String> allWindows=new ArrayList<String>(driver.getWindowHandles());
		System.out.println("No of windows is: "
				+ "" +allWindows.size());
		//Set<String> tab_handles = driver.getWindowHandles();
		allWindows.remove(Main_Window);
		allWindows.remove(Second_Window);
		driver.switchTo().window(allWindows.get(0));
		/*try
		{
			driver.get("javascript:document.getElementById('overridelink').click();");

		}
		catch(Exception e)
		{

		}
		wait(driver, "10");*/
	}

	public static String GetCurrentWindow(WebDriver driver) {
		String CurrentWindow = driver.getWindowHandle();
		return CurrentWindow;
	}

	public static void switchParticularWindow(WebDriver driver,String Window) {
		driver.switchTo().window(Window);
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
	}


	public static void switchWindow(WebDriver driver) {
		driver.switchTo().window(Main_Window);
		try{Thread.sleep(1000);}
		catch(InterruptedException e){e.printStackTrace();}
	}

	public static void switchDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
	}

	public static void getAutoit(WebDriver driver, String inputData) {
		try {
			Runtime.getRuntime().exec(inputData);
		} catch (IOException e1){
			e1.printStackTrace();
		}
	}

	public static void wait(WebDriver driver,String inputData) {
		try {
			int time = Integer.parseInt(inputData);
			int seconds = time*1000;
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static WebElement fromElement;
	public static void dragElement(WebDriver driver, WebElement webElement)	{
		fromElement=webElement;
	}

	public static void dropElement(WebDriver driver, WebElement webElement) {
		Actions action = new Actions(driver);
		Action dragDrop = action.dragAndDrop(fromElement, webElement).build();
		dragDrop.perform();
	}

	public static boolean elementIsSelected(WebDriver driver, WebElement webElement) {
		try {
			webElement.isSelected();
			return true;
		} catch(NoSuchElementException e) {
			log.info("Unable to Select WebElement: " + webElement);
			return false;
		}
	}

	public static boolean verifyElementIsPresent(WebDriver driver, WebElement webElement){
		try {
			webElement.isDisplayed();
			return true;
		} catch(NoSuchElementException e)   {
			log.info("Unable to Displayed WebElement: " + webElement);
			return false;
		}
	}

	public static String verifyElementIsnotdisplayed(WebDriver driver, String webElement){
		String Result="";
		try {
			if(driver.findElement(By.xpath(webElement)).isDisplayed())
			{
				Result="Element is present";
			}
			else
			{
				Result="Element is Not present";
			}

		} catch(Exception e)   {
			Result="Element is Not present";
		}

		return Result;
	}


	public static String verifyElementIsNotPresent(WebDriver driver, WebElement webElement){
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
			wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(webElement)));
		}
		catch(Exception e)
		{
			return "Verified Element is Hidden";
		}
		return "Verified Element is Visible";
	}

	public static boolean elementIsEnable(WebDriver driver, WebElement webElement){
		try {
			webElement.isEnabled();
			return true;
		} catch(NoSuchElementException e)  {
			log.info("Unable to Enabled WebElement: " + webElement);
			return false;
		}
	}

	public static void visibilityElement(WebDriver driver, WebElement webElement){
		WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public static void inVisibilityElement(WebDriver driver, String NormalXpath){
		WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(NormalXpath)));
	}

	public static void testIsPresent(WebDriver driver,WebElement webElement, String inputData){
		driver.getPageSource().contains(inputData);
	}

	public static void textTobePresent(WebDriver driver,WebElement webElement, String inputData){
		WebDriverWait waits = new WebDriverWait(driver, WaitElementSeconds);
		waits.until(ExpectedConditions.textToBePresentInElement(webElement, inputData));
	}

	public static void testIsNotPresent(WebDriver driver, String NormalXpath,String inputData){
		WebDriverWait waits = new WebDriverWait(driver, WaitElementSeconds);
		waits.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(NormalXpath), inputData));
	}

	public static void elementTobeClickable(WebDriver driver,WebElement webElement)	{
		WebDriverWait waits = new WebDriverWait(driver, WaitElementSeconds);
		waits.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	public static void elementToBeSelected(WebDriver driver,WebElement webElement)	{
		WebDriverWait waits = new WebDriverWait(driver, WaitElementSeconds);
		waits.until(ExpectedConditions.elementToBeSelected(webElement));
	}

	public static void waitForElement(WebDriver driver, String NormalXpath)	{
		WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NormalXpath)));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(NormalXpath)));
	}

	public static void waitForElementNotpresent(WebDriver driver,String NormalXpath) {
		WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
		wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.xpath(NormalXpath))));
	}

	public static void textElementPresentValue(WebDriver driver,WebElement webElement, String inputData){
		WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
		wait.until(ExpectedConditions.textToBePresentInElementValue(webElement, inputData));
	}

	public static String linkCounts(WebDriver driver, String NormalXpath){
		int count = driver.findElements(By.xpath(NormalXpath)).size();
		String elementCounts = String.valueOf(count);
		return elementCounts;
	}

	/**

	 * Description :
	 * Ticket ID :
	 * Required Inputs :
	 * Purpose :
	 */
	public static String dynamicSendkeys(WebDriver driver,String inputData, WebElement webElement){
		webElement.clear();
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		String currenttime = new SimpleDateFormat("EHHmmss").format(Calendar.getInstance().getTime());
		String originalValue = inputData;
		String combinedValues = currenttime+originalValue;
		sendKeys(webElement, combinedValues);
		//		System.out.println(combinedValues);
		return combinedValues;
	}

	public static void waitForAjax(WebDriver driver) {
		new WebDriverWait(driver, 180).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) 	{
				JavascriptExecutor js = (JavascriptExecutor) driver;
				return (Boolean) js.executeScript("return jQuery.active == 0");
			}
		});
	}

	public static String condtionMatch(String GetText1, String GetText2){
		String output = "";
		System.out.println("Object is Before :" +GetText1);
		System.out.println("Object to After : "+GetText2);
		try	{
			if((GetText1.trim()).equalsIgnoreCase(GetText2.trim()))	{
				output = "The value "+GetText2+" is Verified";
				return output;
			}
			else
			{
				output = "The value is not matched";
				Assert.fail();
				return output;
			}
		} catch(NoSuchElementException e)
		{
			log.info("Unable to Matched WebElement: " + output);
			output = "The value "+GetText2+" is not Matched";
			return output;
		}
	}
	public static String condtionNotMatch(String GetText1, String GetText2){
		String output = "";
		System.out.println("Object is Before :" +GetText1);
		System.out.println("Object to After : "+GetText2);
		try	{
			//if((GetText1.trim())!=(GetText2.trim()))
			if(!GetText1.trim().equalsIgnoreCase(GetText2.trim()))
			{
				output = "The value is not matched";
				return output;
			}
			else
			{
				output = "The value"+GetText2+" is Verified";
				Assert.fail();
				return output;
			}
		} catch(NoSuchElementException e)
		{
			log.info("Unable to Matched WebElement: " + output);
			output = "The value"+GetText2+" is not Matched";
			return output;
		}
	}

	/**
	 * Name : Abinaya.P
	 * Created Date:12/30/2015
	 * Modified Date:
	 * Description : Image or File Upload using Robot
	 * Ticket ID :
	 * Required Inputs :
	 * Purpose :
	 * @throws AWTException
	 */
	public static void fileUploadRobot(WebDriver driver, String inputData) throws AWTException {

		StringSelection ss = new StringSelection(inputData);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		wait(driver, "2");
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		wait(driver, "2");
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		wait(driver, "2");
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}


	/*
	 * Description : RightClick MailDrop Generated link
	 *
	 */
	public static void rightClickMailDrop(WebDriver driver, WebElement webElement) {
		Main_Window = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		Actions newwin = new Actions(driver);
		newwin.keyDown(Keys.SHIFT).click(webElement).keyUp(Keys.SHIFT).build().perform();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		ArrayList<String> allWindows=new ArrayList<String>(driver.getWindowHandles());
		allWindows.remove(Main_Window);
		driver.switchTo().window(allWindows.get(0));
	}
	/**
	 * Name :Abinaya.P
	 * Created Date:02/Feb/2016
	 * Modified Date:
	 * Description : Split number and character
	 *
	 */
	public static String separateDigitsAndAlphabets(WebDriver driver,String inputData) {
		String number = "";
		String letter = "";
		for (int i = 0; i < inputData.length(); i++) {
			char a = inputData.charAt(i);
			if (Character.isDigit(a)) {
				number = number + a;

			} else {
				letter = letter + a;

			}
		}
		return number;

	}

	public static String sumOfTwoString(String GetText1, String GetText2)
	{
		System.out.println("Object is Before :" +GetText1);
		System.out.println("Object to After : "+GetText2);
		int string1= Integer.parseInt(GetText1);
		int string2= Integer.parseInt(GetText2);
		int sum1= string1+string2;
		String sum= Integer.toString(sum1);
		System.out.println("Sum of two strings"+sum);
		return sum;
	}
	public static String getSelectedFirstElementOfSelectBox(WebElement element) {
		Select dropdown= new Select(element);
		WebElement option = dropdown.getFirstSelectedOption();
		String text=option.getText();
		return text;
	}
	public static String checkPartialText(String GetText1, String GetText2){
		String output = "";
		System.out.println("Object is Before :" +GetText1.trim().toLowerCase());
		System.out.println("Object to After : "+GetText2);
		try	{
			if((GetText1.trim()).toLowerCase().contains(GetText2.trim().toLowerCase())||(GetText2.trim()).toLowerCase().contains(GetText1.trim().toLowerCase()))	{
				output = "The value "+GetText2+" is Verified";
				return output;
			}
			else
			{
				output = "The value is not matched";
				Assert.fail();
				return output;
			}
		} catch(NoSuchElementException e)
		{
			log.info("Unable to Matched WebElement: " + output);
			output = "The value "+GetText2+" is not Matched";
			return output;
		}
	}
	public static void clickUsingSize(WebDriver driver,String NormalXpath) {
		//WebElement element = driver.findElement(By.xpath(NormalXpath));
		int size =driver.findElements(By.xpath(NormalXpath)).size();
		driver.findElements(By.xpath(NormalXpath)).get(size-1).click();
	}

	public static String stringIsEmpty(String refSte2)
	{
		if (refSte2.equalsIgnoreCase(" "))
		{
			System.out.println("The value is Empty");
		}
		else
		{
			System.out.println("The value is not Empty");
			Assert.fail();

		}
		return refSte2;

	}

	public static void browserURLSecurityException(WebDriver driver)

	{
		final String osName = Platform.OS.toUpperCase();
		Manipulation.wait(driver, "2");
		try {
			driver.findElement(By.xpath("//*[text()='Advanced']")).click();
			Manipulation.wait(driver, "2");
			driver.findElement(By.xpath("//*[text()='Add Exception…']")).click();

			if (osName.contains("WINDOWS")) {
				Manipulation.wait(driver, "1");
				Robot robot5 = null;
				try {
					robot5 = new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Manipulation.wait(driver, "1");
				robot5.keyPress(KeyEvent.VK_TAB);
				Manipulation.wait(driver, "1");
				robot5.keyPress(KeyEvent.VK_TAB);
				Manipulation.wait(driver, "1");
				robot5.keyPress(KeyEvent.VK_TAB);
				Manipulation.wait(driver, "1");
				robot5.keyPress(KeyEvent.VK_TAB);
				Manipulation.wait(driver, "1");
				robot5.keyPress(KeyEvent.VK_ENTER);
				Manipulation.wait(driver, "5");
				Manipulation.wait(driver, "8");
			}

			else if (osName.contains("MAC")) {
				Robot ss;
				try {
					ss = new Robot();
					ss.mousePress(InputEvent.BUTTON1_MASK);
					ss.mouseRelease(InputEvent.BUTTON1_MASK);
					Manipulation.wait(driver, "1");
					ss.keyPress(KeyEvent.VK_ENTER);
					ss.keyRelease(KeyEvent.VK_ENTER);
					Manipulation.wait(driver, "1");
					ss.keyPress(KeyEvent.VK_CONTROL);
					System.out.println("Entered");
					ss.keyPress(KeyEvent.VK_C);
					ss.keyRelease(KeyEvent.VK_C);
					ss.keyRelease(KeyEvent.VK_CONTROL);
					Manipulation.wait(driver, "1");
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (Exception ex) {

		}
	}

	public static void getWindow1(WebDriver driver, WebElement webElement)
	{
		Navigate.waitTime(driver, "5");
		Main_Window = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		wait(driver, "3");
		click(webElement);
		wait(driver, "15");

		ArrayList<String> tabs2=new ArrayList<String>(driver.getWindowHandles());
		wait(driver, "3");

		driver.switchTo().window(tabs2.get(2));
		wait(driver, "3");
		System.out.println("Entered tabs");
		wait(driver, "3");

		/*try{
			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);
		}
		catch (Exception e)
		{

		}	*/
	}
	/**

	 * Description : Image or File Upload using Robot
	 * Ticket ID :
	 * Required Inputs :
	 * Purpose :
	 * @throws AWTException
	 */
	public static void coachFileuploadrobot(WebDriver driver, String inputData) throws AWTException {

		StringSelection ss = new StringSelection(inputData);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		wait(driver, "2");
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		wait(driver, "2");

	}
	public static String dynamickeys(WebDriver driver){

		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		String currenttime = new SimpleDateFormat("yyyy"+"-"+"MM"+"-"+"dd").format(Calendar.getInstance().getTime());
		String combinedValues = currenttime;
		System.out.println(combinedValues);
		return combinedValues;
	}

	/**

	 * Description :   Create a common method for get Attribute value
	 */
	public  static String getAttribute(WebDriver driver,WebElement element)
	{
		//WebElement Email_ID= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_MEMBER_PROFILE_EMAIL));
		String Member_Email_ID=element.getAttribute("value");
		return Member_Email_ID;
	}

	public static void flightapp(String refdata) throws IOException, FindFailed, InterruptedException
	{
		Runtime rs = Runtime.getRuntime();
		Process app = null;
		try {
			app=rs.exec(Directory.testCasePath+"\\Flights Application\\FlightsGUI.exe");	
			Screen s=new Screen();   
			SensipleReports.add("Launch Flight booking application", "Launched Flight Booking Application successfully", null, LogAs.PASSED , null);
			//Login to the application and negative validation
			s.click();
			s.type(Directory.uploadFilePath+"FlightUsername.PNG","test");
			s.type(Directory.uploadFilePath+"FlightPassword.PNG","HP");
			s.click(Directory.uploadFilePath+"FlightLogin.PNG");

			s.wait(Directory.uploadFilePath+"LoginFailedMessage.PNG");
			s.find(Directory.uploadFilePath+"LoginFailedMessage.PNG");
			s.click(Directory.uploadFilePath+"LoginFailedOk.PNG");		
			s.doubleClick(Directory.uploadFilePath+"FlightUsername.PNG");
			s.type(Key.BACKSPACE);				
			s.type(Directory.uploadFilePath+"FlightUsername.PNG","john");
			s.type(Directory.uploadFilePath+"FlightPassword.PNG","HP");		
			s.click(Directory.uploadFilePath+"FlightLogin.PNG");
			//Select flights and order
			s.wait(Directory.uploadFilePath+"FlightFindFlights.PNG");						
			s.click(Directory.uploadFilePath+"FlightSearch1.PNG");
			s.click(Directory.uploadFilePath+"FlightSearch1Portland.PNG");
			s.click(Directory.uploadFilePath+"FlightSearch2Paris.PNG");
			s.click(Directory.uploadFilePath+"FlightSearch2Zurich.PNG");		
			s.click(Directory.uploadFilePath+"FlightCalender.PNG");	
			s.click(Directory.uploadFilePath+"nextButton.PNG");
			s.click(Directory.uploadFilePath+"Date.PNG");
			s.click(Directory.uploadFilePath+"SelectClass.PNG");
			s.click(Directory.uploadFilePath+"FirstClass.PNG");
			s.click(Directory.uploadFilePath+"TicketCount.PNG");
			s.click(Directory.uploadFilePath+"TktCount4.PNG");


			s.click(Directory.uploadFilePath+"FlightFindFlights.PNG");
			s.wait(Directory.uploadFilePath+"FlightPrice.PNG");
			s.click(Directory.uploadFilePath+"FlightSelect.PNG");
			s.click(Directory.uploadFilePath+"SelectFlight.PNG");
			s.wait(Directory.uploadFilePath+"PassengerName.PNG");
			s.type(Directory.uploadFilePath+"PassengerName.PNG",refdata);
			s.click(Directory.uploadFilePath+"Order.PNG");
			Thread.sleep(1000);
			s.find(Directory.uploadFilePath+"OrderVerify.PNG");
			Thread.sleep(1000);
			s.click(Directory.uploadFilePath+"NewSearch.PNG");
			s.click(Directory.uploadFilePath+"SearchOrder.PNG");
			s.type(Directory.uploadFilePath+"NameSearch.PNG",refdata);	
			s.click(Directory.uploadFilePath+"SearchButton.PNG");
			s.click(Directory.uploadFilePath+"Delete1st.PNG");

			s.click(Directory.uploadFilePath+"SelectOrder.PNG");

			s.click(Directory.uploadFilePath+"DeleteOrder.PNG");
			s.click(Directory.uploadFilePath+"OrderDeleteYes.PNG");
			s.click(Directory.uploadFilePath+"OrderDeleteConfirm.PNG");
			Thread.sleep(2000);

			/*  s.click(Directory.uploadFilePath+"CRMLogin.PNG");
		    s.wait(Directory.uploadFilePath+"CRM_NewCall.PNG");
		    s.click(Directory.uploadFilePath+"CRM_NewCall.PNG");
		    s.type(Directory.uploadFilePath+"NewCall_Name.PNG","Babu.S");
		    s.type(Directory.uploadFilePath+"NewCall_Address.PNG","Pammal");
		    s.type(Directory.uploadFilePath+"NewCall_City.PNG","Chennai");
		    //s.type(Directory.uploadFilePath+"NewCall_State.PNG","TN");
		    s.type(Directory.uploadFilePath+"NewCall_ZIP.PNG","600005");
		    s.click(Directory.uploadFilePath+"NewCall_CallType.PNG");
		    s.click(Directory.uploadFilePath+"NewCall_SelectProblem.PNG");*/

			/* s.type(Directory.uploadFilePath+"NotepadType.PNG","This is Nice Sikuli Tutorial!!!!");
		    s.click(Directory.uploadFilePath+"NotepadFile.PNG");
		    s.click(Directory.uploadFilePath+"NotepadSave.PNG");
		    s.click(Directory.uploadFilePath+"NotepadCancel.PNG");
		    s.click(Directory.uploadFilePath+"NotepadClose.PNG");
		    s.click(Directory.uploadFilePath+"NotepadDontSave.PNG");*/		    
			System.out.println("Sikuli execution completed successfully");
		}catch(Exception e) {

		}
		finally {
			app.destroy();
		}
	}

	public static void swingapp(String ref1, String ref2, String ref3) throws FindFailed
	{
		Screen s=new Screen();
		s.click(Directory.uploadFilePath+"JavaAppIcon.PNG");
		s.type(Directory.uploadFilePath+"SAccountName.PNG",ref1);
		s.type(Directory.uploadFilePath+"SCompanyName.PNG",ref3);
		s.type(Directory.uploadFilePath+"SCustomerName.PNG",ref2);
		s.type(Directory.uploadFilePath+"SAddress.PNG","Siruseri");
		s.click(Directory.uploadFilePath+"SMale.PNG");
		s.click(Directory.uploadFilePath+"MSelect.PNG");
		s.click(Directory.uploadFilePath+"SDoctor.PNG");
		s.click(Directory.uploadFilePath+"SSubmit.PNG");
		s.click(Directory.uploadFilePath+"SOkbutton.PNG");
		s.click(Directory.uploadFilePath+"SClear.PNG");		
		s.click(Directory.uploadFilePath+"Sminimize.PNG");
	}

	public static void movieapp() throws FindFailed, IOException, InterruptedException {

		Runtime rs = Runtime.getRuntime();
		Process app = null;
		app=rs.exec("C:\\Program Files\\Windows Media Player\\wmplayer.exe");
		try {
			SensipleReports.add("Launch Windows Media Player", "Windows Media player successfully launched", null, LogAs.PASSED , null);
			Screen s=new Screen();   		
			Thread.sleep(2000);
			s.type("o",Key.CTRL);
			Thread.sleep(1000);
			s.click(Directory.uploadFilePath+"Desktop.PNG");
			s.click(Directory.uploadFilePath+"Documents.PNG");
			//s.type(Key.ENTER);
			s.type(Directory.uploadFilePath+"SearchFile.PNG","Video.mp4");
			s.click(Directory.uploadFilePath+"OpenButton.PNG");
			SensipleReports.add("Press CTRL+O to open video", "Opened dialogue box successfully", null, LogAs.PASSED , null);
			Thread.sleep(5000);		
			s.click(Directory.uploadFilePath+"PauseButton.PNG");
			SensipleReports.add("Click on Pause Button", "Pause button clicked successfully", null, LogAs.PASSED , null);
			s.mouseMove(Directory.uploadFilePath+"MouseOverSeekBar.PNG");
			SensipleReports.add("Mouse Over on Seek Bar", "Mouse over successfully done", null, LogAs.PASSED , null);
			s.dragDrop(Directory.uploadFilePath+"SeekBarStart.PNG", Directory.uploadFilePath+"SeekBarEnd.PNG");
			SensipleReports.add("Drag and drop ", "Drag and Drop seekbar done ", null, LogAs.PASSED , null);
			s.click(Directory.uploadFilePath+"PlayButton.PNG");
			SensipleReports.add("Press Play button", "Play button pressed successfully", null, LogAs.PASSED , null);
			s.click(Directory.uploadFilePath+"WMClose.PNG");
			SensipleReports.add("Click on X button to close the app", "X button clicked successfully and application closed", null, LogAs.PASSED , null);			   
			//    JOptionPane.showMessageDialog(null, "Sikuli successful", "InfoBox: " + "Sikuli", JOptionPane.INFORMATION_MESSAGE);

		}
		catch (Exception e) {}
		finally {
			app.destroy();
		}
	}
	public static void notepad() throws FindFailed, IOException
	{
		Runtime rs = Runtime.getRuntime();
		Process app = null;
		app=rs.exec("C:\\Windows\\notepad.exe");
		try {


			SensipleReports.add("Launch Notepad application", "Launched Notepad Application successfully", null, LogAs.PASSED , null);
			Screen s=new Screen();   
			s.click(Directory.uploadFilePath+"NotepadType.PNG");
			for(int i=0;i<10;i++) {
				s.type(Key.ENTER);
			}
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.type("These scenarios were automated using SIKULI.!!!!");
			//s.type(Directory.uploadFilePath+"NotepadType.PNG","These scenarios were automated by SIKULI.!!!!");
			SensipleReports.add("Type text in Notepad", "Typed texts successfully", null, LogAs.PASSED , null);
			s.click(Directory.uploadFilePath+"NotepadFile.PNG");
			SensipleReports.add("Click on File Menu", "Clicked on File Menu successfully", null, LogAs.PASSED , null);
			s.click(Directory.uploadFilePath+"NotepadSave.PNG");
			SensipleReports.add("Click on Save Option", "Clicked on Save option successfully", null, LogAs.PASSED , null);
			s.click(Directory.uploadFilePath+"NotepadCancel.PNG");
			SensipleReports.add("Click on Cancel", "Clicked on Cancel successfully", null, LogAs.PASSED , null);
			s.click(Directory.uploadFilePath+"NotepadClose.PNG");
			SensipleReports.add("Click on Close Button", "Clicked on Close Button successfully", null, LogAs.PASSED , null);
			s.click(Directory.uploadFilePath+"NotepadDontSave.PNG");
			SensipleReports.add("Click on Don't Save Button", "Clicked on Don't Save Button successfully", null, LogAs.PASSED , null);
		}
		catch(Exception e) {

		}
		finally {
			app.destroy();
		}		
	}

	public static void MobileSikuli() throws FindFailed {
		Screen s=new Screen();
		try {
			s.click(Directory.uploadFilePath+"SimulatorButton.PNG");
			s.click(Directory.uploadFilePath+"HomeButton.PNG");
			s.click(Directory.uploadFilePath+"APIDemosicon.PNG");
			s.click(Directory.uploadFilePath+"AppLink.PNG");
			s.click(Directory.uploadFilePath+"AlertDialogue.PNG");
			s.click(Directory.uploadFilePath+"OkCancelDialogue.PNG");
			s.click(Directory.uploadFilePath+"OkCancelDialogueOkButton.PNG");

			s.click(Directory.uploadFilePath+"SingleChoiseList.PNG");
			s.click(Directory.uploadFilePath+"Satellite.PNG");
			s.click(Directory.uploadFilePath+"StreetView.PNG");
			s.click(Directory.uploadFilePath+"OkCancelDialogueOkButton.PNG");

			s.click(Directory.uploadFilePath+"Alarm.PNG");
			s.click(Directory.uploadFilePath+"Wednesday.PNG");
			s.click(Directory.uploadFilePath+"Saturday.PNG");
			s.click(Directory.uploadFilePath+"OkCancelDialogueOkButton.PNG");


		}catch (Exception e) {}
		finally {
			s.click(Directory.uploadFilePath+"RecentItems.PNG");
			s.click(Directory.uploadFilePath+"ClearAllRecent.PNG");
			s.click(Directory.uploadFilePath+"CloseEmulator.PNG");
		}
	}

	public static void sikuliscriptcalc() throws FindFailed, IOException, InterruptedException {

		Screen s=new Screen();   
		s.click(Directory.uploadFilePath+"startwindow.PNG");
		SensipleReports.add("Navigating via Windows - Click on Windows button", "Windows button clicked successfully", null, LogAs.PASSED , null);
		s.click(Directory.uploadFilePath+"Calculator.PNG");
		SensipleReports.add("Click on Calculator icon", "Calculator launched successfully", null, LogAs.PASSED , null);
		s.click(Directory.uploadFilePath+"Two.PNG");
		s.click(Directory.uploadFilePath+"multiply.PNG");
		s.click(Directory.uploadFilePath+"six.PNG");
		s.click(Directory.uploadFilePath+"equal.PNG");
		SensipleReports.add("Multiply two numbers", "Multiplied two numbers successfully", null, LogAs.PASSED , null);
		s.click(Directory.uploadFilePath+"close.PNG");
		SensipleReports.add("Close the Calculator Application", "Calculator closed successfully", null, LogAs.PASSED , null);		
		System.out.println("Sikuli execution completed successfully");
	}

	public static void autoItscripttest() throws FindFailed, IOException, InterruptedException {
		Screen s=new Screen();
		s.click(Directory.uploadFilePath+"startwindow.PNG");
		String command="D:/Calc.exe";

		//	Runtime.getRuntime().exec(command);

		Runtime rs = Runtime.getRuntime();
		Process app;

		app=rs.exec(command);
		Thread.sleep(5000);

		System.out.println("AutoIT execution completed successfully");
		//app.destroy();
	}
	public static void sikuliClick(String imgpath) throws FindFailed, IOException, InterruptedException {
		Screen s=new Screen();
		s.click(Directory.uploadFilePath+imgpath+".PNG");

	}
	public static void jPay() throws FindFailed, IOException, InterruptedException {

		//mobile

		AndroidDriver adriver1;

		File appDir = new File(Directory.MOBILE_APPPATH);
		File app = new File(appDir, Directory.MOBILEAPP_APK_NAME);	  
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"30" );
		capabilities.setCapability("deviceName",Directory.MOBILE_DEVICE_NAME);
		capabilities.setCapability("platformVersion", Directory.MOBILE_DEVICE_VERSION);

		capabilities.setCapability("appActivity", "com.jpay.jpaymobileapp.base.JPayMainActivity");

		capabilities.setCapability("appPackage", Directory.MOBILE_APK_APPPACKAGE); 
		adriver1 = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Thread.sleep(5000);

		// adriver1.pressKeyCode(AndroidKeyCode.BACK);
		adriver1.findElement(By.xpath("//android.widget.Button[contains(@text,'OK')]")).click();		
		System.out.println("1st");
		Thread.sleep(1000);
		// adriver1.findElement(By.xpath("//android.widget.Button[contains(@text,'OK')]")).click();
		System.out.println("2nd click");
		// adriver1.findElement(By.xpath("//android.widget.Button[@resource-id='resource:id/button1']")).click();

		adriver1.findElement(By.xpath("//android.widget.Button[@text='ALLOW']")).click();
		Thread.sleep(000);
		System.out.println("bfor ok");
		adriver1.findElement(By.xpath("//android.widget.Button[contains(@text,'OK')]")).click();
		Thread.sleep(1000);
		(new TouchAction(adriver1)).tap(870, 1143).perform();//login
		//adriver1.findElement(By.xpath("//android.widget.ImageView[@index='0']")).click();//Login button click
		adriver1.findElement(By.xpath("//android.widget.Button[@text='LOG IN']")).click();//Login button click		  
		Thread.sleep(1000);
		MobileElement el1 = (MobileElement) adriver1.findElementById("com.brisk.jpay.qa:id/txt_username");
		el1.sendKeys("qatest12@qa.com");
		Thread.sleep(1000);
		MobileElement el2 = (MobileElement) adriver1.findElementById("com.brisk.jpay.qa:id/txt_password");
		el2.sendKeys("Test@123");
		MobileElement el3 = (MobileElement) adriver1.findElementById("com.brisk.jpay.qa:id/btn_login");
		el3.click();
		System.out.println("Login button clicked");
		Thread.sleep(8000);
		System.out.println("5sec");
		 MobileElement el4 = (MobileElement) adriver1.findElementById("com.android.packageinstaller:id/permission_allow_button");
		  el4.click();
		adriver1.findElement(By.xpath("//android.widget.Button[@text='ALLOW']")).click();  Thread.sleep(1000);
		System.out.println("Allow1");
		adriver1.findElement(By.xpath("//android.widget.Button[@text='ALLOW']")).click();  Thread.sleep(1000);
		adriver1.findElement(By.xpath("//android.widget.Button[@text='ALLOW']")).click();  Thread.sleep(1000);
		adriver1.findElement(By.xpath("//android.widget.Button[@text='ALLOW']")).click();  Thread.sleep(2000);

		System.out.println("Allow4");
		MobileElement el10 = (MobileElement) adriver1.findElementByXPath("//android.widget.TextView[@text='Email']");
		el10.click(); System.out.println("email button"); Thread.sleep(9000);

		MobileElement el11 = (MobileElement) adriver1.findElementById("com.brisk.jpay.qa:id/menu_email");// email top link list
		el11.click();  System.out.println("email link list"); Thread.sleep(3000);

		MobileElement el15 = (MobileElement) adriver1.findElementByXPath("//android.widget.TextView[@index='1']");
		el15.click();

		 MobileElement el13 = (MobileElement) adriver1.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]");
		  el13.click();  System.out.println("sent link ");
		  Thread.sleep(3000);
		  // MobileElement el12 = (MobileElement) adriver1.findElementByXPath("//android.widget.TextView[contains(@text,'How are you? We will meet soon')]");
		  //el12.click();
		  // MobileElement el16 = (MobileElement) adriver1.findElementByXPath("//android.widget.TextView[(@index,'0']");
		  MobileElement el16 = (MobileElement) adriver1.findElementByXPath("//android.widget.TextView[@index='0']");
		  el16.click();
		  Manipulation.verifyElementIsPresent(adriver1, el16);
		  System.out.println("1st msg ");
		  Thread.sleep(3000);
		  adriver1.closeApp();

		  //desktop
		  Runtime rs = Runtime.getRuntime();
			Process app1 = null;
			app1=rs.exec("C:\\Program Files (x86)\\JPay\\InmateKiosk\\JMailInmate.exe");
			Thread.sleep(30000);
			
			try {
				SensipleReports.add("Launch InmateKiosk application", "Launched Notepad Application successfully", null, LogAs.PASSED , null);
				Screen s=new Screen();   
				
				s.type(Directory.uploadFilePath+"JPAY_UserID.PNG","a312348");
				Thread.sleep(1000);
				s.type(Directory.uploadFilePath+"JPAY_Password.PNG", "123456");
				s.click(Directory.uploadFilePath+"JPAY_Login.PNG");
				SensipleReports.add("Click on Login button", "Clicked on Login Button successfully", null, LogAs.PASSED , null);
				Thread.sleep(30000);
				
				s.click(Directory.uploadFilePath+"JPAY_Mail.PNG"); Thread.sleep(15000); System.out.println("click on jpay mail and 5sec");
				s.doubleClick(Directory.uploadFilePath+"JPAY_Mail_List.PNG"); Thread.sleep(10000);System.out.println("2click on jpay mail-list and 5sec");
				s.click(Directory.uploadFilePath+"JPAY_Logoff.PNG"); Thread.sleep(5000);			System.out.println("click on logoff 5sec");
				
			}
			catch(Exception e) {

			}
			finally {
				app1.destroy();
			}	
	}

}

