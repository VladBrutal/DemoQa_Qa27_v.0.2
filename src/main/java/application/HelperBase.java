package application;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
        }
    }


    public void click(By locator) {
        wd.findElement(locator).click();

    }


    public void typeByElement(WebElement element, String text) {

        element.click();
        element.clear();
        element.sendKeys(text);
    }


    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }

    public void hideFooter(){
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('footer').style.display='none';");
    }

    public void selectByIndex(By locator, String option) {
        Integer index = Integer.parseInt(option);
        new Select(wd.findElement(locator)).selectByIndex(index);

    }

    public void selectByValue(By locator, String value) {
        new Select(wd.findElement(locator)).selectByValue(value);

    }
}
