package com.sensiple.qa.writers;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.sensiple.qa.utils.TestParameters;
import com.sensiple.qa.utils.Utils;
import com.sensiple.qa.SensipleReports;
import com.sensiple.qa.enums.ReportLabels;
import com.sensiple.qa.utils.Attributes;
import com.sensiple.qa.utils.Directory;

public class CurrentRunPageWriter  extends ReportsPage {
	
	public static int passCount;
	public static int failCount;
	public static int skipCount;
	public static String[] failedModuleName = null;
	public static String[] failedTestcaseName = null;
	public static ArrayList<String> Failed_ModuleName= new ArrayList<String>();
	public static ArrayList<String> Failed_TestCaseName= new ArrayList<String>();
	public static int FailedTestCasesCount = 0;	

	public static void menuLink(PrintWriter paramPrintWriter, int paramInt)  {
		paramPrintWriter.println("\n            <tr id=\"container\">\n                <td id=\"menu\">\n                    <ul> \n");
		paramPrintWriter.println(" <li class=\"menuStyle\"><a href=\"../../index.html\" >Detail Report View</a></li><li style=\"padding-top: 4px;\"><a href=\"../ConsolidatedPage.html\" >Test Report Chart View</a></li>\n");
		if (paramInt == 1) {
			paramPrintWriter.println("\n <li class=\"menuStyle\"><a href=\"" + Directory.RUNName + paramInt + Directory.SEP + "CurrentRun.html\" >" + " Execution " + paramInt + " </a></li>\n");
		} else {
			for (int i = 1; i <= paramInt; i++)      {
				if (i == paramInt)        {
					paramPrintWriter.println("\n <li style=\"padding-top: 4px;padding-bottom: 4px;\"><a href=\"" + Directory.RUNName + i + Directory.SEP + "CurrentRun.html\" >" + " Execution " + i + " </a></li>\n");
					break;
				}
				paramPrintWriter.println("\n <li class=\"menuStyle\"><a href=\"" + Directory.RUNName + i + Directory.SEP + "CurrentRun.html\" >" + " Execution " + i + " </a></li>\n");
			}
		}
		paramPrintWriter.println("\n                    </ul>\n                </td>\n\n");
	}

	public static String getExecutionTime(ITestResult testResult)  {
		long l = testResult.getEndMillis() - testResult.getStartMillis();
		if (l > 1000L)
		{
			l /= 1000L;
			//return l + " Sec";
			return l + " s";
		}
		//return l + " Milli Sec";
		return l + " ms";
	}

	public static String getExecutionTime(long paramLong1, long paramLong2)  {
		long l = paramLong2 - paramLong1;
		if (l > 1000L)
		{
			l /= 1000L;
			return l + " s";
		}
		return l + " ms";
	}

