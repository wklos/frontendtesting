package pro.klos.testing.tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import pro.klos.testing.pages.TravelexCoUkPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TravelexCoUkTest extends BaseTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TravelexCoUkTest.class);
    private static final String TRAVELEX_CO_UK_URL = "https://www.travelex.co.uk/";

    @Test
    public void travelexTest()
    {
        LOGGER.info("");
        driver.get(TRAVELEX_CO_UK_URL);
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase(TRAVELEX_CO_UK_URL),
                String.format("Current page is not: %s", TRAVELEX_CO_UK_URL));
        LOGGER.info(TRAVELEX_CO_UK_URL + " is opened");

        LOGGER.info("Resize window to 550x550 px");
        resizeWindow(550, 550);
        verifyWindowSize(550, 550);
        LOGGER.info("Window resized to 550x550 px");

        TravelexCoUkPage travelexPage = new TravelexCoUkPage(driver);

        travelexPage.scrollDownToSlider();
        assertTrue(travelexPage.getSlider().isDisplayed(), "Slider is not displayed!");

        WebElement firstSlide = travelexPage.getSliderElements().get(0);
        assertTrue(firstSlide.isDisplayed(), "First slide is not displayed!");

        LOGGER.info("Swiping left");
        travelexPage.swipeSliderLeft();
        LOGGER.info("Swiping left");
        travelexPage.swipeSliderLeft();

        WebElement thirdSlide = travelexPage.getSliderElements().get(2);
        assertTrue(thirdSlide.isDisplayed(), "Third slide is not displayed!");
        LOGGER.info("travelexTest - passed");
    }

    private void resizeWindow(int width, int height)
    {
        Dimension targetWindowDimension = new Dimension(width, height);
        driver.manage().window().setSize(targetWindowDimension);
    }

    private void verifyWindowSize(int width, int height)
    {
        Dimension actualWindowDimension = driver.manage().window().getSize();
        Dimension expectedWindowDimension = new Dimension(width, height);

        int actualWidth = actualWindowDimension.getWidth();
        int expectedWidth = expectedWindowDimension.getWidth();

        assertEquals(actualWidth, expectedWidth, String.format("Window width is not: %s", expectedWidth));

        int actualHeight = actualWindowDimension.getHeight();
        int expectedHeight = expectedWindowDimension.getHeight();

        assertEquals(actualHeight, expectedHeight, String.format("Window height is not: %s", expectedHeight));
    }
}
