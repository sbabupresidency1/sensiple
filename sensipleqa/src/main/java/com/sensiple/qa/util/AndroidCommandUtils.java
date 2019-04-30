package com.sensiple.qa.util;

import io.appium.java_client.MobileCommand;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobilePlatform;

import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import com.sensiple.qa.commands.ElementActions;
import com.sensiple.qa.commands.LocatorBy;
import com.sensiple.qa.commands.Manipulation;
import com.sensiple.qa.commands.Navigate;
import com.sensiple.qa.commands.AndroidActions;

/**
 * Common methods for all kind of actions (Selenium Android Actions)
 * @author Babu
 *
 */
public class AndroidCommandUtils {

	public WebElement element;
	public String normalXpath;
	public static String AndroidUIAutomatorXpath;
	public static String AndroidUIAutomatorByID;
	public static String AndroidUIAutomatorElement;
	Object returnObj = null;
	private WebElement element1;
	private WebElement element2;
	public static String getText = "";
	public static String getSize = "";
	public static String getText1 = "";

	public static HashMap<Integer, String> getTextMap = new HashMap<Integer, String>();
	public static String[] widgetUrlCount=new String[100];
	public static int widgetUrls=0;
	public static String[] splitInputData;
	/**
	 * Locators
	 * @param driver
	 * @param locateBy
	 * @param orLocator
	 * @return
	 */
	public WebElement findElement(AndroidDriver<MobileElement> driver, String locateBy,
			String orLocator, String orLocatorStart, String orLocatorEnd, String referenceStep) {

		switch (locateBy) {
		case "ByID":
			element = LocatorBy.locateById(driver, orLocator);
			break;
		case "ByName":
			element = LocatorBy.locateByName(driver, orLocator);
			break;
		case "ByOrName":
			element = LocatorBy.locateByOrName(driver, orLocator);
			break;
		case "ByXPath":
			element = LocatorBy.locateByXPath(driver, orLocator);
			break;
		case "ByLinkText":
			element = LocatorBy.locateByLinkText(driver, orLocator);
			break;
		case "ByTagName":
			element = LocatorBy.locateByTagName(driver, orLocator);
			break;
		case "ByClassName":
			element = LocatorBy.locateByClassName(driver, orLocator);
			break;
		case "ByCssSelector":
			element = LocatorBy.locateByCssSelector(driver, orLocator);
			break;
		case "ByPartialLinkText":
			element = LocatorBy.locateByPartialLinkText(driver, orLocator);
			break;
		case "Xpath":
			normalXpath = LocatorBy.linkCount(driver, orLocator);
			break;
		case "MergeByXpath":
			int refStep = new Integer(referenceStep);
			String refText = getTextMap.get(Integer.valueOf(refStep));
			orLocator = orLocatorStart+refText+orLocatorEnd;
			element = LocatorBy.locateByXPath(driver, orLocator);
			break;
		case "MergeXpath":
			int refSteps = new Integer(referenceStep);
			String refTexts = getTextMap.get(Integer.valueOf(refSteps));
			orLocator = orLocatorStart+refTexts+orLocatorEnd;
			normalXpath = LocatorBy.linkCount(driver, orLocator);
			break;
		case "ByXpaths":
			element1 = LocatorBy.locateByXPath(driver, orLocatorStart);
			element2 = LocatorBy.locateByXPath(driver, orLocatorEnd);
			break;	
		case "ByResourceID":
			element = LocatorBy.locateByResourceId(driver, orLocator);
			break;
		case "ByAccessibityID":
			element = LocatorBy.locateByAccessibilityId(driver, orLocator);
			break;
		default:
			break;
		}
		return element;
	}

	/**
	 * Common selenium Actions
	 * @param driver
	 * @param element
	 * @param action
	 * @param inputData
	 * @param stepNo
	 * @param referenceStep
	 * @return
	 * @throws Exception
	 */

