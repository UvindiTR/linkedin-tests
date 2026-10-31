package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // LinkedIn's ids are random codes, so we use stable attributes.
    // The page also has hidden inputs, so we always pick the first VISIBLE match.
    private final By usernameField = By.cssSelector("input[type='email'], input[autocomplete*='username']");
    private final By passwordField = By.cssSelector("input[type='password']");
    private final By signInButton  = By.xpath("//button[normalize-space(.)='Sign in']");
    private final By errorMessage  = By.cssSelector(
            "#error-for-password, #error-for-username, [role='alert'], [aria-live='assertive']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private WebElement firstVisible(By locator) {
        return wait.until(d -> d.findElements(locator).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElse(null));
    }

    public void open() {
        driver.get("https://www.linkedin.com/login");
    }

    public void login(String email, String password) {
        firstVisible(usernameField).sendKeys(email);
        firstVisible(passwordField).sendKeys(password);
        firstVisible(signInButton).click();
    }

    public String getErrorText() {
        return wait.until(d -> d.findElements(errorMessage).stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .filter(t -> !t.isBlank())
                .findFirst()
                .orElse(null));
    }
}