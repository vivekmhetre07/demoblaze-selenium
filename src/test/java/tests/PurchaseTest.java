package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.CartPage;
import pages.CheckoutPage;
import utils.TestUtils;

public class PurchaseTest extends BaseTest {
    
    @Test(priority = 1)
    public void testSuccessfulPurchase() {
        // Login first
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        
        homePage.clickLogin();
        loginPage.loginUser("admin", "admin");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Add product to cart
        CartPage cartPage = new CartPage(driver);
        homePage.clickProduct("Samsung galaxy s6");
        cartPage.addToCart();
        TestUtils.getAlertText(driver);
        
        // Go to cart and place order
        homePage.clickCart();
        cartPage.clickPlaceOrder();
        
        // Fill checkout details
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCheckoutDetails(
                "John Doe", 
                "USA", 
                "New York", 
                "1234567890123456", 
                "12", 
                "2025"
        );
        
        checkoutPage.clickPurchase();
        
        // Verify purchase success
        Assert.assertTrue(checkoutPage.isPurchaseSuccessful(), 
                "Purchase should be successful");
        
        String successMessage = checkoutPage.getSuccessMessage();
        Assert.assertTrue(successMessage.contains("Thank you for your purchase!"), 
                "Should show purchase confirmation message");
        
        checkoutPage.clickOk();
    }
}
