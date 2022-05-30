package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.util.List;

public class LaunchPage extends BasePage{
    public LaunchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public LaunchPage openPage() {
        driver.navigate().to(base_url.replaceFirst("login", System.getProperty("login") + "_personal/dashboard"));
        return this;
    }

    private static By getEachDemoTestXpath(String i) {
        return By.xpath("//div[contains(@class,'gridRow__grid-row-wrapper') and @data-id='" + i + "']//div[contains(@class,'statistics')]");
    }

    public List<String> getEachDemoTestData(String testNumber) {
        return Utils.getElementsTextList(driver, getEachDemoTestXpath(testNumber));
    }
}
