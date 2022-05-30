package tests;

import model.User;
import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LaunchPage;
import pages.ReportPortalMainPage;
import service.UserCreator;

import java.util.Arrays;
import java.util.List;


//@RunWith(JUnitParallelized.class)
//@RunWith(Parameterized.class)
public class LaunchPageTest extends BaseTestingClass {
    private final Logger LOGGER = Logger.getLogger(String.valueOf(LaunchPageTest.class));

    public LaunchPageTest(String testN, List<String> expected) {
        super(testN, expected);
    }

    @Parameters({"testN", "expected"})
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"1", Arrays.asList("10", "1", "9", "1", "8", "5")},
                {"2", Arrays.asList("15", "5", "9", "1", "1", "5", "4", "8")},
                {"3", Arrays.asList("20", "10", "8", "2", "4", "4", "10")},
                {"4", Arrays.asList("25", "20", "5", "4", "1", "2")},
                {"5", Arrays.asList("30", "30")}
        });
    }

    @Test
    public void getAllDataLaunchesTest() {
        User testUser = UserCreator.withCredentialsFromProperty();
        LOGGER.info("User login and password: " + testUser);
        LOGGER.info("Url: " + System.getProperty("url"));
        LOGGER.info("Browser: " + System.getProperty("browser"));
        ReportPortalMainPage page = loginToPortalMainPage(testUser);
        LaunchPage launchPage = page.getLaunchReport();
       // List<String> actualTest1 = launchPage.getEachDemoTestData(testN);
       // Assert.assertEquals(expected, actualTest1);
    }


}

