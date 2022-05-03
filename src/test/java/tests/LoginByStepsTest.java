package tests;

import model.User;
import org.apache.log4j.Logger;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.ReportLoginPage;
import pages.ReportPortalMainPage;
import service.UserCreator;
import steps.LoginPageSteps;

import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginByStepsTest extends BaseTestingClass {

    private final Logger LOGGER = Logger.getLogger(String.valueOf(LoginByStepsTest.class));

    @Test
    @Ignore
    public void testLoginViaSmallSteps() throws URISyntaxException {
        ReportLoginPage page = new ReportLoginPage(driver);
        User testUser = UserCreator.withCredentialsFromProperty();

        LoginPageSteps steps = new LoginPageSteps(page);

        steps.openLoginPage();
        steps.enterUserCredentials(testUser);
        LOGGER.info("User login and password: " + testUser.getLogin() + ", " + testUser.getPassword());
        steps.clickLoginButton();
        ReportPortalMainPage loginPage = steps.getReportPortalMainPage();

        assertThat(loginPage.getCurrentPageURL().toString(),
                is(System.getProperty("url")
                        .replaceFirst("login", testUser.getLogin() + "_personal/dashboard")));
        LOGGER.info("Test runs: " + loginPage.getCurrentPageURL().getHost());
        assertThat(loginPage.getAllDashboards().getText(), is("ALL DASHBOARDS"));
    }
}