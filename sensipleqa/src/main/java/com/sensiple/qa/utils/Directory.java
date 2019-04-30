package com.sensiple.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import com.sensiple.qa.writers.HTMLDesignFilesWriter;
import com.sensiple.qa.SensipleReports;
import com.sensiple.qa.enums.ReportLabels;
import com.sensiple.qa.exceptions.SensipleReporterException;
/**
 * Configurations
 * @author Babu 
 *
 */
public class Directory {

	static Logger log = Logger.getLogger(Directory.class.getName());
	public static final String CURRENTDir = System.getProperty("user.dir");
	public static final String SEP = System.getProperty("file.separator");
	public static String REPORTSDIRName = "Sensiple Reports";
	public static String REPORTSDir = CURRENTDir + SEP + REPORTSDIRName;
	public static String RESULTSDir = REPORTSDir + SEP + "Results";
	public static String HTMLDESIGNDIRName = "HTML_Design_Files";
	public static String HTMLDESIGNDir = REPORTSDir + SEP + HTMLDESIGNDIRName;
	public static String CSSDIRName = "CSS";
	public static String CSSDir = HTMLDESIGNDir + SEP + CSSDIRName;
	public static String IMGDIRName = "IMG";
	public static String IMGDir = HTMLDESIGNDir + SEP + IMGDIRName;
	public static String JSDIRName = "JS";
	public static String JSDir = HTMLDESIGNDir + SEP + JSDIRName;
	public static String RUNName = "Test Execution_1"; // Babu
	public static String RUNDir = RESULTSDir + SEP + RUNName;
	public static String SETTINGSFile = RESULTSDir + SEP + "Settings.properties";
	public static final char JS_SETTINGS_DELIM = ';';
	public static final String REPO_DELIM = "##@##@##";
	public static final char JS_FILE_DELIM = ',';
	public static final String MENU_LINK_NAME = "Run ";
	public static final String SCREENSHOT_TYPE = "PNG";
	public static final String SCREENSHOT_EXTENSION = ".PNG";
	private static String logo = null;
	public static String SCREENSHOT_DIRName = "img";
	public static boolean generateConfigReports = true;
	public static boolean generateExcelReports = false;
	public static boolean takeScreenshot = false;
	public static boolean continueExecutionAfterStepFailed = false;
	public static boolean recordExecutionAvailable = false;
	public static boolean recordSuiteExecution = false;
	public static boolean recordTestMethodExecution = false;
	public static boolean reexecution=true;
	public static final String MAIN_RECORD_FILE_NAME = "ATU_CompleteSuiteRecording";
	public static String userName=null;
	public static String password=null;
	public static String smtpHost=null;
	public static String fromAddress=null;
	public static String toAddress=null;
	public static String ccAddress=null;
	//public static String bccAddress=null;
	public static String testCasePath=null;
	public static String ORSheetPath=null;
	public static String browser=null;
	public static String Subject=null;
	public static String Reports_Path=null;
	public static String Zipfolder_Path=null;
	public static String WIDGET_HTML_FILE=null;
	public static String WaitFor=null;
	public static String yopmailurl=null;
	public static String Providerurl=null;
	public static String Coachesurl=null;
	public static String OpsAdminurl=null;
	public static String Memberurl=null;
	public static String Practitionerusername=null;
	public static String Practitionerpassword=null;
	public static String Programadminusername=null;
	public static String Programadminpassword=null;
	public static String Coachesusername1=null;
	public static String Coachespassword1=null;
	public static String Coachesusername2=null;
	public static String Coachespassword2=null;
	public static String Coachesusername3=null;
	public static String Coachespassword3=null;
	public static String OpsAdminusername=null;
	public static String OpsAdminpassword=null;
	public static String Memberusername1=null;
	public static String Memberpassword1=null;
	public static String Memberusername2=null;
	public static String Memberpassword2=null;
	public static String uploadFilePath=null;
	public static String MailDropurl=null;
	public static String Member_Tracker_Journal_181826_178095_mailid=null;
	public static String Coaches_Member_Profile_186455_mailid=null;
	public static String Lecturememberusername=null;
	public static String Lecturememberpassword=null;
	public static String Verification_186452_mailid=null;
	public static String Zado_Program_Admin_General_Tab_192272_mailid=null;
	public static String PAwellnesscorpusername=null;
	public static String PAwellnesscorppassword=null;
	public static String Guerrillamailurl=null;
	public static String Sessionmemberusername=null;
	public static String Sessionmemberpassword=null;
	public static String AppendURL=null;
	public static String Accountidofthemember=null;
	public static String LiveSessionCoachUsername=null;
	public static String LiveSessionCoachPassword=null;
	public static String liveSessionMemberUsername=null;
	public static String liveSessionMemberPassword=null;
	public static String CoachAppendURL=null;
	public static String liveSessionCoachbrowser=null;
	public static String retrieveNameOfTheMember_mailid=null;
	public static String Oracle_Port=null;
	public static String Oracle_Databasename=null;
	public static String Oracle_User=null;
	public static String Oracle_Pass =null;
	public static String Oracle_Hostname=null;
	public static String Sql_Hostname=null;
	public static String Sql_Databasename=null;
	public static String Sql_User=null;
	public static String Sql_Pass=null;
	public static String Sql_Port=null;	
	public static String PAapolloEndousername=null;
	public static String PAapolloEndopassword=null;
	public static String RA_Member_Url=null;
	public static String RA_Provider_Url=null;
	public static String RA_Member_Username1=null;
	public static String RA_Member_Password1=null;
	public static String RA_Coach_Username1=null;
	public static String RA_Coach_Pasword1=null;
	public static String RA_PA_Username1=null;
	public static String RA_PA_Pasword1=null;
	public static String MOBILE_APPPATH=null;
	public static String MOBILEAPP_APK_NAME=null;
	public static String MOBILE_DEVICE_NAME=null;
	public static String MOBILE_DEVICE_VERSION=null;
	public static String MOBILE_APK_APPPACKAGE=null;
	public static String MOBILE_DEVICE_TYPE=null;
	public static String MOBILE_IOSDEVICE_UDID= null;
	public static String MOBILE_APP_TYPE=null;
	public static String MOBILE_WEB_BROWSER_NAME=null;
	public static String MOBILE_WEB_URL=null;
	public static String RAMemberAppendURL=null;
	public static String RACoachAppendURL=null;
	public static String AllSessioncoachesusername=null;
	public static String AllSessioncoachespassword=null;
	public static String Epochurl=null;
	public static String RA_Enrollment_Insurance_Provider=null;
	public static String RA_Enrollment_Member_Id=null;
	public static String RA_Enrollment_Group_Member=null;
	public static String RA_Enrollment_First_Name=null;
	public static String RA_Enrollment_Last_Name=null;
	public static String RA_Enrollment_Month=null;
	public static String RA_Enrollment_Day=null;
	public static String RA_Enrollment_Year=null;
	public static String Client2_RA_Enrollment_Member_Id=null;
	public static String Client2_RA_Enrollment_Group_Number=null;
	public static String Client2_RA_Enrollment_Last_Name=null;
	public static String Client2_RA_Enrollment_First_Name=null;
	public static String Client2_RA_Enrollment_Insurance_Provider=null;
	public static String Client1_RA_Organization_ID=null;
	public static String Client2_RA_Organization_ID=null;
	public static String Client2_RA_Enrollment_Month=null;
	public static String Client2_RA_Enrollment_Day=null;
	public static String Client2_RA_Enrollment_Year=null;
	public static String RA_Enrollment_Organization_Name=null;
	public static String UseCaseOne_RA_Enrollment_Month=null;
	public static String UseCaseOne_RA_Enrollment_Day=null;
	public static String UseCaseOne_RA_Enrollment_Year=null;
	public static String UseCaseOne_RA_Enrollment_Member_Id=null;
	public static String UseCaseOne_RA_Enrollment_Group_Number=null;
	public static String UseCaseOne_RA_Enrollment_Last_Name=null;
	public static String UseCaseOne_RA_Enrollment_First_Name=null;
	public static String UseCaseOne_RA_Enrollment_Insurance_Provider=null;
	public static String RA_UseCaseOneCustomEnroll_Member_Url=null;
	public static String UseCaseTwo_RA_Enrollment_Month=null;
	public static String UseCaseTwo_RA_Enrollment_Day=null;
	public static String UseCaseTwo_RA_Enrollment_Year=null;
	public static String UseCaseTwo_RA_Enrollment_Member_Id=null;
	public static String UseCaseTwo_RA_Enrollment_Group_Number=null;
	public static String UseCaseTwo_RA_Enrollment_Last_Name=null;
	public static String UseCaseTwo_RA_Enrollment_First_Name=null;
	public static String UseCaseTwo_RA_Enrollment_Insurance_Provider=null;
	public static String RA_UseCaseTwoCustomEnroll_Member_Url=null;
	public static String UseCaseThree_RA_Enrollment_Month=null;
	public static String UseCaseThree_RA_Enrollment_Day=null;
	public static String UseCaseThree_RA_Enrollment_Year=null;
	public static String UseCaseThree_RA_Enrollment_Member_Id=null;
	public static String UseCaseThree_RA_Enrollment_Group_Number=null;
	public static String UseCaseThree_RA_Enrollment_Last_Name=null;
	public static String UseCaseThree_RA_Enrollment_First_Name=null;
	public static String UseCaseThree_RA_Enrollment_Insurance_Provider=null;
	public static String RA_UseCaseThreeCustomEnroll_Member_Url=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_Month=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_Day=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_Year=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_Member_Id=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_Group_Number=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_Last_Name=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_First_Name=null;
	public static String UseCaseFourUnitedHeath_RA_Enrollment_Insurance_Provider=null;
	public static String RA_UseCaseFourUnitedHeathCustomEnroll_Member_Url=null;
	public static String UseCaseFourClient2_RA_Enrollment_Month=null;
	public static String UseCaseFourClient2_RA_Enrollment_Day=null;
	public static String UseCaseFourClient2_RA_Enrollment_Year=null;
	public static String UseCaseFourClient2_RA_Enrollment_Member_Id=null;
	public static String UseCaseFourClient2_RA_Enrollment_Group_Number=null;
	public static String UseCaseFourClient2_RA_Enrollment_Last_Name=null;
	public static String UseCaseFourClient2_RA_Enrollment_First_Name=null;
	public static String UseCaseFourClient2_RA_Enrollment_Insurance_Provider=null;
	public static String RA_UseCaseFourClient2CustomEnroll_Member_Url=null;
	public static String UseCaseFourClient3_RA_Enrollment_Month=null;
	public static String UseCaseFourClient3_RA_Enrollment_Day=null;
	public static String UseCaseFourClient3_RA_Enrollment_Year=null;
	public static String UseCaseFourClient3_RA_Enrollment_Member_Id=null;
	public static String UseCaseFourClient3_RA_Enrollment_Group_Number=null;
	public static String UseCaseFourClient3_RA_Enrollment_Last_Name=null;
	public static String UseCaseFourClient3_RA_Enrollment_First_Name=null;
	public static String UseCaseFourClient3_RA_Enrollment_Insurance_Provider=null;
	public static String RA_UseCaseFourClient3CustomEnroll_Member_Url=null;
	public static String PracticePAusername=null;
	public static String PracticePApassword=null;
	public static String PracticePA2username=null;
	public static String PracticePA2password=null;
	public static String RA_Prov_Org=null;
	public static String PracticePA2name=null;
	public static String PracticePAABCusername=null;
	public static String PracticePAABCpassword=null;
	public static String ProgramadminKidneyusername=null;
	public static String KidneyMemberusername1=null;
	public static String Orbera_Configurablecolumn_OrganizationID=null;
	public static String Orbera_UserAlert_AbcPractice_OrganizationID=null;
	public static String RA_Member_username2=null;
	public static String RA_Member_username3=null;
	public static String RA_Member_username4=null;
	public static String RA_Member_username5=null;
	public static String GRID_IP=null;
	public static String RA_Member_Client=null;
	public static String MDAC_Jira_Url=null;
	public static String MDAC_Jira_Username=null;
	public static String MDAC_Jira_Password=null;
	public static String T2_Coach_Username2=null;
	public static String T2_Coach_Username1=null;
	public static String Mast_ProgramId=null;
	public static String T2_SFTPUsername=null;
	public static String T2_SFTPPassword=null;
	public static String T2_SFTPHostName=null;
	public static String T2_SFTPChannel=null;	
	public static String Session_TypeID=null;
	public static String Rally_Url=null;
	public static String Rally_Test_Page_Url=null;
	public static String Rally_Member_Username1=null;
	public static String Rally_Member_Password1=null;
	public static String Rally_TestPage_DefaultSubdomain_Email=null;
	public static String Rally_TestPage_DefaultSubdomain_Rally_ID=null;
	public static String Rally_TestPage_DefaultSubdomain_First_Name=null;
	public static String Rally_TestPage_DefaultSubdomain_Last_Name=null;
	public static String Rally_TestPage_DefaultSubdomain_DOB=null;
	public static String Rally_TestPage_DefaultSubdomain_Employee_ID=null;
	public static String Rally_TestPage_DefaultSubdomain_Client_ID=null;
	public static String Rally_TestPage_DefaultSubdomain_Group_Number=null;
	public static String Rally_TestPage_DefaultSubdomain_Member_ID=null;
	public static String Rally_Authentication_Username=null;
	public static String Rally_Authentication_Password=null;
	public static String Rally_TestPage_DefaultSubdomain_Insurance_Provider=null;
	public static String Rally_TestPage_CustomSubdomain1_Email=null;
	public static String Rally_TestPage_CustomSubdomain1_Rally_ID=null;
	public static String Rally_TestPage_CustomSubdomain1_First_Name=null;
	public static String Rally_TestPage_CustomSubdomain1_Last_Name=null;
	public static String Rally_TestPage_CustomSubdomain1_DOB=null;
	public static String Rally_TestPage_CustomSubdomain1_Employee_ID=null;
	public static String Rally_TestPage_CustomSubdomain1_Client_ID=null;
	public static String Rally_TestPage_CustomSubdomain1_Group_Number=null;
	public static String Rally_TestPage_CustomSubdomain1_Member_ID=null;
	public static String Rally_TestPage_CustomSubdomain1_Insurance_Provider=null;
	public static String Rally_TestPage_CustomSubdomain2_Email=null;
	public static String Rally_TestPage_CustomSubdomain2_Rally_ID=null;
	public static String Rally_TestPage_CustomSubdomain2_First_Name=null;
	public static String Rally_TestPage_CustomSubdomain2_Last_Name=null;
	public static String Rally_TestPage_CustomSubdomain2_DOB=null;
	public static String Rally_TestPage_CustomSubdomain2_Employee_ID=null;
	public static String Rally_TestPage_CustomSubdomain2_Client_ID=null;
	public static String Rally_TestPage_CustomSubdomain2_Group_Number=null;
	public static String Rally_TestPage_CustomSubdomain2_Member_ID=null;
	public static String Rally_TestPage_CustomSubdomain2_Insurance_Provider=null;
	public static String Rally_TestPage_CustomSubdomain3_Email=null;
	public static String Rally_TestPage_CustomSubdomain3_Rally_ID=null;
	public static String Rally_TestPage_CustomSubdomain3_First_Name=null;
	public static String Rally_TestPage_CustomSubdomain3_Last_Name=null;
	public static String Rally_TestPage_CustomSubdomain3_DOB=null;
	public static String Rally_TestPage_CustomSubdomain3_Employee_ID=null;
	public static String Rally_TestPage_CustomSubdomain3_Client_ID=null;
	public static String Rally_TestPage_CustomSubdomain3_Group_Number=null;
	public static String Rally_TestPage_CustomSubdomain3_Member_ID=null;
	public static String Rally_TestPage_CustomSubdomain3_Insurance_Provider=null;
	public static String Rally_TestPage_CustomSubdomain4_Email=null;
	public static String Rally_TestPage_CustomSubdomain4_Rally_ID=null;
	public static String Rally_TestPage_CustomSubdomain4_First_Name=null;
	public static String Rally_TestPage_CustomSubdomain4_Last_Name=null;
	public static String Rally_TestPage_CustomSubdomain4_DOB=null;
	public static String Rally_TestPage_CustomSubdomain4_Employee_ID=null;
	public static String Rally_TestPage_CustomSubdomain4_Client_ID=null;
	public static String Rally_TestPage_CustomSubdomain4_Group_Number=null;
	public static String Rally_TestPage_CustomSubdomain4_Member_ID=null;
	public static String Rally_TestPage_CustomSubdomain4_Insurance_Provider=null;
	public static String QS_Member_Url=null;
	public static String QS1_Member_Url=null;
	public static String QS2_Member_Url=null;
	public static String QS3_Member_Url=null;
	public static String QS4_Member_Url=null;
	public static String QS5_Member_Url=null;
	public static String QS_Member_Username=null;
	public static String QS_Member_Password=null;
	public static String QS1_Member_Username=null;
	public static String QS1_Member_Password=null;
	public static String QS2_Member_Username=null;
	public static String QS2_Member_Password=null;
	public static String QS3_Member_Username=null;
	public static String QS3_Member_Password=null;
	public static String QS4_Member_Username=null;
	public static String QS4_Member_Password=null;
	public static String QS5_Member_Username=null;
	public static String QS5_Member_Password=null;
	public static String QS_BASICINFOPAGE_FIRSTNAME=null;
	public static String QS_BASICINFOPAGE_LASTNAME=null;
	public static String QS1_BASICINFOPAGE_FIRSTNAME=null;
	public static String QS1_BASICINFOPAGE_LASTNAME=null;
	public static String QS2_BASICINFOPAGE_FIRSTNAME=null;
	public static String QS2_BASICINFOPAGE_LASTNAME=null;
	public static String QS3_BASICINFOPAGE_FIRSTNAME=null;
	public static String QS3_BASICINFOPAGE_LASTNAME=null;
	public static String QS4_BASICINFOPAGE_FIRSTNAME=null;
	public static String QS4_BASICINFOPAGE_LASTNAME=null;
	public static String QS5_BASICINFOPAGE_FIRSTNAME=null;
	public static String QS5_BASICINFOPAGE_LASTNAME=null;
	public static String QS_MONTH=null;
	public static String QS_DAY=null;
	public static String QS_YEAR=null;
	public static String QS_INSURANCEINFORMATION_INSURANCEPLAN=null;
	public static String QS_INSURANCEINFORMATION_MEMBERID=null;
	public static String QS_INSURANCEINFORMATION_GROUPNUMBER=null;
	public static String QS1_INSURANCEINFORMATION_INSURANCEPLAN=null;
	public static String QS1_INSURANCEINFORMATION_MEMBERID=null;
	public static String QS1_INSURANCEINFORMATION_GROUPNUMBER=null;
	public static String QS2_INSURANCEINFORMATION_INSURANCEPLAN=null;
	public static String QS2_INSURANCEINFORMATION_MEMBERID=null;
	public static String QS2_INSURANCEINFORMATION_GROUPNUMBER=null;
	public static String QS3_INSURANCEINFORMATION_INSURANCEPLAN=null;
	public static String QS3_INSURANCEINFORMATION_MEMBERID=null;
	public static String QS3_INSURANCEINFORMATION_GROUPNUMBER=null;
	public static String QS4_INSURANCEINFORMATION_INSURANCEPLAN=null;
	public static String QS4_INSURANCEINFORMATION_MEMBERID=null;
	public static String QS4_INSURANCEINFORMATION_GROUPNUMBER=null;
	public static String QS5_INSURANCEINFORMATION_INSURANCEPLAN=null;
	public static String QS5_INSURANCEINFORMATION_MEMBERID=null;
	public static String QS5_INSURANCEINFORMATION_GROUPNUMBER=null;

