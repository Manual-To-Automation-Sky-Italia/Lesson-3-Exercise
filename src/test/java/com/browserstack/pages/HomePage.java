package com.browserstack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;

    By homeMenuLocator = By.xpath("//a[@href='/']");
    By productsMenuLocator = By.xpath("//a[@href='/products']");
    By cartMenuLocator = By.xpath("//a[@href='/view_cart']");
    By signUpLoginLocator = By.xpath("//a[@href='/login']");
    By testCasesMenuLocator = By.xpath("//a[@href='/test_cases']");
    By apiTestingMenuLocator = By.xpath("//a[@href='/api_list']");
    By youtubeTutorialsMenuLocator = By.xpath("//a[@href='https://www.youtube.com/c/AutomationExercise']");
    By contactUsMenuLocator = By.xpath("//a[@href='/contact_us']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getHomeMenuElement() { return driver.findElement(homeMenuLocator); }
    public WebElement getProductsMenuElement() { return driver.findElement(productsMenuLocator); }
    public WebElement getCartMenuElement() { return driver.findElement(cartMenuLocator); }
    public WebElement getSignUpMenuElement() { return driver.findElement(signUpLoginLocator); }
    public WebElement getTestCaseMenuElement() { return driver.findElement(testCasesMenuLocator); }
    public WebElement getApiTestingMenuElement() { return driver.findElement(apiTestingMenuLocator); }
    public WebElement getYouTubeTutorialsMenuElement() { return driver.findElement(youtubeTutorialsMenuLocator); }
    public WebElement getContactUsMenuElement() { return driver.findElement(contactUsMenuLocator); }

    public void goToHomePage() {
        getHomeMenuElement().click();
    }

    public void goToProductsPage() {
        getProductsMenuElement().click();
    }

    public void goToCartPage() {
        getCartMenuElement().click();
    }

    public void goToSignUpLoginPage() {
        getSignUpMenuElement().click();
    }

    public void goToTestCasesPage() {
        getTestCaseMenuElement().click();
    }

    public void goToApiTestingPage() {
        getApiTestingMenuElement().click();
    }

    public void goToYoutubeTutorialsPage() {
        getYouTubeTutorialsMenuElement().click();
    }

    public void goToContactUsPage() {
        getContactUsMenuElement().click();
    }
}
