package com.playwright.base;

import com.microsoft.playwright.*;
import com.playwright.utils.ConfigReader;

public class Base {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    public void setUp() {
        playwright = Playwright.create();

        // Detect if running in CI
        boolean isCi = System.getenv("CI") != null;

        // Launch browser
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(isCi) // headless=true on CI, headless=false locally
        );
        
        System.out.println("Running headless? " + isCi);

        context = browser.newContext();
        page = context.newPage();
    }

    public void tearDown() {
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}
