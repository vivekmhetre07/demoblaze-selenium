package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.TestUtils;

public class LoginTest extends BaseTest {
    
    @Test(priority = 1)
    public void testLoginWithBlankCredentials() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        
        homePage.clickLogin();
        loginPage.loginUser("", "");
        
        String alertText = TestUtils.getAlertText(driver);
        Assert.assertEquals(alertText, "Please fill out Username and Password.", 
                "Should show error for blank credentials");
    }
    
    @Test(priority = 2)
    public void testLoginWithWrongPassword() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        
        homePage.clickLogin();
        loginPage.loginUser("admin", "wrongpassword");
        
        String alertText = TestUtils.getAlertText(driver);
        Assert.assertEquals(alertText, "Wrong password.", 
                "Should show error for wrong password");
    }
    
    @Test(priority = 3)
    public void testLoginWithCorrectCredentials() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        
        homePage.clickLogin();
        loginPage.loginUser("admin", "admin");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify successful login
        Assert.assertTrue(homePage.isLoggedIn(), 
                "User should be logged in successfully");
    }
}
