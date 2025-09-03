package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(id = "signin2")
    private WebElement signUpLink;
    
    @FindBy(id = "login2")
    private WebElement loginLink;
    
    @FindBy(id = "logout2")
    private WebElement logoutLink;
    
    @FindBy(id = "cartur")
    private WebElement cartLink;
    
    @FindBy(id = "nameofuser")
    private WebElement welcomeMessage;
    
    @FindBy(xpath = "//a[contains(@class, 'nav-link') and contains(text(), 'Home')]")
    private WebElement homeTab;
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public void navigateToHome() {
        wait.until(ExpectedConditions.elementToBeClickable(homeTab)).click();
        wait.until(ExpectedConditions.urlContains("index.html"));
    }
    
    public void clickProduct(String productName) {
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.linkText(productName)));
        product.click();
    }
    
    public void clickSignUp() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpLink)).click();
    }
    
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
    
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
    
    public void clickCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }
    
    public boolean isLoggedIn() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(welcomeMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isLoggedOut() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}