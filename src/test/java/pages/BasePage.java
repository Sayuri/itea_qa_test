package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigurationManager;

/**
 * Super class for PageObjects
 */
public class BasePage {

    /**
     * Explicitly waits for WebElement visibility
     * @param element WebElement that will be explicitly waited to be visible
     * @param timeouts Maximum wait time in seconds
     * @return WebElement that was explicitly waited
     */
    public WebElement waitUntilElementIsDisplayed(WebElement element, int timeouts) {
        WebDriverWait wait = new WebDriverWait(ConfigurationManager.getDriver(), timeouts);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


    /**
     * Explicitly waits for WebElement visibility for 10 seconds
     * @param element WebElement that will be explicitly waited to be visible
     * @return WebElement that was explicitly waited to be displayed
     */
    public WebElement waitUntilElementIsDisplayed(WebElement element) {
        return waitUntilElementIsDisplayed(element, 10);
    }

    public WebElement waitUntilElementIsClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(ConfigurationManager.getDriver(), 10);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Gets current Browser URL
     * @return String with current URL value
     */
    public String getCurrentURL() {
        return ConfigurationManager.getDriver().getCurrentUrl();
    }

}
