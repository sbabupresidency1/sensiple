import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sensiple.qa.commands.Navigate;
import com.sensiple.qa.config.ChromeBrowser;
import com.sensiple.qa.config.FirefoxBrowser;
import com.sensiple.qa.config.IEBrowser;
import com.sensiple.qa.config.IOSSetup;
import com.sensiple.qa.config.SafariBrowser;
import com.sensiple.qa.enums.LogAs;
import com.sensiple.qa.listeners.MethodListener;
import com.sensiple.qa.listeners.SensipleReportsListener;
import com.sensiple.qa.util.ExcelUtils;
import com.sensiple.qa.utils.TestParameters;
import com.sensiple.qa.SensipleReports;
import com.sensiple.qa.config.AndroidSetup;
import com.sensiple.qa.datadriver.CaseStep;
import com.sensiple.qa.datadriver.TestCaseRunner;
import com.sensiple.qa.listeners.ConfigurationListener;
import com.sensiple.qa.reports.CaptureScreen;
import com.sensiple.qa.reports.CaptureScreen.ScreenshotOf;
import com.sensiple.qa.utils.Directory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

@Listeners({ ConfigurationListener.class, SensipleReportsListener.class,
	MethodListener.class })
public class TestNGClass {
   private static final AtomicInteger testCaseCount = new AtomicInteger(1);
   public static String tcModuleName;
	Logger log = Logger.getLogger(TestNGClass.class.getName());
	@BeforeTest 
	@DataProvider(name = "data", parallel = true)
	public static Iterator<Object[]> testCaseProvider() {
		List<Object[]> data = new ArrayList<Object[]>();
		ExcelUtils utils = new ExcelUtils();
		Collection<File> testCaseList = utils.readTestCaseFiles(Directory.testCasePath);
		Iterator<File> testCaseItr = testCaseList.iterator();

		while (testCaseItr.hasNext()) {
			String[] browser = Directory.browser.split(",");
			File tcFileName = testCaseItr.next();
			for (int i = 0; i < browser.length; i++) {
				TestParameters params = new TestParameters();
				params.setBrowserName(browser[i]);
				params.setTestCaseFileName(tcFileName);
				params.setTestCaseName(FilenameUtils.getBaseName(tcFileName
						.getName()) + "_" + browser[i]);
				String parentmodule=tcFileName.getParent();
			    String[] parentpath=parentmodule.split("testcases");
			    params.setModuleName(parentpath[1]);
			    tcModuleName=parentpath[1];
				params.setOrSheetFileName(new File(Directory.ORSheetPath));
				data.add(new Object[] { params });
			}
		}
		return data.iterator();
	}

