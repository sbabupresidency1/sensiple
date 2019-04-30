package com.sensiple.qa.config;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Class to configure and get Details for Firefox Browser
 */
public class FirefoxBrowser extends DefaultBrowser implements Browser {
    @Override
    protected Capabilities createRemoteCapabilities() {
        final DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setCapability("marionette",true);
        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
      
    	WebDriver dd=new FirefoxDriver(caps);
    	return caps ;
    }

    @Override
    protected RemoteWebDriver createLocalDriver() {
    	
    	/*System.setProperty("webdriver.gecko.driver", ApplicationConstants.GECKO_DRIVER_PATH_WINDOWS);
    	WebDriver dd= new FirefoxDriver();
    	dd.manage().window().maximize();*/
        return new FirefoxDriver();
    }
}