	public Object executeAction(AndroidDriver<MobileElement> driver, WebElement element,
			String action, String inputData, int stepNo, String referenceStep ) throws Exception {
		//Object returnObj = null;

		switch (action) {

		case "GetUrl":
			Navigate.get(driver, inputData);
			break;
		case "NavigateToURL":
			Navigate.navigateUrl(driver,inputData);
			break;	
		case "Wait":
			Manipulation.wait(driver, inputData);
			break;
		case "WaitTime":
			Navigate.waitTime(driver, inputData);
			break;
		case "Maximize":
			Navigate.maximize(driver);
			break;
		case "Click":
			Manipulation.click(element);
			break;
		case "ActionClick":
			Manipulation.actionClick(driver,element);
			break;
		case "JsClick":
			Manipulation.jsClickByXPath(driver, normalXpath);
			break;
		case "AndroidClick":
			AndroidActions.aclick(driver,normalXpath);
			break;
		case "DoubleClick":
			Manipulation.doubleClick(driver, element);
			break;
		case "ClickAt":
			String[] coordinates = StringUtils.split(inputData, ",");
			int x = new Integer(coordinates[0]);
			int y = new Integer(coordinates[1]);
			Manipulation.clickAt(driver, element, x, y);
			break;
		case "ClickAndHold":
			Manipulation.clickAndHold(driver, element);
			break; 
		case "Clear":
			Manipulation.clear(element);
			break;
		case "Type":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep = new Integer(referenceStep);
				inputData= getTextMap.get(Integer.valueOf(refStep));
			}
			returnObj=Manipulation.sendKeys(element, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "ClearAndType":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep = new Integer(referenceStep);
				inputData= getTextMap.get(Integer.valueOf(refStep));
			}
			Manipulation.clearAndType(element,inputData);
			//			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "ActionType":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep = new Integer(referenceStep);
				inputData= getTextMap.get(Integer.valueOf(refStep));
			}
			Manipulation.actionType(driver,element, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "TypeDynamicValue":
			returnObj = Manipulation.dynamicSendkeys(driver ,inputData, element);
			getTextMap.put(stepNo, returnObj.toString());
			break;	
		case "Submit":
			Manipulation.submit(element);
			break;
		case "MouseOver":
			Manipulation.mouseOver(driver, element);
			break;
		case "MouseOverAndClick":
			Manipulation.mouseOverAndClick(driver, element);
			break;
		case "GetText":
			returnObj = ElementActions.getText(element);
			getText = returnObj.toString();	
			getTextMap.put(stepNo, returnObj.toString());				
			break;
		case "GetAttribute":
			returnObj = ElementActions.getAttribute(element, inputData);
			break;		
		case "GetCount":
			returnObj = Manipulation.linkCounts(driver, normalXpath);			
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "GetCurrentURL":
			returnObj = Manipulation.getCurrentURL(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "SelectCheckBox":
			Manipulation.selectCheckBox(element);
			break;		
		case "SelectByIndex":
			Manipulation.selectByIndex(element, inputData);
			break;
		case "SelectByValue":
			Manipulation.selectByValue(element, inputData);
			break;
		case "SelectByVisibleText":		
			if (inputData == null && referenceStep != null && !referenceStep.trim().equals("")) {
				int refStep1 = new Integer(referenceStep);			
				String getText1 = getTextMap.get(Integer.valueOf(refStep1));				
				returnObj = Manipulation.selectByVisibletext(element,getText1);
				getTextMap.put(stepNo, returnObj.toString());
			} else {				
				returnObj = Manipulation.selectByVisibletext(element,inputData);
				getTextMap.put(stepNo, returnObj.toString());
			}
			break;
		case "DeSelectCheckBox":
			Manipulation.deSelectCheckBox(element);
			break;
		case "DeSelectByIndex":
			Manipulation.deSelectByIndex(element, inputData);
			break;
		case "DeSelectByValue":
			Manipulation.deSelectByValue(element, inputData);
			break;
		case "DeSelectByVisibleText":
			if (inputData == null && referenceStep != null && !referenceStep.trim().equals("")) 
			{
				int refStep1 = new Integer(referenceStep);			
				String getText1 = getTextMap.get(Integer.valueOf(refStep1));				
				Manipulation.deSelectByVisibletext(element, getText1);
			} 
			else {
				Manipulation.deSelectByVisibletext(element, inputData);
			}
			break;			
		case "SwitchFrameByName":
			Navigate.switchToFrame(driver, inputData);
			break;
		case "SwitchFrameByIndex":
			int index = new Integer(inputData);
			Navigate.switchToFrame(driver, index);
			break;
		case "SwitchFrameByXpath":
			Navigate.switchToFrame(driver, element);
			break;
		case "SwitchFrame":
			Navigate.switchToFrame(driver, element);
			break;
		case "SwitchToDefaultFrame":			
			Navigate.switchToDefaultFrame(driver);
			break;			
		case "Refresh":
			Navigate.refreshPage(driver);
			break;
		case "Back":
			Navigate.goBack(driver);
			break;
		case "Forward":
			Navigate.goForward(driver);
			break;
		case "AlertOk":
			returnObj = Navigate.alertOk(driver, element);
			break;	    
		case "DismissAlert":
			Navigate.dismissAlert(driver);
			break;  	    
		case "AlertDismiss":
			returnObj = Navigate.alertDismiss(driver, element);
			break;
		case "PromptBox":
			returnObj = Navigate.promptBox(driver, element, inputData);
			break;		
		case "GenerateAlert":
			Navigate.alertGenerate(driver,inputData);
			break;			
		case "Close":
			Navigate.close(driver);
			break;		
		default:
			break;
		case "GetWindowHandle":
			Manipulation.getWindow(driver, element);
			break;
		case "SwitchToDefaultWindow":
			Manipulation.switchWindow(driver);
			break;	
		case "SwitchToDefaultContent":
			Manipulation.switchDefaultContent(driver);
			break;
		case "GetAutoIt":
			Manipulation.getAutoit(driver, inputData);
			break;
		case "ScrollDown":
			Navigate.pageDown(driver);
			break;
		case "ScrollUp":
			Navigate.pageUp(driver);
			break;
		case "ScrollBottom":
			Navigate.scrollBottom(driver);
			break;      
		case "KeyboardPageUp":
			Navigate.keyboardPageUp(driver);
			break;
		case "KeyboardPageDown":
			Navigate.keyboardPageDown(driver);
			break;
		case "KeyboardEnd":
			Navigate.keyboardEnd(driver);
			break;	
		case "KeyboardTab":
			Navigate.keyboardTab(driver);
			break;		
		case "PageMaximize":
			Navigate.pageMaximize(driver);
			break;	
		case "Enter":
			Navigate.enter(driver);
			break;	
		case "KeyboardArrowUp":
			Navigate.keyboardArrowUp(driver);
			break;	
		case "KeyboardArrowDown":
			Navigate.keyboardArrowDown(driver);
			break;	
		case "KeyboardArrowLeft":
			Navigate.keyboardArrowLeft(driver);
			break;	
		case "KeyboardArrowRight":
			Navigate.keyboardArrowRight(driver);
			break;			
		case "Drag":
			Manipulation.dragElement(driver, element);
			break;
		case "Drop":
			Manipulation.dropElement(driver, element);
			break;		
		case "VerifyElementIsSelected":
			Manipulation.elementIsSelected(driver, element);
			break;
		case "VerifyElementIsPresent":
			Manipulation.verifyElementIsPresent(driver, element);
			break;
		case "VerifyElementIsNotPresent":
			returnObj = Manipulation.verifyElementIsNotPresent(driver, element);
			break;
		case "VerifyElementIsEnable":
			Manipulation.elementIsEnable(driver, element);
			break;		
		case "WaitUntilVisibilityOfElement":
			Manipulation.visibilityElement(driver, element);
			break;	
		case "WaitUntilInvisibilityOfElement":
			Manipulation.inVisibilityElement(driver, normalXpath);
			break;			
		case "VerifyTextIsPresent":
			Manipulation.testIsPresent(driver,element, inputData);
			break;	
		case "WaitUntilTextToBeNotPresent":
			Manipulation.testIsNotPresent(driver,normalXpath, inputData);
			break;		
		case "WaitUntilTextToBePresent":
			Manipulation.textTobePresent(driver,element, inputData);
			break;	
		case "WaitUntilElementToBeClickable":
			Manipulation.elementTobeClickable(driver,element);
			break;	
		case "WaitUntilElementToBeSelected":
			Manipulation.elementToBeSelected(driver,element);
			break;	
		case "TextToBePresentInElementValue":
			Manipulation.textElementPresentValue(driver,element,inputData);
			break;		
		case "WaitForElementPresent":
			Manipulation.waitForElement(driver, normalXpath);
			break;
		case "WaitForElementNotPresent":
			Manipulation.waitForElementNotpresent(driver,normalXpath);
			break;	
		case "CheckTwoString":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				String[] referenceSteps = StringUtils.split(referenceStep, ",");
				int refStep1 = new Integer(referenceSteps[0]);
				int refStep2 = new Integer(referenceSteps[1]);
				String getText1 = getTextMap.get(Integer.valueOf(refStep1));
				String getText2 = getTextMap.get(Integer.valueOf(refStep2));
				returnObj = Manipulation.condtionMatch(getText1, getText2);
			}
			else if(inputData != null && referenceStep != null && !referenceStep.trim().equals(""))
			{
				int refStep2 = new Integer(referenceStep);
				String getText1 = getTextMap.get(Integer.valueOf(refStep2));
				returnObj = Manipulation.condtionMatch(inputData,getText1);		
			}
			break;		
		case "DeleteAllCookies":
			Navigate.deleteAllCookies(driver);
			break;			
		case "TakeScreeShot":
			Navigate.screenShot(driver,inputData);
			break;					
		case "Highlight":
			Navigate.highLightElement(driver,element);
			break;	
		case "NewTab":
			Navigate.newTab(driver);
			break;
		case "CloseTab":
			Navigate.closeTab(driver);
			break;	
		case "WaitForAjaxQuery":
			Manipulation.waitForAjax(driver);
			break;	
		case "SendHttpPost":
			returnObj=Navigate.sendHttpPost(inputData);
			break;
		case "SplitAndOpenURL":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int refStep = new Integer(referenceStep);
				String getText=getTextMap.get(Integer.valueOf(refStep));				
				String[] openURL = getText.split("https://www.google.de/");				
				driver.get(openURL[0]);
			}
			break;			

