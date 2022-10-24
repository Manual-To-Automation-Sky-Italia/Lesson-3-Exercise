package com.browserstack.tests;

import com.browserstack.pages.ContactUsPage;
import com.browserstack.pages.HomePage;
import com.browserstack.pages.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;

public class AddProductsTest {

    public WebDriver driver;
    public Wait<WebDriver> wait;

    public HomePage homePage;
    public ProductsPage productsPage;

    public String url = "https://automationexercise.com";

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        driver = new RemoteWebDriver(
                new URL("https://hub.browserstack.com/wd/hub"), capabilities);

        driver.manage().window().maximize();

        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void addProductsToCartFromDetailsPage() {
        driver.get(url);

        homePage.goToProductsPage();

        productsPage.viewProduct(1);
        productsPage.addToCart();
        productsPage.viewCart();

        Assert.assertEquals(wait.until(webDriver -> driver.findElements(By.xpath("//tbody/tr"))).size(), 1);

        homePage.goToProductsPage();

        productsPage.viewProduct(2);
        productsPage.addToCart();
        productsPage.viewCart();

        Assert.assertEquals(wait.until(webDriver -> driver.findElements(By.xpath("//tbody/tr"))).size(), 2);
    }

    @Test
    public void addProductsToCartFromProductsPage() {
        driver.get(url);

        homePage.goToProductsPage();

        productsPage.hoverAndAddProductToCart(1);
        productsPage.viewCart();

        Assert.assertEquals(wait.until(webDriver -> driver.findElements(By.xpath("//tbody/tr"))).size(), 1);

        homePage.goToProductsPage();

        productsPage.hoverAndAddProductToCart(2);
        productsPage.viewCart();

        Assert.assertEquals(wait.until(webDriver -> driver.findElements(By.xpath("//tbody/tr"))).size(), 2);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
