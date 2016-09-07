import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class GitHub {
    public static void main(String[] args) throws Exception {
        FirefoxDriver wd;
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("https://github.com/");
        wd.findElement(By.linkText("Sign in")).click();
        wd.findElement(By.id("login_field")).click();
        wd.findElement(By.id("login_field")).clear();
        wd.findElement(By.id("login_field")).sendKeys("Purushoth88");
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys("October@12");
        wd.findElement(By.name("commit")).click();
        wd.findElement(By.linkText("Read the guide")).click();
        wd.findElement(By.linkText("Start a project")).click();
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
