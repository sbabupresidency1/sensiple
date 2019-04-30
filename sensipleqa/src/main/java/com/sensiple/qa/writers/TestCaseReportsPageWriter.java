package com.sensiple.qa.writers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.sensiple.qa.enums.ExceptionDetails;
import com.sensiple.qa.enums.LogAs;
import com.sensiple.qa.utils.Platform;
import com.sensiple.qa.utils.Steps;
import com.sensiple.qa.utils.TestParameters;
import com.sensiple.qa.utils.Utils;
import com.sensiple.qa.enums.Colors;
import com.sensiple.qa.enums.ReportLabels;
import com.sensiple.qa.utils.Directory;

public class TestCaseReportsPageWriter extends ReportsPage {
	public static String browserName;
	public static String browserVersion;
	public static String failureReason;
	public static String failedTestcaseName;
	public static String detailtrace;
	private static String fileName = "";
	public static String stepNumber;

	public static void header(PrintWriter paramPrintWriter, ITestResult paramITestResult)  {
		paramPrintWriter.println("<!DOCTYPE html>\n\n<html>\n    <head>\n        <title>Test Snapshot</title>\n\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"../" + getTestCaseHTMLPath(paramITestResult) + "HTML_Design_Files/CSS/design.css\" />\n" + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"../" + getTestCaseHTMLPath(paramITestResult) + "HTML_Design_Files/CSS/jquery.jqplot.css\" />\n" + "\n" + "        <script type=\"text/javascript\" src=\"../" + getTestCaseHTMLPath(paramITestResult) + "HTML_Design_Files/JS/jquery.min.js\"></script>\n" + "        <script type=\"text/javascript\" src=\"../" + getTestCaseHTMLPath(paramITestResult) + "HTML_Design_Files/JS/jquery.jqplot.min.js\"></script>\n" + "        <!--[if lt IE 9]>\n" + "        <script language=\"javascript\" type=\"text/javascript\" src=\"../" + getTestCaseHTMLPath(paramITestResult) + "HTML_Design_Files/JS/excanvas.js\"></script>\n" + "        <![endif]-->\n" + "\n" + "        <script language=\"javascript\" type=\"text/javascript\" src=\"../" + getTestCaseHTMLPath(paramITestResult) + "HTML_Design_Files/JS/jqplot.pieRenderer.min.js\"></script>\n" + "        <script language=\"javascript\" type=\"text/javascript\">" + "$(document).ready(function() {" + " $(\".exception\").hide();" + " $(\"#showmenu\").show();" + " $('#showmenu').click(function() {" + " $('.exception').toggle(\"slide\");" + " });" + " });" + "        </script>" + "    </head>\n" + "    <body>\n" + "        <table id=\"mainTable\">\n" + "            <tr id=\"header\" >\n" + "                <td id=\"logo\">" + "<img src=\"../" + getTestCaseHTMLPath(paramITestResult) + "HTML_Design_Files/IMG/" + ReportLabels.SENSIPLE_LOGO.getLabel() + "\" alt=\"Logo\" height=\"80\" width=\"140\" /> " + "<br/>" + "                <td id=\"headertext\">\n" + "           " + ReportLabels.HEADER_TEXT.getLabel() + "         \n" + "<div style=\"padding-right:20px;float:right\"><img src=\"../" + getTestCaseHTMLPath(paramITestResult) + "HTML_Design_Files/IMG/" + ReportLabels.PROJ_LOGO.getLabel() + "\" height=\"70\" width=\"140\" /></div>" + "                </td>\n" + "            </tr>");
	}

	public static synchronized String createOrGetFile() {
		if (TestCaseReportsPageWriter.fileName.isEmpty()) {
			final File filePath = new File(Directory.Reports_Path + "/logs");
			filePath.mkdir();

			final String now = new SimpleDateFormat("EyyyyMMddHHmmssa").format(Calendar.getInstance().getTime());

			TestCaseReportsPageWriter.fileName = filePath + "/Failure_Log- " + now + ".log";
		}
		return TestCaseReportsPageWriter.fileName;
	}

