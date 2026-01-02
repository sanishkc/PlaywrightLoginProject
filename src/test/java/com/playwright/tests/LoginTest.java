package com.playwright.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import com.playwright.base.Base;
import com.playwright.pages.LoginPage;
import com.playwright.pages.HomePage;
import com.playwright.utils.ConfigReader;

public class LoginTest extends Base {

    // Page Object references
    LoginPage loginPage;
    HomePage homePage;

    /**
     * This method runs BEFORE every test method
     * - Launches browser
     * - Initializes Page Objects
     * - Navigates to Login page
     */
    @BeforeMethod
    public void launchBrowser() {
        setUp(); // from Base class

        // Initialize page objects with Playwright Page
        loginPage = new LoginPage(page);
        homePage = new HomePage(page);

        // Navigate to application login page
        loginPage.navigate();
    }

    /**
     * Negative test case
     * - Uses invalid credentials from config.properties
     * - Verifies proper error message is displayed
     */
    @Test
    public void invalidLoginTest() {

        // Login with invalid credentials (non-secret data)
        loginPage.login(
                ConfigReader.get("invalid.username"),
                ConfigReader.get("invalid.password")
        );

        // Validate error message for invalid login
		
		  Assert.assertTrue(
		  loginPage.getMessage().contains("Your username is invalid"),
		  "Invalid login error message not displayed" );
    }

    /**
     * Positive test case
     * - Uses valid credentials from environment variables
     * - Verifies successful login by checking Logout link
     */
    @Test
    public void validLoginTest() {

        // Fetch sensitive credentials from environment variables
        String username = ConfigReader.getEnv("APP_USERNAME");
        String password = ConfigReader.getEnv("APP_PASSWORD");

        // Perform login
        loginPage.login(username, password);

        // Validate successful login
        Assert.assertTrue(
                homePage.isLogoutVisible(),
                "Logout link not visible. Login may have failed."
        );
    }

    /**
     * This method runs AFTER every test method
     * - Closes page, context, browser and Playwright
     */
    @AfterMethod
    public void closeBrowser() {
        tearDown(); // from Base class
    }
}
