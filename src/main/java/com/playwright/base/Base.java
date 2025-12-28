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

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(Boolean.parseBoolean(
                                ConfigReader.get("headless")))
        );

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
