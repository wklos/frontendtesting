package pro.klos.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WikipediaOrgMainPage extends PageObject
{
    private static final String TAG_NAME_LI = "li";
    private static final String SUGGESTION_LINK = "suggestion-link";
    private static final String SUGGESTION_HIGHLIGHT = "suggestion-highlight";

    @FindBy(id = "searchInput")
    private WebElement searchInput;

    @FindBy(xpath = "//*[@id=\"search-form\"]//button[@type=\"submit\"]")
    private WebElement searchButton;

    @FindBy(id = "js-lang-list-button")
    private WebElement langListButton;

    @FindBy(className = "lang-list-container")
    private WebElement langListContainer;

    // Languages with around 100+ entries
    @FindBy(xpath = "//div[@class=\"langlist langlist-tiny hlist\"]")
    private WebElement languagesList100;

    @FindBy(className = "suggestions-dropdown")
    private WebElement suggestionsDropdown;

    public WikipediaOrgMainPage(WebDriver driver)
    {
        super(driver);
    }

    public void enterSearchText(String searchText)
    {
        searchInput.clear();
        searchInput.sendKeys(searchText);
    }

    public WikipediaOrgSearchResultsPage pressSearchButton()
    {
        searchButton.click();

        return new WikipediaOrgSearchResultsPage(driver);
    }

    public void pressLangListButton()
    {
        langListButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(langListContainer));
    }

    public WebElement getLangListContainer()
    {
        return langListContainer;
    }

    public WebElement getLanguagesWithMoreThan100ArticlesElement()
    {
        return languagesList100;
    }

    private List<WebElement> getLanguagesFromElement(WebElement languagesListElement)
    {
        return languagesListElement.findElements(By.tagName(TAG_NAME_LI));
    }

    public List<WebElement> getLanguagesWithMoreThan100Articles()
    {
        return getLanguagesFromElement(getLanguagesWithMoreThan100ArticlesElement());
    }

    private WebElement getSuggestionsDropdown()
    {
        return suggestionsDropdown;
    }

    public List<WebElement> getSuggestions()
    {
        return getSuggestionsDropdown().findElements(By.className(SUGGESTION_LINK));
    }

    public String getSuggestionHighlight(WebElement suggestionLink)
    {
        return suggestionLink.findElement(By.className(SUGGESTION_HIGHLIGHT)).getText();
    }
}
