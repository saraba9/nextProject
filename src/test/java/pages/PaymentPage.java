package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage {
    //set up driver
    private WebDriver driver;
    //locators on page
    private By cardRadioLocator = By.id("PaymentId");
    private By cardChoiceLocator = By.id("card_option");
    private By CardholderNameLoacator = By.id("cardholderName");
    private By cardNumberBoxLocator = By.cssSelector("input#cardNumber");
    private By expiryMonthLocator = By.cssSelector("input#expiryMonth");
    private By expiryYearLocator = By.cssSelector("input#expiryYear");
    private By securityCodeLocator = By.cssSelector("input#securityCode");
    private By payNowBtnLocator = By.xpath("//*[@id=\"submitButton\"]");
    private By cardErrorMessageLocator = By.cssSelector("small#cardNumber-hint");
    //constructor
    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }
    //functions
    public void clickCardPaymentMethod(){
        driver.findElement(cardChoiceLocator).click();
    }
    public boolean isSelectedCardPaymentMethod(){
        return driver.findElement(cardRadioLocator).isSelected();
    }
    public void enterCardNumber(String cardNumber){
        driver.findElement(cardNumberBoxLocator).sendKeys(cardNumber);
    }
    public void enterCardholderName(String cardholderName){
        driver.findElement(CardholderNameLoacator).sendKeys(cardholderName);
    }
    public void enterExpiryMonth(String expiryMonth){
        driver.findElement(expiryMonthLocator).sendKeys(expiryMonth);
    }
    public void enterExpiryYear(String expiryYear){
        driver.findElement(expiryYearLocator).sendKeys(expiryYear);
    }
    public void enterSecurityCode(String securityCode){driver.findElement(securityCodeLocator).sendKeys(securityCode);}
    public void clickPayNow(){
        driver.findElement(payNowBtnLocator).click();
    }
    public String getCardErrorMessage(){
        return driver.findElement(cardErrorMessageLocator).getText();
    }
}
