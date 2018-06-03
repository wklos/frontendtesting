package pro.klos.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikipediaOrgArticlePage extends PageObject
{
    @FindBy(id = "firstHeading")
    private
    WebElement articleTitle;

    @FindBy(id = "toc")
    private
    WebElement tableOfContents;

    public WikipediaOrgArticlePage(WebDriver driver)
    {
        super(driver);
    }

    public WebElement getArticleTitle()
    {
        return articleTitle;
    }

    public WebElement getTableOfContents()
    {
        return tableOfContents;
    }
}
