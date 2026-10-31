package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchTest extends BaseTest {

    @Test
    public void searchShowsResults() {
        loginAsValidUser();
        new SearchPage(driver, wait).search("software testing");
        wait.until(ExpectedConditions.urlContains("/search/results"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/search/results"));
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("software testing"));
    }
}