	public static void content(PrintWriter printWriter, List<ITestResult> passedList, List<ITestResult> failedList, List<ITestResult> skippedList, List<ITestResult> passedConfig, List<ITestResult> failedConfig, List<ITestResult> skippedConfig, int runCount, long exectution, long executionTime)
	{
		int i = passedList.size() + failedList.size() + skippedList.size();
		passCount=passedList.size();
		failCount=failedList.size();
		skipCount=skippedList.size();
		//printWriter.println("<td id=\"content\">\n                    <div class=\"info\">\n                        The following pie chart demonstrates the percentage of Passed, Failed and Skipped Test Cases<br/>\n                        Time Taken for Executing below Test Cases: <b>" + getExecutionTime(exectution, executionTime) + "</b> <br/>\n" + "                        Current Run Number: <b>Run " + runCount + "</b>\n" + "                    </div>\n" + "<div class=\"info\">" + "<br/>" + "<b>Run Description</b><br/><br/>" + SensipleReports.currentRunDescription + "</div>" + "                    <div>\n" + "                        <div class=\"chartStyle summary\" style=\"width: 32%;background-color: #3B9C9C;\">\n" + "                            <b>Summary</b><br/><br/>\n" + "                            <table>\n" + "                            <tr>\n" + "                                <td>Execution Date</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + Utils.getCurrentTime() + "</td>\n" + "                            </tr>\n" + "                            <tr>\n" + "                                <td>Total Test Cases</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + i + "</td>\n" + "                            </tr>\n" + "                            <tr>\n" + "                                <td>Passed</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + passedList.size() + "</td>\n" + "                            </tr>\n" + "                            \n" + "                            <tr>\n" + "                                <td>Failed</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + failedList.size() + "</td>\n" + "                            </tr>\n" + "\n" + "                            <tr>\n" + "                                <td>Skipped</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + skippedList.size() + "</td>\n" + "                            </tr>\n" + "                        </table> \n" + "                        </div>" + "                        <div class=\"chartStyle\" style=\"text-align: left;margin-left: 30px;float: left;width: 60%;\">                        \n" + "                            <div id=\"chart\" style=\"height:300px;color:black;\"></div>\n" + "                        </div>\n" + "                    </div>\n" + "                    <div>\n");
		printWriter.println("<td id=\"content\">\n                    <div class=\"info\">\n                                             Time Taken for Executing below Test Cases: <b>" + getExecutionTime(exectution, executionTime) + "</b> <br/>\n" + "                        Current Test Execution: <b> Execution " + runCount + "</b>\n" + "                    </div>\n" + "<div class=\"info\">" + "<br/>" + "<b></b><br/><br/>" + SensipleReports.currentRunDescription + "</div>" + "                    <div>\n" + "                        <div class=\"chartStyle summary\" style=\"width: 32%;background-color: #16a4ce;\">\n" + "                            <b>Summary</b><br/><br/>\n" + "                            <table>\n" + "                            <tr>\n" + "                                <td>Execution Date</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + Utils.getCurrentTime() + "</td>\n" + "                            </tr>\n" + "                            <tr>\n" + "                                <td>Total Test Cases</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + i + "</td>\n" + "                            </tr>\n" + "                            <tr>\n" + "                                <td>Passed</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + passedList.size() + "</td>\n" + "                            </tr>\n" + "                            \n" + "                            <tr>\n" + "                                <td>Failed</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + failedList.size() + "</td>\n" + "                            </tr>\n" + "\n" + "                            <tr>\n" + "                                <td>Skipped</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + skippedList.size() + "</td>\n" + "                            </tr>\n" + "                        </table> \n" + "                        </div>" + "                        <div class=\"chartStyle\" style=\"text-align: left;margin-left: 30px;float: left;width: 60%;\">                        \n" + "                            <div id=\"chart\" style=\"height:300px;color:black;\"></div>\n" + "                        </div>\n" + "                    </div>\n" + "                    <div>\n");
		if (Directory.recordSuiteExecution) {
			printWriter.println("<p id=\"showmenu\">Click Me to Show/Hide the Execution Video</p><div id=\"video\" class=\"video\"><object classid=\"clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921\" codebase=\"http://downloads.videolan.org/pub/videolan/vlc/latest/win32/axvlc.cab\" width=\"400\" height=\"300\" id=\"vlc\" events=\"True\">  <param name=\"Src\" value=\"Recording" + Directory.SEP + "ATU_CompleteSuiteRecording" + ".mov\"></param>" + "  <param name=\"ShowDisplay\" value=\"True\" ></param>" + "    <param name=\"AutoLoop\" value=\"no\"></param>" + "    <param name=\"AutoPlay\" value=\"no\"></param>" + "    <embed type=\"application/x-google-vlc-plugin\" name=\"vlcfirefox\" autoplay=\"no\" loop=\"no\" width=\"99%\"  height=\"100%\" target=\"" + "Recording" + Directory.SEP + "ATU_CompleteSuiteRecording" + ".mov\"></embed>" + " </object>" + "</div>");
		} else {
			//printWriter.println("<p id=\"showmenu\">No Video Recording Available</p>");
		}
		//printWriter.println("<div style=\"float:left;  color: #585858; font-size: 14px;\">\t<select id=\"tcFilter\" class=\"filter\">\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"all\">All Methods</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"tests\">Test Methods</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"pass\">Passed Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"fail\">Failed Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"skip\">Skipped Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"config\">Configuration Methods</option>\n\t\t\t\t\t</select>\tFilter Methods &nbsp;</div>");
		printWriter.println("<div style=\"float:left;  color: #585858; font-size: 14px;\">\t<select id=\"tcFilter\" class=\"filter\">\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"all\">All Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"pass\">Passed Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"fail\">Failed Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"skip\">Skipped Test Cases</option>\n\t\t\t\t\t\t</option>\n\t\t\t\t\t</select>\tFilter Test Cases &nbsp;</div>");
		printWriter.println("<div style=\"float:left;  color: #585858; font-size: 14px;\">\t<select id=\"suiteFilter\" class=\"filter\">\n<option class=\"filterOption\" value=\"all\">All Suites</option>\n");
		Iterator<String> localIterator = Attributes.getSuiteNameMapperMap().keySet().iterator();
		while (localIterator.hasNext())    {
			String str = localIterator.next();
			printWriter.println("<option class=\"filterOption\" value=\"" + Attributes.getSuiteNameMapperMap().get(str) + "\">" + str + "</option>\n");
		}
		printWriter.println("</select>Filter Suites&nbsp;&nbsp;</div>");
		printWriter.println("                        <table id=\"tableStyle\" class=\"chartStyle\" style=\"height:50px; float: left\">\n                            <tr>\n                                <th>Suite Name</th>\n                                <th>Module Name</th>\n                                                               <th>Test Case Name</th>\n<th>Iteration</th>                                <th>Time</th>\n                                <th style=\"width: 7%\">Status</th>\n                            </tr>\n");
		writePassedData(printWriter, passedList, runCount);
		writeFailedData(printWriter, failedList, runCount);
		writeSkippedData(printWriter, skippedList, runCount);
		writePassedData(printWriter, passedConfig, runCount);
		writeFailedData(printWriter, failedConfig, runCount);
		writeSkippedData(printWriter, skippedConfig, runCount);
		printWriter.print("                        </table>\n                    </div>\n                </td>\n            </tr>");
	}

