package pro.klos.testing.tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest
{
        private static final String CHROMEDRIVER_PATH= "/Users/Wojtek/chromedriver/chromedriver";
        static WebDriver driver;

        @BeforeClass
        public static void setUp()
        {
            ChromeDriverManager.getInstance().setup();
            ChromeOptions chromeOptions = new ChromeOptions();

            // Enable/disable headless mode
            chromeOptions.setHeadless(false);

            // If 'headless' mode is disabled this property is required to launch web browser
            System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);

            // Set browser language
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("intl.accept_languages", "en-US");
            chromeOptions.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(chromeOptions);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        @AfterClass
        public static void tearDown()
        {
            driver.close();
        }
    }
