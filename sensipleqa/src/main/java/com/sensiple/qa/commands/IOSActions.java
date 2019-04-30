package com.sensiple.qa.commands;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.sensiple.qa.commands.Manipulation;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;;

public class IOSActions extends Manipulation {

	/*public static String iosscrolluntilelement(IOSDriver driver,String data, String normalXpath) {
		org.openqa.selenium.Dimension dimensions = driver.manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.5;
		int scrollStart = screenHeightStart.intValue();
		//System.out.println("s="+scrollStart);
		Double screenHeightEnd = dimensions.getHeight() * 0.2;
		int scrollEnd = screenHeightEnd.intValue();
		String cval = data;
		Integer on = Integer.parseInt(cval);
		//int count = Integer.parseInt(cval);
		for (int i = 0; i < on; i++) {
			driver.sw
			driver.   swipe(0,scrollStart,0,scrollEnd,2000);
			driver.findElement(By.xpath(normalXpath));  
		}
		return normalXpath;	
	}*/

	public static void iosPageScrolldown(IOSDriver driver) {
		TouchAction action = new TouchAction(driver).longPress(20,0).moveTo(20,10).release();
		action.perform();
	}


	public static void iOStouchElement(IOSDriver driver, WebElement webElement)  
	{
		TouchAction tAction=new TouchAction(driver);
		int startx = webElement.getLocation().getX();
		int starty = webElement.getLocation().getY();      
		System.out.println(startx + " ::::::: " + starty);         
		tAction.press(startx+20,starty+20).perform(); 
		tAction.tap(startx+20,starty+20).perform();
		wait(driver, "2");
	}


	public static void clearTextField(IOSDriver driver, WebElement webElement) throws InterruptedException  
	{
		int stringLength = webElement.getText().length();
		System.out.println("string length"+stringLength);

		TouchAction tAction=new TouchAction(driver);
		int startx = webElement.getLocation().getX();
		int starty = webElement.getLocation().getY();		       
		tAction.press(startx+20,starty+20).perform(); 
		tAction.press(startx+20,starty+20).perform(); 
		tAction.press(startx+20,starty+20).perform();
		wait(driver,"3");
	}

	public static void iosScrollUp(IOSDriver driver,WebElement element1,WebElement element2)
	{

		TouchAction tAction7=new TouchAction(driver);

		int startx7 = element1.getLocation().getX();
		int starty7 = element1.getLocation().getY();
		int endx7 = element2.getLocation().getX();
		int endy7 = element2.getLocation().getY();
		System.out.println(startx7 + " ::::::: " + starty7 + " ::::::: " + endx7 +  " ::::::: " + endy7);

		//First tap on the screen and swipe it right using moveTo function
		tAction7.press(startx7+20,starty7+20).moveTo(endx7+20,endy7+20).release().perform(); 
		wait(driver, "2");

		//Second tap on the screen and swipe it left using moveTo function
		tAction7.press(endx7+20,endy7+20).moveTo(startx7+20,starty7+20).release().perform();
		wait(driver, "2");
	}


	public static void mobilePageDown(IOSDriver driver) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);
	}
	
	public static void mobilePageUp(IOSDriver driver) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "up");
		js.executeScript("mobile: scroll", scrollObject);
	}
}




