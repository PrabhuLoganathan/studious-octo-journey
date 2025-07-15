package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {
    public static String getText(WebElement el) {
        return el.getText().trim();
    }

    public static String getValue(WebElement el) {
        return el.getAttribute("value");
    }
    
    public static void navigateBackAndForward(WebDriver driver) {
		// Work Around to handle Chrome Password Manager issue in MAC
		driver.navigate().back();
		driver.navigate().back();
		// to login Again
		driver.navigate().forward();
		driver.navigate().forward();
    }
}