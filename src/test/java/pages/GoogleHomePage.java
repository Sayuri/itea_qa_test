package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigurationManager;

import static java.lang.Thread.sleep;

public class GoogleHomePage extends BasePage {

    @FindBy(id = "lst-ib")
    private WebElement searchField;


    public GoogleHomePage() {
        PageFactory.initElements(ConfigurationManager.getDriver(), this);
        waitUntilElementIsDisplayed(searchField, 5).isDisplayed();
    }

    public void enterWordsForSearch(String wordsForSearch) {
        searchField.sendKeys(wordsForSearch);
    }
}
