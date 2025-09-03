package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(id = "name")
    private WebElement nameField;
    
    @FindBy(id = "country")
    private WebElement countryField;
    
    @FindBy(id = "city")
    private WebElement cityField;
    
    @FindBy(id = "card")
    private WebElement cardField;
    
    @FindBy(id = "month")
    private WebElement monthField;
    
    @FindBy(id = "year")
    private WebElement yearField;
    
    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseButton;
    
    @FindBy(xpath = "//button[text()='OK']")
    private WebElement okButton;
    
    @FindBy(xpath = "//div[@class='sweet-alert  showSweetAlert visible']//h2")
    private WebElement successMessage;
    
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public void fillCheckoutDetails(String name, String country, String city, 
                                   String card, String month, String year) {
        wait.until(ExpectedConditions.visibilityOf(nameField)).sendKeys(name);
        countryField.sendKeys(country);
        cityField.sendKeys(city);
        cardField.sendKeys(card);
        monthField.sendKeys(month);
        yearField.sendKeys(year);
    }
    
    public void clickPurchase() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseButton)).click();
    }
    
    public void clickOk() {
        wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
    }
    
    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOf(successMessage)).getText();
    }
    
    public boolean isPurchaseSuccessful() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
