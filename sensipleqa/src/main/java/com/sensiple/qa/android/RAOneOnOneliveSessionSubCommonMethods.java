package com.sensiple.qa.android;

import io.appium.java_client.android.AndroidDriver;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import com.sensiple.qa.commands.Manipulation;
import com.sensiple.qa.commands.Navigate;
import com.sensiple.qa.utils.Directory;

public class RAOneOnOneliveSessionSubCommonMethods extends Manipulation implements OR

{

	/**
	     
	 * Description : Common method to 1on1 Live Session Coach Mic Enable/Disable from spread sheet
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static String coachOneOnOneSessionMicEnableAndDisable(WebDriver driver1,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputCoachMic=testData1[2];


		if ("CoachMicEnable".equalsIgnoreCase(inputCoachMic) ) 
		{ 
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_OFF);
				WebElement coachMicOn = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
				verifyElementIsPresent(driver1, coachMicOn);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_ON);
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_OFF);
				WebElement coachMicOn = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_ON));
				verifyElementIsPresent(driver1, coachMicOn);
			}

		}

		else if ( "CoachMicDisable".equalsIgnoreCase(inputCoachMic) )
		{
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_ON);
				WebElement coachMicOff = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				verifyElementIsPresent(driver1, coachMicOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_OFF);
				jsClickByXPath(driver1, OR.COACH_SESSION_MIC_ON);
				WebElement coachMicOff = driver1.findElement(By.xpath(OR.COACH_SESSION_MIC_OFF));
				verifyElementIsPresent(driver1, coachMicOff);
			}
		}
		return ElementWait;
	}

	/**
	    
	 * Description :   Common method to 1on1 Live Session Coach Video Enable/Disable from spread sheet in Place [8]
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String coachOneOnOneSessionVideoEnableAndDisable(WebDriver driver1,String testData)
	{
		String[] testData1 = testData.split(",");
		String inputCoachVideo=testData1[3];
		if ("CoachVideoEnable".equalsIgnoreCase(inputCoachVideo) ) 
		{ 
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_OFF);
				wait(driver1, "2" );
				WebElement coachVideoOn = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
				verifyElementIsPresent(driver1, coachVideoOn);
			}
			catch (Exception e)
			{

				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_ON);
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_OFF);
				WebElement coachVideoOn = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_ON));
				verifyElementIsPresent(driver1, coachVideoOn);
			}

		}

		else if ( "CoachVideoDisable".equalsIgnoreCase(inputCoachVideo) )
		{
			try
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_ON);
				wait(driver1, "2" );
				WebElement coachVideoOff = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver1, coachVideoOff);
			}
			catch (Exception e)
			{
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_OFF);
				jsClickByXPath(driver1, OR.COACH_SESSION_VIDEO_ON);
				WebElement coachVideoOff = driver1.findElement(By.xpath(OR.COACH_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver1, coachVideoOff);
			}
		}
		return ElementWait;

	}

	/***
	 * 
	 * @param driver
	 * @param testData :Create a common method to retrieve Member who is available Schedule button for RA 1on1 live session
	 * @return
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String retrievedscheduleAvailableMemberEmail="";
	public static String retrievedscheduleAvailableMemberAssignedCoachEmail="";
	public  static String retrieveAvailableScheduleMember(AndroidDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputSessionType=testData1[0];
		String inputSessionStatus=testData1[1];
		String port =Directory.Oracle_Port;
		String database_name= Directory.Oracle_Databasename;
		String user = Directory.Oracle_User;
		String pass = Directory.Oracle_Pass;
		String hostname =Directory.Oracle_Hostname;
		String url ="jdbc:oracle:thin:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT ACNT.EMAIL Member_Email, PROV.EMAIL Coach_Email FROM SUMMARY_ACCOUNT_TODATE SMRY, PROVIDER PROV, ACCOUNT ACNT WHERE PROV.ID=SMRY.ASSIGNED_PROVIDER_ID AND SMRY.ACCOUNT_ID=ACNT.ID AND SMRY.ONBOARDING_STATUS='MEMBER ONBOARDED' AND ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM ACCOUNT_PROGRAM WHERE ACCOUNT_PROGRAM.START_DT_TIME>SYSDATE + INTERVAL '-50' DAY AND ID IN (SELECT ACCOUNT_PROGRAM_ID FROM ACCOUNT_PROGRAM_SESSION_DETAIL WHERE SESSION_STATUS in ('"+inputSessionStatus+"') AND PROGRAM_INTERVAL_START_DT<SYSDATE AND PROGRAM_INTERVAL_END_DT>SYSDATE AND SESSION_TYPE_ID='"+inputSessionType+"')) AND ACNT.EMAIL not like '%api%' AND PROV.EMAIL not like '%api%' ORDER BY SMRY.SESSION_DATE_UTC DESC");
		System.out.println("query executed");
		String retrievedscheduleAvailableMemberEmail="";
		String result="";
		if(rs.next())
		{
			retrievedscheduleAvailableMemberEmail= rs.getString("MEMBER_EMAIL"); 
			retrievedscheduleAvailableMemberAssignedCoachEmail = rs.getString("COACH_EMAIL");
			System.out.println("Available Member Email is "+retrievedscheduleAvailableMemberEmail);
			System.out.println("Available Member Email Assigned coach Email is "+retrievedscheduleAvailableMemberAssignedCoachEmail);
			result=retrievedscheduleAvailableMemberEmail+","+retrievedscheduleAvailableMemberAssignedCoachEmail;
		}
		else
		{
			result=""; 
		}
		System.out.println("result "+result);
		return retrievedscheduleAvailableMemberEmail;  
	}
	/**
	   
	 * Description : Create a common method to Select RA Environments
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static void selectRAAndroidEnvironments(AndroidDriver driver) 
	{
		driver.navigate().back();
		wait(driver, "1");
		WebElement VersionName= driver.findElement(By.xpath(OR.ANDROID_RA_MEMBER_VERSION_NAME));
		verifyElementIsPresent(driver, VersionName);
		click(VersionName);
		wait(driver, "3");
	}

	/**
	     
	 * Description :   Common method to RA 1on1 Enter Weight durint attending 1on1 Session
	 * Ticket ID :     
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String raEnterWeightDuringLiveSession(AndroidDriver driver)
	{
		try
		{
			waitForElement(driver, OR.ANDROID_RA_MEMBER_ENTER_WEIGHT_TEXTBOX);
			WebElement weightTextBox= driver.findElement(By.xpath(OR.ANDROID_RA_MEMBER_ENTER_WEIGHT_TEXTBOX));
			click(weightTextBox);
			sendKeys(weightTextBox, "150");

			driver.hideKeyboard();
			WebElement weightSubmitButton= driver.findElement(By.xpath(OR.ANDROID_RA_MEMBER_SAVE_AND_CONTINUE_BUTTON));
			click(weightSubmitButton);

		}
		catch (Exception e)
		{

		}

		return ElementWait;

	}

	/**
	  
	 * Description : Create a common method to RA Live Session 1on1 Coach Login
	 * Ticket ID :   
	 * Required Inputs :  Email from the DB 
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */

