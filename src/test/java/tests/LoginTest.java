package tests;

import model.User;
import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ReportPortalMainPage;

import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginTest extends BaseTestingClass {
    private final Logger LOGGER = Logger.getLogger(String.valueOf(LoginTest.class));

    @Test
    @Parameters({"url", "login", "password"})
    public void testLogin(String url, String login, String password) throws URISyntaxException {
        User testUser = new User(login, password);
        System.setProperty("url", url);
        LOGGER.info("User login and password: " + login + ", " + password);
        ReportPortalMainPage loginPage = loginToPortalMainPage(testUser);

        assertThat("https://"+loginPage.getCurrentPageURL().getHost()+"/ui/#login", is(url));
        //assertThat(loginPage.getCurrentPageURL().toString(), is(url.replaceFirst("login", login + "_personal/dashboard")));
        LOGGER.info("Tests run: "+ loginPage.getCurrentPageURL().getHost());
        assertThat(loginPage.getAllDashboards().getText(), is("ALL DASHBOARDS"));
        LOGGER.info("Header of page: " + loginPage.getAllDashboards().getText());
    }
}