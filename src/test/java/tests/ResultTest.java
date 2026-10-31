package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;

import java.io.IOException;
import java.net.URI;

public class ResultTest extends BaseTest {

    @Test
    public void firstResultOpens() throws IOException {
        loginAsValidUser();
        SearchPage search = new SearchPage(driver, wait);
        search.search("Microsoft");
        wait.until(ExpectedConditions.urlContains("/search/results"));

        // Click the first result and remember where it pointed
        String clickedHref = search.clickFirstResult();
        String expectedPath = URI.create(clickedHref).getPath().replaceAll("/+$", "");

        // The page that opened must be the one we clicked
        wait.until(ExpectedConditions.urlContains(expectedPath));
        takeScreenshot("firstResultOpens_pageOpened");
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedPath),
                "Expected the URL to contain " + expectedPath);
        Assert.assertTrue(driver.getCurrentUrl().matches(".*/(in|company|school)/.*"));
        Assert.assertFalse(driver.getTitle().isBlank());
    }
}