package tests;

import model.User;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import pages.NewDashboartPopUp;
import pages.ReportPortalMainPage;
import service.UserCreator;
import utils.Utils;

import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DashboardTests extends BaseTestingClass {

    private final User testUser = UserCreator.withCredentialsFromProperty();
    private final Logger LOGGER = Logger.getLogger(String.valueOf(DashboardTests.class));

    @Test(priority = 1)
    public void testEmptyDashboard() {
        ReportPortalMainPage reportPage = loginToPortalMainPage(testUser);

        assertThat(reportPage.getEmptyDashboard(), is("You have no dashboards"));
        LOGGER.info("There is no any dashboard: " + reportPage.getEmptyDashboard());
    }

    @Test(priority = 2)
    public void testAddNewDeleteDashboard() throws URISyntaxException {
        ReportPortalMainPage reportPage = loginToPortalMainPage(testUser);
        NewDashboartPopUp popupWindow = reportPage.displayPopup();
        ReportPortalMainPage reportPageAfterPopUp = popupWindow.FillPopUp("test dashboard name", "test dashboard name");

        assertThat(reportPageAfterPopUp.getDashboardTitleName().getText(), is("TEST DASHBOARD NAME"));
        LOGGER.info(reportPageAfterPopUp.getDashboardTitleName().getText());
        assertThat(reportPageAfterPopUp.getCurrentPageURL().getHost(), is(reportPage.getCurrentPageURL().getHost()));
        LOGGER.info(reportPageAfterPopUp.getCurrentPageURL().getHost());

        reportPageAfterPopUp.deleteDashboardfromDashboardScreen()
                .confirmDashboardDeletion();
        Utils.waitForXpath(reportPageAfterPopUp.getDriver(),200, "//*[contains(text(), 'You have no dashboards')]");

        assertThat(reportPage.getEmptyDashboard(), is("You have no dashboards"));
        LOGGER.info(reportPage.getEmptyDashboard());
    }
}
