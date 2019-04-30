package com.sensiple.qa.config;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Class to configure and get Details for Chrome Browser
 */
public class ChromeBrowser extends DefaultBrowser implements Browser {
	
    @Override    
    protected Capabilities createRemoteCapabilities() {
    	ChromeDriver driver = null;
    	System.setProperty("webdriver.chrome.driver", "C:\\workspace\\grid\\chromedriver.exe");
    	ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=foo;bar");
        driver = new ChromeDriver(options);

        Object userAgent = driver.executeScript("return window.navigator.userAgent");
        assertEquals("foo;bar", userAgent);
        
    	//ChromeDriver driver = null;
        final DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return caps;
    }

    @Override
    protected RemoteWebDriver createLocalDriver() {
        return new ChromeDriver();
    }
}
