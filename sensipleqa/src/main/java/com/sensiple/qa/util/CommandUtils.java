package com.sensiple.qa.util;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.xml.xpath.XPath;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.sensiple.qa.commands.ElementActions;
import com.sensiple.qa.commands.LocatorBy;
import com.sensiple.qa.commands.Manipulation;
import com.sensiple.qa.commands.Navigate;
import com.sensiple.qa.utils.Directory;

/**
 * Common methods for all kind of actions (Selenium Actions,  specific
 * common methods)
 * @author Babu
 */
public class CommandUtils {

	public WebElement element;
	public String normalXpath;
	Object returnObj = null;
	public WebElement element1;
	public WebElement element2;
	public static String getText = "";
	public static String getSize = "";
	public static String getText1 = "";
	public static HashMap<Integer, String> getTextMap = new HashMap<Integer, String>();
	public static String[] widgetUrlCount = new String[100];
	public static int widgetUrls = 0;
	public static String[] splitInputData;
	/**
	 * Locators
	 *
	 * @param driver
	 * @param locateBy
	 * @param orLocator
	 * @return
	 */
	public WebElement findElement(WebDriver driver, String locateBy,
			String orLocator, String orLocatorStart, String orLocatorEnd,
			String referenceStep) {

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
			orLocator = orLocatorStart + refText + orLocatorEnd;
			element = LocatorBy.locateByXPath(driver, orLocator);
			break;
		case "MergeXpath":
			int refSteps = new Integer(referenceStep);
			String refTexts = getTextMap.get(Integer.valueOf(refSteps));
			orLocator = orLocatorStart + refTexts + orLocatorEnd;
			normalXpath = LocatorBy.linkCount(driver, orLocator);
			break;
		case "ByXpaths":
			element1 = LocatorBy.locateByXPath(driver, orLocatorStart);
			element2 = LocatorBy.locateByXPath(driver, orLocatorEnd);
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
	@SuppressWarnings("unused")
	public Object executeAction(WebDriver driver, WebElement element,
			String action, String inputData, int stepNo, String referenceStep)
					throws Exception {
		// Object returnObj = null;
		if(true){
			switch (action)
			{
			case "GetUrl":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refStep = new Integer(referenceStep);
					String getText = getTextMap.get(Integer.valueOf(refStep));
					Navigate.navigateTo(driver, getText);
				} else {
					Navigate.get(driver, inputData);
				}
				break;
			case "NavigateToURL":
				Navigate.navigateUrl(driver, inputData);
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
				Manipulation.actionClick(driver, element);
				break;
			case "JsClick":
				Manipulation.jsClickByXPath(driver, normalXpath);
				break;
			case "JsTypeByXPath":
				Manipulation.jsTypeByXPath(driver, normalXpath, inputData);
				break;
			case "DoubleClick":
				Manipulation.doubleClick(driver, element);
				break;
			case "ClickAt":
				if (inputData != null) {
					String[] coordinates = StringUtils.split(inputData, ",");
					int x = new Integer(coordinates[0]);
					int y = new Integer(coordinates[1].trim());
					Manipulation.clickAt(driver, element, x, y);
				} else if (referenceStep != null) {
					int getxyref = new Integer(referenceStep);
					String getxyref1 = getTextMap.get(Integer.valueOf(getxyref));
					String[] coordinates = StringUtils.split(getxyref1, ",");
					int x = new Integer(coordinates[0]);
					int y = new Integer(coordinates[1].trim());
					Manipulation.clickAt(driver, element, x, y);
				}
				break;
				/*case "ClickUnavailabilityRedArea":
				if (inputData != null) {
					String[] coordinates = StringUtils.split(inputData, ",");
					int x = new Integer(coordinates[0]);
					int y = new Integer(coordinates[1].trim());
					com.zillion.qa.realappealcoach.coach.clickUnavailabilityRedArea(driver, element, x, y);
				} else if (referenceStep != null) {
					int getxyref = new Integer(referenceStep);
					String getxyref1 = getTextMap.get(Integer.valueOf(getxyref));
					String[] coordinates = StringUtils.split(getxyref1, ",");
					int x = new Integer(coordinates[0]);
					int y = new Integer(coordinates[1].trim());
					com.zillion.qa.realappealcoach.coach.clickUnavailabilityRedArea(driver, element, x, y);
					break;
				}*/

			case "ClickAndHold":
				Manipulation.clickAndHold(driver, element);
				break;
			case "Clear":
				Manipulation.clear(element);
				break;
			case "ActionClear":
				Manipulation.actionClear(driver, element);
				break;
			case "Type":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refStep = new Integer(referenceStep);
					String gettext = getTextMap.get(Integer.valueOf(refStep));
					returnObj = Manipulation.sendKeys(element, gettext);
					getTextMap.put(stepNo, returnObj.toString());
				} else {
					returnObj = Manipulation.sendKeys(element, inputData);
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;
			case "ClearAndType":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refStep = new Integer(referenceStep);
					inputData = getTextMap.get(Integer.valueOf(refStep));
				}
				returnObj = Manipulation.clearAndType(element, inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "ActionType":
				if (referenceStep != null) {
					int refStep = new Integer(referenceStep);
					inputData = getTextMap.get(Integer.valueOf(refStep));
					Manipulation.actionType(driver, element, inputData);
				} else if (inputData != null) {
					Manipulation.actionType(driver, element, inputData);
					getTextMap.put(stepNo, inputData);
				}
				break;
			case "TypeDynamicValue":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refStep = new Integer(referenceStep);
					inputData = getTextMap.get(Integer.valueOf(refStep));
				}
				returnObj = Manipulation
						.dynamicSendkeys(driver, inputData, element);
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
				getText1 = getText.replace("...", "");
				returnObj = getText1;
				getTextMap.put(stepNo, returnObj.toString());				
				break;
			case "GetAttribute":
				returnObj = ElementActions.getAttribute(element, inputData);
				getTextMap.put(stepNo, returnObj.toString());
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
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refStep1 = new Integer(referenceStep);
					String getText1 = getTextMap.get(Integer.valueOf(refStep1));
					returnObj = Manipulation.selectByVisibletext(element, getText1);
				} else {
					returnObj = Manipulation
							.selectByVisibletext(element, inputData);
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
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refStep1 = new Integer(referenceStep);
					String getText1 = getTextMap.get(Integer.valueOf(refStep1));
					Manipulation.deSelectByVisibletext(element, getText1);
				} else {
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
				Navigate.alertGenerate(driver, inputData);
				break;
			case "Close":
				Navigate.close(driver);
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
			case "EnterUsingSendKeys":
				Navigate.enter(element, driver);
				break;
			case "RobotEnter":
				Navigate.robotEnter(driver);
				break;
			case "Esc":
				Navigate.esc(driver);
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
				Manipulation.testIsPresent(driver, element, inputData);
				break;
			case "WaitUntilTextToBeNotPresent":
				Manipulation.testIsNotPresent(driver, normalXpath, inputData);
				break;
			case "WaitUntilTextToBePresent":
				Manipulation.textTobePresent(driver, element, inputData);
				break;
			case "WaitUntilElementToBeClickable":
				Manipulation.elementTobeClickable(driver, element);
				break;
			case "WaitUntilElementToBeSelected":
				Manipulation.elementToBeSelected(driver, element);
				break;
			case "TextToBePresentInElementValue":
				Manipulation.textElementPresentValue(driver, element, inputData);
				break;
			case "WaitForElementPresent":
				Manipulation.waitForElement(driver, normalXpath);
				break;
			case "WaitForElementNotPresent":
				Manipulation.waitForElementNotpresent(driver, normalXpath);
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
				} else if (inputData != null && referenceStep != null) {
					int refStep6 = new Integer(referenceStep);
					String getText1 = getTextMap.get(Integer.valueOf(refStep6));
					System.out.println("getText1" + getText1);
					returnObj = Manipulation.condtionMatch(getText1, inputData);
				}
				break;
			case "CheckStringNotMatched":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					String[] referenceSteps = StringUtils.split(referenceStep, ",");
					int refStep1 = new Integer(referenceSteps[0]);
					int refStep2 = new Integer(referenceSteps[1]);
					String getText1 = getTextMap.get(Integer.valueOf(refStep1));
					String getText2 = getTextMap.get(Integer.valueOf(refStep2));
					returnObj = Manipulation.condtionNotMatch(getText1, getText2);
				} else if (inputData != null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					int refStep2 = new Integer(referenceStep);
					String getText1 = getTextMap.get(Integer.valueOf(refStep2));
					returnObj = Manipulation.condtionNotMatch(inputData, getText1);
				}
				break;
			case "DeleteAllCookies":
				Navigate.deleteAllCookies(driver);
				break;
			case "TakeScreenShot":
				Navigate.screenShot(driver, inputData);
				break;
			case "Highlight":
				Navigate.highLightElement(driver, element);
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
				returnObj = Navigate.sendHttpPost(inputData);
				break;
			case "SplitAndOpenURL":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int refStep = new Integer(referenceStep);
					String getText = getTextMap.get(Integer.valueOf(refStep));
					String[] openURL = getText.split("https://www.google.de/");
					driver.get(openURL[0]);
				}
				break;

			case "ConcatStrings":
				String concat = "";
				if (inputData == null && referenceStep != null
						&& !referenceStep.trim().equals("")) {
					String[] splitReference = referenceStep.split(",");
					int size = splitReference.length;
					for (int i = 0; i < size; i++) {
						String getText12 = getTextMap.get(Integer
								.valueOf(splitReference[i]));
						concat = concat + getText12;
					}
				}
				if (inputData != null && referenceStep == null
						&& !inputData.trim().equals("")) {
					splitInputData = inputData.split(",");
					int size = splitInputData.length;
					for (int i = 0; i < size; i++) {
						concat = concat + splitInputData[i];
					}
				}
				System.out.println(concat);
				returnObj = concat;
				break;
			case "Concat2String":
				String[] splitreference = referenceStep.split(",");
				int refStep12 = new Integer(splitreference[0]);
				int refStep13 = new Integer(splitreference[1]);
				String getText12 = getTextMap.get(Integer.valueOf(refStep12));
				String getText13 = getTextMap.get(Integer.valueOf(refStep13));
				String con = getText12.concat(getText13);
				returnObj = con;
				break;

			case "CurrentWindowName":
				returnObj = Manipulation.GetCurrentWindow(driver);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "GetSecondWindowHandle":
				Manipulation.getSecondWindow(driver, element);
				break;
			case "SwitchParticularWindow":
				int refStep2 = new Integer(referenceStep);
				String win = getTextMap.get(Integer.valueOf(refStep2));
				Manipulation.switchParticularWindow(driver, win);
				break;
			case "FileUploadRobot":
				Manipulation.fileUploadRobot(driver, inputData);
				break;
			case "RightClickMailDrop":
				Manipulation.rightClickMailDrop(driver, element);
				break;

			case "SeparateDigitsAndAlphabets":
				com.sensiple.qa.commands.Manipulation.separateDigitsAndAlphabets(
						driver, inputData);
				break;
			case "TypeDynamicValueReuse":
				returnObj = Manipulation
				.dynamicSendkeys(driver, inputData, element);
				getTextMap.put(stepNo, returnObj.toString());
				break;

			case "TypeReference":
				int refStep = new Integer(referenceStep);
				String gettext = getTextMap.get(Integer.valueOf(refStep));
				returnObj = Manipulation.sendKeys(element, gettext);
				getTextMap.put(stepNo, returnObj.toString());
				break;			
			case "AlertAccept":
				returnObj = Navigate.alertAccept(driver);
				break;

			case "ScreenShotInReport":
				Navigate.ScreenShotinReport(driver, inputData);
				break;

			case "RobotTab":
				Navigate.robotTab(driver);
				break;
			case "GetAttributeWithPrefixConcate":
				returnObj = ElementActions.getAttributeWithPrefixConcate(element,
						inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
			case "CheckTwoStringWithConcatedPrefixForFirstValue":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					String[] referenceSteps14 = StringUtils.split(referenceStep,
							",");
					int refStep11 = new Integer(referenceSteps14[0]);
					int refStep21 = new Integer(referenceSteps14[1]);
					String getText115 = getTextMap.get(Integer.valueOf(refStep11));
					String getText215 = getTextMap.get(Integer.valueOf(refStep21));
					returnObj = Manipulation.condtionMatch("Re:" + getText115,
							getText215);
				}
				break;

			case "GetWindowUsingJsclick":
				Manipulation.getWindowUsingJsclick(driver, normalXpath);
				break;

				/*
			case "AppendTextToRACoachURL":
				com.zillion.qa.realappealcoach.coach.appendTextToRACoachURL(driver);
				break;*/

			case "GetAttributeValue":
				returnObj =Manipulation.getAttribute(driver, element);
				getTextMap.put(stepNo, returnObj.toString());
				break;

			case "SwitchToTab":
				int inputwindow = Integer.parseInt(inputData);
				Navigate.switchtotab(driver, inputwindow);
				break;		
			case "PageUp":
				Navigate.pageUp(driver);
				break;

				/*case "GetSessionID":
				returnObj = com.zillion.qa.realappealmember.RAOneOnOneLiveSessionSubCommonMethods
				.getCurrentURL(driver);
				break;*/

			case "AddNumberWithGetAttribute":
				int addref = new Integer(referenceStep);
				String addget = getTextMap.get(Integer.valueOf(addref));
				System.out.print("addget" + addget);
				System.out.print("inputData" + inputData);
				returnObj = Navigate.addNumberWithGetAttribute(driver, addget,
						inputData);
				getTextMap.put(stepNo, returnObj.toString());
				break;
				/*
			case "SplitUsingColon":
				int text = new Integer(referenceStep);
				String text1 = getTextMap.get(Integer.valueOf(text));
				returnObj = com.zillion.qa.realappealmember.member.splitUsingColon(
						driver, text1);
				getTextMap.put(stepNo, returnObj.toString());
				break;*/

			case "RobotRefreshPage":
				Navigate.robotRefreshPage(driver);
				break;

			case "ScrollToParticularElement":
				Navigate.scrollToParticularElement(driver, normalXpath);
				break;


			case "GetWindowandClose":
				Manipulation.getwindowandclose(driver, element);
				break;
				/*
			case "ValidateTimeZone":
				int reference = new Integer(referenceStep);
				String timezone = getTextMap.get(Integer.valueOf(reference));
				com.zillion.qa.programadmin.Classes.validateTimeZone(driver,
						timezone);
				break;*/
			case "GetSelectedFirstElementOfSelectBox":
				returnObj = Manipulation
				.getSelectedFirstElementOfSelectBox(element);
				getTextMap.put(stepNo, returnObj.toString());
				break;

			case "CheckPartialText":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					String[] referenceStepspartial = StringUtils.split(
							referenceStep, ",");
					int refSteppartial1 = new Integer(referenceStepspartial[0]);
					int refSteppartial2 = new Integer(referenceStepspartial[1]);
					String getText1par1 = getTextMap.get(Integer
							.valueOf(refSteppartial1));
					String getText2par2 = getTextMap.get(Integer
							.valueOf(refSteppartial2));
					returnObj = Manipulation.checkPartialText(getText1par1,
							getText2par2);
				} else if (inputData != null && referenceStep != null) {
					int refStep6 = new Integer(referenceStep);
					String getText1par1 = getTextMap.get(Integer.valueOf(refStep6));
					System.out.println("getText1" + getText1par1);
					returnObj = Manipulation.checkPartialText(getText1par1,
							inputData);
				}
				break;

			case "ClickUsingSize":
				Manipulation.jsClickByXPath(driver, normalXpath);
				break;

			case "CheckValueIsEmpty":
				if (inputData == null && referenceStep != null) {
					int refSte = new Integer(referenceStep);
					String refSte2 = getTextMap.get(Integer.valueOf(refSte));
					returnObj = Manipulation.stringIsEmpty(refSte2);
				}
				break;

			case "ReturnTypeValueReUse":
				if (inputData != null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					int referenceData = new Integer(referenceStep);
					int testData = new Integer(inputData);
					String refData = getTextMap.get(Integer.valueOf(referenceData));
					String[] refData1 = refData.split(",");
					String refData2 = refData1[testData];
					returnObj = refData2;
					getTextMap.put(stepNo, returnObj.toString());
				}
				break;

			case "VerifyElementIsNotDisplayed":
				returnObj = Manipulation.verifyElementIsnotdisplayed(driver, normalXpath);
				break;


			case "TypeEmailID":
				int coachemailref1 = new Integer(referenceStep);
				String coachemailref2 = getTextMap.get(Integer
						.valueOf(coachemailref1));
				String memberCoach[]=coachemailref2.split(",");
				if(inputData.equalsIgnoreCase("CoachID"))
				{
					returnObj = Manipulation.sendKeys(element, memberCoach[0]);
				}
				else if(inputData.equalsIgnoreCase("MemberID"))
				{
					returnObj = Manipulation.sendKeys(element, memberCoach[1]);
				}
				getTextMap.put(stepNo, returnObj.toString());
				break;

			case "VerifyStringisNotEmpty":
				int elementValue = new Integer(referenceStep);
				String elementValue1 = getTextMap.get(Integer
						.valueOf(elementValue));
				if(!elementValue1.isEmpty() && elementValue1!=null){
					System.out.println("Element value is not null or empty");
				}
				else {
					System.out.println("Element value is either null or empty");
					Assert.fail();
				}
				break;
			case "Calc":

				Manipulation.sikuliscriptcalc();
				break;
			case "Notepad":
				Manipulation.notepad();
				break;

			case "AutoItRun":

				Manipulation.autoItscripttest();
				break;		
			case "Flightapp":
				int refStep1 = new Integer(referenceStep);
				String refText = getTextMap.get(Integer.valueOf(refStep1));
				Manipulation.flightapp(refText);

				break;
			case "MobileSikuli":
				Manipulation.MobileSikuli();
				break;
			case "MediaPlayer":
				Manipulation.movieapp();
				break;
			case "SwingApp":
				if (inputData == null && referenceStep != null
				&& !referenceStep.trim().equals("")) {
					String[] referenceSteps = StringUtils.split(referenceStep, ",");
					int refSteps1 = new Integer(referenceSteps[0]);
					int refSteps2 = new Integer(referenceSteps[1]);
					int refSteps3 = new Integer(referenceSteps[2]);
					String getText1 = getTextMap.get(Integer.valueOf(refSteps1));
					String getText2 = getTextMap.get(Integer.valueOf(refSteps2));
					String getText3 = getTextMap.get(Integer.valueOf(refSteps3));
					Manipulation.swingapp(getText1, getText2, getText3);
					//Manipulation.swingapp();
				}
				break;
				//Clicking any image using sikuli with path
			case "SikuliClick":
				Manipulation.sikuliClick(inputData);

			case "MinimizeChrome":
				Robot robot=new Robot();

				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyPress(KeyEvent.VK_N);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_N);
				Thread.sleep(1000);
			case "JPay":
				
				Manipulation.jPay();
			}
		}
		return returnObj;
	}
}