	public  static void raOneOnOneLiveSessionCoachLogin(WebDriver driver1) throws ClassNotFoundException, ParseException, SQLException
	{
		wait(driver1, "3");
		Navigate.get(driver1, Directory.RA_Provider_Url);
		wait(driver1, "5");
		Navigate.maximize(driver1);
		WebElement Coacheslogin_logo= driver1.findElement(By.xpath(OR.COACHES_LOGIN_PAGE_LOGO_REF));
		waitForElement( driver1, OR.COACHES_LOGIN_PAGE_LOGO_REF );
		verifyElementIsPresent(driver1, Coacheslogin_logo);
		WebElement coach_username= driver1.findElement(By.xpath(OR.COACHES_USERNAME_1));
		verifyElementIsPresent(driver1, coach_username);
		WebElement coach_password= driver1.findElement(By.xpath(OR.COACHES_PASSWORD));
		verifyElementIsPresent(driver1, coach_password);
		WebElement Coacheslogin_button= driver1.findElement(By.xpath(OR.COACHES_LOGIN_BUTTON));
		verifyElementIsPresent(driver1, Coacheslogin_button);
		//		sendKeys(coach_username,retrievedscheduleAvailableMemberAssignedCoachEmail);
		sendKeys(coach_username,"Thu20170112154803PMqa@guerrillamail.com");
		sendKeys(coach_password,"Healthfleet2015");
		wait(driver1, "2");
		click(Coacheslogin_button);
		wait(driver1, "10");

		/*try
		{

			wait(driver1, "2");
			sendKeys(coach_password,"Healthfleet2015");
			wait(driver1, "2");
			click(Coacheslogin_button);
			wait(driver1, "2");
			try
			{

				wait(driver1, "2");
				clear( coach_password );
				sendKeys(coach_password,"Zillion2016");
				wait(driver1, "2");
				click(Coacheslogin_button);
				wait(driver1, "2");
				try
				{

					wait(driver1, "2");
					clear( coach_password );
					sendKeys(coach_password,"Healthfleet2016");
					wait(driver1, "2");
					click(Coacheslogin_button);
					wait(driver1, "3");

				}

				catch(Exception e)
				{
					System.out.println("Password are in matched");
				}

			}
			catch(Exception e)
			{
				System.out.println("Password not in matched");
			}
		}
		catch(Exception e)
		{
			System.out.println("Password are in matched");
		}
		Navigate.waitTime(driver1, "20");
		System.out.println("Coaches Logged in successfully");*/
	}

