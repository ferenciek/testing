

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Marina on 6/7/2017.
 */
public class LoginTest {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.postcrossing.com/");
        mainPage = new MainPage(driver);
        loginPage = mainPage.login();
    }

    @Test
    public void testAuthenticationFailureWhenProvidingBadCredentials(){
        loginPage.typeNameAndPassword("fakeuser", "fakepassword");
        loginPage.login();
        assertTrue(driver.getCurrentUrl().endsWith("login"));
    }

    @Test
    public void testAuthenticationSuccessWhenProvidingCorrectCredentials(){
        loginPage.typeNameAndPassword("kkt", "29091994");
        loginPage.login();
        assertFalse(driver.getCurrentUrl().endsWith("login"));
    }

    @After
    public void after(){
        driver.close();
    }

}
