package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.CartPage;
import utils.TestUtils;

public class CartTest extends BaseTest {
    
    private void loginUser() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        
        homePage.clickLogin();
        loginPage.loginUser("admin", "admin");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    
    @Test
    public void testAddProductToCart() {
        loginUser();
        
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        
        // Click on Samsung Galaxy S6
        homePage.clickProduct("Samsung galaxy s6");
        
        // Add to cart
        cartPage.addToCart();
        
        // Handle alert
        String alertText = TestUtils.getAlertText(driver);
        Assert.assertEquals(alertText, "Product added.", 
                "Product should be added to cart");
        
        // Go to cart and verify product is there
        homePage.clickCart();
        Assert.assertTrue(cartPage.isProductInCart("Samsung galaxy s6"), 
                "Samsung Galaxy S6 should be in cart");
    }
    

}
