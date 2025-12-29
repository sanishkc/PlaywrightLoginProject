package com.playwright.pages;

import com.microsoft.playwright.Page;

public class HomePage {

    // Playwright Page instance representing the Home page
    private Page page;

    /**
     * Constructor to initialize HomePage with Playwright Page
     *
     * @param page Playwright Page instance from the test
     */
    public HomePage(Page page) {
        this.page = page;
    }

    /**
     * Checks whether the Logout link is visible on the Home page
     *
     * @return true if Logout link is visible, false otherwise
     */
    public boolean isLogoutVisible() {

        // Locate the Logout anchor element and check visibility
        return page.locator("a[href='/logout']").isVisible();
    }
}
