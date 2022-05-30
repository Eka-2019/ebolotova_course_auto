package pages;

import driver.DriverManager;
import lombok.Getter;
import model.User;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.net.URISyntaxException;

@Getter
public abstract class BasePage {

    protected DriverManager dm = new DriverManager();
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final int WAIT_TIME_OUT = 20;
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(BasePage.class));
    protected String base_url;

    protected abstract BasePage openPage();

    public URI getCurrentPageURL() throws URISyntaxException {
        return new URI(driver.getCurrentUrl());
    }

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.base_url = System.getProperty("url");
    }

}
