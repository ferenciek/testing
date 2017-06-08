import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import pages.LoginPage;
import pages.MainPage;

import java.util.Properties;

import static java.util.concurrent.TimeUnit.SECONDS;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Marins on 08.06.2017.
 */
public class UploadFileTest {
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
    public void testUploadAvatar() {
        ActionBot actionBot = new ActionBot(driver);
        loginPage.typeNameAndPassword(goodUser, password);
        loginPage.login();
        driver.get("https://www.postcrossing.com/edit/avatar");
        actionBot.type(By.name("upload_avatar[avatar]"), "C:\\Users\\Marins\\IdeaProjects\\testing\\src\\test.png");
        actionBot.click(By.xpath("//*[@id=\"uploadAvatarForm\"]/div[2]/div/button[1]"));

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, SECONDS)
                .pollingEvery(1, SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement text = wait.until(driver -> driver.findElement(By.xpath("//*[@id=\"mainContentArea\"]/p[2]")));

        assertEquals(text.getText(), "Please select the square image to use for your avatar. When you are happy with the result shown on the Preview, choose Crop.");


    }

    @After
    public void after(){
        driver.close();
    }
}
