package testcases.web;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.Selenium_REST_TestNG.Environment;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import static org.testng.AssertJUnit.assertEquals;

public class SampleWebAutomation {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;

    public ExtentReports report;

    private CartPage cartPage;

    private CheckoutPage checkoutPage;

    private CheckoutConfirmation checkoutPageStep2;

    private ExtentTest test;

    private WebTestData webTestData;

    @BeforeSuite
    public void intializeDriver() throws IOException {
        driver = Environment.initializeDriver();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReport.html");
        report = new ExtentReports();
        report.attachReporter(sparkReporter);
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = SampleWebAutomation.class.getClassLoader().getResourceAsStream("testData.json");
        webTestData = objectMapper.readValue(inputStream, WebTestData.class);


    }



    @BeforeMethod
    public void setUp(Method method) {
        test = report.createTest(method.getAnnotation(Test.class).testName()); // create a new test in the report
        test.log(Status.INFO, method.getAnnotation(Test.class).testName()+"Test case started successfully");
    }

    @Test(priority = 1,testName = "User logs in to Sauce Demo")
    public void loginTest() throws InterruptedException {

        loginPage = new LoginPage(driver);
        loginPage.landingPage();
        loginPage.setUser(webTestData.getUsername());
        loginPage.setPASSWORD(webTestData.getPassword());
        loginPage.clickLogin();
        Thread.sleep(2000);
        test.log(Status.INFO,"User was able to successfuly login");
        test.log(Status.INFO,"Title 'Swag Labs' verified on the home page successfully ");
        assertEquals("Swag Labs",driver.getTitle());

    }

    @Test(priority = 2,testName = "User adds items to cart")
    public void addToCartTest() throws InterruptedException {
        productsPage = new ProductsPage(driver);
        productsPage.addItemByLabel(webTestData.getItemname());
        test.log(Status.INFO,"Title 'Swag Labs' verified on the home page successfully ");
        assertEquals(productsPage.getShoppingCartCount(),"1");
    }

    @Test(priority = 3,testName = "User clicks on cart icon to checkout")
    public void user_lands_in_cartPage() {
        productsPage.clickShoppingCart();
        cartPage = new CartPage(this.driver);
        System.out.println(driver.getTitle());
        test.log(Status.INFO,"Title "+driver.getTitle() +"verified on the cart page successfully ");
        assertEquals(cartPage.foundCheckoutButton(),true);
    }

    @Test(priority = 4,testName = "User clicks on checkout button")
    public void userCheckouts(){
        cartPage.checkout();;
        checkoutPage = new CheckoutPage(this.driver);
        test.log(Status.INFO,"Title "+checkoutPage.getChekoutPageTitle() +"verified on the checkout page successfully ");
        System.out.println(checkoutPage.getChekoutPageTitle());

        //loginPage.quit();
    }

    @Test(priority = 5,testName = "User enters the billing details and proceeds to the checkout ")
    public void user_enter_details_checkout(){
        checkoutPage.setFirstName(webTestData.getFirstname());
        checkoutPage.setLastName(webTestData.getLastname());
        checkoutPage.setZipCode(webTestData.getZipcode());
        test.log(Status.INFO,"User enters information and proceeds to payment page");

        checkoutPage.clickContinue();
    }

    @Test(priority = 6,testName = "User verifies the items in the cart ")
    public void user_click_continue() throws InterruptedException {
        checkoutPageStep2  = new CheckoutConfirmation(this.driver);
        assert (checkoutPageStep2.checkAddedItem(webTestData.getItemname()));
        test.log(Status.INFO,"User checks the added item in the cart while checkout");
    }


    @Test(priority = 7,testName = "User clicks finish checkout")
    public void user_click_finish(){
        checkoutPageStep2.clickFinish();
        assertEquals("Thank you for your order!",checkoutPageStep2.getOrderCompleteText());
        test.log(Status.INFO,"User completes the checkout successfully");

    }



    @AfterSuite
    public void reportClean() {
        report.flush();
        driver.quit();
    }
}

