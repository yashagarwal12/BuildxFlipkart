package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import dev.failsafe.internal.util.Durations;

public class TestCases {
        ChromeDriver driver;
        Wrappers wrap = new Wrappers();
        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

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

                driver.manage().window().maximize();
        }

        @Test

        public void testCase01() throws InterruptedException {
                driver.get("https://www.flipkart.com/");

                WebElement search = driver.findElement(By.name("q"));
                wrap.sendString(search, "Washing Machine");
                wrap.pressEnter(search);

                FluentWait<ChromeDriver> wait = new FluentWait<>(driver)
                                .withTimeout(Duration.ofSeconds(5))
                                .pollingEvery(Duration.ofMillis(100))
                                .ignoring(Exception.class);

                wait.until(ExpectedConditions.urlContains("Washing"));

                WebElement popularity = driver.findElement(By.xpath("//div[@class='sHCOk2']/div[text()='Popularity']"));
                wrap.clk(popularity);

                Thread.sleep(1000);

                /*
                 * WebElement pages =
                 * driver.findElement(By.xpath("//div[@class='_1G0WLw']/span"));
                 * int pages_count = wrap.getPages(pages);
                 * System.out.println(pages_count);
                 * for (int i = 2; i <= pages_count; i++) {
                 */
                List<WebElement> product = driver.findElements(
                                By.xpath(
                                                "//div[@class='DOjaWF gdgoEp']/div[@class='cPHDOP col-12-12']//a//span[contains(@id,'productRating')]/div"));
                wrap.rating(product.size(), driver);
        }

        // }
        @Test
        public void testCase02() throws InterruptedException {
                driver.get("https://www.flipkart.com/");

                WebElement search = driver.findElement(By.name("q"));
                wrap.sendString(search, "iPhone");
                wrap.pressEnter(search);

                FluentWait<ChromeDriver> wait = new FluentWait<>(driver)
                                .withTimeout(Duration.ofSeconds(5))
                                .pollingEvery(Duration.ofMillis(100))
                                .ignoring(Exception.class);

                wait.until(ExpectedConditions.urlContains("iPhone"));

                // List<WebElement> discount=driver.findElements(By.xpath("//div[@class='DOjaWF
                // gdgoEp']/div[@class='cPHDOP col-12-12']//div[@class='col col-5-12
                // BfVC2z']/div/div[1]//div[@class='UkUFwK']/span"));
                List<WebElement> title = driver.findElements(By.xpath(
                                "//div[@class='DOjaWF gdgoEp']/div[@class='cPHDOP col-12-12']//div[@class='yKfJKb row']/div/div[@class='KzDlHZ']"));
                wrap.iphone(title.size(), driver);
        }

        @Test
        public void testCase03() throws InterruptedException {
                driver.get("https://www.flipkart.com/");

                WebElement search = driver.findElement(By.name("q"));
                wrap.sendString(search, "Coffee Mug");
                wrap.pressEnter(search);

                /*FluentWait<ChromeDriver> wait = new FluentWait<>(driver)
                                .withTimeout(Duration.ofSeconds(5))
                                .pollingEvery(Duration.ofMillis(100))
                                .ignoring(Exception.class);*/
                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.urlContains("Mug"));

                WebElement col1 = driver.findElement(
                                By.xpath("//div[@id='container']//div[@class='DOjaWF gdgoEp col-2-12']/div"));
                WebElement rating = col1
                                .findElement(
                                                By.xpath("//div[@class='ewzVkT _3DvUAf' and contains(@title,'4')]"));
                wrap.moveToEle(rating, driver);
                wrap.clk(rating);

                Thread.sleep(2000);

                int no_of_products = driver
                                .findElements(
                                                By.xpath("//div[@class='DOjaWF gdgoEp']/div[@class='cPHDOP col-12-12']/div[@class='_75nlfW']"))
                                .size();
                                System.out.println(no_of_products);
                wrap.coffee_mug(no_of_products, driver);

        }

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}