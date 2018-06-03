package pro.klos.testing.tests;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pro.klos.testing.pages.WikipediaOrgMainPage;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class WikipediaOrgAdditionalTests extends BaseTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WikipediaOrgAdditionalTests.class);
    private static final String WIKIPEDIA_ORG_URL = "https://www.wikipedia.org/";

    private WikipediaOrgMainPage mainPage;

    @BeforeClass
    private void openWikipediaOrg()
    {
        driver.get(WIKIPEDIA_ORG_URL);
    }

    @Test(testName = "checkIfLanguageListIsDisplayed")
    public void checkIfLanguageListIsDisplayed()
    {
        mainPage = new WikipediaOrgMainPage(driver);

        WebElement langListContainer = mainPage.getLangListContainer();
        assertFalse(langListContainer.isDisplayed(), "Language list should not be displayed!");

        mainPage.pressLangListButton();
        assertTrue(langListContainer.isDisplayed(), "Language list container is not displayed!");

        LOGGER.info("checkIfLanguageListIsDisplayed - passed");
    }

    @Test(testName = "checkIfThereAreLanguagesWithMoreThan100Articles",
            dependsOnMethods = "checkIfLanguageListIsDisplayed")
    public void checkIfThereAreLanguagesWithMoreThan100Articles()
    {
        WebElement languagesWithMoreThan100ArticlesElement = mainPage.getLanguagesWithMoreThan100ArticlesElement();
        assertTrue(languagesWithMoreThan100ArticlesElement.isDisplayed());
        assertFalse(mainPage.getLanguagesWithMoreThan100Articles().isEmpty(),
                "No languages on the list");

        LOGGER.info("checkIfThereAreLanguagesWithMoreThan100Articles - passed");
    }

    @Test(testName = "checkIfSesothoIsOnTheList",
            dependsOnMethods = "checkIfThereAreLanguagesWithMoreThan100Articles")
    public void checkIfSesothoIsOnTheList()
    {
        final String SESOTHO = "Sesotho";

        List<String> languages = mainPage.getLanguagesWithMoreThan100Articles()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        assertTrue(languages.contains(SESOTHO), String.format("%s is not on the list!", SESOTHO));

        LOGGER.info("checkIfSesothoIsOnTheList - passed");
    }

    @Test(testName = "checkSearchSuggestions", dependsOnMethods = "checkIfSesothoIsOnTheList")
    public void checkSearchSuggestions()
    {
        final String SEARCH_TEXT = "Elon";
        mainPage.enterSearchText(SEARCH_TEXT);

        List<WebElement> suggestions = mainPage.getSuggestions();
        assertFalse(suggestions.isEmpty(), "Search suggestions are missing!");

        boolean allMatch = suggestions
                .stream()
                .allMatch(webElement -> SEARCH_TEXT.equalsIgnoreCase(mainPage.getSuggestionHighlight(webElement)));

        assertTrue(allMatch, String.format("Suggestion highlight is not matching: %s", SEARCH_TEXT));
        LOGGER.info("checkSearchSuggestions - passed");
    }

}
