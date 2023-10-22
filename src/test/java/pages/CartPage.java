package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{
    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean foundCheckoutButton(){
       if( driver.findElement(By.cssSelector(".checkout_button")) !=null){
           return true;
       }
       return false;
    }

    public void checkout(){
        driver.findElement(By.cssSelector(".checkout_button")).click();
    }
}