		case "ConcatStrings":
			String concat="";
			if (inputData == null && referenceStep != null
					&& !referenceStep.trim().equals("")){
				String[] splitReference=referenceStep.split(",");
				int size=splitReference.length;
				for(int i=0;i<size;i++){
					String getText12=getTextMap.get(Integer.valueOf(splitReference[i]));
					concat=concat+getText12;
				}
			}
			if (inputData != null && referenceStep == null
					&& !inputData.trim().equals("")) {
				splitInputData=inputData.split(",");
				int size=splitInputData.length;
				for(int i=0;i<size;i++)
				{
					concat=concat+splitInputData[i];
				}	
			}
			System.out.println(concat);
			returnObj=concat;
			break;
		case "Concat2String":
			String[] splitreference=referenceStep.split(",");
			int refStep12 = new Integer(splitreference[0]);
			int refStep13 = new Integer(splitreference[1]);
			String getText12=getTextMap.get(Integer.valueOf(refStep12));
			String getText13=getTextMap.get(Integer.valueOf(refStep13));
			String con = getText12.concat(getText13);
			returnObj=con;
			break;

		case "AndroidType":
			AndroidActions.atype(driver,normalXpath,inputData);
			break;
		case "AndroidPageScrolldown":
			AndroidActions.pageScrolldown(driver);
			break;
		case "ScrollToParticularElement":
			AndroidActions.Scrolldown(driver,normalXpath);
			break;	
		case "UIAutomatorClick":				
			AndroidActions.androidClick(driver,element);			
			break;
		case "ScrollingToElementofAPage":
			if (inputData != null && referenceStep == null) {
				returnObj= AndroidActions.androidscrolluntilelement(driver,inputData,normalXpath);

			}	
			break;	
		case "KeyboardHide":
			AndroidActions.hideKeyboard(driver);
			break;	

		case "AndroidDynamicSendkeys":
			returnObj=AndroidActions.androidDynamicSendkeys(driver ,inputData, element);
			getTextMap.put(stepNo, returnObj.toString());
			break;		
		case "AndroidClear":
			AndroidActions.androidClear(driver,element);	
			break;	
		case "AndroidTouchElement":
			AndroidActions.androidTouchElement(driver,element);	
			break;	
		case "AndroidPageScrollup":
			AndroidActions.androidscrollUp(driver,element1,element2);
			break;	

		case "TypeDynamicValueReuse":
			returnObj = Manipulation.dynamicSendkeys(driver ,inputData, element);
			getTextMap.put(stepNo, returnObj.toString());
			break; 

		case "MenuButtonTouch":
			AndroidActions.menuButtonTouch(driver,element); 
			break;
		case "SwipeRightToLeft":
			AndroidActions.swipeRightToLeft(driver,element);
		break;
		
		}
		return returnObj;

	}
}
