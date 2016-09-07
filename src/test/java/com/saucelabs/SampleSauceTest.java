package com.saucelabs;

import com.saucelabs.common.SauceOnDemandAuthentication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.saucelabs.junit.Parallelized;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Demonstrates how to write a JUnit test that runs tests against Sauce Labs using multiple browsers in parallel.
 * <p/>
 * The test also includes the {@link SauceOnDemandTestWatcher} which will invoke the Sauce REST API to mark
 * the test as passed or failed.
 *
 * @author Ross Rowe
 */
@RunWith(ConcurrentParameterized.class)
public class SampleSauceTest implements SauceOnDemandSessionIdProvider {

    /**
     * Constructs a {@link SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
     * supplied by environment variables or from an external file, use the no-arg {@link SauceOnDemandAuthentication} constructor.
     */
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("Purush_12", "3b1c6846-0b29-40e3-a87a-70d93820d78a");

    /**
     * JUnit Rule which will mark the Sauce Job as passed/failed when the test succeeds or fails.
     */
    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

    /**
     * Represents the browser to be used as part of the test run.
     */
    private String browser;
    /**
     * Represents the operating system to be used as part of the test run.
     */
    private String os;
    /**
     * Represents the version of the browser to be used as part of the test run.
     */
    private String version;
    /**
     * Instance variable which contains the Sauce Job Id.
     */
    private String sessionId;

    /**
     * The {@link WebDriver} instance which is used to perform browser interactions with.
     */
    private WebDriver driver;

    /**
     * Constructs a new instance of the test.  The constructor requires three string parameters, which represent the operating
     * system, version and browser to be used when launching a Sauce VM.  The order of the parameters should be the same
     * as that of the elements within the {@link #browsersStrings()} method.
     * @param os
     * @param version
     * @param browser
     */
    public SampleSauceTest(String os, String version, String browser) {
        super();
        this.os = os;
        this.version = version;
        this.browser = browser;
    }

    /**
     * @return a LinkedList containing String arrays representing the browser combinations the test should be run against. The values
     * in the String array are used as part of the invocation of the test constructor
     */
    @ConcurrentParameterized.Parameters
    public static LinkedList browsersStrings() {
        LinkedList browsers = new LinkedList();
        browsers.add(new String[]{"Windows 8.1", "11", "internet explorer"});
        browsers.add(new String[]{"Windows 7", "10", "internet explorer"});
        browsers.add(new String[]{"OSX 10.8", "6", "safari"});
        return browsers;
    }


    /**
     * Constructs a new {@link RemoteWebDriver} instance which is configured to use the capabilities defined by the {@link #browser},
     * {@link #version} and {@link #os} instance variables, and which is configured to run against ondemand.saucelabs.com, using
     * the username and access key populated by the {@link #authentication} instance.
     *
     * @throws Exception if an error occurs during the creation of the {@link RemoteWebDriver} instance.
     */
    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) {
            capabilities.setCapability(CapabilityType.VERSION, version);
        }
/*        if (System.getenv("TUNNEL_IDENTIFIER") != null) {
            capabilities.setCapability("tunnelIdentifier", System.getenv("TUNNEL_IDENTIFIER"));
        }*/
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", "Sauce Sample Test");
        this.driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();

    }
    
    /**
     * Opens google and searches for Sauce Labs. Then asserts that the Sauce Labs' website in the top 10 hit list. 
     * @throws Exception
     */
    private static void googleSauce(WebDriver driver) {
        driver.get("http://www.google.com/");
        
        WebElement input = driver.findElement(By.name("q"));
        input.sendKeys("Sauce Labs Selenium");
        
        WebElement button = driver.findElement(By.name("btnG"));
        button.click();
        
        // Make sure saucelabs.com is under the first hits
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                List<WebElement> h3s = d.findElements(By.tagName("h3"));
                for (WebElement h3 : h3s) {
					String actual = h3.getText();
					if (actual.contains("Sauce Labs: Selenium Testing, Mobile Testing, JS")) {
						return true;
					}
				}
				return false;
            }
        });
    }

    /**
     * Test: Opens google and searches for Sauce Labs.
     * @throws Exception
     */
    @Test
    public void googleAllTheSauce() throws Exception {
    	this.googleSauce(driver);
    }

    /**
     * Test: Opens google and searches for Sauce Labs. Then pages to page two of the search results.
     * @throws Exception
     */
    @Test
    public void googleSauceAndPageAhead() throws Exception {
    	this.googleSauce(driver);
        
    	WebElement pager = driver.findElement(By.id("pnnext"));
    	pager.click();
    	
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
				String str = "'";
				try {
				    WebElement results = driver.findElement(By.id("resultStats"));
					str = results.getText();
				} catch (Exception e) {
					str = "";
				}

				return str.contains("Page 2 of about ");
            }
        });
    }
    
    /**
     * Closes the {@link WebDriver} session.
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    /**
     *
     * @return the value of the Sauce Job id.
     */
    @Override
    public String getSessionId() {
        return sessionId;
    }
}