	public static synchronized void failureLog(String testcaseName, String failureReason, String detailStackTrace) {
		try(FileWriter fw = new FileWriter(TestCaseReportsPageWriter.createOrGetFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw))
		{
		    out.println("Testcase Name: "+testcaseName);
		    out.println("Failure StepNumber: "+stepNumber);
		    out.println("Reason for Failure:\n\t"+failureReason);
		    out.println("Full Stack Trace:\n\t"+detailStackTrace);
			out.println("");
		    out.println("");
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	public static String getExecutionTime(ITestResult paramITestResult)  {
		long l = paramITestResult.getEndMillis() - paramITestResult.getStartMillis();
		if (l > 1000L)
		{
			l /= 1000L;
			// return l + " Sec";
			return l + " s";
		}
		return l + " ms";
		//return l + " Milli Sec";
	}

	private static String getExceptionDetails(ITestResult testResult)  {
		try
		{
			testResult.getThrowable().toString();
		}
		catch (Throwable localThrowable)
		{
			return "";
		}
		String str1 = testResult.getThrowable().toString();
		String str2 = str1;
		if (str1.contains(":")) {
			str2 = str1.substring(0, str1.indexOf(":")).trim();
		} else {
			str1 = "";
		}
		try
		{
			str2 = getExceptionClassName(str2, str1);
			if (str2.equals("Assertion Error"))
			{
				if (str1.contains(">"))
				{
					str2 = str2 + str1.substring(str1.indexOf(":"), str1.lastIndexOf(">") + 1).replace(">", "\"");
					str2 = str2.replace("<", "\"");
				}
				if (testResult.getThrowable().getMessage().trim().length() > 0) {
					str2 = testResult.getThrowable().getMessage().trim();
				}
			}
			else if (str1.contains("{"))
			{
				str2 = str2 + str1.substring(str1.indexOf("{"), str1.lastIndexOf("}"));
				str2 = str2.replace("{\"method\":", " With ").replace(",\"selector\":", " = ");
			}
			else if ((str2.equals("Unable to connect Browser")) && (str1.contains(".")))
			{
				str2 = str2 + ": Browser is Closed";
			}
			else if (str2.equals("WebDriver Exception"))
			{
				str2 = testResult.getThrowable().getMessage();
			}
		}
		catch (ClassNotFoundException localClassNotFoundException) {}catch (Exception localException) {}
		str2 = str2.replace(">", "\"");
		str2 = str2.replace("<", "\"");
		return str2;
	}

	private static String getExceptionClassName(String paramString1, String paramString2) throws ClassNotFoundException  {
		String str = "";
		try
		{
			str = ExceptionDetails.valueOf(paramString1.trim().replace(".", "_")).getExceptionInfo();
		}
		catch (Exception localException)
		{
			str = paramString1;
		}
		return str;
	}

	public static String getReqCoverageInfo(ITestResult testResult)  {
		try
		{
			if (testResult.getAttribute("reqCoverage") == null) {
				return ReportLabels.TC_INFO_LABEL.getLabel();
			}
			return testResult.getAttribute("reqCoverage").toString();
		}
		catch (Exception localException) {}
		return ReportLabels.TC_INFO_LABEL.getLabel();
	}

	public static void content(PrintWriter printwriter, ITestResult testResult, int i)  {
		browserName = getBrowserName(testResult);
		browserVersion = getBrowserVersion(testResult);
		//printwriter.println((new StringBuilder()).append("<td id=\"content\">   \n                    <div class=\"info\">\n                        The following table lists down the Sequential Steps during the Run <br/>\nTestCase Name: <b>").append(getTestCaseName(testResult)).append("</b><br/>").append("                        Time Taken for Executing: <b>").append(getExecutionTime(testResult)).append("</b> <br/>\n").append("                        Current Run Number: <b>Run ").append(i).append("</b></br>\n").append("Method Type: <b>").append(CurrentRunPageWriter.getMethodType(testResult)).append("</b></br>").append("                    </div>").toString());
		printwriter.println((new StringBuilder()).append("<td id=\"content\">   \n                    <div class=\"info\"><b><font size=3>\n                        Test Case Steps </font></b><br/>\nTest Case Name: <b>").append(getTestCaseName(testResult)).append("</b><br/>").append("<b>"));
		//printwriter.println((new StringBuilder()).append("<div class=\"info\"><br/><b>Requirement Coverage/ TestCase Description</b><br/><br/>").append(getReqCoverageInfo(testResult)).append("</div>").toString());
		printwriter.println((new StringBuilder()).append("<div class=\"chartStyle summary\" style=\"background-color: #FF9A36;width: 40%;margin-left: 80px; height: 200px;\">          \n                        <b>Execution Platform Details</b><br/><br/>\n                        <table>\n                            <tr>\n                                <td>O.S</td>\n                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n                                <td>").append(Platform.OS).append(", ").append(Platform.OS_ARCH).append("Bit, V").append(Platform.OS_VERSION).append("</td>\n").append("                            </tr>\n").append("                            <tr>\n").append("                                <td>Browser</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(getBrowserName(testResult)).append(",").append(getBrowserVersion(testResult)).append("</td>\n").append("                            </tr>\n").append("                                <td>Java</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(Platform.JAVA_VERSION).append("</td>\n").append("                            </tr>\n").append("\n").append("                            <tr>\n").append("                                <td>Hostname</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(Platform.getHostName()).append("</td>\n").append("                            </tr>\n").append("\n").append("                            <tr>\n").append("                                <td>Selenium</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(Platform.DRIVER_VERSION).append("</td>\n").append("                            </tr>\n").append("                        </table>  \n").append("                    </div>\n").append("                   ").toString());
		//printwriter.println((new StringBuilder()).append(" <div class=\"chartStyle summary\" style=\"background-color: ").append(getColorBasedOnResult(testResult)).append(";margin-left: 20px; height: 200px;width: 40%; \">\n").append("                        <b>Test Snapshot</b><br/><br/>\n").append("                        <table>\n").append("                            <tr>\n").append("                                <td>Status</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(getResult(testResult)).append("</td>\n").append("                            </tr>\n").append("                            <tr>\n").append("                                <td>Execution Date</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(Utils.getCurrentTime()).append("</td>\n").append("                            </tr>\n").append("                            \n").append("\n").append("                            <tr>\n").append("                                <td>Browser</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(getBrowserName(testResult)).append(",").append(getBrowserVersion(testResult)).append("</td>\n").append("                            </tr>\n").append("                        </table> \n").append("                    </div>").toString());
		printwriter.println((new StringBuilder()).append(" <div class=\"chartStyle summary\" style=\"background-color: ").append(getColorBasedOnResult(testResult)).append(";margin-left: 20px; height: 200px;width: 40%; \">\n").append("                        <b>\n Test Snapshot</b><br/><br/>\n").append("                        <table>\n").append("                            <tr>\n").append("                                <td>Status</td>\n").append("                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(getResult(testResult)).append("</td>\n").append("                            </tr>\n").append("                            <tr>\n").append("                                <td>Execution Date</td>\n").append("                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(Utils.getCurrentTime()).append("                            </tr>\n").append("                        Current Test Execution   &nbsp;&nbsp;&nbsp;&nbsp;: &nbsp;&nbsp;<b> Execution ").append(i).append("</b></br>\n").append("                        Time Taken for Execution :&nbsp;&nbsp;<b>").append("                                <td>&nbsp;&nbsp;&nbsp;</td>").append(getExecutionTime(testResult)).append("                                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>\n").append("</b> <br/>").append("                        </table> \n").append("                    </div>").toString());
		//printwriter.println((new StringBuilder()).append(" <div class=\"chartStyle summary\" style=\"background-color: #7F525D;margin-left: 20px; height: 200px;width: 30%; \">\n                       <b>Author Info</b><br/><br/>\n                        <table>\n                            <tr>\n                                <td>Author Name</td>\n                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n                                <td>").append(authordetails1.getAuthorName()).append("</td>                                \n").append("                            </tr>\n").append("                            <tr>\n").append("                                <td>Creation Date</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(authordetails1.getCreationDate()).append("</td>\n").append("                            </tr>\n").append("                            <tr>\n").append("                                <td>Version</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(authordetails1.getVersion()).append("</td>\n").append("                            </tr>\n").append("                            <tr>\n").append("                                <td>System User</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(Platform.USER).append("</td>\n").append("                            </tr>\n").append("                        </table> \n").append("                    </div>\n").append("                    ").toString());
		List<String> list = Reporter.getOutput(testResult);
		printwriter.println("   <div>\n                        <table class=\"chartStyle\" id=\"tableStyle\" style=\"height:50px; float: left\">\n                            <tr>\n                                <th>S.No</th>\n                                <th>Test Procedure</th>\n                                <th>Input Data</th>\n                                <th>Expected Results</th>\n                                <th>Actual Value</th>\n                                <th>Time</th>\n                                <th>Status</th>\n                                <th>Screen shot</th>\n                            </tr>\n                           \n");
		int j = 1;
		if(Reporter.getOutput(testResult).size() <= 0)
		{
			printwriter.print("<tr>");
			printwriter.print("<td colspan=\"7\"><b>No Steps Available</b></td>");
			printwriter.print("</tr>");
		}
		j = 1;
		for(Iterator<String> iterator = list.iterator(); iterator.hasNext();)
		{
			String s2 = (String)iterator.next();
			Steps steps = null;
			steps = (Steps)testResult.getAttribute(s2);
			if(steps == null)
			{
				stepNumber = String.valueOf(j);
				printwriter.print("<tr>");
				printwriter.println((new StringBuilder()).append("<td>").append(j).append("</td>").toString());
				printwriter.print((new StringBuilder()).append("<td style=\"text-align:left\" colspan=\"7\">").append(s2).append("</td></tr>").toString());
				j++;
			}
			/*else if(steps.getDescription().equalsIgnoreCase("Wait"))
			{
				//System.out.println("Ok the wait : "+steps.getDescription());
			}*/
			else
			{
				stepNumber = String.valueOf(j);
				printwriter.print("<tr>");
				printwriter.println((new StringBuilder()).append("<td>").append(j).append("</td>").toString());
				printwriter.println((new StringBuilder()).append("<td>").append(steps.getDescription()).append("</td>").toString());
				printwriter.println((new StringBuilder()).append("<td>").append(steps.getInputValue()).append("</td>").toString());
				printwriter.println((new StringBuilder()).append("<td>").append(steps.getExpectedValue()).append("</td>").toString());
				printwriter.println((new StringBuilder()).append("<td>").append(steps.getActualValue()).append("</td>").toString());
				printwriter.println((new StringBuilder()).append("<td>").append(steps.getTime()).append("</td>").toString());
				//              printwriter.println((new StringBuilder()).append("<td>").append(steps.getLineNum()).append("</td>").toString());
				printwriter.println((new StringBuilder()).append("<td>").append(getLogDescription(steps.getLogAs(), testResult)).append("</td>").toString());
				try
				{
					Integer.parseInt(steps.getScreenShot().trim());
					printwriter.println((new StringBuilder()).append("<td><a target=\"_blank\" href=\"img/").append(j).append(".PNG").append("\"><img alt=\"No Screenshot\" src=\"img/").append(j).append(".PNG").append("\"/></a></td>").toString());
				}
				catch(Exception exception3)
				{
					printwriter.println("<td></td>");
				}
				printwriter.print("</tr>");
				j++;
			}
		}

		printwriter.print("\n                        </div></table>  \n");

		if(testResult.getParameters().length > 0 || testResult.getStatus() == 3 || testResult.getStatus() == 2)
		{
			printwriter.println("<div class=\"chartStyle summary\" style=\"color: black;width: 98%; height: 100%; padding-bottom: 30px;\">          \n");
			if(testResult.getParameters().length > 0)
			{
				printwriter.print("<b> </b><br/>");
				Object aobj[] = testResult.getParameters();
				int k = aobj.length;
				for(int l = 0; l < k; l++)
				{
					Object obj = aobj[l];
					failedTestcaseName = aobj[l].toString();
					printwriter.print((new StringBuilder()).append("Test Case Name: ").append(obj.toString()).append("<br/>").toString());
				}

			}
			if(testResult.getStatus() == 3)
			{
				printwriter.print("<br/><br/>");
				printwriter.println("                      <b>Reason for Skipping</b><br/><br/>\n");
				String as[] = testResult.getMethod().getGroupsDependedUpon();
				String as1[] = testResult.getMethod().getMethodsDependedUpon();
				if(as.length > 0)
				{
					String s = "";
					String as2[] = as;
					int i1 = as2.length;
					for(int k1 = 0; k1 < i1; k1++)
					{
						String s3 = as2[k1];
						s = (new StringBuilder()).append(s).append(s3).append("<br/>").toString();
					}

					printwriter.print((new StringBuilder()).append("<b>Depends on Groups: </b><br/>").append(s).toString());
				}
				if(as1.length > 0)
				{
					String s1 = "";
					String as3[] = as1;
					int j1 = as3.length;
					for(int l1 = 0; l1 < j1; l1++)
					{
						String s4 = as3[l1];
						s1 = (new StringBuilder()).append(s1).append(s4).append("<br/>").toString();
					}

					printwriter.print((new StringBuilder()).append("<b>Depends on Methods: </b><br/>").append(s1).append("<br/><br/>").toString());
				}
				try
				{
					if(testResult.getThrowable() instanceof SkipException)
						printwriter.print((new StringBuilder()).append("TestNG Skip Exception: ").append(testResult.getThrowable().getMessage()).append("<br/><br/>").toString());
				}
				catch(Exception exception2) { }
			} else
				if(testResult.getStatus() == 2)
				{
					failureReason = getExceptionDetails(testResult);
					printwriter.println((new StringBuilder()).append("                        <br/><br/><b>Reason for Failure:&nbsp;&nbsp;</b>").append(getExceptionDetails(testResult)).append("<br/><br/>\n").append("<b id=\"showmenu\">Click Me to Show/Hide the Full Stack Trace</b>").append("<div class=\"exception\">").toString());
					try
					{
						detailtrace = testResult.getThrowable().toString();
						 testResult.getThrowable().printStackTrace(printwriter);
					}
					catch(Exception exception1) { }
					failureLog(failedTestcaseName,failureReason,detailtrace);
				}
			printwriter.print("</div>");
		}
		printwriter.print("                    \n\n                </td>\n            </tr>");
	}

	public static String getLogDescription(LogAs logas, ITestResult itestresult)  {
		switch(logas)
		{
		case PASSED:
			return (new StringBuilder()).append("<img style=\"height:20px;width:20px;border:none\"  alt=\"PASS\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("/HTML_Design_Files/IMG/logpass.png\" />").toString();

		case FAILED:
			return (new StringBuilder()).append("<img style=\"height:18px;width:18px;border:none\"  alt=\"FAIL\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("/HTML_Design_Files/IMG/logfail.png\" />").toString();

		case INFO:
			return (new StringBuilder()).append("<img style=\"height:20px;width:20px;border:none\" alt=\"INFO\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("/HTML_Design_Files/IMG/loginfo.png\" />").toString();

		case WARNING:
			return (new StringBuilder()).append("<img style=\"height:20px;width:20px;border:none\"  alt=\"WARNING\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("/HTML_Design_Files/IMG/logwarning.png\" />").toString();
		}
		return "img";
	}

	private static String getBrowserName(ITestResult testResult)  {
		try
		{
			return testResult.getAttribute(Platform.BROWSER_NAME_PROP).toString();
		}
		catch (Exception localException) {}
		return "";
	}

	private static String getBrowserVersion(ITestResult paramITestResult)  {
		try
		{
			return paramITestResult.getAttribute(Platform.BROWSER_VERSION_PROP).toString();
		}
		catch (Exception localException) {}
		return "";
	}

	private static String getColorBasedOnResult(ITestResult paramITestResult)  {
		if (paramITestResult.getStatus() == 1) {
			return Colors.PASS.getColor();
		}
		if (paramITestResult.getStatus() == 2) {
			return Colors.FAIL.getColor();
		}
		if (paramITestResult.getStatus() == 3) {
			return Colors.SKIP.getColor();
		}
		return Colors.PASS.getColor();
	}

	private static String getResult(ITestResult paramITestResult)  {
		if (paramITestResult.getStatus() == 1) {
			try
			{
				if (paramITestResult.getAttribute("passedButFailed").equals("passedButFailed")) {
					return "Failed";
				}
				return "Passed";
			}
			catch (Exception localException)
			{
				return "Passed";
			}
		}
		if (paramITestResult.getStatus() == 2) {
			return "Failed";
		}
		if (paramITestResult.getStatus() == 3) {
			return "Skipped";
		}
		return "Unknown";
	}

	public static String getTestCaseHTMLPath(ITestResult paramITestResult)  {
		String str = paramITestResult.getAttribute("reportDir").toString();
		str = str.substring(str.indexOf(Directory.RESULTSDir) + (Directory.RESULTSDir.length() + 1));
		String[] arrayOfString = str.replace(Directory.SEP, "##@##@##").split("##@##@##");
		str = "";
		for (int i = 0; i < arrayOfString.length; i++) {
			str = str + "../";
		}
		return str;
	}

	public static void menuLink(PrintWriter paramPrintWriter, ITestResult paramITestResult, int paramInt)  {
		getTestCaseHTMLPath(paramITestResult);
		paramPrintWriter.println("\n            <tr id=\"container\">\n                <td id=\"menu\">\n                    <ul> \n");
		paramPrintWriter.println(" <li class=\"menuStyle\"><a href=\"../" + getTestCaseHTMLPath(paramITestResult) + "index.html\" >Detail Report View</a></li>" + "<li style=\"padding-top: 4px;\"><a href=\"" + getTestCaseHTMLPath(paramITestResult) + "ConsolidatedPage.html\" >Test Report Chart View</a></li>\n");
		if (paramInt == 1) {
			paramPrintWriter.println("\n <li class=\"menuStyle\"><a href=\"" + Directory.RUNName + paramInt + Directory.SEP + "CurrentRun.html\" >" + "Run " + paramInt + " </a></li>\n");
		} else {
			for (int i = 1; i <= paramInt; i++)
			{
				if (i == paramInt)
				{
					paramPrintWriter.println("\n <li style=\"padding-top: 4px;padding-bottom: 4px;\"><a href=\"" + Directory.RUNName + i + Directory.SEP + "CurrentRun.html\" >" + "Run " + i + " </a></li>\n");
					break;
				}
				paramPrintWriter.println("\n <li class=\"menuStyle\"><a href=\"" + Directory.RUNName + i + Directory.SEP + "CurrentRun.html\" >" + "Run " + i + " </a></li>\n");
			}
		}
		paramPrintWriter.println("\n                    </ul>\n                </td>\n\n");
	}

	public static String getTestCaseName(ITestResult testResult)  {
		TestParameters params = ((TestParameters)testResult.getParameters()[0]);
		return params.getTestCaseName();
	}
}

