package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(xpath = "//a[text()='Add to cart']")
    private WebElement addToCartButton;
    
    @FindBy(xpath = "//tbody[@id='tbodyid']//tr")
    private List<WebElement> cartItems;
    
    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderButton;
    
    @FindBy(xpath = "//a[text()='Delete']")
    private List<WebElement> deleteButtons;
    
    @FindBy(id = "totalp")
    private WebElement totalPrice;
    
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }
    
    public int getCartItemsCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        return cartItems.size();
    }
    
    public boolean isProductInCart(String productName) {
        try {
            WebElement product = driver.findElement(By.xpath("//td[text()='" + productName + "']"));
            return product.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void deleteFirstItem() {
        if (!deleteButtons.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(0))).click();
        }
    }
    
    public void deleteAllItems() {
        while (!deleteButtons.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(0))).click();
            try {
                Thread.sleep(1000); // Wait for item to be removed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }
    
    public String getTotalPrice() {
        return wait.until(ExpectedConditions.visibilityOf(totalPrice)).getText();
    }
}
