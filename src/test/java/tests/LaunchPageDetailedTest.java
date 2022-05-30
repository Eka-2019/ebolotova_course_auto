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
import pages.BasePage;
import pages.LaunchPage;
import pages.ReportPortalMainPage;
import service.UserCreator;
import testlistener.TestListener;
import utils.LaunchPageTestData;
import utils.LaunchPageTestDetailedData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Listeners({TestListener.class})
public class LaunchPageDetailedTest extends BaseTestingClass {

    private final Logger LOGGER = Logger.getLogger(String.valueOf(LaunchPageDetailedTest.class));

    @DataProvider
    public Object[][] getLaunchPageDetailedData() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/testdata/LaunchReportDetailedData.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
        List<LaunchPageTestDetailedData> testData = new Gson().fromJson(dataSet, new TypeToken<List<LaunchPageTestDetailedData>>() {
        }.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }

    @Test(dataProvider = "getLaunchPageDetailedData")
    public void getAllDataLaunchesTest(LaunchPageTestDetailedData data) {
        User testUser = UserCreator.withCredentialsFromProperty();
        LOGGER.info("User login and password: " + testUser);
        LOGGER.info("Url: " + System.getProperty("url"));
        LOGGER.info("Browser: " + System.getProperty("browser"));
        ReportPortalMainPage page = loginToPortalMainPage(testUser);
        LaunchPage launchPage = page.getLaunchReport();
        Assert.assertTrue(launchPage.getDemoTestDataByName(data.getTestN(), "name").contains("Demo Api Tests#"+data.getTestN()));
        Assert.assertEquals(launchPage.getDemoTestDataByName(data.getTestN(), "total"), data.getTotal());
        Assert.assertEquals(launchPage.getDemoTestDataByName(data.getTestN(), "passed"), data.getPassed());
        Assert.assertEquals(launchPage.getDemoTestDataByName(data.getTestN(), "failed"), data.getFailed());
        Assert.assertEquals(launchPage.getDemoTestDataByName(data.getTestN(),"skipped"), data.getSkipped());
        Assert.assertEquals(launchPage.getDemoTestDataByName(data.getTestN(),"pb"), data.getPb());
        Assert.assertEquals(launchPage.getDemoTestDataByName(data.getTestN(),"ab"), data.getAb());
        Assert.assertEquals(launchPage.getDemoTestDataByName(data.getTestN(),"si"), data.getSi());
        Assert.assertEquals(launchPage.getDemoTestDataByName(data.getTestN(),"ti"), data.getTi());
    }
}