	private static void writePassedData(PrintWriter paramPrintWriter, List<ITestResult> testResultList, int paramInt)  {
		String str = "pass";
		Iterator<ITestResult> localIterator = testResultList.iterator();
//		passCount=testResultList.size();
		while (localIterator.hasNext())    {
			ITestResult localITestResult = localIterator.next();
			str = "pass " + getSuiteNameMapper(localITestResult);
			if (!localITestResult.getMethod().isTest()) {
				str = "config " + getSuiteNameMapper(localITestResult);
			}
			paramPrintWriter.print("                            <tr class=\"all " + str + "\">\n" + "                                <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getSuiteName(localITestResult) + "</a></td>\n" + "                                <td><a href=\"" 
					+ getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getModuleName(localITestResult) + "</a></td>\n"  + "                                <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" 
					+ getTestCaseName(localITestResult) + "</a></td>\n" + "                                <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) 
					+ "\">" + getIteration(localITestResult) + "</a></td>\n" + "                                <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getExecutionTime(localITestResult) + "</a></td>\n" 
					+ "                                <td><img  style=\"border: none; width: 25px\" src=\"../../HTML_Design_Files/IMG/pass.png\"></td>\n" + "                            </tr>\n");
		}
	}

	private static void writeFailedData(PrintWriter paramPrintWriter, List<ITestResult> paramList, int paramInt)  {
		String str = "fail";
		Iterator<ITestResult> localIterator = paramList.iterator();
//		failCount=paramList.size();
		
//		int i=0;
		while (localIterator.hasNext())
		{
			ITestResult localITestResult = localIterator.next();
			str = "fail " + getSuiteNameMapper(localITestResult);
			if (!localITestResult.getMethod().isTest()) {
				str = "config " + getSuiteNameMapper(localITestResult);
			}
			paramPrintWriter.print("                            <tr class=\"all " + str + "\">\n" + "                                <td><a href=\"" 
					+ getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getSuiteName(localITestResult) + "</a></td>\n" 
					+ "                              <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" 
					+ getModuleName(localITestResult) + "</a></td>\n"  + "                                <td><a href=\"" 
					+ getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getTestCaseName(localITestResult) + "</a></td>\n" 
					+ "                                <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) 
					+ "\">" + getIteration(localITestResult) + "</a></td>\n" + "                                <td><a href=\"" 
					+ getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getExecutionTime(localITestResult) + "</a></td>\n" 
					+ "                                <td><img  style=\"border: none;width: 25px\" src=\"../../HTML_Design_Files/IMG/fail.png\"></td>\n" + "                            </tr>\n");
		System.out.println("Failed Module name:"+getModuleName(localITestResult)+", Testcase Name:"+getTestCaseName(localITestResult));
		String ModuleName = getModuleName(localITestResult);
		String TestCaseName = getTestCaseName(localITestResult);			
		Failed_ModuleName.add(ModuleName);
		Failed_TestCaseName.add(TestCaseName);	
		FailedTestCasesCount=FailedTestCasesCount+1;
		//System.out.println("Count Exact : "+FailedTestCasesCount);
//		i++;
		}
	}

