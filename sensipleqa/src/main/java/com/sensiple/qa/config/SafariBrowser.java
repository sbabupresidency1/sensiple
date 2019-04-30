package com.sensiple.qa.config;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Class to configure and get Details for Safari Browser
 */
public class SafariBrowser extends DefaultBrowser implements Browser {
    @Override
    protected Capabilities createRemoteCapabilities() {
        final DesiredCapabilities caps = DesiredCapabilities.safari();
        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return caps;
    }

    @Override
    protected RemoteWebDriver createLocalDriver() {
        return new SafariDriver();
    }
}
