package com.sensiple.qa.config;

import com.sensiple.qa.utils.Directory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import static com.sensiple.qa.utils.Utils.isGrid;

import java.net.URL;
import java.util.concurrent.TimeUnit;


/**
 * Default implementation of Browser interface. Subclasses should provide the
 * desired capabilities to the constructor. These are used when creating drivers
 * for a Selenium Grid.
 */
public abstract class DefaultBrowser implements Browser {
    private RemoteWebDriver driver;


    public String getBrowserName() {
        return getDriver().getCapabilities().getBrowserName();
    }

    public String getVersion() {
        return getDriver().getCapabilities().getVersion();
    }

    public RemoteWebDriver getDriver() {
        initDriver();
        return this.driver;
    }

    private synchronized void initDriver() {
        if (this.driver != null) {
            return;
        }

        DriverPathConfig.setDriverPaths();
        this.driver = createDriver();
        setTimeouts();
    }


    abstract protected Capabilities createRemoteCapabilities();

    abstract protected RemoteWebDriver createLocalDriver();


    private RemoteWebDriver createDriver() {
        return isGrid() ? createRemoteDriver() : createLocalDriver();
    }

    private RemoteWebDriver createRemoteDriver() {
        RemoteWebDriver driver = null;
        int tries = 5;
        while (tries > 0 && driver == null) {
            --tries;
            try {
                driver = createRemoteDriverSimple();
            } catch (final UnreachableBrowserException unreachable) {
                driver = null;
                printMessageAndWait(unreachable);
            }
        }
        if (driver == null) {
            throw new UnreachableBrowserException("Failed to create RemoteWebDriver; too many UnreachableBrowserExceptions");
        }
        return driver;
    }

    private RemoteWebDriver createRemoteDriverSimple() {
        return new RemoteWebDriver(createUrlOfGrid(), createRemoteCapabilities());
    }

    private static URL createUrlOfGrid() {
        try {
            return new URL(Directory.GRID_IP);
        } catch (final Throwable ex) {
            throw new IllegalStateException("Invalid Selenium Grid URL.", ex);
        }
    }

    private static void printMessageAndWait(final Throwable ex) {
        System.err.println("Received the following exception while trying to create RemoteWebDriver:");
        ex.printStackTrace();

        System.err.println("Retrying...");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void setTimeouts() {
        final WebDriver.Timeouts timeouts = this.driver.manage().timeouts();

        timeouts.implicitlyWait(67, TimeUnit.SECONDS);
        timeouts.pageLoadTimeout(67, TimeUnit.SECONDS);
        timeouts.setScriptTimeout(67, TimeUnit.SECONDS);
    }
}
