import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

public class Google implements SauceOnDemandSessionIdProvider {

    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("Purush_12", "3b1c6846-0b29-40e3-a87a-70d93820d78a");
    
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

     
    public static void set() throws Exception {
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
        //wd = (FirefoxDriver) wd.switchTo().window("Windows 10", "Firefox 48");
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
