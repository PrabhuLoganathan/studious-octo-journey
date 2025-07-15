package test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.RetryAnalyzer;
import base.TestBase;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class E2ETests extends TestBase {

	@Test
	public void verifyAddToCartFlow() {
		LoginPage login = new LoginPage(driver);
		login.login("standard_user", "secret_sauce");
		// Work Around to handle Chrome Password Manager issue in MAC
		driver.navigate().back();
		driver.navigate().back();
		// to login Again
		driver.navigate().forward();
		driver.navigate().forward();
		InventoryPage inventory = new InventoryPage(driver);
		inventory.addToCart("Sauce Labs Backpack");
		inventory.goToCart();
		CartPage cart = new CartPage(driver);
		Assert.assertTrue(cart.isProductInCart("Sauce Labs Backpack"), "Product not found in cart.");

	}

	@Test
	public void verifyLogoutFunctionality() {
		LoginPage login = new LoginPage(driver);
		login.login("standard_user", "secret_sauce");
		// Work Around to handle Chrome Password Manager issue in MAC
		driver.navigate().back();
		driver.navigate().back();
		// to login Again
		driver.navigate().forward();
		driver.navigate().forward();
		InventoryPage inventory = new InventoryPage(driver);
		inventory.logout();
		Assert.assertTrue(login.isLoginPageDisplayed(), "User was not logged out properly.");
	}

}
