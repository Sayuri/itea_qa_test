package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigurationManager;

import java.util.List;

import static java.lang.Thread.sleep;

public class SearchResultsPage extends BasePage{

    @FindBy(xpath = "//*[@id='sblsbb']/button")
    private WebElement searchButton;

    @FindBy(id = "res")
    private WebElement resultsBlock;

    @FindBy(xpath = "//*[@class='g']")
    private List<WebElement> results;

    @FindBy(xpath = ".//a[text()= '2']")
    private WebElement linkToTheSecondPage;

    @FindBy(id = "resultStats")
    private WebElement resultStats;


    /**
     * Constructor of SearchResultsPage
     * WebElements of the class are initializes by PageFactory
     * WebElement searchButton is implicitly waited to be displayed
     */
    public SearchResultsPage() {
        PageFactory.initElements(ConfigurationManager.getDriver(), this);
        waitUntilElementIsDisplayed(searchButton, 10).isDisplayed();
    }

    /**
     * Getter of WebElement searchButton
     * @return WebElement searchButton
     */
    public WebElement getSearchButton() {
        return searchButton;
    }

    /**
     * WebElement resultsBlock is implicitly waited for 10 seconds to be displayed
     * @return true if WebElement resultsBlock is displayed
     */
    public boolean resultsPageIsLoaded() {
        waitUntilElementIsDisplayed(resultsBlock, 10);
        return resultsBlock.isDisplayed();
    }

    /**
     * Getter of WebElement results
     * @return WebElement results
     */
    public List<WebElement> getResults() {
        return results;
    }

    /**
     * Getter of WebElement linkToTheSecondPage
     * @return WebElement linkToTheSecondPage
     */
    public WebElement getLinkToTheSecondPage() {
        return linkToTheSecondPage;
    }

    /**
     * Compares the text of WebElement result with String searchWords
     * @param result WebElement text of which is compared to the searWords
     * @param searchWords String that is compared to the text of WebElement result
     * @return true if result and searchWords are equal
     */
    private boolean searchWordsArePresentInTheTitle(WebElement result, String searchWords) {
        return result.findElement(By.xpath("/div[@class='rc']/h3[@class='r']")).getText().toLowerCase().contains(searchWords.toLowerCase());
    }

    /**
     * Compares the text of WebElement result with String searchWords
     * @param result WebElement text of which is compared to the searWords
     * @param searchWords String that is compared to the text of WebElement result
     * @return true if result and searchWords are equal
     */
    private boolean searchWordsArePresentInTheLink(WebElement result, String searchWords) {
        return result.findElement(By.xpath("//cite")).getText().toLowerCase().contains(searchWords.toLowerCase());
    }

    /**
     * Compares the text of WebElement result with String searchWords
     * @param result WebElement text of which is compared to the searWords
     * @param searchWords String that is compared to the text of WebElement result
     * @return true if result and searchWords are equal
     */
    private boolean searchWordsArePresentInTheDescription(WebElement result, String searchWords) {
        return result.findElement(By.xpath("//span[@class='st']")).getText().toLowerCase().contains(searchWords.toLowerCase());
    }

    /**
     * Compares searchWords with the text of WebElements in the results list
     * @param searchWords String that is compared with WebElements in the results list
     * @return true if searchWords are equal to the text of WebElements in the results list
     */
    public boolean searchWordsArePresentInTheResults(String searchWords) {
        for(WebElement result: getResults()) {
            if(!searchWordsArePresentInTheDescription(result, searchWords) && !searchWordsArePresentInTheLink(result, searchWords)
                && !searchWordsArePresentInTheTitle(result, searchWords)) {
                return false;
            }
        }
        return true;
    }

    public boolean waitUntilTheSecondPageIsDisplayed() {
        int i = 0;
        while(i < 20){
            System.out.println("resultStats text " + resultStats.getText());
            if(resultStats.getText().contains("страница 2")) {
                return true;
            }else {
                try {
                    sleep(500);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
