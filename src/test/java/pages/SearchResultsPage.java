package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigurationManager;

import java.util.List;

public class SearchResultsPage extends BasePage{

    @FindBy(xpath = "//*[@id='sblsbb']/button")
    private WebElement searchButton;

    @FindBy(id = "res")
    private WebElement resultsBlock;

    @FindBy(xpath = "//*[@class='g']")
    private List<WebElement> results;


    public SearchResultsPage() {
        PageFactory.initElements(ConfigurationManager.getDriver(), this);
        waitUntilElementIsDisplayed(searchButton, 10).isDisplayed();
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public boolean resultsPageIsLoaded() {
        waitUntilElementIsDisplayed(resultsBlock, 10);
        return resultsBlock.isDisplayed();
    }

    public List<WebElement> getResults() {
        return results;
    }

    private boolean searchWordsArePresentInTheTitle(WebElement result, String searchWords) {
        return result.findElement(By.xpath("/div[@class='rc']/h3[@class='r']")).getText().toLowerCase().contains(searchWords.toLowerCase());
    }

    private boolean searchWordsArePresentInTheLink(WebElement result, String searchWords) {
        return result.findElement(By.xpath("//cite")).getText().toLowerCase().contains(searchWords.toLowerCase());
    }

    private boolean searchWordsArePresentInTheDescription(WebElement result, String searchWords) {
        return result.findElement(By.xpath("//span[@class='st']")).getText().toLowerCase().contains(searchWords.toLowerCase());
    }

    public boolean searchWordsArePresentInTheResults(String searchWords) {
        for(WebElement result: getResults()) {
            if(!searchWordsArePresentInTheDescription(result, searchWords) && !searchWordsArePresentInTheLink(result, searchWords)
                && !searchWordsArePresentInTheTitle(result, searchWords)) {
                return false;
            }
        }
        return true;
    }
}
