package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
	private WebDriver driver;

	public InventoryPage(WebDriver driver) {
		this.driver = driver;
	}

	public void addToCart(String productName) {

		String formattedName = productName.toLowerCase().replace(" ", "-");
		By addToCartBtn = By.id("add-to-cart-" + formattedName);

		driver.findElement(addToCartBtn).click();
	}

	public void goToCart() {
		driver.findElement(By.className("shopping_cart_link")).click();
	}

	public void logout() {
		driver.findElement(By.id("react-burger-menu-btn")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("logout_sidebar_link")).click();
	}
}
