package tests;

import model.User;
import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ReportPortalMainPage;
import service.UserCreator;
import testlistener.TestListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Listeners({TestListener.class})
public class EmptyDashboardTest extends BaseTestingClass {
    private final User testUser = UserCreator.withCredentialsFromProperty();
    private final Logger LOGGER = Logger.getLogger(EmptyDashboardTest.class);

    @Test
    public void testEmptyDashboard() {
        ReportPortalMainPage reportPage = loginToPortalMainPage(testUser);

        assertThat(reportPage.getEmptyDashboard(), is("You have no dashboards"));
        LOGGER.info("There is no any dashboard: " + reportPage.getEmptyDashboard());
    }
}
