package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigurationManager;

public class GoogleHomePage extends BasePage {

    @FindBy(id = "lst-ib")
    private WebElement searchField;

    /**
     * Constructor of GoogleHomePage
     * WebElements of the class are initializes by PageFactory
     * WebElement searchField is implicitly waited to be displayed
     */
    public GoogleHomePage() {
        PageFactory.initElements(ConfigurationManager.getDriver(), this);
        waitUntilElementIsDisplayed(searchField, 5).isDisplayed();
    }

    /**
     * Enters words for search into the search field
     * @param wordsForSearch String that is entered into the search field
     */
    public void enterWordsForSearch(String wordsForSearch) {
        searchField.sendKeys(wordsForSearch);
    }
}
