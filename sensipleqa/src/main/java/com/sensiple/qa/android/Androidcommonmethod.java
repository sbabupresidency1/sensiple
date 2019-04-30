/*package com.sensiple.qa.android;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.sensiple.qa.commands.AndroidActions;
import com.sensiple.qa.commands.Manipulation;
import com.sensiple.qa.commands.Navigate;
import com.sensiple.qa.utils.Directory;

public class Androidcommonmethod extends Manipulation  implements OR{

	*//**
	  
	 * Description : Create a common method to Reset password
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 *//*
	public static AndroidDriver androidWebResetPassword(AndroidDriver driver,String testData) throws MalformedURLException, InterruptedException 
	{
		DesiredCapabilities  capabilities = DesiredCapabilities.android();
		capabilities.setCapability("deviceName","Lenovo A536");
		capabilities.setCapability("app", "Chrome");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "BROWSER");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.2");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		String[] testData1 = testData.split(",");
		String inputURL=testData1[0];
		driver.get(inputURL);
		return driver;
	}

	public static void guerrillamail(AndroidDriver driver,String testData) throws MalformedURLException, InterruptedException
	{
		String[] testData1 = testData.split(",");
		String inputEmail=testData1[0];
		WebElement Guerrillamaildropdown= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
		selectByValue(Guerrillamaildropdown, "guerrillamail.com");
		WebElement GuerrillaMailEditButton= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_EDIT_BUTTON));
		click(GuerrillaMailEditButton);
		wait(driver, "2");
		WebElement GuerrillaMailTextbox= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
		clearAndType(GuerrillaMailTextbox, inputEmail);
		jsClickByXPath(driver, OR.GUERRILLA_MAIL_SET_BUTTON);
		wait(driver, "5");

		try{         
			if(driver.findElement( By.xpath( GUERRILLA_MAIL_RESET_YOUR_PASSWORD_LINK ) ).isDisplayed())
			{
				waitForElement(driver, GUERRILLA_MAIL_RESET_YOUR_PASSWORD_LINK);
			}
			else
			{
				driver.navigate().refresh();
				wait( driver, "3" );     
				jsClickByXPath(driver, OR.GUERRILLA_MAIL_EDIT_BUTTON);
				wait(driver, "2");
				clearAndType(GuerrillaMailTextbox, inputEmail);
				jsClickByXPath(driver, OR.GUERRILLA_MAIL_SET_BUTTON);

				wait( driver, "3" );


				if(driver.findElement( By.xpath( GUERRILLA_MAIL_RESET_YOUR_PASSWORD_LINK ) ).isDisplayed())
				{
					waitForElement(driver, GUERRILLA_MAIL_RESET_YOUR_PASSWORD_LINK);
				}
				else
				{
					driver.navigate().refresh();
					wait( driver, "3" );
					selectByValue(Guerrillamaildropdown, "guerrillamail.com");
					jsClickByXPath(driver, OR.GUERRILLA_MAIL_EDIT_BUTTON);
					wait(driver, "2");
					clearAndType(GuerrillaMailTextbox,inputEmail);
					jsClickByXPath(driver, OR.GUERRILLA_MAIL_SET_BUTTON);

					wait( driver, "3" );
					waitForElement(driver, GUERRILLA_MAIL_RESET_YOUR_PASSWORD_LINK);
				}
			}
		}
		catch(Exception e)
		{
			driver.navigate().refresh();
			wait( driver, "3" );
			WebElement Guerrillamaildropdown1= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_DROPDOWN));
			selectByValue(Guerrillamaildropdown1, "guerrillamail.com");
			jsClickByXPath(driver, OR.GUERRILLA_MAIL_EDIT_BUTTON);
			wait(driver, "2");
			WebElement GuerrillaMailTextbox1= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_TEXTBOX));
			clearAndType(GuerrillaMailTextbox1,inputEmail);
			jsClickByXPath(driver, OR.GUERRILLA_MAIL_SET_BUTTON);
			wait( driver, "3" );       
		}
		Navigate.pageDown(driver);
		driver.navigate().refresh();
		wait( driver, "3" );
		WebElement GuerrillaMailCheckBoxToDelete= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_MAIL_CHECKBOX_TO_DELETE));
		verifyElementIsPresent(driver, GuerrillaMailCheckBoxToDelete);
		jsClickByXPath(driver,OR.GUERRILLA_MAIL_MAIL_CHECKBOX_TO_DELETE);
		wait( driver, "3" );
		WebElement GuerrillaMailResetYourPasswordLink= driver.findElement(By.xpath(GUERRILLA_MAIL_RESET_YOUR_PASSWORD_LINK));
		verifyElementIsPresent(driver, GuerrillaMailResetYourPasswordLink);
		jsClickByXPath(driver,GUERRILLA_MAIL_RESET_YOUR_PASSWORD_LINK);
		wait(driver, "5");
		Navigate.pageDown(driver);
		Main_Window = driver.getWindowHandle();
		WebElement generatedLink= driver.findElement(By.xpath(OR.GUERRILLA_MAIL_CLICK_HERE_TO_RESET_YOUR_PASSWORD_LINK));
		verifyElementIsPresent(driver, generatedLink);
		jsClickByXPath(driver, OR.GUERRILLA_MAIL_CLICK_HERE_TO_RESET_YOUR_PASSWORD_LINK);
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		ArrayList<String> allWindows=new ArrayList<String>(driver.getWindowHandles());
		allWindows.remove(Main_Window);
		driver.switchTo().window(allWindows.get(0));	
		wait(driver, "10");

	}

	*//**
	 
	 * Description : Create a common method to Reset password Login
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 *//*
	public static void androidNativeResetPasswordLogin(AndroidDriver driver,String testData,String getText115) throws MalformedURLException 
	{
		File appDir = new File(Directory.MOBILE_APPPATH);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName",Directory.MOBILE_DEVICE_NAME);
		capabilities.setCapability("platformVersion", Directory.MOBILE_DEVICE_VERSION);
		capabilities.setCapability("app", appDir.getAbsolutePath());
		capabilities.setCapability("appPackage", Directory.MOBILE_APK_APPPACKAGE); 
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		wait(driver, "10");
		String[] testData1 = testData.split(",");
		String inputEmail=testData1[0];

		WebElement androidRAMemberLoginEmailTextbox= driver.findElement(By.xpath(ANDROID_RA_MEMBER_LOGIN_EMAIL_TEXTBOX));
		WebElement androidRAMemberLoginPasswordTextbox= driver.findElement(By.xpath(ANDROID_RA_MEMBER_LOGIN_PASSWORD_TEXTBOX));
		WebElement androidRAMemberLoginSigninButton= driver.findElement(By.xpath(ANDROID_RA_MEMBER_LOGIN_SIGNIN_BUTTON));
		verifyElementIsPresent(driver, androidRAMemberLoginEmailTextbox);
		verifyElementIsPresent(driver, androidRAMemberLoginPasswordTextbox);
		verifyElementIsPresent(driver, androidRAMemberLoginSigninButton);
		clearAndType(androidRAMemberLoginEmailTextbox,inputEmail);
		wait(driver, "2");
		clearAndType(androidRAMemberLoginPasswordTextbox,getText115);
		System.out.println("P1"+getText115);
		wait(driver, "2");
		AndroidActions.hideKeyboard(driver);
		click(androidRAMemberLoginSigninButton);
		wait(driver, "30");
		WebElement androidRAMemberMenuButton= driver.findElement(By.xpath(ANDROID_RA_MEMBER_MENU_BUTTON));
		click(androidRAMemberMenuButton);
		wait(driver, "5");
		AndroidActions.pageScrolldown(driver);
		driver.findElementByAccessibilityId("LOG OUT").click();
		wait(driver, "5");

	}

	*//**
	  
	 * Description : Create a common method to Retrieve Member
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 *//*
	public  static String dBRealAppealMember(AndroidDriver driver,String testData) throws ParseException, ClassNotFoundException, SQLException
	{
		String[] testData1 = testData.split(",");
		String inputMemberStatus=testData1[0];
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
		ResultSet rs = stat.executeQuery("Select a.email from SUMMARY_ACCOUNT_TODATE s,ACCOUNT a where s.ONBOARDING_STATUS= '"+inputMemberStatus+"' and s.account_id=a.id and a.email not like 'api%' and s.MAST_PROGRAM_ID in ('04','02','01')");       
		System.out.println("query executed");
		String mailID="";
		String result="";
		if(rs.next())
		{
			mailID = rs.getString("EMAIL");
			System.out.println("DBEnrollmentstatus"+mailID); 

			result=mailID;
		}

		return mailID;  
	}
	*//**
	   
	 * Description : Create a common method to Retrieve Breakfast,Lunch,Dinner,Snacks Calories Value 
	 * Ticket ID :     
	 * Required Inputs :  
	 * @return 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 *//*
	public static String addfoodCaloriesValue(AndroidDriver driver,String breakfast,String lunch,String dinner,String snacks)
	{
		System.out.println("Breakfast calories :"+breakfast);
		System.out.println("Lunch calories :"+lunch);
		System.out.println("Dinner calories :"+dinner);
		System.out.println("Snacks calories :"+snacks);

		String[] Breakfast_value=breakfast.split("\\s+");
		String Breakfast_calories=Breakfast_value[0];
		System.out.println("Breakfast calories1 :"+Breakfast_calories);
		int Breakfast_calories_value=Integer.parseInt(Breakfast_calories);

		String[] Lunch_value=lunch.split("\\s+");
		String Lunch_calories=Lunch_value[0];
		System.out.println("Lunch calories1 :"+Lunch_calories);
		int Lunch_calories_value=Integer.parseInt(Lunch_calories);

		String[] Dinner_value=dinner.split("\\s+");
		String Dinner_calories=Dinner_value[0];
		System.out.println("Dinner calories1 :"+dinner);
		int Dinner_calories_value=Integer.parseInt(Dinner_calories);

		String[] Snacks_value=snacks.split("\\s+");
		String Snacks_calories=Snacks_value[0];
		System.out.println("Snacks calories1 :"+ Snacks_calories);
		int Snacks_calories_value=Integer.parseInt(Snacks_calories);

		int Total_food_calories_value=Breakfast_calories_value+Lunch_calories_value+Dinner_calories_value+Snacks_calories_value;
		System.out.println("Total_food_calories_value:"+Total_food_calories_value);
		String totalFoodCalories= Integer.toString(Total_food_calories_value).trim();

		return totalFoodCalories;
	}


	*//**
	  
	 * Description : Create a common method to Retrieve Total Calories Value from DB match with given Calories
	 * Ticket ID :     
	 * Required Inputs :  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 *//*
	public  static String memberCalorieValue(AndroidDriver driver, String testData,String getText116) throws ParseException, ClassNotFoundException, SQLException
	{
		String sumOfCalorieString="";
		String[] testData1 = testData.split(",");
		String inputMemberEmail=testData1[0];
		String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String currentdate1 = currentdate.toUpperCase() ;
		System.out.println("Current Date :"+currentdate1);
		String port =Directory.Sql_Port ;
		String database_name= Directory.Sql_Databasename;
		String user = Directory.Sql_User;
		String pass = Directory.Sql_Pass;
		String hostname =Directory.Sql_Hostname;
		String url ="jdbc:mysql:@"+hostname+":"+port+":"+database_name+"";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		System.out.println("connection success");
		Statement stat=conn.createStatement();
		ResultSet rs = stat.executeQuery("select  fe.dailyEntryId 'Daily Entry Id', de.dailyEntryDate 'Daily Entry Date', fe.foodEntryNo 'Food Entry Number',mt.mealTypeDesc 'Meal Type',fe.foodId 'Food ID', f.fooddesc 'Food Description',fnv.nutrVal 'Calories per 100g',case when fw.gmWgt is not null then round(fnv.nutrVal  weightConsumed / 100, 0)else round(fnv.nutrVal  weightConsumed, 0) end 'Total Calories', fe.weightConsumed 'Weight Consumed',fe.foodInputString 'Food Input String', fe.weightInputString 'Weight Input String',  case when f.retired = 1 then 'Yes' else 'No' end 'Retired ?',  fw.msreDesc 'Units',fw.amount 'Amount',fw.gmWgt 'Gram Weight',fw.sortOrder 'Food Weight Sort Order' from foodEntry fe inner join dailyEntry de on fe.dailyEntryId = de.dailyEntryId inner join food f on f.foodid = fe.foodid left join foodweight fw on fw.foodid = f.foodid   left join mealtype mt on fe.mealTypeId = mt.mealTypeId  left join foodNutrientValue fnv on fnv.foodId = f.foodId and fnv.nutrNo = 208 where de.userId = (select userid from account where email = 'memtest08@yopmail.com') and DailyEntryDate like '2016-08-04' order by 2 desc, 3");
		while(rs.next())

		{
			String totalCalories[] = null;
			for(int i=0; i<=4;i++)
			{
				totalCalories[i]= rs.getString("Total Calories"); 
			}
			int string1= Integer.parseInt(totalCalories[0]);
			int string2= Integer.parseInt(totalCalories[1]);
			int string3= Integer.parseInt(totalCalories[2]);
			int string4= Integer.parseInt(totalCalories[3]);
			int string5= string1+string2+string3+string4;
			sumOfCalorieString= Integer.toString(string5);
		}
		System.out.println("Sum of calories:"+sumOfCalorieString);

		if(getText116.equalsIgnoreCase( sumOfCalorieString))
		{
			System.out.println("Given Calories is matched with DB Total Calories: "+sumOfCalorieString);
		}

		return sumOfCalorieString;
	}

	
}

*/