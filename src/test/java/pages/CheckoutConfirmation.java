package pages;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutConfirmation extends BasePage{



    private By finishButton = By.cssSelector(".cart_button");

    private By orderCompleteText = By.cssSelector(".complete-header");
    public CheckoutConfirmation(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean checkAddedItem(String value){
        List<WebElement> labels = driver.findElements(By.className("inventory_item_name"));
        for (int i = 0; i < labels.size(); i++) {
            // For illustration, let's assume you want to click a button when the label text is "DesiredItem"
            if (labels.get(i).getText().equals(value)) {
               return true;
            }
        }
        System.out.println("No Matching items found in product list");
        return false;
    }

    public void clickFinish(){
        doClick(finishButton,5);
    }

    public String getOrderCompleteText(){
        return getText(orderCompleteText,10);
    }
}
