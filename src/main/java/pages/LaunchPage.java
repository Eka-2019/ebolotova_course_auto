package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.util.List;

public class LaunchPage extends BasePage {

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

    private static By getDemoTestXpathByName(String dataId, String name) {
        return By.xpath("//div[contains(@data-id,'" + dataId + "')]//div[contains(@class,'launchSuiteGrid__" + name + "-col')]");
    }

    private static By getEachTestName(String i) {
        return By.xpath("//div[contains(@class, \"itemInfo__main-info--2HB9g\")]");
    }

    public List<String> getEachDemoTestData(String testNumber) {
        return Utils.getElementsTextList(driver, getEachDemoTestXpath(testNumber));
    }

    public String getDemoTestDataByName(String testNumber, String name) {
        List<String> resultList = Utils.getElementsTextList(driver, getDemoTestXpathByName(testNumber, name));
        String result = "0";
        if (!resultList.isEmpty()) {
            result = resultList.get(0);
        }
        return result;
    }
}
