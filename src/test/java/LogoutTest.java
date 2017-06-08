import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Marins on 08.06.2017.
 */
public class LogoutTest {
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
    public void testLogout(){
        loginPage.typeNameAndPassword("kkt", "29091994");
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
