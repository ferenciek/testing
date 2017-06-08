import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SignupPage;

import java.util.Properties;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Marins on 08.06.2017.
 */
public class RegistrationEmailTest {
    private WebDriver driver;
    private Properties properties = PropertiesReader.readProperties();
    private String url = properties.getProperty("signup");
    private String username = properties.getProperty("goodUser");
    private String wrongEmail = properties.getProperty("wrongEmail");
    private String password = properties.getProperty("password");

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @Test
    public void testIncorrectEmailRegistraton(){
        SignupPage signupPage = new SignupPage(driver);
        signupPage.enterCredentials(username, wrongEmail, password);
        signupPage.enterAddress("Hungary", "Budapest");
        signupPage.singUp();
        assertEquals(driver.findElement(By.xpath("//*[@id=\"signupForm\"]/fieldset[1]/div[2]/div/ul/li")).getText(),
                "Email address is not valid.");
    }

    @After
    public void after(){
        driver.close();
    }
}
