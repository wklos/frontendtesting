package pro.klos.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TravelexCoUkPage extends PageObject
{
    @FindBy(xpath = "//div[@class=\"matchHeights matchTitleHeights simple__animation simple__animation_animate clearfix slick-initialized slick-slider\"]//div[@class=\"slick-track\"]")
    private WebElement slider;

    public TravelexCoUkPage(WebDriver driver)
    {
        super(driver);
    }

    public WebElement getSlider()
    {
        return slider;
    }

    public List<WebElement> getSliderElements()
    {
        return getSlider().findElements(By.xpath("//div[contains(@class, \"sliderdb\") or contains(@class, \"slidermb\")]"));
    }

    public void scrollDownToSlider()
    {
        new Actions(driver).moveToElement(getSlider()).perform();
    }

    public void swipeSliderLeft()
    {
        WebElement activeSlide = getSliderElements().stream()
                .filter(webElement -> webElement.getAttribute("class").contains("slick-active"))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Slide with 'slick-active' class not found!"));

        new Actions(driver).sendKeys(activeSlide, Keys.ARROW_RIGHT).perform();

//        Few unsuccessful tryouts of implementing "swipe"
//        new Actions(driver).dragAndDropBy(activeSlide, -activeSlide.getSize().getWidth(), 0)
//                .build()
//                .perform();

//        new Actions(driver)
//                .clickAndHold(activeSlide)
//                .moveByOffset(-activeSlide.getSize().getWidth(), 0)
//                .release()
//                .build()
//                .perform();

//        new Actions(driver).moveToElement(activeSlide, new Double(activeSlide.getSize().getWidth()*0.5).intValue(),
//                new Double(activeSlide.getSize().getHeight()*0.5).intValue())
//                .clickAndHold()
//                .moveByOffset(-activeSlide.getSize().getWidth(), 0)
//                .release()
//                .build()
//                .perform();
    }

}
