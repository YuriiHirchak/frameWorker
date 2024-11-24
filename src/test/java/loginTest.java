import PageObjects.LogInPage;
import PageObjects.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class loginTest {
    LogInPage logInPage;
    ProductPage productPage;
    SoftAssert softAssert;
    WebDriver driver;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
        logInPage = new LogInPage(driver);
        productPage = new ProductPage(driver);
        softAssert = new SoftAssert();
    }
    @Test(priority = 1)
    public void verifyPageTitle() {
        Assert.assertEquals(logInPage.title.getText(), "Swag Labs");
        Assert.assertEquals(logInPage.isUserNameFieldDisplayed(), true);
        Assert.assertEquals(logInPage.isPasswordFieldDisplayed(), true);
        Assert.assertEquals(logInPage.isLoginButtonDisplayed(), true);
    }
    @Test(priority = 2)
    public void verifyLoginErrorMessages() {
        logInPage.login("","");
//        Assert.assertEquals(logInPage.getErrorMessageContainerText(), "Epic sadface: Username is required");
        softAssert.assertEquals(logInPage.getErrorMessageContainerText(), "Epic sadface: Username is required");

        driver.navigate().refresh();
        logInPage.login("standard_user","");
        softAssert.assertEquals(logInPage.getErrorMessageContainerText(), "Epic sadface: Password is required");

        driver.navigate().refresh();
        logInPage.login("fakeUser","secret_sauce");
        softAssert.assertEquals(logInPage.getErrorMessageContainerText(), "Epic sadface: Username and password do not match any user in this service");

        driver.navigate().refresh();
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void verifySuccessfulLogin() {
        logInPage.login();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(productPage.getTitleText(), "Swag Labs");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
