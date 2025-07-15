package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductInCart(String productName) {
        return driver.findElements(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']")).size() > 0;
    }
}
