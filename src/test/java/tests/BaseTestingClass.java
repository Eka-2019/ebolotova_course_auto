package tests;

import driver.DriverManager;
import io.qameta.allure.Step;
import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.ReportLoginPage;
import pages.ReportPortalMainPage;
import testlistener.TestListener;

@Listeners({TestListener.class})
public class BaseTestingClass {

    protected DriverManager dm = new DriverManager();
    protected WebDriver driver;

    @BeforeMethod
    public void setUp(ITestContext context) {
        driver = dm.getDriver();
        context.setAttribute("driver", driver);
    }

    @Step("Login to Report Portal as normal User")
    public ReportPortalMainPage loginToPortalMainPage(User testUser) {
        return new ReportLoginPage(driver)
                .openPage()
                .loginToDashboardPage(testUser);
    }

    public ReportPortalMainPage loginToPortalMainPage_new(User testUser) {
        return new ReportLoginPage(driver)
                .openPage()
                .loginToDashboardPage(testUser);
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        if (driver != null) {
            dm.closeDriver();
            driver = null;
        }
    }
}
