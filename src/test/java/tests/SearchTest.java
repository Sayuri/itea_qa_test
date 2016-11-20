package tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GoogleHomePage;
import pages.SearchResultsPage;

public class SearchTest extends BaseTest{
    private GoogleHomePage googleHomePage;
    private SearchResultsPage searchResultsPage;

    /**
     * On the home page of google.com enters text "ITEA" to the search field,
     * enters the search button,
     * Checks that the result page is loaded,
     * Checks that there are 7 results displayed on the first result page,
     * Checks that the word "ITEA" is present in every result,
     * Navigates to the second page,
     * Checks that the second page is loaded,
     * Checks that there are 10 results displayed on the second page,
     * Checks that the word "ITEA" is present in every result
     */
    @Test
    public void checkThatSearchedWordsArePresentInResults() {
        googleHomePage = new GoogleHomePage();
        googleHomePage.enterWordsForSearch("ITEA");
        searchResultsPage = new SearchResultsPage();
        searchResultsPage.getSearchButton().click();
        Assert.assertTrue(searchResultsPage.resultsPageIsLoaded());
        Assert.assertTrue(searchResultsPage.getResults().size() == 7);
        Assert.assertTrue(searchResultsPage.searchWordsArePresentInTheResults("ITEA"));
        searchResultsPage.getLinkToTheSecondPage().click();
        searchResultsPage = new SearchResultsPage();
        Assert.assertTrue(searchResultsPage.waitUntilTheSecondPageIsDisplayed());
        Assert.assertTrue(searchResultsPage.getResults().size() == 10);
        Assert.assertTrue(searchResultsPage.searchWordsArePresentInTheResults("ITEA"));
    }
}
