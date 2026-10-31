package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // LinkedIn's class names are random, so we use stable attributes
    private final By searchBox = By.cssSelector(
            "input[aria-label='Search'], input[placeholder='Search'], input[role='combobox']");

    // First result that is a person (/in/), company (/company/) or school page
    private final By firstResult = By.cssSelector(
            "main a[href*='/in/'], main a[href*='/company/'], main a[href*='/school/']");

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void search(String term) {
        WebElement box = wait.until(d -> d.findElements(searchBox).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElse(null));
        box.click();
        box.sendKeys(term, Keys.ENTER);
    }

    public String clickFirstResult() {
        // The results page redraws itself while loading, so we retry when a link goes stale
        WebDriverWait resultWait = new WebDriverWait(driver, Duration.ofSeconds(25));
        resultWait.ignoring(StaleElementReferenceException.class, ElementClickInterceptedException.class);

        return resultWait.until(d -> {
            List<WebElement> links = d.findElements(firstResult);
            for (WebElement link : links) {
                if (link.isDisplayed()) {
                    String href = link.getAttribute("href");
                    link.click();
                    return href;
                }
            }
            return null;
        });
    }
}