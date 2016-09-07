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
import java.util.LinkedList

public class Google {
    public static void main(String[] args) throws Exception {
        RemoteWebDriver wd;
        DesiredCapabilities caps = DesiredCapabilities.firefox();
            caps.setCapability("name", "Google");
        wd = new RemoteWebDriver(new URL("http://Purush_12:3b1c6846-0b29-40e3-a87a-70d93820d78a@ondemand.saucelabs.com:80/wd/hub"),
            caps);
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("https://www.google.co.in/?gfe_rd=cr&ei=SKTGV4LWNouCvAT2iYEQ");
        wd.findElement(By.id("lst-ib")).click();
        wd.findElement(By.id("lst-ib")).clear();
        wd.findElement(By.id("lst-ib")).sendKeys("git");
        wd.findElement(By.xpath("//div[@id='sbse0']//div[.='github']")).click();
        wd.findElement(By.linkText("How people build software GitHub")).click();
        wd.findElement(By.linkText("Sign in")).click();
        wd.findElement(By.id("login_field")).click();
        wd.findElement(By.id("login_field")).clear();
        wd.findElement(By.id("login_field")).sendKeys("Purushoth88");
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys("October@12");
        wd.findElement(By.name("commit")).click();
        wd.findElement(By.xpath("//ul[@id='user-links']/li[3]/a/span")).click();
        wd.findElement(By.xpath("//ul[@id='user-links']/li[3]/a/span")).click();
        wd.findElement(By.xpath("//form[@class='logout-form']/button")).click();
        wd = (FirefoxDriver) wd.switchTo().window("Windows 10", "Firefox 48");
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
