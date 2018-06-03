package pro.klos.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WikipediaOrgSearchResultsPage extends PageObject
{
    private static final String TAG_NAME_LI = "li";
    private static final String TAG_NAME_A = "a";

    @FindBy(className = "searchdidyoumean")
    private WebElement dymElement;

    @FindBy(id = "mw-search-DYM-suggestion")
    private WebElement dymSuggestion;

    @FindBy(className = "mw-search-results")
    private WebElement searchResults;

    public WikipediaOrgSearchResultsPage(WebDriver driver)
    {
        super(driver);
    }

    public WebElement getSearchDidYouMeanElement()
    {
        return dymElement;
    }

    public WebElement getDidYouMeanSuggestion()
    {
        return dymSuggestion;
    }

    public void clickOnSuggestion()
    {
        dymSuggestion.click();
    }

    private List<WebElement> getSearchResults()
    {
        return searchResults.findElements(By.tagName(TAG_NAME_LI));
    }

    public int getSearchResultsSize()
    {
        return getSearchResults().size();
    }

    private WebElement getFirstResultUrl()
    {
        WebElement firstResult = getSearchResults().get(0);
        return firstResult.findElement(By.tagName(TAG_NAME_A));
    }

    public WikipediaOrgArticlePage clickOnFirstResult()
    {
        WebElement firstResultUrl = getFirstResultUrl();
        firstResultUrl.click();

        return new WikipediaOrgArticlePage(driver);
    }
}
