package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    //set up driver
    private  WebDriver driver;
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators on page
    private By selectColorLocator = By.xpath("//*[@id=\"dk_container_Colour-1038597\"]/a");
    private By selectSizeLocator= By.xpath("//*[@id=\"dk_container_Size-C33-109\"]/a");
    private By addToBagLocator= By.linkText("הוסף לסל");
    private By shoppingBagLocator = By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[7]/div[2]/a/div");
    private By checkOutLocator= By.linkText("לקופה");

    //functions
    public void selectColor(String color){
        driver.findElement(selectColorLocator).click();
        driver.findElement(By.linkText(color)).click();
    }
    public String getSelectColor(){
       return driver.findElement(selectColorLocator).getText();
    }
    public void selectSize(String size){
        driver.findElement(selectSizeLocator).click();
         driver.findElement(By.xpath("//a[text()=\"First Size (UK ) (EU 50cm) - 50 ₪\"]"
         )).click();
    }
    public String getSelectSize(){
       return driver.findElement(selectSizeLocator).getText();
    }
    public void addToBag(){
        driver.findElement(addToBagLocator).click();
    }
    public void shoppingBagClick(){driver.findElement(shoppingBagLocator).click();}
    public void checkOut(){
        driver.findElement(checkOutLocator).click();
    }





}
