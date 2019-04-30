/*package com.sensiple.qa.android;


import java.sql.SQLException;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.sensiple.qa.commands.Manipulation;
import com.sensiple.qa.commands.Navigate;
import com.sensiple.qa.config.FirefoxBrowser;
import com.sensiple.qa.config.IEBrowser;
import com.sensiple.qa.config.SafariBrowser;
//import com.sensiple.qa.realappealmember.OR;

public class RAOneOnOneLiveSession  extends Manipulation implements OR
{
	public static  WebDriver driver1;
	public static String inputCoachChatText;

	public static String webCoachAttendOneOnOneliveSession(AndroidDriver driver,WebElement element,String testData) throws Exception
	{
		// Coach Live Session in Browser Launch 

		String[] testData1 = testData.split(",");
		String inputBrowser1=testData1[0];
		inputCoachChatText=testData1[1];

		if ("firefox".equalsIgnoreCase(inputBrowser1) ) 
		{
			driver1 =  new FirefoxBrowser().getDriver();
		}
		else if ( "ie".equalsIgnoreCase(inputBrowser1) )
		{
			driver1 = ( WebDriver ) new IEBrowser().getDriver();
		}
		else if ( "safari".equalsIgnoreCase(inputBrowser1)) 
		{
			driver1 = new SafariBrowser().getDriver();
		}

		com.zillion.qa.android.RAOneOnOneliveSessionSubCommonMethods.raOneOnOneLiveSessionCoachLogin( driver1);

		// Append Coach URL to force attend the RA 1on1 Live session 

		String getCoachCurrentURL= driver1.getCurrentUrl();
		String appendCoachCurrentURL= getCoachCurrentURL+"?attendnow";
		Navigate.get( driver1, appendCoachCurrentURL );

		waitForElement(driver1, OR.COACH_1ON1_ATTEND_NOW_BUTTON );
		WebElement coachAttendNow = driver1.findElement(By.xpath(OR.COACH_1ON1_ATTEND_NOW_BUTTON));
		verifyElementIsPresent(driver1, coachAttendNow);
		jsClickByXPath(driver1, OR.COACH_1ON1_ATTEND_NOW_BUTTON);
		wait( driver, "15" );

		// Common method to Allow system Plugins. Click Allow and Allow&Remember

		com.zillion.qa.android.RAOneOnOneliveSessionSubCommonMethods.allowPlugins(driver1);


		// Attend Live session and verify All the fields

		WebElement settingsButton = driver1.findElement(By.xpath(OR.COACH_SESSION_SETTINGS_BUTTON));
		WebElement endSessionButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_BUTTON));
		WebElement coachMicOn = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
		WebElement coachVideoOn = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
		WebElement coachChatTextbox = driver1.findElement(By.xpath(OR.COACH_SESSION_CHAT_TEXTBOX));
		WebElement coachSendButton = driver1.findElement(By.xpath(OR.COACH_SESSION_CHAT_SEND_BUTTON));
		verifyElementIsPresent(driver1, coachMicOn);
		verifyElementIsPresent(driver1, coachVideoOn);
		verifyElementIsPresent(driver1, settingsButton);
		verifyElementIsPresent(driver1, endSessionButton);
		verifyElementIsPresent(driver1, coachChatTextbox);
		verifyElementIsPresent(driver1, coachSendButton);

		// Common method to verify Coach gear setting option
		com.zillion.qa.member.liveSessionSubCommonMethods.coachGearSettings( driver1 );

		// Coach send message to member and verify the sent message in coach chat area

		actionType(driver1,coachChatTextbox,inputCoachChatText);
		wait(driver1, "2" );
		actionClick( driver1, coachSendButton );
		wait(driver1, "2" );
		Navigate.pageUp( driver1 );
		WebElement coachAttendToLiveSession = driver1.findElement(By.xpath("//div[@class='chat-message ng-scope']//div[2]/div[contains(text(),'"+inputCoachChatText+"')]"));
		verifyElementIsPresent(driver1, coachAttendToLiveSession);

		// Common method for Coach Mic Enable/Disable

		com.zillion.qa.android.RAOneOnOneliveSessionSubCommonMethods.coachOneOnOneSessionMicEnableAndDisable(driver1,testData);

		// Common method for Coach Video Enable/Disable

		com.zillion.qa.android.RAOneOnOneliveSessionSubCommonMethods.coachOneOnOneSessionVideoEnableAndDisable(driver1,testData);
		wait( driver, "2" );
		return testData;
	}

	*//**
	     
	 * Description :  Coach verify Member Mic enable or disable
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 *//*
	public static String coachRAOneonOneSessionVerifyMemberMic(AndroidDriver driver,WebElement element,String testData) throws Exception
	{

		if ("MemberMicDisable".equalsIgnoreCase(testData))  
		{
			WebElement micDisabled = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_DISABLED));
			verifyElementIsPresent(driver1, micDisabled);
		}
		else if ( "MemberMicEnable".equalsIgnoreCase(testData) )
		{
			WebElement micEnabled = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ENABLED));
			verifyElementIsPresent(driver1, micEnabled);
		}
		return testData;

	}

	*//**
	     
	 * Description :  Coach verify Member Video enable or disable
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 *//*
	public static String coachRAOneonOneSessionVerifyMemberVideo(AndroidDriver driver,WebElement element,String testData) throws Exception
	{

		if ("MemberVideoEnable".equalsIgnoreCase(testData) )  
		{
			WebElement cameraEnabled = driver1.findElement(By.xpath(OR.COACH_SESSION_CAMERA_ENABLED));
			verifyElementIsPresent(driver1, cameraEnabled);
		}
		else if ( "MemberVideoDisable".equalsIgnoreCase(testData) )
		{
			WebElement cameraDisabled = driver1.findElement(By.xpath(OR.COACH_SESSION_CAMERA_DISABLED));
			verifyElementIsPresent(driver1, cameraDisabled);
		}
		return testData;

	}
	*//**
	  
	 * Description : Coach End session
	 * Ticket ID :     
	 * Required Inputs:
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 *//*
	public static String Memberchattext;
	public static String coachEndSession(AndroidDriver driver,WebElement element,String testData) throws Exception

	{

		String inputCoachSessionCommentTextbox=testData;

		com.zillion.qa.android.RAOneOnOneliveSessionSubCommonMethods.browserIconToolbar(driver1);

		WebElement coachVerifyMemberChatText = driver1.findElement(By.xpath("//div[@id='chat-message-area']//div[2][@class='chat-message ng-scope']//div[2]/div"));
		Memberchattext=coachVerifyMemberChatText.getText();

		WebElement memberAccountID = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID));
		WebElement popUpOkButton = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_OK_BUTTON));
		WebElement memberIDHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MEMBER_ID_HEADER));
		WebElement sessionIDHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SESSION_ID_HEADER));
		WebElement userAgentHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_USER_AGENT_HEADER));
		WebElement versionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_LIVESESSION_VERSION_HEADER));
		WebElement micOptionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MICROPHONE_OPTIONS_HEADER));
		WebElement micSelectedHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_MICROPHONE_SELECTED_HEADER));
		WebElement speakerOptionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_OPTIONS_HEADER));
		WebElement speakerSelectedHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_SPEAKER_SELECTED_HEADER));
		WebElement cameraOptionHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CAMERA_OPTIONS_HEADER));
		WebElement cameraSelectedHeader = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_CAMERA_SELECTED_HEADER));
		verifyElementIsPresent(driver1, memberAccountID);
		verifyElementIsPresent(driver1, popUpOkButton);
		verifyElementIsPresent(driver1, memberIDHeader);
		verifyElementIsPresent(driver1, sessionIDHeader);
		verifyElementIsPresent(driver1, userAgentHeader);
		verifyElementIsPresent(driver1, versionHeader);
		verifyElementIsPresent(driver1, micOptionHeader);
		verifyElementIsPresent(driver1, micSelectedHeader);
		verifyElementIsPresent(driver1, speakerOptionHeader);
		verifyElementIsPresent(driver1, speakerSelectedHeader);
		verifyElementIsPresent(driver1, cameraOptionHeader);
		verifyElementIsPresent(driver1, cameraSelectedHeader);
		com.zillion.qa.member.liveSessionSubCommonMethods.compareAccountIDWithBrowserToolBarIcon(driver1);
		jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_OK_BUTTON);
		wait( driver1, "3" );

		// Coach Ends session
		// Coach send Session comments after End session
		// Comments given in Input data in spread sheet
		jsClickByXPath(driver1, OR.COACH_SESSION_END_SESSION_BUTTON);
		wait(driver, "2");
		WebElement coachSessionEndText = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_TEXT));
		WebElement coachSessionYesButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_YES_BUTTON));
		WebElement coachSessionNoButton = driver1.findElement(By.xpath(OR.COACH_SESSION_END_SESSION_NO_BUTTON));
		verifyElementIsPresent(driver1, coachSessionEndText);
		verifyElementIsPresent(driver1, coachSessionYesButton);
		verifyElementIsPresent(driver1, coachSessionNoButton);
		jsClickByXPath(driver1, OR.COACH_SESSION_END_SESSION_YES_BUTTON );
		wait(driver, "3" );
		waitForElement(driver1, OR.COACH_SESSION_SESSION_COMPLETE_HEADER_TEXT );
		WebElement coachSessionComplete = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETE_HEADER_TEXT));
		WebElement coach1on1SessionEndedText = driver1.findElement(By.xpath(OR.COACH_SESSION_YOUR_1ON1_SESSION_ENDED_TEXT));
		WebElement coachSessionCommentTextbox = driver1.findElement(By.xpath(OR.COACH_1ON1_COMMENTS_TEXTBOX));
		WebElement coachSessionCompletedRadio = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETED_RADIO_BUTTON));
		WebElement coachMemberDidNotAttendRadio = driver1.findElement(By.xpath(OR.COACH_SESSION_MEMBER_DID_NOT_ATTEND_RADIO_BUTTON));
		WebElement coachSessionCompleteText = driver1.findElement(By.xpath(OR.COACH_SESSION_SESSION_COMPLETED_TEXT));
		WebElement coachMemberDidNotAttendText = driver1.findElement(By.xpath(OR.COACH_SESSION_MEMBER_DID_NOT_ATTEND_TEXT));
		WebElement coachEndSessionDoneButton = driver1.findElement(By.xpath(OR.COACH_SESSION_MEMBER_DONE_BUTTON));
		verifyElementIsPresent(driver1, coachSessionComplete);
		verifyElementIsPresent(driver1, coach1on1SessionEndedText);
		verifyElementIsPresent(driver1, coachSessionCommentTextbox);
		verifyElementIsPresent(driver1, coachSessionCompletedRadio);
		verifyElementIsPresent(driver1, coachMemberDidNotAttendRadio);
		verifyElementIsPresent(driver1, coachSessionCompleteText);
		verifyElementIsPresent(driver1, coachMemberDidNotAttendText);
		verifyElementIsPresent(driver1, coachEndSessionDoneButton);
		jsClickByXPath(driver1, OR.COACH_1ON1_COMMENTS_TEXTBOX);
		sendKeys(coachSessionCommentTextbox,inputCoachSessionCommentTextbox);
		wait( driver, "2" );
		jsClickByXPath(driver1, OR.COACH_SESSION_SESSION_COMPLETED_RADIO_BUTTON);
		jsClickByXPath(driver1, OR.COACH_SESSION_MEMBER_DONE_BUTTON);
		wait( driver, "3" );
		return testData;

	}

	*//**
	     
	 * Description : Member verifies coach message in member chat area
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 *//*
	public static String memberverifyCoachMessage(AndroidDriver driver,String testData) throws Exception
	{
		if(testData.equalsIgnoreCase(inputCoachChatText))
		{
			return "Member verifies coach message in member chat area ";
		}
		else
		{
			return "Member Not verifies coach message in member chat area ";

		}

	}

	*//**
	    
	 * Description : Coach verifies member message in coach chat area 
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 *//*
	public static String coachverifyMemberMessage(AndroidDriver driver,String testData) throws Exception
	{
		System.out.println("String 1"+Memberchattext);
		System.out.println("String 2"+testData);
		if(testData.equalsIgnoreCase(Memberchattext))
		{
			return "Coach verifies member message in coach chat area ";
		}
		else
		{
			return "Coach not verifies member message in coach chat area";

		}

	}
}

*/