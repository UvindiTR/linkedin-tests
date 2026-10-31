package base;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SetupCheckTest extends BaseTest {

    @Test
    public void browserOpens() {
        driver.get("https://www.google.com");
        Assert.assertTrue(driver.getTitle().contains("Google"));
    }
}