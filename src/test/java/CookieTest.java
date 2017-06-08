import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Marins on 08.06.2017.
 */
public class CookieTest {
    private WebDriver driver;
    private Properties properties = PropertiesReader.readProperties();
    private String url = properties.getProperty("url");

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    @Test
    public void testAddCookie(){
        Set<Cookie> cookies = driver.manage().getCookies();
        int initial_size = cookies.size();
        Cookie testCookie = new Cookie("test", "1994");
        driver.manage().addCookie(testCookie);
        Cookie tempCookie = driver.manage().getCookieNamed("test");
        assertEquals(tempCookie.getValue(), "1994");
        assertEquals(driver.manage().getCookies().size(), initial_size + 1);
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
