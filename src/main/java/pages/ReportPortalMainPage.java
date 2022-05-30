package pages;

import io.qameta.allure.Step;
import lombok.Getter;
import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

@Getter
public class ReportPortalMainPage extends BasePage {

    @FindBy(xpath = "//p[contains(@class, \"emptyDashboards__empty-dashboard-headline--\")]")
    private WebElement noDashboardText;

    @FindBy(xpath = "//a[contains(@class, \"pageBreadcrumbs__page\")]")
    private WebElement allDashBoardTitle;

    @FindBy(xpath = "//button[contains(@class, \"ghostButton\")][1]")
    private WebElement buttonAddNewDashboard;

    @FindBy(xpath = "//span[contains(@title, \"All Dashboards\")]")
    private WebElement allDashboards;

    @FindBy(xpath = "//span[contains(@title, \"test dashboard name\")]")
    private WebElement dashboardTitleName;

    @FindBy(xpath = "//a[contains(@class, \"gridCell\") and contains(text(), \"test dashboard name\")]")
    private WebElement dashboardName;

    @FindBy(xpath = "//i[contains(@class, \"icon-delete\")][1]")
    private WebElement deleteDashboardIcon;

    @FindBy(xpath = "//div[contains(@class, \"dashboardItemPage__buttons-container--28m3K\")]//button[3]//span")
    private WebElement deleteDashboardButton;

    @FindBy(xpath = "//button[contains(@type, \"button\") and contains(@class, \"bigButton__color-tomato\")]")
    private WebElement deleteDashboardButtonConfirmation;

    @FindBy(xpath = "//a[contains(@class, \"gridCell\")]")
    private WebElement dashboardRowName;

    public ReportPortalMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public ReportPortalMainPage openPage() {
        driver.navigate().to(base_url.replaceFirst("login",System.getProperty("login") + "_personal/dashboard"));
        return this;
    }

    @Step("Get Empty Dahsboard")
    public String getEmptyDashboard() {
        return noDashboardText.getText();
    }

    @Step("Get New Dashboard Pop up window")
    public NewDashboartPopUp displayPopup() {
        buttonAddNewDashboard.click();
        return new NewDashboartPopUp(driver);
    }

    @Step("Delete Dashboard from Dashboard Screen")
    public ReportPortalMainPage deleteDashboardfromDashboardScreen(){
        deleteDashboardButton.click();
        Utils.waitForXpath(driver, 200,"//button[contains(@type, \"button\") and contains(@class, \"bigButton__color-tomato\")]");
        return this;
    }

    @Step("Confirm Dashboard Deletion")
    public ReportPortalMainPage confirmDashboardDeletion() {
        deleteDashboardButtonConfirmation.click();
        return this;
    }

    @Step("Get Dashboard Name")
    public String getDashboardRowNameInRow(){
        return dashboardRowName.getText();
    }

    @Step("Get particular Dashboard")
    public ReportPortalMainPage getParticularDashboardPage(){
        dashboardName.click();
        return this;
    }

    public ReportPortalMainPage loginToPortalMainPage(User testUser) {
        driver = dm.getDriver();
        return new ReportLoginPage(driver)
                .openPage()
                .loginToDashboardPage(testUser);
    }
}
