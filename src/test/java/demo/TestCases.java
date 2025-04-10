package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.logging.Level;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
    WebDriverWait wait;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    @Test
    public void testCase01() throws InterruptedException {

        System.out.println("Start TestCase01");

       //to open and peint the url
        Wrappers.openUrl(driver, " https://www.flipkart.com/");

        //entertext method is to send text to an element
        Wrappers.enterText(driver, By.xpath("//input[@name = 'q']"), "Washing Machine");

        //this method is to wait for an element until it's visible
        Wrappers.waitForAnElement(driver, By.xpath("//div[normalize-space()='Popularity']"), 10);

        //this method is for locating and clicking an element
        Wrappers.clickElement(driver, By.xpath("//div[normalize-space()='Popularity']"));

        //this method is to count the no of products having rating less than or equal to 4 stars
        Wrappers.checkCountFourStarOrLessRating(driver, By.xpath("//div[@class='col col-7-12']//div[@class='XQDdHH']"));

        System.out.println("End TestCase01");

    }

    @Test
    public void testCase02() throws InterruptedException {

        System.out.println("Start TestCase02");

        Wrappers.openUrl(driver, " https://www.flipkart.com/");

        Wrappers.enterText(driver, By.xpath("//input[@name = 'q']"), "iPhone");

        Wrappers.waitForAnElement(driver, By.xpath("//div[normalize-space()='Popularity']"), 10);

        //this method is to fetch the title of the products having more than 17% discount
        Wrappers.fetchTitleWithDiscMoreThan17Perc(driver,
                By.xpath("//div[@class='col col-5-12 BfVC2z']//div[@class='UkUFwK']"),
                By.xpath("//div[@class='col col-7-12']//div[@class='KzDlHZ']"));

        System.out.println("End TestCase02");
    }

    @Test
    public void testCase03() throws InterruptedException {

        System.out.println("Start TestCase03");

        Wrappers.openUrl(driver, " https://www.flipkart.com/");

        Wrappers.enterText(driver, By.xpath("//input[@name = 'q']"), "Coffee Mug");

        Wrappers.waitForAnElement(driver, By.xpath("(//div[@class='XqNaEv'])[1]"), 10);

        //this method is to click on an element by using Actions class
        Wrappers.clickUsingActionsClass(driver, By.xpath("(//div[@class='XqNaEv'])[1]"));
        Thread.sleep(3000);

        //this method is to print title,imageUrl and reviews for the top 5 products having highest reviews
        Wrappers.fetchDetailsOfHighestRatedProduct(driver, By.xpath("//div[@class='slAVV4']"),
                By.xpath(".//a[@class='wjcEIp']"), By.xpath(".//a[@class='wjcEIp']"),
                By.xpath(".//span[@class='Wphh3N']"));

        System.out.println("End TestCase03");
    }

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();
    }
}