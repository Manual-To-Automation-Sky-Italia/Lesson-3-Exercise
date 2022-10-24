package com.browserstack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    Wait<WebDriver> fluentWait;

    By usernameLocator = By.cssSelector("#username");
    By passwordLocator = By.cssSelector("#password");
    By submitButtonLocator = By.cssSelector("#submit");
    By errorMessageLocator = By.cssSelector("#error");

    // Not strictly best practice - demo purposes only
    public String loggedInUrlSnippet = "logged-in-successfully";
    public String invalidUserMessage = "Your username is invalid!";
    public String invalidPasswordMessage = "Your password is invalid!";

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        fluentWait = new FluentWait<>(this.driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }

    public void enterUsername(String username) {
        fluentWait.until(webDriver -> driver.findElement(usernameLocator)).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButtonLocator).click();
    }

    public WebElement getErrorMessageElement() {
        return driver.findElement(errorMessageLocator);
    }
}
