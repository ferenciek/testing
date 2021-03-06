import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MainPage;

import java.util.Properties;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Marins on 08.06.2017.
 */
public class LogoutTest {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private Properties properties = PropertiesReader.readProperties();
    private String url = properties.getProperty("url");
    private String goodUser = properties.getProperty("goodUser");
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
    public void testLogout(){
        loginPage.typeNameAndPassword(goodUser, password);
        loginPage.login();
        loginPage.logout();
        assertEquals(driver.findElement(By.xpath("//*[@id=\"mainContentArea\"]//h1")).getText(),
                "What is Postcrossing?");
    }

    @After
    public void after(){
        driver.close();
    }

}
