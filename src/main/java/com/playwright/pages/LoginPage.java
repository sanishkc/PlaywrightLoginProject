package com.playwright.pages;

import com.microsoft.playwright.Page;
import com.playwright.utils.ConfigReader;

public class LoginPage {

    // Playwright Page instance representing the Login page
    private Page page;

    /**
     * Constructor to initialize LoginPage with Playwright Page
     *
     * @param page Playwright Page instance passed from test/Base class
     */
    public LoginPage(Page page) {
        this.page = page;
    }

    /**
     * Navigates to the application login URL
     * URL is read from configuration (config file or environment variable)
     */
    public void navigate() {

        // Navigate to the login page using configured application URL
        page.navigate(ConfigReader.get("app.url"));
    }

    /**
     * Performs login action using provided credentials
     *
     * @param username application username
     * @param password application password
     */
    public void login(String username, String password) {

        // Fill username input field
        page.locator("#username").fill(username);

        // Fill password input field
        page.locator("#password").fill(password);

        // Click on the Login/Submit button
        page.locator("button[type='submit']").click();
    }

    /**
     * Retrieves the login success or error message displayed on the page
     *
     * @return message text from the flash notification
     */
    public String getMessage() {

        // Read and return the text content of the flash message
        return page.locator("#flash").textContent();
    }
}