	/**
	     
	 * Description :   Common method to 1on1 Live Session Member Mic Enable/Disable from spread sheet
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	public static String memberOneonOneSessionMicEnableAndDisable(AndroidDriver driver,String testData)
	{

		if ("MemberMicEnable".equalsIgnoreCase(testData) ) 
		{ 
			try
			{
				WebElement memberMicOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_OFF));
				click(memberMicOff);
				wait(driver, "2");
				WebElement memberMicOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
				verifyElementIsPresent(driver, memberMicOn);
			}
			catch (Exception e)
			{
				WebElement memberMicOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
				click(memberMicOn);
				wait(driver, "2");
				WebElement memberMicOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_OFF));
				click(memberMicOff);
				wait(driver, "2");
				verifyElementIsPresent(driver, memberMicOn);
			}

		}

		else if ( "MemberMicDisable".equalsIgnoreCase(testData) )
		{
			try
			{
				WebElement memberMicOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
				click(memberMicOn);
				wait(driver, "2");
				WebElement memberMicOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_OFF));
				verifyElementIsPresent(driver, memberMicOff);
			}
			catch (Exception e)
			{
				WebElement memberMicOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_OFF));
				click(memberMicOff);
				wait(driver, "2");
				WebElement memberMicOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_MIC_ON));
				click(memberMicOn);
				wait(driver, "2");
				verifyElementIsPresent(driver, memberMicOff);
			}


		}
		return ElementWait;
	}
	/**
	      
	 * Description :   Common method to 1on1 Live Session Video Enable/Disable from spread sheet
	 * Ticket ID :     
	 * Required Inputs :  Input from Input data from spreadsheet
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String memberOneonOneSessionVideoEnableAndDisable(AndroidDriver driver,String testData)
	{
		if ("MemberVideoEnable".equalsIgnoreCase(testData) ) 
		{ 
			try
			{
				WebElement memberVideoOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_OFF));
				click(memberVideoOff);
				wait(driver, "2");
				WebElement memberVideoOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_ON));
				verifyElementIsPresent(driver, memberVideoOn);

			}
			catch (Exception e)
			{
				WebElement memberVideoOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_ON));
				click(memberVideoOn);
				wait(driver, "2");
				WebElement memberVideoOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_OFF));
				click(memberVideoOff);
				wait(driver, "2");
				verifyElementIsPresent(driver, memberVideoOn);
			}

		}

		else if ( "MemberVideoDisable".equalsIgnoreCase(testData) )
		{
			try
			{
				WebElement memberVideoOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_ON));
				click(memberVideoOn);
				wait(driver, "2");
				WebElement memberVideoOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_OFF));
				verifyElementIsPresent(driver, memberVideoOff);
			}
			catch (Exception e)
			{
				WebElement memberVideoOff = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_OFF));
				click(memberVideoOff);
				wait(driver, "2");
				WebElement memberVideoOn = driver.findElement(By.xpath(OR.MEMBER_SESSION_VIDEO_ON));
				click(memberVideoOn);
				wait(driver, "2");
				verifyElementIsPresent(driver, memberVideoOff);
			}
		}
		return ElementWait;
	}

	/**
	
	 * Description :   Create a common method to Allow the Plugin and Remember
	 * Ticket ID :
	 * Required Inputs :
	 * @throws FindFailed
	 * @throws AWTException
	 */
	// Method will Allow the plugin if there is Allow and Remember Else it will skip the execution
	public static void allowPlugins(WebDriver driver1) throws FindFailed, AWTException
	{
		Robot rb = new Robot();
		try
		{
			{
				rb.keyPress(KeyEvent.VK_ALT);
				rb.keyPress(KeyEvent.VK_A);
				rb.keyRelease(KeyEvent.VK_ALT);
				rb.keyRelease(KeyEvent.VK_A);
				wait( driver1, "2" );
				rb.keyPress(KeyEvent.VK_ALT);
				rb.keyPress(KeyEvent.VK_R);
				rb.keyRelease(KeyEvent.VK_ALT);
				rb.keyRelease(KeyEvent.VK_R);
				wait( driver1, "5" );
			}
		}
		catch(Exception e)
		{
			{
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_A);
				rb.keyRelease(KeyEvent.VK_CONTROL);
				rb.keyRelease(KeyEvent.VK_A);
				wait( driver1, "4" );
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_R);
				rb.keyRelease(KeyEvent.VK_CONTROL);
				rb.keyRelease(KeyEvent.VK_R);
				wait( driver1, "5" );
			}
		}
	}
     
	/**
	    
	 * Description :   Common method to Check Browser Icon Coach Toolbar compare with Input member Browser
	 * Ticket ID :     
	 * Required Inputs :
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static String browserIconToolbar(WebDriver driver1)
	{

		WebElement firefoxBrowserIcon = driver1.findElement(By.xpath(OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_ANDROID_DEVICE_ICON));
		verifyElementIsPresent(driver1, firefoxBrowserIcon);
		jsClickByXPath(driver1, OR.LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_ANDROID_DEVICE_ICON);
		wait( driver1, "3" );
		return ElementWait;
	}
}
