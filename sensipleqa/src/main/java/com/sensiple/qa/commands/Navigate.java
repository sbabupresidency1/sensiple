package com.sensiple.qa.commands;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.sensiple.qa.utils.Platform;
import com.sensiple.qa.config.Browser;
import com.sensiple.qa.utils.Directory;
public class Navigate {

	public static void deleteCookies(WebDriver driver) {

		driver.manage().deleteAllCookies();

	}
	public static void get(WebDriver driver, String url)  {
		try
		{
			driver.get(url);
			if (Directory.browser.equalsIgnoreCase("chrome")) 
			{
			driver.manage().window().maximize();		
			}
		}

		catch(Exception e)
		{
			com.sensiple.qa.commands.Manipulation.browserURLSecurityException(driver);
		}
	}
	public static void navigateTo(WebDriver driver, String url)  {
		try
		{
			driver.navigate().to(url);
			
		}

		catch(Exception e)
		{
			com.sensiple.qa.commands.Manipulation.browserURLSecurityException(driver);
		}
	}
	public static void close(WebDriver driver) {
		driver.close();
	}
	public static void quit(WebDriver driver) throws InterruptedException, IOException
	{
		//driver.close();
		driver.quit();

		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS")){
			if(Directory.browser.equalsIgnoreCase("firefox"))
			{   
				Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
				Thread.sleep(2000);
				Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
		//		Runtime.getRuntime().exec("taskkill /F /IM WerFault.exe");
			} 
		}
	}
	public static void waitTime(WebDriver driver, String waitSeconds) {
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(waitSeconds), TimeUnit.SECONDS);
	}
	public static void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	public static void maximize(WebDriver driver) {
		driver.manage().window().maximize();
	}
	public static void goBack(WebDriver driver) {
		driver.navigate().back();
	}
	public static void goForward(WebDriver driver) {
		driver.navigate().forward();
	}
	public static void goTo(WebDriver driver, String url) {
		driver.navigate().to(url);
	}
	public static void keyboardTab(WebDriver driver) throws AWTException  {
		/*Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).perform();*/
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_TAB);
	}
	//	public static void enter(WebDriver driver)  {
	//		Actions actionObject = new Actions(driver);
	//		actionObject.sendKeys(Keys.ENTER).build().perform();
	//	}
	public static void enter(WebDriver driver) throws AWTException  {
		
		final String osName = Platform.OS.toUpperCase();
		if (osName.contains("WINDOWS")) {
			Manipulation.wait(driver, "1");
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}
		else if (osName.contains("MAC")) 
		{
			Robot ss;
				ss = new Robot();
				ss.mousePress(InputEvent.BUTTON1_MASK);
				ss.mouseRelease(InputEvent.BUTTON1_MASK);
				Manipulation.wait(driver, "1");
				ss.keyPress(KeyEvent.VK_ENTER);
				ss.keyRelease(KeyEvent.VK_ENTER);
				Manipulation.wait(driver, "1");
		}
		
	}
	
	public static void enter(WebElement ele, WebDriver driver) {
		  ele.sendKeys(Keys.DOWN);
		  ele.sendKeys(Keys.ENTER);
	}

	public static void robotEnter(WebDriver driver) throws AWTException  {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	//	public static void esc(WebDriver driver)  {
	//		Actions actionObject = new Actions(driver);
	//		actionObject.sendKeys(Keys.ESCAPE).build().perform();
	//	}
	public static void esc(WebDriver driver) throws AWTException  {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	public static String alertOk(WebDriver driver, WebElement element) {
		element.click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		return alertText;
	}

	public static void dismissAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public static String alertDismiss(WebDriver driver, WebElement element) {
		element.click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.dismiss();
		return alertText;
	}

	public static String promptBox(WebDriver driver, WebElement element, String inputData) {
		element.click();
		Alert alert = driver.switchTo().alert();
		driver.switchTo().alert().sendKeys(inputData);
		String alertText = alert.getText();
		alert.accept();
		return alertText;
	}

	public static void switchToFrame(WebDriver driver,String frameName) {
		try {
			driver.switchTo().frame(frameName);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with Name " + frameName
					+ e.getStackTrace());
		}
	}

	public static void switchToFrame(WebDriver driver,int frameIndex) {
		try {
			driver.switchTo().frame(frameIndex);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with id " + frameIndex
					+ e.getStackTrace());
		}
	}

	public static void switchToDefaultFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public static void switchToFrame(WebDriver driver,WebElement element) {
		try {			
			driver.switchTo().frame(element);			
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with Xpath " + element
					+ e.getStackTrace());
		}
	}

	public static void pageDown(WebDriver driver)  {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,400)", "");
	}

	public static void pageUp(WebDriver driver)  {
		//((JavascriptExecutor) driver).executeScript("scroll(0,-250);");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0, -250)", "");
	}

	public static void scrollBottom(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,"
				+ "document.body.scrollHeight,document.documentElement.clientHeight));");
	}

	public static void keyboardPageUp(WebDriver driver) {
		/*Actions actionObject = new Actions(driver);
			actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_UP).perform();
		 */
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS"))
		{
			/*driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
				ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				driver.switchTo().window(tabs.get(0));*/
			Robot rr;
			try {
				rr = new Robot();
				rr.keyPress(KeyEvent.VK_CONTROL);
				rr.keyPress(KeyEvent.VK_PAGE_UP);
				rr.keyRelease(KeyEvent.VK_CONTROL);
				rr.keyRelease(KeyEvent.VK_T);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(OSName.contains("MAC"))
		{
			Robot rr;
			try {
				rr = new Robot();
				rr.mousePress(InputEvent.BUTTON1_MASK);
				rr.mousePress(InputEvent.BUTTON1_MASK);
				rr.keyPress(KeyEvent.VK_META);
				rr.keyPress(KeyEvent.VK_T);
				rr.keyRelease(KeyEvent.VK_META);
				rr.keyRelease(KeyEvent.VK_T);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void keyboardPageDown(WebDriver driver) {
		//Actions actionObject = new Actions(driver);
		//actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).perform();
		Robot keyboarddown;
		try {
			keyboarddown = new Robot();
			keyboarddown.keyPress(KeyEvent.VK_CONTROL);
			keyboarddown.keyPress(KeyEvent.VK_END);
			keyboarddown.keyRelease(KeyEvent.VK_END);
			keyboarddown.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//	public static void keyboardEnd(WebDriver driver) {
	//		Actions actionObject = new Actions(driver);
	//		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
	//	}
	public static void keyboardEnd(WebDriver driver) throws AWTException {

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_END);
		robot.keyRelease(KeyEvent.VK_END);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		//Actions actionObject = new Actions(driver);
		//actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
	}
	public static void keyboardArrowUp(WebDriver driver) {
		//	Actions actionObject = new Actions(driver);
		//	actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_UP).perform();
		Robot keyboardup;
		try {
			keyboardup = new Robot();
			keyboardup.keyPress(KeyEvent.VK_CONTROL);
			keyboardup.keyPress(KeyEvent.VK_UP);
			keyboardup.keyRelease(KeyEvent.VK_CONTROL);
			keyboardup.keyRelease(KeyEvent.VK_UP);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//	public static void keyboardArrowDown(WebDriver driver) {
	//		Actions actionObject = new Actions(driver);
	//		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_DOWN).perform();
	//	}
	public static void keyboardArrowDown(WebDriver driver) throws AWTException {
		/*Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_DOWN).perform();*/
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_DOWN);
	}
	public static void keyboardArrowLeft(WebDriver driver) {
		/*	Actions actionObject = new Actions(driver);
			actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_LEFT).perform();
		 */
		Robot arrow;
		try {
			arrow = new Robot();
			arrow.keyPress(KeyEvent.VK_CONTROL);
			arrow.keyPress(KeyEvent.VK_LEFT);
			arrow.keyRelease(KeyEvent.VK_CONTROL);
			arrow.keyRelease(KeyEvent.VK_LEFT);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void keyboardArrowRight(WebDriver driver) {
		/*	Actions actionObject = new Actions(driver);
			actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_RIGHT).perform();
		 */
		Robot arrowright;
		try {
			arrowright = new Robot();
			arrowright.keyPress(KeyEvent.VK_CONTROL);
			arrowright.keyRelease(KeyEvent.VK_LEFT);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void pageMaximize(WebDriver driver) {
		//driver.findElement(By.tagName("body")).sendKeys(Keys.F11);
		//Actions actionObject = new Actions(driver);
		//actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.F11).perform();
		Robot pagemax;
		try {
			pagemax = new Robot();
			//pagemax.keyPress(KeyEvent.VK_CONTROL);
			pagemax.keyPress(KeyEvent.VK_F11);
			//pagemax.keyRelease(KeyEvent.VK_CONTROL);
			pagemax.keyRelease(KeyEvent.VK_F11);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void deleteAllCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}
	//	public static void navigateUrl(WebDriver driver,String inputData) {
	//		driver.navigate().to(inputData);
	//	}
	public static void navigateUrl(WebDriver driver,String inputData) {
		driver.navigate().to(inputData);
		try {
			driver.findElement(By.xpath("//*[text()='Advanced']")).click();
			Manipulation.wait(driver, "2");
			driver.findElement(By.xpath("//*[text()='Add Exception…']")).click();
			Manipulation.wait(driver, "1");
			Manipulation.wait(driver, "1");
			Robot robot5;

			robot5 = new Robot();
			robot5.keyPress(KeyEvent.VK_TAB);
			Manipulation.wait(driver, "1");
			robot5.keyPress(KeyEvent.VK_TAB);
			Manipulation.wait(driver, "1");
			robot5.keyPress(KeyEvent.VK_TAB);
			Manipulation.wait(driver, "1");
			robot5.keyPress(KeyEvent.VK_TAB);
			Manipulation.wait(driver, "1");
			robot5.keyPress(KeyEvent.VK_ENTER);
			Manipulation.wait(driver, "2");
			robot5.keyRelease(KeyEvent.VK_TAB);
			robot5.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e1)
		{
		}
	}
	public static void screenShot(WebDriver driver,String inputData) {
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {	FileUtils.copyFile(screenshot, new File(inputData));	} catch (IOException e) { e.printStackTrace();
		}
	}
	public static void alertGenerate(WebDriver driver,String inputData) {
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript("alert('"+inputData+"');");
		try {Thread.sleep(3000);} catch (InterruptedException e) { e.printStackTrace();	}
	}

	public static void highLightElement(WebDriver driver, WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",webElement, "color: red; border: 3px solid red;");
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",webElement, "");
	}

	public static ArrayList<String> tabs;
	public static void newTab(WebDriver driver)
	{
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS"))
		{
			Robot rr;
			try {
				rr = new Robot();
				if((Directory.browser).equalsIgnoreCase("firefox"))
				{	
					rr.keyPress(KeyEvent.VK_CONTROL);
					rr.keyPress(KeyEvent.VK_T);
					rr.keyRelease(KeyEvent.VK_CONTROL);
					rr.keyRelease(KeyEvent.VK_T);
					Manipulation.wait(driver, "4");
					ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs.get(1));
				}
				else if((Directory.browser).equalsIgnoreCase("chrome"))
				{	
					rr.keyPress(KeyEvent.VK_CONTROL);
					rr.keyPress(KeyEvent.VK_T);
					rr.keyRelease(KeyEvent.VK_CONTROL);
					rr.keyRelease(KeyEvent.VK_T);
					Manipulation.wait(driver, "4");
					ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs.get(1));

				}
			}
			catch (AWTException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if(OSName.contains("MAC"))
		{
			Robot rr;
			try 
			{
				if((Directory.browser).equalsIgnoreCase("firefox"))
				{
					{ System.out.println("Inside firefox");
						rr = new Robot();
						rr.mousePress(InputEvent.BUTTON1_MASK);
						rr.mouseRelease(InputEvent.BUTTON1_MASK);
						rr.keyPress(KeyEvent.VK_META);
						rr.keyPress(KeyEvent.VK_T);
						rr.keyRelease(KeyEvent.VK_META);
						rr.keyRelease(KeyEvent.VK_T);
						System.out.println("Entered111");
						Manipulation.wait(driver, "4");
						ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
						driver.switchTo().window(tabs.get(1));
					}
				}
				else if((Directory.browser).equalsIgnoreCase("chrome"))
				{	System.out.println("Inside chrome");
				
					rr = new Robot();
					rr.mousePress(InputEvent.BUTTON1_MASK);
					rr.mouseRelease(InputEvent.BUTTON1_MASK);
					Manipulation.wait(driver, "5");
					rr.keyPress(KeyEvent.VK_META);
					rr.keyPress(KeyEvent.VK_T);
					Manipulation.wait(driver, "3");
					rr.keyRelease(KeyEvent.VK_META);
					rr.keyRelease(KeyEvent.VK_T);
					System.out.println("Entered111");
					Manipulation.wait(driver, "5");
					Set<String> tab_handles = driver.getWindowHandles();
					System.out.println("No of windows is " +tab_handles.size());
					
					if (tab_handles.size()==1){
						System.out.println("New tab did not open the first time");
						Manipulation.wait(driver, "3");
						rr.keyPress(KeyEvent.VK_META);
						rr.keyPress(KeyEvent.VK_T);
						Manipulation.wait(driver, "3");
						rr.keyRelease(KeyEvent.VK_META);
						rr.keyRelease(KeyEvent.VK_T);
						Manipulation.wait(driver, "5");
						Set<String> tab_handles1 = driver.getWindowHandles();
						int new_tab_index = tab_handles1.size()-1;
						driver.switchTo().window(tab_handles1.toArray()[new_tab_index].toString()).getWindowHandle();
						Manipulation.wait(driver, "2");
						
					}
					else {

						int new_tab_index = tab_handles.size()-1;
						driver.switchTo().window(tab_handles.toArray()[new_tab_index].toString()).getWindowHandle();
						Manipulation.wait(driver, "2");
					}
				}
					
			}
			catch (AWTException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void closeTab(WebDriver driver)

	{
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS"))
		{
			Robot ee;
			try {
				ee = new Robot();
				ee.keyPress(KeyEvent.VK_CONTROL);								
				ee.keyPress(KeyEvent.VK_W);
				ee.keyRelease(KeyEvent.VK_CONTROL);
				ee.keyRelease(KeyEvent.VK_W);		

				ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
				driver.switchTo().window(tabs1.get(0));
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else if(OSName.contains("MAC"))
		{
			Robot s;
			try
			{
				s=new Robot();
				s.keyPress(KeyEvent.VK_META);
				s.keyPress(KeyEvent.VK_W);
				s.keyRelease(KeyEvent.VK_META);
				s.keyRelease(KeyEvent.VK_W);
				ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
				driver.switchTo().window(tabs1.get(0));
			}
			catch(AWTException e)
			{
				e.printStackTrace();
			}
		}
	}


	public static String sendHttpPost(String inputData) throws Exception
	{
		String[] requestParameters = inputData.split(",");
		String url = requestParameters[0];
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		//add request header
		con.setRequestMethod(requestParameters[1]);
		con.setRequestProperty(requestParameters[2],requestParameters[3]);
		con.setRequestProperty(requestParameters[4], requestParameters[5]);
		String urlParameters = requestParameters[6];
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());
		//String a=response.toString();
		//String testStr = "< > \" &";
		return response.toString();
	}
	public static boolean alertAccept(WebDriver driver)
	{
		boolean presentFlag = false;
		try{
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
		catch (Exception e)
		{
			// Alert not present
			e.printStackTrace();
		}
		return presentFlag;
	}
	/***
	 * pull Screen shot in Report
	 * Description Required : Description
	 * Like : Screenshot
	 * @param driver
	 * @param inputData
	 */
	public static void ScreenShotinReport(WebDriver driver,String inputData) {
		/* File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	      try { FileUtils.copyFile(screenshot, new File(inputData));  } catch (IOException e) { e.printStackTrace();
	      }
		 */
	}
	public static void robotTab(WebDriver driver) throws AWTException  {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	/**
	 * Description : common method to switch multiple tab in same browser
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static void switchtotab(WebDriver driver,int inputData)
	{
		Capabilities localCapabilities = ((RemoteWebDriver)driver).getCapabilities();
		String BROWSER_NAME = localCapabilities.getBrowserName().toLowerCase();
		if(BROWSER_NAME.equalsIgnoreCase("firefox"))
		{
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"\t");
			driver.switchTo().defaultContent();
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(inputData));
		}
		if(BROWSER_NAME.equalsIgnoreCase("chrome"))
		{
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND +"\t");
			driver.switchTo().defaultContent();
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(inputData));
		}
	}
	/**

	 * Description : common method to add number with get text
	 * Ticket ID :
	 * Required Inputs :
	 */
	public static String addNumberWithGetAttribute(WebDriver driver, String app, String input) {
		
		String result1=null;
	
		SimpleDateFormat df = new SimpleDateFormat("mm");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MINUTE, Integer.parseInt(input));
		result1 = df.format(cal.getTime());
		System.out.println("Added minutes are: "+result1);
		
		return result1;
	}
	public static void openFirstTab(WebDriver driver)
	{
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
	}

	public static void switchDefaultTab(WebDriver driver)
	{
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
		driver.switchTo().defaultContent();
	}

	public static void switchbetweentabs(WebDriver driver,String tab)
	{
		int tabs = Integer.parseInt(tab);
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(tabs));
	}
	public static void robotRefreshPage(WebDriver driver) throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F5);
		robot.keyRelease(KeyEvent.VK_F5);
		Manipulation.wait(driver, "5");
	}
	public static void scrollToParticularElement(WebDriver driver, String normalXpath)
	{
		WebElement element = driver.findElement(By.xpath(normalXpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Manipulation.wait(driver, "5");
	}
	public static String getXYOFElement(WebDriver driver, String normalXpath)
	{
		System.out.println("normalXpath"+normalXpath);
		WebElement element = driver.findElement(By.xpath(normalXpath));
		Point point = element.getLocation();
		int xcord = point.getX();
		System.out.println("Element's Position from left side Is "+xcord +" pixels.");
		int ycord = point.getY();
		System.out.println("Element's Position from top side Is "+ycord +" pixels.");
		return xcord+","+ycord;
	}
}