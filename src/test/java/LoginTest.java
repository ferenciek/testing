

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

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.postcrossing.com/");
    }

    @Test
    public void testAuthenticationFailureWhenProvidingBadCredentials(){
        driver.findElement(By.className("button")).click();
        driver.findElement(By.id("username")).sendKeys("fakeuser");
        driver.findElement(By.id("password")).sendKeys("fakepassword");
        driver.findElement(By.xpath("//div[@id='loginContainer']//button")).click();

        assertTrue(driver.getCurrentUrl().endsWith("login"));
    }

    @Test
    public void testAuthenticationSuccessWhenProvidingCorrectCredentials(){
        driver.findElement(By.className("button")).click();
        driver.findElement(By.id("username")).sendKeys("kkt");
        driver.findElement(By.id("password")).sendKeys("29091994");
        driver.findElement(By.xpath("//div[@id='loginContainer']//button")).click();

        assertFalse(driver.getCurrentUrl().endsWith("login"));
    }

    @After
    public void after(){
        driver.close();
    }

}
