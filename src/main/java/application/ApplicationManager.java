package application;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    HelperForm studentForm;
    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }


    public void init() {
       // wd = new ChromeDriver();
        if (browser.equals(BrowserType.CHROME)){
            wd = new ChromeDriver();
        }else if (browser.equals(BrowserType.FIREFOX)){
            wd = new FirefoxDriver();
        }

        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.navigate().to("https://demoqa.com/");
        studentForm = new HelperForm(wd);

        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('footer').style.display='none';");
    }

    public HelperForm form() {
        return studentForm;
    }

    public void stop() {
        wd.quit();
    }
}
