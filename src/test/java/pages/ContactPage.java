package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ContactPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(xpath = "//a[@data-target='#exampleModal']")
    private WebElement contactLink;
    
    @FindBy(id = "recipient-email")
    private WebElement emailField;
    
    @FindBy(id = "recipient-name")
    private WebElement nameField;
    
    @FindBy(id = "message-text")
    private WebElement messageField;
    
    @FindBy(xpath = "//button[text()='Send message']")
    private WebElement sendMessageButton;
    
    @FindBy(xpath = "//button[@class='btn btn-secondary' and @data-dismiss='modal']")
    private WebElement closeButton;
    
    public ContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public void clickContact() {
        wait.until(ExpectedConditions.elementToBeClickable(contactLink)).click();
    }
    
    public void fillContactForm(String email, String name, String message) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        nameField.sendKeys(name);
        messageField.sendKeys(message);
    }
    
    public void sendMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(sendMessageButton)).click();
    }
    
    public void closeModal() {
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
    }
}
