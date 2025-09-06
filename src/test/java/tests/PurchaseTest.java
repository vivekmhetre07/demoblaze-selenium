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
        
        // Define test data
        String name = "Vivek M";
        String country = "India";
        String city = "Mumbai";
        String cardNumber = "1234567890123456";
        String month = "12";
        String year = "2025";
        
        // Validate form data before filling
        // Name should be alphabetical (letters and spaces only)
        Assert.assertTrue(name.matches("^[a-zA-Z\\s]+$"), 
                "Name should contain only alphabetical characters and spaces");
        
        // Country should be alphabetical (letters and spaces only)
        Assert.assertTrue(country.matches("^[a-zA-Z\\s]+$"), 
                "Country should contain only alphabetical characters and spaces");
        
        // City should be alphabetical (letters and spaces only)
        Assert.assertTrue(city.matches("^[a-zA-Z\\s]+$"), 
                "City should contain only alphabetical characters and spaces");
        
        // Month should be numeric only
        Assert.assertTrue(month.matches("^[0-9]+$"), 
                "Month should contain only numeric characters");
        
        // Year should be numeric only
        Assert.assertTrue(year.matches("^[0-9]+$"), 
                "Year should contain only numeric characters");
        
        // Additional validations for logical values
        int monthValue = Integer.parseInt(month);
        int yearValue = Integer.parseInt(year);
        
        Assert.assertTrue(monthValue >= 1 && monthValue <= 12, 
                "Month should be between 1 and 12");
        
        Assert.assertTrue(yearValue >= 2024 && yearValue <= 2030, 
                "Year should be a valid future year");
        
        // Fill checkout details after validation
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCheckoutDetails(name, country, city, cardNumber, month, year);
        
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
