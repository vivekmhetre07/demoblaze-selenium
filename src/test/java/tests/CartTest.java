package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.CartPage;
import utils.TestUtils;

public class CartTest extends BaseTest {
    
    private HomePage homePage;
    private CartPage cartPage;
    
    @BeforeMethod
    public void setUpTest() {
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        
        loginUser();
        clearCart();
    }
    
    private void loginUser() {
        LoginPage loginPage = new LoginPage(driver);
        
        homePage.clickLogin();
        loginPage.loginUser("admin", "admin");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void clearCart() {
        homePage.clickCart();
        cartPage.deleteAllItems(); 
        
        driver.navigate().to(BASE_URL);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Test(priority = 1)
    public void testAddProductToCart() {
        homePage.clickProduct("Samsung galaxy s6");
        cartPage.addToCart();
        
        String alertText = TestUtils.getAlertText(driver);
        Assert.assertEquals(alertText, "Product added.", 
                "Product should be added to cart");
        
        homePage.clickCart();
        Assert.assertTrue(cartPage.isProductInCart("Samsung galaxy s6"), 
                "Samsung Galaxy S6 should be in cart");
        
        int itemCount = cartPage.getCartItemsCount();
        Assert.assertEquals(itemCount, 1, "Should have exactly 1 item in cart");
    }
    
    @Test(priority = 2)
    public void testAddSameProductTwice() {
        homePage.clickProduct("Samsung galaxy s6");
        cartPage.addToCart();
        TestUtils.getAlertText(driver); 
        
        driver.navigate().to(BASE_URL);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        homePage.clickProduct("Samsung galaxy s6");
        cartPage.addToCart();
        TestUtils.getAlertText(driver); 
        
        homePage.clickCart();
        int itemCount = cartPage.getCartItemsCount();
        Assert.assertEquals(itemCount, 2, 
                "Should have exactly 2 items of same product in cart");
    }
    
    @Test(priority = 3)
    public void testRemoveItemFromCart() {

        
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        
        homePage.clickProduct("Nokia lumia 1520");
        cartPage.addToCart();
        TestUtils.getAlertText(driver);
        
        homePage.navigateToHome();
        homePage.clickProduct("Nokia lumia 1520");
        cartPage.addToCart();
        TestUtils.getAlertText(driver);
        
        homePage.clickCart();
        int initialCount = cartPage.getCartItemsCount();
        Assert.assertEquals(initialCount, 2, "Should start with 2 items");
        
        cartPage.deleteFirstItem();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        int finalCount = cartPage.getCartItemsCount();
        Assert.assertEquals(finalCount, initialCount - 1, 
                "Item count should decrease after removal");
    }
}