	private static void writeSkippedData(PrintWriter paramPrintWriter, List<ITestResult> paramList, int paramInt)  {
		String str = "skip";
		Iterator<ITestResult> localIterator = paramList.iterator();
//		skipCount=paramList.size();
		while (localIterator.hasNext())
		{
			ITestResult localITestResult = localIterator.next();
			str = "skip " + getSuiteNameMapper(localITestResult);
			if (!localITestResult.getMethod().isTest()) {
				str = "config " + getSuiteNameMapper(localITestResult);
			}
			paramPrintWriter.print("                            <tr class=\"all " + str + "\">\n" + "                                <td><a href=\"" 
					+ getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getSuiteName(localITestResult) + "</a></td>\n" 
					+ "                                  <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) 
					+ "\">" + getModuleName(localITestResult) + "</a></td>\n"  + "                                <td><a href=\"" 
					+ getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getTestCaseName(localITestResult) + "</a></td>\n" 
					+ "                                <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" 
					+ getIteration(localITestResult) + "</a></td>\n" + "                                <td><a href=\"" 
					+ getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getExecutionTime(localITestResult) + "</a></td>\n" 
					+ "                                <td><img  style=\" border: none;width: 25px\" src=\"../../HTML_Design_Files/IMG/skip.png\"></td>\n" + "                            </tr>\n");
		}
	}

	public static String getSuiteName(ITestResult testResult)  {
		return testResult.getTestContext().getSuite().getName();
	}

	public static String getSuiteNameMapper(ITestResult testResult)  {
		return Attributes.getSuiteNameMapper(testResult.getTestContext().getSuite().getName());
	}

	public static String getTestCaseHTMLPath(ITestResult testResult, int paramInt)  {
		String str1 = testResult.getAttribute("reportDir").toString();
		int i = (Directory.RUNName + paramInt).length();
		String str2 = str1.substring(str1.indexOf(Directory.RUNName + paramInt) + (i + 1));
		return str2 + Directory.SEP + getTestCaseName(testResult) + ".html";
	}

	public static String getModuleName(ITestResult testResult)  {
		TestParameters params = ((TestParameters)testResult.getParameters()[0]);
		return params.getModuleName();
	}

	public static String getClassName(ITestResult testResult)  {
		return testResult.getTestClass().getRealClass().getSimpleName();
	}

	public static String getIteration(ITestResult testResult)  {
		return testResult.getAttribute("iteration").toString();
	}

	public static String getTestCaseName(ITestResult testResult)  {
		TestParameters params = ((TestParameters)testResult.getParameters()[0]);
		return params.getTestCaseName();
	}

	public static String getReportDir(ITestResult testResult)  {

		String suiteName = testResult.getTestContext().getSuite().getName();
		String testName = testResult.getTestContext().getCurrentXmlTest().getName();
		String str3 = testResult.getTestClass().getName().replace(".", Directory.SEP);
		String methodName = testResult.getMethod().getMethodName();
		methodName = methodName + "_Iteration" + testResult.getMethod().getCurrentInvocationCount();
		String str5 = suiteName + Directory.SEP + testName + Directory.SEP + str3 + Directory.SEP + methodName;
		return str5;
	}

