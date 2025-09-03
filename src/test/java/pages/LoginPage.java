package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(id = "loginusername")
    private WebElement usernameField;
    
    @FindBy(id = "loginpassword")
    private WebElement passwordField;
    
    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginButton;
    
    @FindBy(xpath = "//button[@class='btn btn-secondary' and @data-dismiss='modal']")
    private WebElement closeButton;
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).clear();
        usernameField.sendKeys(username);
    }
    
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        passwordField.sendKeys(password);
    }
    
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
    
    public void closeModal() {
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
    }
    
    public void loginUser(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