	@Test(dataProvider = "data", enabled = true)
	public void launchapp(TestParameters params) throws Exception {
		WebDriver driver = null;
		AndroidDriver adriver = null;
		IOSDriver idriver = null;
		ExcelUtils utils = new ExcelUtils();
		
		try {
			if (params.getBrowserName().equals("chrome")) {
				driver = new ChromeBrowser().getDriver();
			} else if (params.getBrowserName().equals("firefox")) {
				driver =  new FirefoxBrowser().getDriver();
			} else if (params.getBrowserName().equals("ie")) {
				driver = new IEBrowser().getDriver();
			} else if (params.getBrowserName().equals("safari")) {
				driver = new SafariBrowser().getDriver();
			}else if(params.getBrowserName().equals("android")) {
				adriver = AndroidSetup.getDriver();
			} else if(params.getBrowserName().equalsIgnoreCase("IOS")) {
				idriver = IOSSetup.getDriver();
			} else if(params.getBrowserName().equalsIgnoreCase("Desktop")) {
				
			}
			

			final int iTest = TestNGClass.testCaseCount.getAndIncrement();
			System.out.println("Executing Testcase "+iTest+" :"+params.getTestCaseFileName());

			if (Directory.browser.equalsIgnoreCase("android")){
				log.info("before set driver Thread -----"+Thread.currentThread().getId() +"------------driver------------"+adriver);
				SensipleReports.setWebDriver(adriver);
				log.info("after set driver Thread -----"+Thread.currentThread().getId() +"------------driver------------"+adriver);
			} else if (params.getBrowserName().equalsIgnoreCase("IOS")) {
				log.info("before set driver Thread -----"+Thread.currentThread().getId() +"------------driver------------"+idriver);
				SensipleReports.setWebDriver(idriver);
				log.info("after set driver Thread -----"+Thread.currentThread().getId() +"------------driver------------"+idriver);
			} 
			 else if (params.getBrowserName().equalsIgnoreCase("Desktop")) {
					log.info("Desktop application running");
					//SensipleReports.setWebDriver(idriver);
					//log.info("after set driver Thread -----"+Thread.currentThread().getId() +"------------driver------------"+idriver);
				} 
			else {
				log.info("before set driver Thread -----"+Thread.currentThread().getId() +"------------driver------------"+driver);
				SensipleReports.setWebDriver(driver);
				log.info("after set driver Thread -----"+Thread.currentThread().getId() +"------------driver------------"+driver);
			}

			try {
				List<CaseStep> steps = utils.readTestCase(
						params.getTestCaseFileName(),
						params.getOrSheetFileName());
				TestCaseRunner.exectuteTestCase(adriver,idriver,driver, steps);
				} catch (NoSuchElementException e) {
					if(Directory.browser.equalsIgnoreCase("android")){
						SensipleReports.add("Failed to find Element", LogAs.FAILED,
								new CaptureScreen(ScreenshotOf.BROWSER_PAGE));


						log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+adriver);		
						//				driver.quit();
						throw e; 
					}
					else if(Directory.browser.equalsIgnoreCase("IOS")){
						SensipleReports.add("Failed to find Element", LogAs.FAILED,
								new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

						log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+idriver);		
						//				driver.quit();
						throw e; 
					}
					else {
						SensipleReports.add("Failed to find Element", LogAs.FAILED,
								new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

						log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+driver);		

						throw e;
				}
				}
			
			catch (TimeoutException e) {
				if(Directory.browser.equalsIgnoreCase("android")){
					SensipleReports.add("Timeout Exception", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));


					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+adriver);		
					//				driver.quit();
					throw e; 
				}
				else if(Directory.browser.equalsIgnoreCase("IOS")){
					SensipleReports.add("Timeout Exception", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+idriver);		
					//				driver.quit();
					throw e; 
				}
				else if(Directory.browser.equalsIgnoreCase("Desktop")){
					SensipleReports.add("Timeout Exception", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));

					throw e; 
				}
				else {
					SensipleReports.add("Timeout Exception", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+driver);		

					throw e;
			}
			}
			catch (NullPointerException e){
				if(Directory.browser.equalsIgnoreCase("android")){
					SensipleReports.add("NullPointerException", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+adriver);

					throw e;
				}
				else if(Directory.browser.equalsIgnoreCase("IOS")){
					SensipleReports.add("NullPointerException", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+idriver);
					throw e;
				}
				else if(Directory.browser.equalsIgnoreCase("Desktop")){
					SensipleReports.add("NullPointerException", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));

					throw e; 
				}
				else {
					SensipleReports.add("NullPointerException", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+driver);

					throw e; }
			}	
			catch (Exception e){
				if(Directory.browser.equalsIgnoreCase("android")){
					SensipleReports.add("Exception", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+adriver);
					throw e;
				}
				else if(Directory.browser.equalsIgnoreCase("Desktop")){
					SensipleReports.add("Exception", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));

					throw e; 
				}
				else if(Directory.browser.equalsIgnoreCase("IOS")){
					SensipleReports.add("Exception", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+idriver);
					throw e;
				}
				else {
					SensipleReports.add("Exception", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					log.info("Thread @ first close-----"+Thread.currentThread().getId() +"------------driver------------"+driver);
					throw e;
				}
			}	
			finally
			{

				if(Directory.browser.equalsIgnoreCase("android")){
					try
					{
						adriver.closeApp();
						System.out.println("Android application closed successfully");
					}
					catch(Exception e)
					{
						
					}
					
					log.info("Thread @ close-----"+Thread.currentThread().getId() +"------------driver------------"+adriver);}
				else if(Directory.browser.equalsIgnoreCase("IOS")){
					idriver.quit();
					System.out.println("iOS application closed successfully");
					log.info("Thread @ close-----"+Thread.currentThread().getId() +"------------driver------------"+idriver);}
				else if(Directory.browser.equalsIgnoreCase("Desktop")) {
					log.info("Desktop application closed successfully");
				}
				else {
					Navigate.quit(driver);
					System.out.println("Browser closed successfully");
					log.info("Thread @ close-----"+Thread.currentThread().getId() +"------------driver------------"+driver);
				}
			}
		} catch (InvalidFormatException e)
		{
			e.printStackTrace();
		} catch (IOException e) 
		{			
			e.printStackTrace();
		}
	}
}