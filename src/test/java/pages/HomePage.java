package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
    //set up driver
    private WebDriver driver;

    //locators on page
    private By myAccountLocator = By.linkText("My Account");
    private By homeCategoryOnBannerLocator = By.xpath("//*[@id=\"meganav-link-6\"]/div");
    private By babyCategoryOnBannerLocator = By.id("meganav-link-2");
    private By hebrewLanguageBtnLocator = By.xpath("//*[@id=\"platform_modernisation_footer\"]/footer/div/div[2]/div/div[1]/div[2]/div/div[2]/a/strong");
    private By dressesLocator =  By.xpath("//a[text()=\"Dresses\"]");
    private By searchBoxLocator =  By.name("header-big-screen-search-box");
    private By searchButtonLocator =  By.xpath("//*[@id=\"header-search-form\"]/button");


    //constructor
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    //functions
    public void clickMyAccount(){
        driver.findElement(myAccountLocator).click();
    }
    public void doubleClickHomeCategory(){
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(homeCategoryOnBannerLocator)).perform();
    }
    public void clickBabyCategory(){
        Actions action = new Actions(driver);
        action.doubleClick(driver.findElement(babyCategoryOnBannerLocator)).perform();
    }
    public void clickDressesInBabyCategory(){
        driver.findElement(dressesLocator).click();
    }
    public void clickHebrewLanguageBtn(){
        driver.findElement(hebrewLanguageBtnLocator).click();
    }
    public void sendKeysSearchBox(String productName) {driver.findElement(searchBoxLocator).sendKeys(productName);}
    public void searchButtonClick() { driver.findElement(searchButtonLocator).clear();}
}
