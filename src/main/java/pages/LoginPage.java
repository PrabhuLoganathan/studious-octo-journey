package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static utils.ElementUtils.*;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.id("login-button");
    private By error = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
        
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    public boolean isInventoryPage() {
        return driver.getCurrentUrl().contains("inventory.html");
    }


    public String getErrorMessage() {
        return getText(driver.findElement(error));
    }

    
    public boolean isLoginPageDisplayed() {
        return driver.findElements(loginBtn).size() > 0;
    }
}