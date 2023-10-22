package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{

    private By firstName = By.xpath("//input[@id='first-name']");

    private By lastName = By.xpath("//input[@id='last-name']");
    private By zipCode = By.xpath("//input[@id='postal-code']");

    private By contineButton = By.cssSelector(".cart_button");
    public CheckoutPage(WebDriver webDriver) {
        super(webDriver);
    }



    public String getChekoutPageTitle() {
      return  getText(By.cssSelector(".title"),10);
    }

    public void setFirstName(String name){
        doSendKeys(firstName,name,5);
    }

    public void setLastName(String name){
        doSendKeys(lastName,name,5);
    }

    public void setZipCode(String code){
        doSendKeys(zipCode,code,5);
    }

    public void clickContinue(){
        doClick(contineButton,5);
    }

}
