package base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected void takeScreenshot(String name) throws IOException {
        File shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.createDirectories(Path.of("screenshots"));
        Files.copy(shot.toPath(), Path.of("screenshots/" + name + ".png"),
                StandardCopyOption.REPLACE_EXISTING);
    }

    protected void loginAsValidUser() {
        LoginPage login = new LoginPage(driver, wait);
        login.open();
        login.login(Config.email(), Config.password());
        wait.until(ExpectedConditions.urlContains("/feed"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
        if (driver != null) {
            driver.quit();
        }
    }
}