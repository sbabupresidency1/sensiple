package com.sensiple.qa.test;
/**
 * TestNG Runner
 * @author Babu
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestNGRunner {

	public static void main(String args[]) {

		TestNGRunner runner = new TestNGRunner();
		runner.testRunner();
	}

	private void testRunner() {

		TestNG testNG = new TestNG();

		XmlSuite suite = new XmlSuite();
		suite.setName("Sample Suite Name");

		Map<String, String> chromeParams = new HashMap<String, String>();
		chromeParams.put("browser", "chrome");

		Map<String, String> firefoxParams = new HashMap<String, String>();
		firefoxParams.put("browser", "firefox");

		Map<String, String> ieParams = new HashMap<String, String>();
		ieParams.put("browser", "ie");

		List<XmlClass> classez = new ArrayList<XmlClass>();
		classez.add(new XmlClass("TestNGClass"));

		/** Chrome Test **/
		XmlTest chromeTest = new XmlTest(suite);
		chromeTest.setName("Chrome Test");
		//chromeTest.setParameters(chromeParams);
		chromeTest.setXmlClasses(classez);

		/** Firefox Test **//*
	XmlTest firefoxTest = new XmlTest(suite);

	firefoxTest.setName("Firefox Test");
	firefoxTest.setParameters(firefoxParams);
	firefoxTest.setXmlClasses(classez);*/

		/*/** Internet Explorer Test **//*
	XmlTest ieTest = new XmlTest(suite);
	params.put("browser", "ie");
	ieTest.setName("Internet Explorer Test");
	ieTest.setParameters(params);
	ieTest.setXmlClasses(classez);*/

		List<XmlTest> tests = new ArrayList<XmlTest>();
		tests.add(chromeTest);
		//tests.add(firefoxTest);
		//tests.add(ieTest);

		suite.setTests(tests);

		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);

		testNG.setXmlSuites(suites);
		testNG.run();

	}
}
