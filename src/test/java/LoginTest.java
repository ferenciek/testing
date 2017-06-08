import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MainPage;

import java.util.Properties;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Marina on 6/7/2017.
 */
public class LoginTest {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private Properties properties = PropertiesReader.readProperties();
    private String url = properties.getProperty("url");
    private String goodUser = properties.getProperty("goodUser");
    private String wrongUser = properties.getProperty("wrongUser");
    private String password = properties.getProperty("password");

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        mainPage = new MainPage(driver);
        loginPage = mainPage.login();
    }

    @Test
    public void testIncorrectLogin(){
        loginPage.typeNameAndPassword(wrongUser, password);
        loginPage.login();
        assertTrue(driver.getCurrentUrl().endsWith("login"));
    }

    @Test
    public void testCorrectLogin(){
        loginPage.typeNameAndPassword(goodUser, password);
        loginPage.login();
        assertFalse(driver.getCurrentUrl().endsWith("login"));
    }

    @After
    public void after(){
        driver.close();
    }

}
