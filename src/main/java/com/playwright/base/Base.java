package com.playwright.base;

import com.microsoft.playwright.*;

public class Base {

    // Playwright engine instance (entry point for Playwright)
    protected Playwright playwright;

    // Browser instance (Chromium / Firefox / WebKit)
    protected Browser browser;

    // BrowserContext represents an isolated browser session
    protected BrowserContext context;

    // Page represents a single browser tab
    protected Page page;

    /**
     * Setup method to initialize Playwright, browser, context, and page
     * This method should be called before every test
     */
    public void setUp() {

        // Create Playwright instance
        playwright = Playwright.create();

        // Detect if tests are running in CI (GitHub Actions sets CI=true)
        boolean isCi = System.getenv("CI") != null;

        // Launch Chromium browser
        // Headless mode is enabled in CI and disabled locally for debugging
        browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions()
                .setHeadless(isCi)
        );

        // Create a new isolated browser context (clean session)
        context = browser.newContext();

        // Open a new browser page (tab)
        page = context.newPage();
    }

    /**
     * Tear down method to clean up resources
     * This method should be called after every test
     */
    public void tearDown() {

        // Close the current page
        page.close();

        // Close the browser context
        context.close();

        // Close the browser instance
        browser.close();

        // Close Playwright and release all resources
        playwright.close();
    }
}
