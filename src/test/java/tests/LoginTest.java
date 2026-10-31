package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void validLogin() {
        LoginPage login = new LoginPage(driver, wait);
        login.open();
        login.login(System.getenv("LINKEDIN_EMAIL"), System.getenv("LINKEDIN_PASSWORD"));
        wait.until(ExpectedConditions.urlContains("/feed"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/feed"));
    }

    @Test
    public void invalidPassword() {
        LoginPage login = new LoginPage(driver, wait);
        login.open();
        login.login(System.getenv("LINKEDIN_EMAIL"), "WrongPass123!");
        Assert.assertFalse(login.getErrorText().isEmpty());
    }
}