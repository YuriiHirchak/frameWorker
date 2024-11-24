import PageObjects.LogInPage;
import PageObjects.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class addToCartTest {
    LogInPage logInPage;
    ProductPage productPage;
    WebDriver driver;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        logInPage = new LogInPage(driver);
        productPage = new ProductPage(driver);

        logInPage.login();
        Assert.assertEquals(productPage.getTitleText(), "Swag Labs");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void verifyAddingAllItemsToCart(){
        productPage.addAllProductsToCart();
        Assert.assertEquals(productPage.getCartItemCount(), 6);
    }

    @Test
    public void verifyRemovingAllItemsFromCart(){
        productPage.removeAllProductsFromCart();
        Assert.assertEquals(productPage.getCartItemCount(), 0);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
