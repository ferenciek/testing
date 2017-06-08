import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;

import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

/**
 * Created by Marins on 07.06.2017.
 */
public class FindUserTest {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private Properties properties = PropertiesReader.readProperties();
    private String url = properties.getProperty("url");
    private String goodUser = properties.getProperty("goodUser");
    private String password = properties.getProperty("password");

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        mainPage = new MainPage(driver);
        loginPage = mainPage.login();
        loginPage.typeNameAndPassword(goodUser, password);
        loginPage.login();
        driver.get("https://www.postcrossing.com/search/users");
    }

    @Test
    public void testFoundedUser(){
        WebElement search_form = driver.findElement(By.name("search_users[username]"));
        search_form.sendKeys("ferenciek");
        search_form.sendKeys(Keys.ENTER);
        WebElement founded = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("usernameContainer")));

        assertEquals(founded.getText(), "Joined: 22nd Apr, 2017");
    }

    @Test
    public void testNotFoundedUser(){
        WebElement search_form = driver.findElement(By.name("search_users[username]"));
        search_form.sendKeys("ghghghg");
        search_form.sendKeys(Keys.ENTER);
        WebElement notfounded = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("searchResults")));

        assertEquals(notfounded.getText(), "No results matched your query");
    }

    @After
    public void after(){
        driver.close();
    }
}
