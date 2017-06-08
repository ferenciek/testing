package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Marins on 08.06.2017.
 */
public class SignupPage extends PageObject{

    @FindBy(name = "signup[sf_guard_user][username]")
    private WebElement username;

    @FindBy(name = "signup[email]")
    private WebElement email;

    @FindBy(name = "signup[sf_guard_user][password]")
    private WebElement password;

    @FindBy(name = "signup[country_id]")
    private WebElement country;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(xpath = "//*[@id=\"signupForm\"]//button")
    private WebElement signupButton;

    public SignupPage(WebDriver driver){
        super(driver);
    }

    public void enterCredentials(String username, String email, String password){
        this.username.sendKeys(username);
        this.email.sendKeys(email);
        this.password.sendKeys(password);
    }

    public void enterAddress(String country, String city){
        this.country.sendKeys(country);
        this.city.sendKeys(city);
        this.city.submit();
    }

    public void singUp(){
        signupButton.click();
    }
}
