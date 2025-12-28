package com.playwright.pages;

import com.microsoft.playwright.Page;
import com.playwright.utils.ConfigReader;

public class LoginPage {

    private Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate(ConfigReader.get("app.url"));
    }

    public void login(String username, String password) {
        page.locator("#username").fill(username);
        page.locator("#password").fill(password);
        page.locator("button[type='submit']").click();
    }

    public String getMessage() {
        return page.locator("#flash").textContent();
    }
}
