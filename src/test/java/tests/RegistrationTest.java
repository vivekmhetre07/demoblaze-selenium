package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;
import utils.TestUtils;

public class RegistrationTest extends BaseTest {
    
    @Test(priority = 1)
    public void testRegistrationWithUniqueUser() {
        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        
        String uniqueUsername = TestUtils.generateUniqueUsername();
        String password = uniqueUsername; // Same as username as requested
        
        homePage.clickSignUp();
        registerPage.registerUser(uniqueUsername, password);
        
        // Wait for alert and verify success message
        String alertText = TestUtils.getAlertText(driver);
        Assert.assertEquals(alertText, "Sign up successful.", 
                "Registration should be successful with unique username");
    }
    
    @Test(priority = 2)
    public void testRegistrationWithExistingUser() {
        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        
        String existingUsername = "testuser";
        String password = "testuser";
        
        homePage.clickSignUp();
        registerPage.registerUser(existingUsername, password);
        
        // Wait for alert and verify error message
        String alertText = TestUtils.getAlertText(driver);
        Assert.assertEquals(alertText, "This user already exist.", 
                "Should show error message for existing username");
    }
}
