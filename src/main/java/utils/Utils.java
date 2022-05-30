package utils;

import driver.Config;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private WebDriver driver;

    public static void waitForXpath(WebDriver driver, int timeOut, String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public static List<WebElement> getElements(WebDriver driver, By locator) {
        return driver.findElements(locator);
    }

    public static List<String> getElementsTextList(WebDriver driver, By locator) {
        List<String> eleTextList = new ArrayList<>();
        List<WebElement> eleList = getElements(driver, locator);
        for (WebElement e : eleList) {
            if (!e.getText().isEmpty()) {
                eleTextList.add(e.getText());
            }
        }

        return eleTextList;
    }
}
