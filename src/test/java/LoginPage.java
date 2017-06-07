import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Marins on 07.06.2017.
 */
public class LoginPage extends PageObject {
    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//div[@id='loginContainer']//button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void typeNameAndPassword(String name, String password){
        this.username.sendKeys(name);
        this.password.sendKeys(password);
    }

    public void login(){
        loginButton.click();
    }
}
