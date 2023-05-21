package testCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import pages.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

//Run tests in ascending order
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SanityTest {

    private static ExtentSparkReporter spark=  new ExtentSparkReporter("spark.html");
    private static ExtentReports report=new ExtentReports();
    private static WebDriver driver;

    LoginPage loginPage=new LoginPage(driver);
    HomePage homePage=new HomePage(driver);
    SearchPage searchPage=new SearchPage(driver);
    ProductPage productPage =new ProductPage(driver);
    PaymentPage paymentPage=new PaymentPage(driver);

    @BeforeClass
    public static void before(){
        report.attachReporter(spark);
        System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-incognito");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(Constants.URL);
    }

    //Login to the next website with an existing account
    @Test
    public void a_loginTest() throws InterruptedException, IOException, ParserConfigurationException, SAXException {
        ExtentTest loginTest=report.createTest("loginTest");
        Thread.sleep(2000);
        homePage.clickMyAccount();
        Thread.sleep(2000);
        try {
            Assert.assertEquals(Constants.TITLE_LOGIN_PAGE,driver.getTitle());
            loginTest.pass("Step 1 passed - You reached the Sign in page");
        }catch (AssertionError e){
            loginTest.fail("Step 1 failed - There was an error reaching the Sign in page", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\"+"t1"+"loginTest")).build());
        }
        Thread.sleep(2000);
        loginPage.enterEmail(getDataItem("USERNAME",0));
        loginPage.enterPassword(getDataItem("PASSWORD",0));
        loginPage.clickLogin();
        Thread.sleep(2000);
        String accountTitle=driver.getTitle();
        try {
            Assert.assertEquals(Constants.MY_ACCOUNT_TITLE,accountTitle);
            loginTest.pass("Step 2 passed - You reached the my account page");
        }catch (AssertionError e){
            loginTest.fail("Step 2 failed - There was an error reaching the my account page", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\"+"t"+"account")).build());

        }
    }
    //enter to the home page and change the language to Hebrew
    @Test
    public void b_homeTest() throws InterruptedException, IOException, ParserConfigurationException, SAXException {
        ExtentTest homeCategoryTest=report.createTest("homeCategoryTest");
        Thread.sleep(2000);
        homePage.doubleClickHomeCategory();
        Thread.sleep(4000);
        try {
            Assert.assertEquals(Constants.HOME_TITLE,driver.getTitle());
            homeCategoryTest.pass("Step 1 passed - You reached the home page");
        }catch (AssertionError e){
            homeCategoryTest.fail("Step 1 failed - There was an error reaching the home page", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\"+"t1"+"homeTest")).build());
        }
        Thread.sleep(2000);
        homePage.clickBabyCategory();
        Thread.sleep(6000);
        homePage.clickDressesInBabyCategory();
        Thread.sleep(2000);
        try {
            Assert.assertEquals(Constants.DRESSES_URL,driver.getCurrentUrl());
            homeCategoryTest.pass("Step 2 passed - You reached dresses in baby category");
        }catch (AssertionError e){
            homeCategoryTest.fail("Step 2 failed - There was an error reaching dresses in baby category", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\"+"t1"+"dresses")).build());
        }
        driver.navigate().back();
        Thread.sleep(4000);
        homePage.clickHebrewLanguageBtn();
        Thread.sleep(3000);
        try {
            Assert.assertEquals(Constants.URL_EB,driver.getCurrentUrl());
            homeCategoryTest.pass("Step 3 passed - You changed language to hebrew");
        }catch (AssertionError e){
            System.out.println(Constants.URL_EB+driver.getTitle());
            homeCategoryTest.fail("Step 3 failed - There was an error changing language to hebrew", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\"+"t1"+"HebrewLanguage")).build());
        }
    }

    // Search for a specific item
    @Test
    public void c_searchTest() throws InterruptedException, IOException, ParserConfigurationException, SAXException {
        ExtentTest searchProductTest=report.createTest("searchProductTest");
        homePage.sendKeysSearchBox(getDataItem("PRODUCTNAME",0));
        homePage.searchButtonClick();
        searchPage.doubleClickProduct();
        try {
            Assert.assertEquals(Constants.PRODUCT_TITLE,driver.getTitle());
            searchProductTest.pass("Step 1 passed - You reached the product page");
        }catch (AssertionError e){
            searchProductTest.fail("Step 1 failed - There was an error reaching the product page", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\"+"t1"+"productTest")).build());
        }



    }
    //Choose color and size and add to bag
    @Test
    public void d_productTest() throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        driver.navigate().to("https://www.next.co.il/he/style/st277292/c90721#c90721");

        ExtentTest ProductTest=report.createTest("productTest");
        productPage.selectColor(getDataItem("COLOR",0));
        Thread.sleep(3000);
        productPage.selectSize(getDataItem("SIZE",0));
        Thread.sleep(2000);
        productPage.addToBag();
        productPage.selectColor(getDataItem("COLOR",0));
        try {
            Assert.assertEquals(productPage.getSelectColor(),getDataItem("COLOR",0));
            ProductTest.pass("Step 1 passed - You selected color");
        }catch (AssertionError e){
            ProductTest.fail("Step 1 failed - There was an error selecting color", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\"+"t1"+"productTest2")).build());
        }
        productPage.selectSize(getDataItem("SIZE",0));
        try {
            Assert.assertEquals(productPage.getSelectSize(),getDataItem("SIZE",0));
            ProductTest.pass("Step 2 passed - You selected size");
        }catch (AssertionError e){
            ProductTest.fail("Step 2 failed - There was an error selecting size", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\"+"t1"+"productTest3")).build());
        }
        Thread.sleep(2000);
        productPage.addToBag();
        Thread.sleep(2000);
        productPage.shoppingBagClick();
        Thread.sleep(2000);
        productPage.checkOut();
        Thread.sleep(3000);
        try {
            Assert.assertEquals(Constants.TITLE_PAYMENT,driver.getTitle());
            ProductTest.pass("Step 1 passed - You reached the payment page");
        }catch (AssertionError e){
            ProductTest.fail("Step 1 failed - There was an error reaching the payment page", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\"+"t1"+"productTest4")).build());
        }
    }

    //Making the payment
    @Test
    public void e_paymentTest() throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        ExtentTest paymentTest=report.createTest("paymentTest");
        Thread.sleep(2000);
        paymentPage.clickCardPaymentMethod();
        try {
            Assert.assertTrue(paymentPage.isSelectedCardPaymentMethod());
            paymentTest.pass("Step 1 passed - You Selected Card Payment");
        }catch (AssertionError e){
            paymentTest.fail("Step 1 failed - There was an error Selecting Card Payment", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\"+"t1"+"selectingCardPayment")).build());
        }
        paymentPage.enterCardNumber(getDataItem("CARDNUMBER",0));
        paymentPage.enterCardholderName(getDataItem("NAME",0));
        paymentPage.enterExpiryMonth(getDataItem("MOUNTH",0));
        paymentPage.enterExpiryYear(getDataItem("YEAR",0));
        paymentPage.enterSecurityCode(getDataItem("CODE",0));
        paymentPage.clickPayNow();
        try {
            Assert.assertEquals("הזן מספר כרטיס תקין",paymentPage.getCardErrorMessage());
            paymentTest.pass("Step 2 passed -you received thr appropriate comment");
        }catch (AssertionError e){
            paymentTest.fail("Step 2 failed - There was an error receiving thr appropriate comment", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\"+"t1"+"CardErrorMessage")).build());
        }
    }


    @AfterClass
    public static void after() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("after");
        driver.quit();
        report.flush();
    }

    //functions:

    //get data from XML File - gets Key name and index of item - returns the item for the specific index of the key found
    private static String getDataItem (String keyName, int index) throws ParserConfigurationException, IOException, SAXException, SAXException, SAXException {
        File configXmlFile = new File(Constants.CONFIG_XML_FILE);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = null;
        assert dBuilder != null;
        doc = dBuilder.parse(configXmlFile);
        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(index).getTextContent();
    }


    // A function to create a screenshot:
    private static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }
}