	/**
	 * Retrieve values from custom properties
	 * @throws SensipleReporterException
	 * @throws Exception 
	 */
	public static void init() throws SensipleReporterException, Exception {
		if (getCustomProperties() != null) {
			log.info("Reading from custom properties");
			Properties localProperties = new Properties();

			try {
				localProperties.load(new FileReader(getCustomProperties()));
				String reportsDir = localProperties.getProperty("sensiple.reports.dir")			.trim();
				String headerText = localProperties.getProperty(			"sensiple.proj.header.text").trim();	
				String projectDescription = localProperties.getProperty(			"sensiple.proj.description").trim();
				String takeScreenShot = localProperties.getProperty(			"sensiple.reports.takescreenshot").trim();
				String configReports = localProperties.getProperty(			"sensiple.reports.configurationreports").trim();
				String excelReport = localProperties.getProperty("sensiple.reports.excel")			.trim();
				String contExectution = localProperties.getProperty(			"sensiple.reports.continueExecutionAfterStepFailed").trim();
				String reExecution = localProperties.getProperty(   "sensiple.failures.reexecution").trim();
				//String recordExecution = localProperties.getProperty(			"zado.reports.recordExecution").trim();
				userName = localProperties.getProperty(			"sensiple.mail.username").trim();
				password = localProperties.getProperty(			"sensiple.mail.password").trim();
				smtpHost = localProperties.getProperty(			"sensiple.mail.smtp.host").trim();
				fromAddress = localProperties.getProperty(			"sensiple.mail.from.address").trim();
				toAddress = localProperties.getProperty(			"sensiple.mail.to.address").trim();
				ccAddress = localProperties.getProperty(				"sensiple.mail.cc.address").trim();	
				Subject = localProperties.getProperty(				"sensiple.mail.subject").trim();
				Reports_Path = localProperties.getProperty(				"sensiple.reports.dir").trim();
				Zipfolder_Path = localProperties.getProperty(				"sensiple.mail.zipfolder").trim();
				testCasePath = localProperties.getProperty(			"sensiple.proj.testcasePath").trim();
				String ORSheet = localProperties.getProperty(			"sensiple.proj.ORSheet.path").trim();
				ORSheetPath=testCasePath+"/"+ORSheet;
				browser = localProperties.getProperty(			"sensiple.browser.name").trim().toLowerCase();
				//WIDGET_HTML_FILE = localProperties.getProperty(			"zado.proj.widget").trim().toLowerCase();
				WaitFor = localProperties.getProperty(			"sensiple.proj.waitSec").trim().toLowerCase();				
				//Properties urlsProperties = new Properties();
				System.out.println("testCasePath: "+testCasePath);								
				String uploadPath=localProperties.getProperty(   "sensiple.proj.uploadfile").trim();
				uploadFilePath=testCasePath+"/"+uploadPath+"/";
				String logo1 = localProperties.getProperty("sensiple.proj.header.logo")			.trim();
				logo=uploadFilePath+logo1;



				 
				
				//Mobile Configuration
				String MOBILE_APP_PATH=localProperties.getProperty(   "sensiple.mobileapp.apk.path").trim();
				MOBILE_APPPATH=uploadFilePath+MOBILE_APP_PATH;
				MOBILEAPP_APK_NAME=localProperties.getProperty(   "sensiple.mobile.apk.name").trim();
				MOBILE_DEVICE_NAME=localProperties.getProperty(   "sensiple.mobile.devicename").trim();
				MOBILE_DEVICE_VERSION=localProperties.getProperty(   "sensiple.mobile.version").trim();
				MOBILE_APK_APPPACKAGE=localProperties.getProperty(   "sensiple.mobile.apppackage.name").trim();
				MOBILE_APP_TYPE = localProperties.getProperty(   "sensiple.mobile.app.type").trim();
				MOBILE_WEB_BROWSER_NAME = localProperties.getProperty(   "sensiple.mobile.web.browser").trim();
				MOBILE_WEB_URL = localProperties.getProperty(   "sensiple.mobile.web.Url").trim();
				MOBILE_DEVICE_TYPE = localProperties.getProperty(   "sensiple.mobile.device.type").trim();
				MOBILE_IOSDEVICE_UDID = localProperties.getProperty(   "sensiple.ios.mobile.udid").trim();
				GRID_IP = localProperties.getProperty(			"sensiple.Grid").trim().toLowerCase();

				
				try {
					if ((headerText != null) && (headerText.length() > 0)) {
						ReportLabels.HEADER_TEXT.setLabel(headerText);
					}
					if ((reExecution != null) && (reExecution.length() > 0)) {
						try {
							reexecution = Boolean.parseBoolean(reExecution);
						} catch (Exception localException1) {
						}
					}
					if ((takeScreenShot != null) && (takeScreenShot.length() > 0)) {
						try {
							takeScreenshot = Boolean.parseBoolean(takeScreenShot);
						} catch (Exception localException1) {
						}
					}
					if ((configReports != null) && (configReports.length() > 0)) {
						try {
							generateConfigReports = Boolean.parseBoolean(configReports);
						} catch (Exception localException2) {
						}
					}
					if ((excelReport != null) && (excelReport.length() > 0)) {
						try {
							generateExcelReports = Boolean.parseBoolean(excelReport);
						} catch (Exception localException3) {
						}
					}
					if ((contExectution != null) && (contExectution.length() > 0)) {
						try {
							continueExecutionAfterStepFailed = Boolean
									.parseBoolean(contExectution);
						} catch (Exception localException4) {
						}
					}
					/*if ((recordExecution != null) && (recordExecution.length() > 0)) {
			try {
			    RecordingFor localRecordingFor = RecordingFor
				    .valueOf(recordExecution.toUpperCase());
			    if (localRecordingFor == RecordingFor.SUITE) {
				recordSuiteExecution = true;
			    } else if (localRecordingFor == RecordingFor.TESTMETHOD) {
				recordTestMethodExecution = true;
			    }
			} catch (Throwable localThrowable) {
			}
		    }
					File file = new File(reportsDir);
					if(file.exists())
					{
						FileUtils.cleanDirectory(file); //clean out directory (this is optional -- but good know)
			            FileUtils.forceDelete(file);
						System.out.println("Report directory deleted");
					}*/
					File outputFile = new File(Directory.Zipfolder_Path);
					if(!outputFile.exists()){
						outputFile.mkdir();
					}


					if ((projectDescription != null) && (projectDescription.length() > 0)) {
						SensipleReports.indexPageDescription = projectDescription;
					}
					if ((reportsDir != null) && (reportsDir.length() > 0)) {
						REPORTSDir = reportsDir;
						REPORTSDIRName = new File(REPORTSDir).getName();
						RESULTSDir = REPORTSDir + SEP + "Results";
						HTMLDESIGNDIRName = "HTML_Design_Files";
						HTMLDESIGNDir = REPORTSDir + SEP + HTMLDESIGNDIRName;
						CSSDIRName = "CSS";
						CSSDir = HTMLDESIGNDir + SEP + CSSDIRName;
						IMGDIRName = "IMG";
						IMGDir = HTMLDESIGNDir + SEP + IMGDIRName;
						JSDIRName = "JS";
						JSDir = HTMLDESIGNDir + SEP + JSDIRName;
						RUNName = "Run_";
						RUNDir = RESULTSDir + SEP + RUNName;
						SETTINGSFile = RESULTSDir + SEP + "Settings.properties";
					}
				} catch (Exception localException5) {
					throw new SensipleReporterException(localException5.toString());
				}
			} catch (FileNotFoundException localFileNotFoundException) {
				throw new SensipleReporterException(
						"The Path for the Custom Properties file could not be found", localFileNotFoundException);
			} catch (IOException localIOException) {
				throw new SensipleReporterException(
						"Problem Occured while reading the Zado Reporter Config File");
			}
		}
	}

