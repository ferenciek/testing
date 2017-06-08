import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Marins on 08.06.2017.
 */
public class ActionBot {
    private final WebDriver driver;

    ActionBot(WebDriver driver) {
        this.driver = driver;
    }

    void click(By locator) {
        driver.findElement(locator).click();
    }

    void type(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text );
    }
}
