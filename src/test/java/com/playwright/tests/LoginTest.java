package com.playwright.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import com.playwright.base.Base;
import com.playwright.pages.LoginPage;
import com.playwright.pages.HomePage;
import com.playwright.utils.ConfigReader;

public class LoginTest extends Base {

    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void launchBrowser() {
        setUp();
        loginPage = new LoginPage(page);
        homePage = new HomePage(page);
        loginPage.navigate();
    }

    @Test
    public void invalidLoginTest() {

        loginPage.login(
                ConfigReader.get("invalid.username"),
                ConfigReader.get("invalid.password")
        );

        Assert.assertTrue(loginPage.getMessage()
                .contains("Your username is invalid"));
    }


    @Test
    public void validLoginTest() {

        String username = ConfigReader.getEnv("APP_USERNAME");
        String password = ConfigReader.getEnv("APP_PASSWORD");

        loginPage.login(username, password);
        Assert.assertTrue(homePage.isLogoutVisible());
    }


    @AfterMethod
    public void closeBrowser() {
        tearDown();
    }
}
