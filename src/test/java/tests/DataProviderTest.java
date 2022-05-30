package tests;

import model.User;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import pages.ReportPortalMainPage;

import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DataProviderTest extends BaseTestingClass {
    private final Logger LOGGER = Logger.getLogger(String.valueOf(DataProviderTest.class));

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{"https://rp.epam.com/ui/#login", "eka_2022", "Qweasd_123?"}};
    }

    @Test(dataProvider = "data-provider")
    public void testMethod(String data) {
        System.out.println("Data is: " + data);
    }

    @Test(dataProvider = "data-provider")
    public void testLoginWithDataProvider(String url, String login, String password) throws URISyntaxException {
        User testUser = new User(login, password);
        System.setProperty("url", url);
        LOGGER.info("User login and password: " + login + ", " + password);
        ReportPortalMainPage loginPage = loginToPortalMainPage(testUser);

        assertThat(loginPage.getCurrentPageURL().toString(), is(url.replaceFirst("login", login + "_personal/dashboard")));
        LOGGER.info("Tests run: " + loginPage.getCurrentPageURL().getHost());
        assertThat(loginPage.getAllDashboards().getText(), is("ALL DASHBOARDS"));
        LOGGER.info("Header of page: " + loginPage.getAllDashboards().getText());
    }
}
