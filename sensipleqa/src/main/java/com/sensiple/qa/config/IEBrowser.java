package com.sensiple.qa.config;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Class to configure and get Details for IE Browser
 */
public class IEBrowser extends DefaultBrowser implements Browser {
    @Override
    protected Capabilities createRemoteCapabilities() {
        final DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return caps;
    }

    @Override
    protected RemoteWebDriver createLocalDriver() {
        return new InternetExplorerDriver();
    }
}
