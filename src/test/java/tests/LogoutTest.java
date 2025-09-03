package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LogoutTest extends BaseTest {
    
    @Test(priority = 1)
    public void testSuccessfulLogout() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        
        // Login first
        homePage.clickLogin();
        loginPage.loginUser("admin", "admin");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify user is logged in
        Assert.assertTrue(homePage.isLoggedIn(), 
                "User should be logged in before logout");
        
        // Logout
        homePage.clickLogout();
        
        // Wait for logout to process
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify user is logged out
        Assert.assertTrue(homePage.isLoggedOut(), 
                "User should be logged out successfully");
    }
}
