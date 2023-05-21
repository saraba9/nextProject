package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class SearchPage {
    //set up driver
    private WebDriver driver;
    //locator on page
    private  By selectedProduct= By.xpath("//*[@id=\"platform_modernisation_product_summary_A72898\"]/div/div[1]/div[1]/a/img");
    //constructor
    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }
    //function
    public void doubleClickProduct(){
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(selectedProduct)).perform();
    }
}
