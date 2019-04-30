package com.sensiple.qa.commands;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sensiple.qa.util.CommandUtils;

public class AndroidActions extends CommandUtils implements OR
{

	public static void wait(WebDriver driver,String inputData) {
		try {
			int time = Integer.parseInt(inputData);
			int seconds = time*1000;
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String sendKeys(WebElement webElement,String keysToSend) {
		webElement.sendKeys(keysToSend);
		return keysToSend;
	}    

	public static void aclick(AndroidDriver driver, String NormalXpath) {
		driver.findElement(By.xpath(NormalXpath)).click();
	}
	public static void atype(AndroidDriver driver, String xpath,String value) {
		driver.findElement(By.xpath( xpath )).sendKeys(value);
	}

	public static void pageScrolldown(AndroidDriver driver) {
		TouchAction action = new TouchAction(driver).longPress(20,0).moveTo(20,10).release();
		action.perform();
	}

	public static void Scrolldown(AndroidDriver driver,String xpath) {

	//	driver.scrollTo(xpath);
	}

	public static void androidClick(AndroidDriver driver,WebElement webElement)
	{		
		webElement.click();
		try
		{
			webElement.click();	
		}
		catch (Exception e)
		{

		}

	}
	public static String androidDynamicSendkeys(WebDriver driver,String inputData, WebElement webElement){    
		webElement.clear();
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		String currenttime = new SimpleDateFormat("EHHmmssa").format(Calendar.getInstance().getTime());
		String originalValue = inputData;
		String combinedValues = currenttime+originalValue;
		sendKeys(webElement, combinedValues);
		return combinedValues;
	}	

	public static String androidscrolluntilelement(AndroidDriver driver,String data, String normalXpath) {
		org.openqa.selenium.Dimension dimensions = driver.manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.5;
		int scrollStart = screenHeightStart.intValue();
		//System.out.println("s="+scrollStart);
		Double screenHeightEnd = dimensions.getHeight() * 0.2;
		int scrollEnd = screenHeightEnd.intValue();
		String cval = data;
		Integer on = Integer.parseInt(cval);
		int count = Integer.parseInt(cval);
		for (int i = 0; i < on; i++) {
		//	driver.swipe(0,scrollStart,0,scrollEnd,2000);
			driver.findElement(By.xpath(normalXpath));  
		}
		return normalXpath;	
	}

	public static void hideKeyboard(AndroidDriver driver) throws MalformedURLException 
	{
		driver.hideKeyboard();
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

	public static void androidClear(AndroidDriver driver, WebElement webElement)  
	{

		TouchAction tAction=new TouchAction(driver);
		int startx = webElement.getLocation().getX();
		int starty = webElement.getLocation().getY();		    
		System.out.println(startx + " ::::::: " + starty);		       
		tAction.press(startx+20,starty+20).perform(); 
		tAction.press(startx+20,starty+20).perform();
		wait(driver, "2");
		driver.pressKeyCode(AndroidKeyCode.BACKSPACE);		
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_DEL);			
		wait(driver, "2");	    
	}

	public static void androidTouchElement(AndroidDriver driver, WebElement webElement) 
	{

		TouchAction tAction=new TouchAction(driver);
		int startx = webElement.getLocation().getX();
		int starty = webElement.getLocation().getY();		    
		System.out.println(startx + " ::::::: " + starty);		       
		tAction.press(startx+20,starty+20).perform(); 
		tAction.press(startx+20,starty+20).perform();
		wait(driver, "2");	   
		driver.pressKeyCode(AndroidKeyCode.ENTER);		
		wait(driver, "2");	   
		driver.pressKeyCode(AndroidKeyCode.FLAG_LONG_PRESS);			
		wait(driver, "2");	    
	}

	public static void androidscrollUp(AndroidDriver driver,WebElement element1,WebElement element2) {

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

	public static void menuButtonTouch(AndroidDriver driver,WebElement element) 
	{

		TouchAction tAction=new TouchAction(driver);

		int startx = element.getLocation().getY();
		int starty = element.getLocation().getX(); 
		System.out.println(startx + " ::::::: " + starty);         
		tAction.tap(startx+20,starty+20).perform(); 
		tAction.tap(startx+20,starty+20).perform();
		wait(driver, "2");
		tAction.tap(startx+20, starty+20).perform();  
		tAction.tap(startx+20, starty+20).perform();  
		wait(driver, "2");   

	}

	public static void swipeRightToLeft(AndroidDriver driver,WebElement element) 
	{

		Point elementLoc = element.getLocation();
		Dimension elementDim = element.getSize();
		int elementLeftX = elementLoc.getX();
		int elementRightX = elementLeftX + elementDim.getWidth();
		int elementTopY = elementLoc.getY();
		int elementBottomY = elementTopY + elementDim.getHeight();

		System.out.println("startx="+elementLeftX);   

		System.out.println("Endx="+elementRightX); 

		System.out.println("startY="+elementTopY);   

		System.out.println("EndY="+elementBottomY); 

		int startx=elementLeftX+150;
		int starty=elementTopY+20;
		int endx=elementRightX-70;
		int endy=elementBottomY-30;
		/*int startx=elementLeftX-150;
		int starty=elementTopY-20;
		int endx=elementRightX+70;
		int endy=elementBottomY+30;*/

		//driver.swipe(startx, starty, endx, endy, 100);
		wait(driver, "3");
		}		
}

