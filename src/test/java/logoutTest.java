import PageObjects.LogInPage;
import PageObjects.ProductPage;
import PageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class logoutTest {
    LogInPage logInPage;
    ProductPage productPage;
    WebDriver driver;
    BasePage basePage;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
        logInPage = new LogInPage(driver);
        productPage = new ProductPage(driver);
        logInPage.login();
        Assert.assertEquals(productPage.getTitleText(), "Swag Labs", "Title text does not match.");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "URL does not match after login.");
    }

    @Test
    public void verifyLogOut() {
        productPage.clickOnMenu();
        productPage.clickOnLogOut();
        basePage.waitForElementToAppear(By.id("login-button"));    
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "URL does not match after logout.");
    }
//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//            logger.info("Browser closed.");
//        }
//    }
}