	public static String getMethodType(ITestResult testResult)  {

		ITestNGMethod localITestNGMethod = testResult.getMethod();
		if (localITestNGMethod.isAfterClassConfiguration()) {
			return "After Class";
		}
		if (localITestNGMethod.isAfterGroupsConfiguration()) {
			return "After Groups";
		}
		if (localITestNGMethod.isAfterMethodConfiguration()) {
			return "After Method";
		}
		if (localITestNGMethod.isAfterSuiteConfiguration()) {
			return "After Suite";
		}
		if (localITestNGMethod.isAfterTestConfiguration()) {
			return "After Test";
		}
		if (localITestNGMethod.isBeforeClassConfiguration()) {
			return "Before Class";
		}
		if (localITestNGMethod.isBeforeGroupsConfiguration()) {
			return "Before Groups";
		}
		if (localITestNGMethod.isBeforeMethodConfiguration()) {
			return "Before Method";
		}
		if (localITestNGMethod.isBeforeSuiteConfiguration()) {
			return "Before Suite";
		}
		if (localITestNGMethod.isBeforeTestConfiguration()) {
			return "Before Test";
		}
		if (localITestNGMethod.isTest()) {
			return "Test Method";
		}
		return "Unknown";
	}

	public static void header(PrintWriter paramPrintWriter)  {

		paramPrintWriter.println("<!DOCTYPE html>\n\n<html>\n    <head>\n        <title>Current Run Reports</title>\n\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"../../HTML_Design_Files/CSS/design.css\" />\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"../../HTML_Design_Files/CSS/jquery.jqplot.css\" />\n\n        <script type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/jquery.min.js\"></script>\n        <script type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/jquery.jqplot.min.js\"></script>\n        <!--[if lt IE 9]>\n        <script language=\"javascript\" type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/excanvas.js\"></script>\n        <![endif]-->\n\n        <script language=\"javascript\" type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/jqplot.pieRenderer.min.js\"></script>\n        <script type=\"text/javascript\" src=\"pieChart.js\"></script>\n");
		paramPrintWriter.print("<script language=\"javascript\" type=\"text/javascript\">$(document).ready(function() { $(\".video\").hide();$(\"#showmenu\").show(); $('#showmenu').click(function(){ $('.video').toggle(\"slide\"); }); });</script><style>#showmenu{text-align:center; padding-top:350px;color: #585858; font-size: 14px;}#video{height: 550px;    margin-top: 5px;    width: 97%;    border-style: solid;    border-width: 1px;    border-color: #21ABCD;    /* Shadow for boxes */    -moz-box-shadow: 0 0 10px #CCCCCC;    -ms-box-shadow: 0 0 10px #CCCCCC;    -webkit-box-shadow: 0 0 10px #CCCCCC;    box-shadow: 0 0 10px #CCCCCC;    zoom: 1;    filter: progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=0),        progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=90),        progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=180),        progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=270);    background-color: white;}</style>");
		paramPrintWriter.println("<script language=\"javascript\" type=\"text/javascript\">\n$(document).ready(function() {\n\t$('#tcFilter').on('change',function(){\n    if($(this).val()=='pass'){\n        $('.pass').show();\n\t\t$('.fail').hide();\n\t\t$('.skip').hide();\n\t\t$('.config').hide();\n    }\n\tif($(this).val()=='fail'){\n        $('.pass').hide();\n\t\t$('.fail').show();\n\t\t$('.skip').hide();\n\t\t$('.config').hide();\n    }\n\t\n\tif($(this).val()=='skip'){\n        $('.pass').hide();\n\t\t$('.fail').hide();\n\t\t$('.skip').show();\n\t\t$('.config').hide();\n    }\n\t\nif($(this).val()=='tests'){ $('.pass').show(); $('.fail').show(); $('.skip').show(); $('.config').hide(); }\tif($(this).val()=='config'){\n        $('.pass').hide();\n\t\t$('.fail').hide();\n\t\t$('.skip').hide();\n\t\t$('.config').show();\n    }\n\t\n\tif($(this).val()=='all'){\n        $('.pass').show();\n\t\t$('.fail').show();\n\t\t$('.skip').show();\n\t\t$('.config').show();\n    }\n  });\n});       \n</script>");
		paramPrintWriter.print("<script language=\"javascript\" type=\"text/javascript\">$(document).ready(function() {\t$('#suiteFilter').on('change',function(){if($(this).val()=='all'){      $('.all').show();  }");
		Iterator<String> localIterator = Attributes.getSuiteNameMapperMap().keySet().iterator();
		while (localIterator.hasNext())    {

			String str = (String)localIterator.next();
			paramPrintWriter.print("if($(this).val()=='" + (String)Attributes.getSuiteNameMapperMap().get(str) + "'){" + "      $('.all').hide();" + "$('." + (String)Attributes.getSuiteNameMapperMap().get(str) + "').show();" + "" + " }");
		}
		paramPrintWriter.println("  }); });</script>");
		paramPrintWriter.println("    </head>\n    <body>\n        <table id=\"mainTable\">\n            <tr id=\"header\" >\n                <td id=\"logo\"><img src=\"../../HTML_Design_Files/IMG/" + ReportLabels.SENSIPLE_LOGO.getLabel() + "\" alt=\"Logo\" height=\"70\" width=\"140\" /> " + "<br/>" + "                <td id=\"headertext\">\n" + "                    " + ReportLabels.HEADER_TEXT.getLabel() + "<div style=\"padding-right:20px;float:right\"><img src=\"../../HTML_Design_Files/IMG/" + ReportLabels.PROJ_LOGO.getLabel() + "\" height=\"70\" width=\"140\" /> </i></div>" + "</td>\n" + "\n" + "            </tr>");
	}
	