	public static void mkDirs(String paramString) {
		File localFile = new File(paramString);
		if (!localFile.exists()) {
			localFile.mkdirs();
		}
	}

	public static boolean exists(String paramString) {
		File localFile = new File(paramString);
		return localFile.exists();
	}
	/**
	 * Verifying required files for report generation
	 * @throws Exception 
	 */
	public static void verifyRequiredFiles() throws Exception {
		init();
		log.info("Setting Reports Dir---"+REPORTSDir);
		log.info("Setting Results Dir---"+RESULTSDir);
		mkDirs(REPORTSDir);
		if (!exists(RESULTSDir)) {
			mkDirs(RESULTSDir);
			SettingsFile.initSettingsFile();
		}
		if (!exists(HTMLDESIGNDir)) {
			mkDirs(HTMLDESIGNDir);
			mkDirs(CSSDir);
			mkDirs(JSDir);
			mkDirs(IMGDir);
			HTMLDesignFilesWriter.writeCSS();
			HTMLDesignFilesWriter.writeIMG();
			HTMLDesignFilesWriter.writeJS();
		}
		if ((logo != null) && (logo.length() > 0)) {
			String str = new File(logo).getName();
			if (!new File(IMGDir + SEP + str).exists()) {
				copyImage(logo);
			}
			ReportLabels.PROJ_LOGO.setLabel(str);
		}
	}

