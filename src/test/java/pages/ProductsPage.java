package pages;

import com.mycompany.Selenium_REST_TestNG.Environment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage{


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void clickButton(String button) {
        By BUTTON =  By.cssSelector(button);
        super.doClick(BUTTON, Environment.EXPLICIT_WAIT);
    }

    public void addItemByLabel(String labelName) {
        // Find all labels with class 'inventory_item_name'
        List<WebElement> labels = driver.findElements(By.className("inventory_item_name"));
        List<WebElement> btns = driver.findElements(By.cssSelector(".btn_inventory"));
        for (int i = 0; i < labels.size(); i++) {
            // For illustration, let's assume you want to click a button when the label text is "DesiredItem"
            if (labels.get(i).getText().contains(labelName)) {
                btns.get(i).click();
                break;
            }
        }
    }

    public void clickShoppingCart(){
        driver.findElement(By.id("shopping_cart_container")).click();

    }

    public String getShoppingCartCount(){
        String count = driver.findElement(By.cssSelector(".shopping_cart_badge")).getText();
        return count;
    }
}
