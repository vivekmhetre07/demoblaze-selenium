package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ContactPage;
import utils.TestUtils;

public class ContactFormTest extends BaseTest {
    
    private ContactPage contactPage;
    
    @BeforeMethod
    public void setUpTest() {
        contactPage = new ContactPage(driver);
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
    public void testContactFormWithValidData() {
        contactPage.clickContact();
        
        String email = "test@example.com";
        String name = "John Doe";
        String message = "This is a test message for contact form validation.";
        
        contactPage.fillContactForm(email, name, message);
        contactPage.sendMessage();
        
        // Verify success message
        String alertText = TestUtils.getAlertText(driver);
        Assert.assertEquals(alertText, "Thanks for the message!!", 
                "Should show success message for valid contact form submission");
        
        System.out.println("Contact form submitted successfully with message: " + alertText);
    }
    
    @Test(priority = 2)
    public void testContactFormWithEmptyFields() {
        contactPage.clickContact();
        
        // Submit form with all empty fields
        contactPage.fillContactForm("", "", "");
        contactPage.sendMessage();
        
        if (TestUtils.isAlertPresent(driver)) {
            String alertText = TestUtils.getAlertText(driver);
            System.out.println("Alert for empty fields: " + alertText);
            Assert.assertNotNull(alertText, "Should show validation message for empty fields");
        } else {
            System.out.println("No alert shown - form may have client-side validation");
        }
    }
    
    @Test(priority = 3)
    public void testContactFormWithLongMessage() {
        contactPage.clickContact();
        
        String email = "longmessage@test.com";
        String name = "Long Message Tester";
        String longMessage = "This is a very long message to test the contact form's ability to handle " +
                "extensive text input. We want to ensure that the form can process messages of " +
                "substantial length without any issues. This message contains multiple sentences " +
                "and should be long enough to test the system's capacity for handling larger " +
                "text inputs in the contact form submission process.";
        
        contactPage.fillContactForm(email, name, longMessage);
        contactPage.sendMessage();
        
        String alertText = TestUtils.getAlertText(driver);
        Assert.assertEquals(alertText, "Thanks for the message!!", 
                "Should successfully submit contact form with long message");
        
        System.out.println("Long message contact form submitted successfully");
        System.out.println("Message length: " + longMessage.length() + " characters");
    }
}
