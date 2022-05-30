package tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import model.User;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LaunchPage;
import pages.ReportPortalMainPage;
import service.UserCreator;
import testlistener.TestListener;
import utils.LaunchPageTestData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Listeners({TestListener.class})
public class LaunchPageTest extends BaseTestingClass {
    private final Logger LOGGER = Logger.getLogger(String.valueOf(LaunchPageTest.class));

    @DataProvider
    public Object[][] getLaunchPageData() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/testdata/LaunchReportData.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
        List<LaunchPageTestData> testData = new Gson().fromJson(dataSet, new TypeToken<List<LaunchPageTestData>>() {
        }.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }

    @Test (dataProvider = "getLaunchPageData")
    public void getAllDataLaunchesTest(LaunchPageTestData data) {
        User testUser = UserCreator.withCredentialsFromProperty();
        LOGGER.info("User login and password: " + testUser);
        LOGGER.info("Url: " + System.getProperty("url"));
        LOGGER.info("Browser: " + System.getProperty("browser"));
        ReportPortalMainPage page = loginToPortalMainPage(testUser);
        LaunchPage launchPage = page.getLaunchReport();
        List<String> actualTest1 = launchPage.getEachDemoTestData(data.getTestN());
        Assert.assertEquals(data.getExpected(), actualTest1);
    }
}
