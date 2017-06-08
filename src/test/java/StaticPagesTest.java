import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Marins on 08.06.2017.
 */
public class StaticPagesTest {
    private WebDriver driver;
    private Properties properties = PropertiesReader.readProperties();
    private String about = properties.getProperty("staticTest.about");
    private String tos = properties.getProperty("staticTest.tos");
    private String privacy = properties.getProperty("staticTest.privacy");

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testAbout(){
        driver.get(about);
        assertEquals(driver.findElement(By.xpath("//*[@id=\"mainContentArea\"]/h1")).getText(),
                "About Postcrossing");
    }

    @Test
    public void testTos(){
        driver.get(tos);
        assertEquals(driver.findElement(By.xpath("//*[@id=\"mainContentArea\"]//h1")).getText(),
                "Terms Of Service for Postcrossing.com");
    }

    @Test
    public void testPrivacy(){
        driver.get(privacy);
        assertEquals(driver.findElement(By.xpath("//*[@id=\"mainContentArea\"]//h1")).getText(),
                "Postcrossing Privacy Policy");
    }

    @Test
    public void testHistory(){
        driver.get(about);
        driver.get(tos);
        driver.get(privacy);
        driver.navigate().back();
        assertTrue(driver.getCurrentUrl().endsWith("tos"));
        driver.navigate().back();
        assertTrue(driver.getCurrentUrl().endsWith("about"));
    }

    @After
    public void after(){
        driver.close();
    }
}
