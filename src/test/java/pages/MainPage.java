package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.LoginPage;

/**
 * Created by Marins on 07.06.2017.
 */
public class MainPage extends PageObject {
    @FindBy(className = "button")
    private WebElement loginButton;

    public MainPage(WebDriver driver){
        super(driver);
    }

    public LoginPage login(){
        loginButton.click();
        return new LoginPage(driver);
    }

}
