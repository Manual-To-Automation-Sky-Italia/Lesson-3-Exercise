package com.browserstack.tests;

import com.browserstack.pages.ContactUsPage;
import com.browserstack.pages.HomePage;
import com.browserstack.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.net.URL;

public class LoginTest {

    public WebDriver driver;

    public HomePage homePage;
    public LoginPage loginPage;
    public ContactUsPage contactUsPage;
    
    public String url = "https://practicetestautomation.com/practice-test-login/";
    public String username = "student";
    public String password = "Password123";
    public String wrongUsername = "wrong-student";
    public String wrongPassword = "Password456";

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        driver = new RemoteWebDriver(
                new URL("https://hub.browserstack.com/wd/hub"), capabilities);

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        contactUsPage = new ContactUsPage(driver);
    }

    @Test
    public void loginSuccessfully() {
        driver.get(url);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickSubmitButton();

        Assert.assertTrue(driver.getCurrentUrl().contains(loginPage.loggedInUrlSnippet));
    }

    @Test
    public void wrongUsernameEntered() {
        driver.get(url);

        loginPage.enterUsername(wrongUsername);
        loginPage.enterPassword(password);
        loginPage.clickSubmitButton();

        Assert.assertTrue(loginPage.getErrorMessageElement().isDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageElement().getText(), loginPage.invalidUserMessage);
    }

    @Test
    public void wrongPasswordEntered() {
        driver.get(url);

        loginPage.enterUsername(username);
        loginPage.enterPassword(wrongPassword);
        loginPage.clickSubmitButton();

        WebElement invalidUsernameElement = driver.findElement(By.cssSelector("#error"));
        Assert.assertTrue(invalidUsernameElement.isDisplayed());
        Assert.assertEquals(invalidUsernameElement.getText(), loginPage.invalidPasswordMessage);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
