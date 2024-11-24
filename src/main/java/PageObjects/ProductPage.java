package PageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "app_logo")
    public WebElement title;

    @FindBy(className = "shopping_cart_badge")
    public WebElement shopingCartBadge;

    @FindBy(xpath = "//*[@class = 'btn btn_primary btn_small btn_inventory ']")
    public List<WebElement> addToCartButtons;

    @FindBy(xpath = "//*[@class = 'btn btn_secondary btn_small btn_inventory ']")
    public List<WebElement> removeFromCartButtons;

    @FindBy(xpath = "//*[@class = 'shopping_cart_link']")
    public WebElement shopingCartLink;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement openMenuButton;

    @FindBy(xpath = "//*[@id = 'logout_sidebar_link']")
    public WebElement logOutButton;


    public String getTitleText() {
        return title.getText();
    };

    public void addAllProductsToCart() {
        for (WebElement element : addToCartButtons) {
            element.click();
        }
    };

    public void removeAllProductsFromCart() {
        for (WebElement element : removeFromCartButtons) {
            element.click();
        }
    };

    public Integer getCartItemCount() {
        try {
            return Integer.parseInt(shopingCartBadge.getText());
        } catch (NoSuchElementException e) {
            return 0;
        }
    };

    public void clickOnShoppingCart(){
        shopingCartLink.click();
    };

    public void clickOnMenu(){
        openMenuButton.click();
    }

    public void clickOnLogOut(){
        logOutButton.click();
    }
}
