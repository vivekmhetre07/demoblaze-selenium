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
    
    @Test(priority = 1)
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
    
//    @Test(priority = 2)
//    public void testAddSameProductTwice() {
//        loginUser();
//        
//        HomePage homePage = new HomePage(driver);
//        CartPage cartPage = new CartPage(driver);
//        
//        // Add Samsung Galaxy S6 first time
//        homePage.clickProduct("Nokia lumia 1520");
//        cartPage.addToCart();
//        TestUtils.getAlertText(driver); // Handle first alert
//        
//        // Navigate back to home
//        homePage.navigateToHome();
//        
//        // Add same product second time
//        homePage.clickProduct("Nokia lumia 1520");
//        cartPage.addToCart();
//        TestUtils.getAlertText(driver); // Handle second alert
//        
//        // Go to cart and verify count
//        homePage.clickCart();
//        int itemCount = cartPage.getCartItemsCount();
//        Assert.assertEquals(itemCount, 3, 
//                "Should have 3 items of same product in cart");
//    }
//    
//    @Test(priority = 3)
//    public void testRemoveItemFromCart() {
//        loginUser();
//        
//        HomePage homePage = new HomePage(driver);
//        CartPage cartPage = new CartPage(driver);
//        
//        // Go to cart
//        homePage.clickCart();
//        int initialCount = cartPage.getCartItemsCount();
//        
//        // Remove one item
//        cartPage.deleteFirstItem();
//        
//        // Wait for removal
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        
//        // Verify count decreased
//        int finalCount = cartPage.getCartItemsCount();
//        Assert.assertEquals(finalCount, initialCount - 1, 
//                "Item count should decrease after removal");
//    }
}
