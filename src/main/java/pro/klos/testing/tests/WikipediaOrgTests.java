package pro.klos.testing.tests;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pro.klos.testing.pages.WikipediaOrgArticlePage;
import pro.klos.testing.pages.WikipediaOrgMainPage;
import pro.klos.testing.pages.WikipediaOrgSearchResultsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WikipediaOrgTests extends BaseTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WikipediaOrgTests.class);
    private static final String WIKIPEDIA_ORG_URL = "https://www.wikipedia.org/";

    private WikipediaOrgMainPage mainPage;
    private WikipediaOrgSearchResultsPage searchResultsPage;

    @BeforeClass
    private void openWikipediaOrg()
    {
        LOGGER.info("Opening: " + WIKIPEDIA_ORG_URL);
        driver.get(WIKIPEDIA_ORG_URL);
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase(WIKIPEDIA_ORG_URL),
                String.format("Current page is not: %s", WIKIPEDIA_ORG_URL));
        LOGGER.info(WIKIPEDIA_ORG_URL + " is opened");
    }

    @Test(testName = "checkSiteTitle")
    public void checkSiteTitle()
    {
        final String WIKIPEDIA = "wikipedia";

        assertTrue(driver.getTitle().equalsIgnoreCase(WIKIPEDIA),
                String.format("Title is not: %s", WIKIPEDIA));

        LOGGER.info("checkSiteTitle - passed");
    }

    @Test(testName = "checkSuggestion")
    public void checkSuggestion()
    {
        final String DID_YOU_MEAN = "Did you mean:";
        final String SEARCH_QUERY = "furry rabbits";

        mainPage = new WikipediaOrgMainPage(driver);
        mainPage.enterSearchText(SEARCH_QUERY);

        searchResultsPage = mainPage.pressSearchButton();

        WebElement searchDidYouMeanElement = searchResultsPage.getSearchDidYouMeanElement();
        assertTrue(searchDidYouMeanElement.isDisplayed(), "\'Did you mean\' element is not displayed!");
        assertTrue(searchDidYouMeanElement.getText().contains(DID_YOU_MEAN));

        WebElement suggestion = searchResultsPage.getDidYouMeanSuggestion();
        assertTrue(suggestion.isDisplayed(), "Suggestion is not displayed!");

        LOGGER.info("checkSuggestion - passed");
    }

    @Test(testName = "checkSuggestionSearchResults", dependsOnMethods = "checkSuggestion")
    public void checkSuggestionSearchResults()
    {
        final int SEARCH_RESULTS_NO = 20;
        searchResultsPage.clickOnSuggestion();

        int currentSearchResultsSize = searchResultsPage.getSearchResultsSize();
        assertEquals(currentSearchResultsSize, SEARCH_RESULTS_NO,
                String.format("Number of search results is not equal to: %s", SEARCH_RESULTS_NO));

        LOGGER.info("checkSuggestionSearchResults - passed");
    }

    @Test(testName = "checkFirstArticleFromSuggestions", dependsOnMethods = "checkSuggestionSearchResults")
    public void checkFirstArticleFromSuggestions()
    {
        WikipediaOrgArticlePage articlePage = searchResultsPage.clickOnFirstResult();
        assertTrue(articlePage.getArticleTitle().isDisplayed(), "Article title is not displayed!");
        assertTrue(articlePage.getTableOfContents().isDisplayed(), "Table of contents is not displayed!");

        LOGGER.info("checkFirstArticleFromSuggestions - passed");
    }
}