	private static void copyImage(String paramString) throws SensipleReporterException {
		File localFile = new File(paramString);
		if (!localFile.exists()) {
			return;
		}
		FileImageInputStream localFileImageInputStream = null;
		FileImageOutputStream localFileImageOutputStream = null;
		try {
			localFileImageInputStream = new FileImageInputStream(new File(
					paramString));
			localFileImageOutputStream = new FileImageOutputStream(new File(
					IMGDir + SEP + localFile.getName()));
			int i = 0;
			while ((i = localFileImageInputStream.read()) >= 0) {
				localFileImageOutputStream.write(i);
			}
			localFileImageOutputStream.close();
			return;
		} catch (Exception localException2) {
		} finally {
			try {
				localFileImageInputStream.close();
				localFileImageOutputStream.close();
				localFile = null;
			} catch (Exception localException4) {
				localFileImageInputStream = null;
				localFileImageOutputStream = null;
				localFile = null;
			}
		}
	}

	public static String getCustomProperties() {
		return System.getProperty("sensiple.reporter.config");
	}

	public static String createTestRunDateTime() {
		Calendar cal = Calendar.getInstance();
		return DateFormatUtils.format(cal, "dd-MM-yy, hh.mm aa");
	}

	public static String getTestRunDateTime(int run) {
		try {
			String testRun = SettingsFile.get("testRunDT");
			String timeArray [] = testRun.split(";");
			return timeArray[run-1];
		} catch (SensipleReporterException e) {
			e.printStackTrace();
		}
		return " ";
	}
}