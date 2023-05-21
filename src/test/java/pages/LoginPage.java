package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    //set up driver
    private WebDriver driver;
    //locators
    private By inputEmailLocator = By.id("EmailOrAccountNumber");
    private By inputPasswordLocator = By.id("Password");
    private By loginLocator = By.id("SignInNow");

    //constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    //functions
    public void enterEmail (String email){
        driver.findElement(inputEmailLocator).sendKeys(email);
    }
    public void enterPassword(String password){
        driver.findElement(inputPasswordLocator).sendKeys(password);
    }
    public void clickLogin(){
        driver.findElement(loginLocator).click();
    }
}
