package com.playwright.pages;

import com.microsoft.playwright.Page;

public class HomePage {

    private Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    public boolean isLogoutVisible() {
        return page.locator("a[href='/logout']").isVisible();
    }
}
