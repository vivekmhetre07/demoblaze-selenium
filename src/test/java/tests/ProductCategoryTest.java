package tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import java.util.List;

public class ProductCategoryTest extends BaseTest {
    
    private HomePage homePage;
    
    @BeforeMethod
    public void setUpTest() {
        homePage = new HomePage(driver);
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
    public void testPhonesCategory() {
        homePage.clickPhonesCategory();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        List<WebElement> products = homePage.getProductList();
        Assert.assertTrue(products.size() > 0, "Phones category should display products");
        
        // Verify that phones are displayed (check for common phone names)
        boolean phoneFound = false;
        for (WebElement product : products) {
            String productText = product.getText().toLowerCase();
            if (productText.contains("samsung") || productText.contains("nokia") || 
                productText.contains("iphone") || productText.contains("galaxy")) {
                phoneFound = true;
                break;
            }
        }
        
        Assert.assertTrue(phoneFound, "Should display phone products in Phones category");
        System.out.println("Products in Phones category: " + products.size());
    }
    
    @Test(priority = 2)
    public void testLaptopsCategory() {
        homePage.clickLaptopsCategory();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        List<WebElement> products = homePage.getProductList();
        Assert.assertTrue(products.size() > 0, "Laptops category should display products");
        
        // Verify that laptops are displayed
        boolean laptopFound = false;
        for (WebElement product : products) {
            String productText = product.getText().toLowerCase();
            if (productText.contains("macbook") || productText.contains("dell") || 
                productText.contains("laptop") || productText.contains("sony")) {
                laptopFound = true;
                break;
            }
        }
        
        Assert.assertTrue(laptopFound, "Should display laptop products in Laptops category");
        System.out.println("Products in Laptops category: " + products.size());
    }
    
    @Test(priority = 3)
    public void testMonitorsCategory() {
        homePage.clickMonitorsCategory();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        List<WebElement> products = homePage.getProductList();
        Assert.assertTrue(products.size() > 0, "Monitors category should display products");
        
        // Verify that monitors are displayed
        boolean monitorFound = false;
        for (WebElement product : products) {
            String productText = product.getText().toLowerCase();
            if (productText.contains("monitor") || productText.contains("apple") || 
                productText.contains("asus")) {
                monitorFound = true;
                break;
            }
        }
        
        Assert.assertTrue(monitorFound, "Should display monitor products in Monitors category");
        System.out.println("Products in Monitors category: " + products.size());
    }
}
