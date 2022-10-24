package com.browserstack.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class ProductsPage {
    WebDriver driver;
    Wait<WebDriver> fluentWait;
    JavascriptExecutor js;
    Actions actions;

    By viewProduct1Locator = By.xpath("//a[@href='/product_details/1']");
    By viewProduct2Locator = By.xpath("//a[@href='/product_details/2']");
    By product1AddToCartLocator = By.xpath("//a[@data-product-id='1']");
    By product2AddToCartLocator = By.xpath("//a[@data-product-id='2']");
    By addToCartLocator = By.cssSelector(".cart");
    By viewCartLocator = By.xpath("//div[@id='cartModal']/div[1]/div[1]/div[2]/p[2]/a");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;

        fluentWait = new FluentWait<>(this.driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }

    public WebElement getViewProduct1Element() { return fluentWait.until(webDriver -> driver.findElement(viewProduct1Locator)); }
    public WebElement getViewProduct2Element() { return fluentWait.until(webDriver -> driver.findElement(viewProduct2Locator)); }
    public WebElement getProduct1HoverElement() { return fluentWait.until(webDriver -> driver.findElement(product1AddToCartLocator)); }
    public WebElement getProduct2HoverElement() { return fluentWait.until(webDriver -> driver.findElement(product2AddToCartLocator)); }
    public WebElement getAddToCartButtonElement() { return fluentWait.until(webDriver -> driver.findElement(addToCartLocator)); }
    public WebElement getViewCartElement() { return fluentWait.until(webDriver -> driver.findElement(viewCartLocator)); }

    public void viewProduct(int prodNum) {
        js.executeScript("window.scrollBy(0,250)","");

        if (prodNum == 1) {
            getViewProduct1Element().click();
        } else if (prodNum == 2) {
            getViewProduct2Element().click();
        } else {
            System.out.println("Must pass in 1 or 2");
        }
    }

    public void hoverAndAddProductToCart(int prodNum) {
        js.executeScript("window.scrollBy(0,250)","");

        if (prodNum == 1) {
            actions.moveToElement(getProduct1HoverElement());
            getProduct1HoverElement().click();
        } else if (prodNum == 2) {
            actions.moveToElement(getProduct2HoverElement());
            getProduct2HoverElement().click();
        } else {
            System.out.println("Must pass in 1 or 2");
        }
    }

    public void addToCart() { getAddToCartButtonElement().click(); }

    public void viewCart() {
        fluentWait.until(webDriver -> getViewCartElement().isDisplayed());
        getViewCartElement().click();
    }

}
