package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInPage {

    String username = "standard_user";
    String password = "secret_sauce";

    WebDriver driver;
    WebDriverWait wait;
    public LogInPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    };
    @FindBy(className="login_logo")
    public WebElement title;

    @FindBy(id="user-name")
    public WebElement userNameField;

    @FindBy(id="password")
    public WebElement passwordField;

    @FindBy(id="login-button")
    public WebElement loginButton;

    @FindBy(className="error-message-container")
    public WebElement errorMessageContainer;

    public Boolean isUserNameFieldDisplayed(){
        return userNameField.isDisplayed();
    };

    public Boolean isPasswordFieldDisplayed(){
        return passwordField.isDisplayed();
    };

    public Boolean isLoginButtonDisplayed(){
        return loginButton.isDisplayed();
    };

    public void login(String username, String password){
        userNameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void login(){
        userNameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public String getErrorMessageContainerText(){
        return errorMessageContainer.getText();
    };
    public void waitForElementToAppear (By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
