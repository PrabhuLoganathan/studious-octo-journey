package test;

import org.testng.annotations.Test;
import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.Cookie;
import pages.LoginPage;
import base.RetryAnalyzer;


public class LoginTests extends TestBase {

    @DataProvider(name = "logins")
    public Object[][] credentials() {
        return new Object[][] {
            {"standard_user", "secret_sauce", "success"},
            {"locked_out_user", "secret_sauce", "locked"},
            {"standard_user", "wrong_pass", "invalid"},
            {"", "secret_sauce", "empty"},
            {"standard_user", "", "empty"}
        };
    }

    @Test(dataProvider = "logins", retryAnalyzer = RetryAnalyzer.class)
    public void loginValidation(String username, String password, String expected) {
        LoginPage login = new LoginPage(driver);
        login.login(username, password);

        switch (expected) {
            case "success":
            	Assert.assertTrue(login.isInventoryPage());
                break;
            default:
            	Assert.assertTrue(login.getErrorMessage().length() > 0);
                break;
        }
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifySessionUsernameCookie() {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        Assert.assertTrue(login.isInventoryPage());
        Cookie sessionCookie = driver.manage().getCookieNamed("session-username");
        Assert.assertNotNull(sessionCookie);
        Assert.assertEquals(sessionCookie.getValue(), "standard_user");
    }
}