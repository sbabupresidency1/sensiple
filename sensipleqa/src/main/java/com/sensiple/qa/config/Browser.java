package com.sensiple.qa.config;

import org.openqa.selenium.WebDriver;

/**
 * Main interface for the Browser Configuration
 * 
 * @author babu.sathiyaraja
 * 
 */
public interface Browser {
	WebDriver getDriver();
	String getBrowserName();
	String getVersion();
}
