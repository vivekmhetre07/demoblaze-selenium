package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(id = "sign-username")
    private WebElement usernameField;
    
    @FindBy(id = "sign-password")
    private WebElement passwordField;
    
    @FindBy(xpath = "//button[text()='Sign up']")
    private WebElement signUpButton;
    
    @FindBy(xpath = "//button[@class='btn btn-secondary' and @data-dismiss='modal']")
    private WebElement closeButton;
    
    public RegisterPage(WebDriver driver) {
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
    
    public void clickSignUp() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton)).click();
    }
    
    public void closeModal() {
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
    }
    
    public void registerUser(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignUp();
    }
}
