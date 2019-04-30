package com.sensiple.qa.config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sensiple.qa.utils.Directory;

public class AndroidSetup {
	 public static AndroidDriver adriver;
	 public static AndroidDriver getDriver() {
	  File appDir = new File(Directory.MOBILE_APPPATH);
	  File app = new File(appDir, Directory.MOBILEAPP_APK_NAME);	  
	  DesiredCapabilities capabilities = new DesiredCapabilities();
	  capabilities.setCapability("platformName", "Android");
	  capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"30" );
	  capabilities.setCapability("deviceName",Directory.MOBILE_DEVICE_NAME);
	  capabilities.setCapability("platformVersion", Directory.MOBILE_DEVICE_VERSION);
	  
	 /* capabilities.setCapability("appActivity", "com.ifttt.ifttt.intropager.IntroActivity");
	  capabilities.setCapability("appWaitActivity", "com.ifttt.ifttt.intropager.IntroActivity");
	  */
	  if(Directory.MOBILE_APP_TYPE.equalsIgnoreCase("Web")) {
	  capabilities.setCapability(CapabilityType.BROWSER_NAME, Directory.MOBILE_WEB_BROWSER_NAME); }
	  else {
	  capabilities.setCapability("app", app.getAbsolutePath());
	  capabilities.setCapability("appPackage", Directory.MOBILE_APK_APPPACKAGE); 
	  }
	 
	  try {
	   adriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	   
	  } catch (MalformedURLException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  } 
	  return adriver;
	 }
	}
