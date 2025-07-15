package utils;

import org.openqa.selenium.WebElement;

public class ElementUtils {
    public static String getText(WebElement el) {
        return el.getText().trim();
    }

    public static String getValue(WebElement el) {
        return el.getAttribute("value");
    }
}