	/***
	 * Title : Write Failed Testcases to TextCaseExeSheet and Executed only the Failures
	 * Purpose : Automatically Re-Execute Failure Test cases
	 * @throws IOException
	 */
	public static void writeFailedTestCases() throws IOException {
		String ExecutioSheetPath = Directory.testCasePath;
		FileOutputStream fileOut = new FileOutputStream(ExecutioSheetPath+Directory.SEP+"TestCaseExeSheet.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook();		   
		XSSFSheet worksheet = wb.createSheet("Sheet1");		
		int Size = Failed_ModuleName.size();
		//System.out.println("Size : "+Size);				
		int rows = Size;		
		int column = 2;		
		XSSFCellStyle style = wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style.setFillPattern(CellStyle.ALIGN_FILL);	
		XSSFFont font = wb.createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 11);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
		style.setFont(font);		
		XSSFRow row1 = worksheet.createRow(0);
		XSSFCell cell1 = row1.createCell((short) 0);
		cell1.setCellValue("Module Name");	
		cell1.setCellStyle(style);			
		worksheet.setDefaultColumnWidth((short) 20);
		XSSFCell cell11 = row1.createCell((short) 1);
		cell11.setCellValue("Test Case Name");
		cell11.setCellStyle(style);	
		for (int i=0;i< rows;i++) {
			int k = i+1;
			XSSFRow row = worksheet.createRow(k);
			for(int j=0;j< column;j++) 	{
				XSSFCell cell = row.createCell((short) 0);					
				cell.setCellValue(Failed_ModuleName.get(i));
				XSSFCell cell1q = row.createCell((short) 1);
				String FailedTestCaseName = "";
				if(Failed_TestCaseName.get(i).contains("_chrome"))	{
					//System.out.println("Chrome");
					String[] testcase = Failed_TestCaseName.get(i).split("_chrome");
					FailedTestCaseName = testcase[0];
				}
				else if (Failed_TestCaseName.get(i).contains("_firefox"))
				{
					//System.out.println("Firefox");
					String[] testcase = Failed_TestCaseName.get(i).split("_firefox");
					FailedTestCaseName = testcase[0];
				}
				else if (Failed_TestCaseName.get(i).contains("_safari"))
				{
					//System.out.println("Firefox");
					String[] testcase = Failed_TestCaseName.get(i).split("_safari");
					FailedTestCaseName = testcase[0];
				}
				else if (Failed_TestCaseName.get(i).contains("_ie")) {
					//System.out.println("Internet Explorer");
					String[] testcase = Failed_TestCaseName.get(i).split("_ie");
					FailedTestCaseName = testcase[0];
				}					
				cell1q.setCellValue(FailedTestCaseName);				
			}	
		}
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		System.out.println("Failed Testcase(s) Created Successfully!!");
	}
}

