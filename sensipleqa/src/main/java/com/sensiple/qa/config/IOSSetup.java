package com.sensiple.qa.config;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sensiple.qa.utils.Directory;

import io.appium.java_client.ios.IOSDriver;

public class IOSSetup {
	public static IOSDriver driver;
	public static IOSDriver getDriver() throws Exception {
		System.out.println("Started IOS Driver");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//File appDir = new File(Directory.MOBILE_APPPATH);
		File app = new File(Directory.MOBILE_APPPATH);
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
		if(Directory.MOBILE_DEVICE_TYPE.equalsIgnoreCase("real")) {
			capabilities.setCapability("deviceName", Directory.MOBILE_DEVICE_NAME);
			capabilities.setCapability("platformVersion", Directory.MOBILE_DEVICE_VERSION);
			capabilities.setCapability("udid", Directory.MOBILE_IOSDEVICE_UDID);
			if(Directory.MOBILE_APP_TYPE.equalsIgnoreCase("native")) {
//				capabilities.setCapability("app","/Users/apple/Library/Developer/Xcode/DerivedData/MyNetDiary-iPhone-ewvkxsvswtmquqfwqbwlshsoxlxr/Build/Products/Debug-iphoneos/RealAppeal-TEST.app");
				capabilities.setCapability("app",app.getAbsoluteFile());
				capabilities.setCapability("bundleId","com.healthfleet.jovia");
			}
			else if(Directory.MOBILE_APP_TYPE.equalsIgnoreCase("web")) {
				capabilities.setCapability("browserName", "safari");
			}
		}
		else if (Directory.MOBILE_DEVICE_TYPE.equalsIgnoreCase("simulator")) {
			capabilities.setCapability("deviceName", Directory.MOBILE_DEVICE_NAME);
			capabilities.setCapability("platformVersion", Directory.MOBILE_DEVICE_VERSION);
			if(Directory.MOBILE_APP_TYPE.equalsIgnoreCase("native")) {
				capabilities.setCapability("bundleId","com.healthfleet.jovia");
				capabilities.setCapability("app","/Users/apple/Library/Developer/Xcode/DerivedData/MyNetDiary-iPhone-ewvkxsvswtmquqfwqbwlshsoxlxr/Build/Products/Debug-iphoneos/RealAppeal-TEST.app");
			}
			else if(Directory.MOBILE_APP_TYPE.equalsIgnoreCase("web")) {
				capabilities.setCapability("browserName", "safari");
			}
		}
		//capabilities.setCapability("app","/Applications/Xcode.app/Contents/Developer/Applications/Simulator.app");
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		return driver;
	}

}
