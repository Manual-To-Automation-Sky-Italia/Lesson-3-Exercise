package com.browserstack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class ContactUsPage {
    WebDriver driver;
    Wait<WebDriver> fluentWait;

    By nameFieldLocator = By.name("name");
    By emailFieldLocator = By.name("email");
    By subjectFieldLocator = By.name("subject");
    By messageFieldLocator = By.id("message");
    By submitButtonLocator = By.name("submit");
    By successMessageLocator = By.xpath("//div[@class='status alert alert-success']");

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;

        fluentWait = new FluentWait<>(this.driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }

    public void fillContactForm(String name, String emailAddress, String subject, String messageText) {
        fluentWait.until(webDriver -> driver.findElement(nameFieldLocator)).sendKeys(name);
        driver.findElement(emailFieldLocator).sendKeys(emailAddress);
        driver.findElement(subjectFieldLocator).sendKeys(subject);
        driver.findElement(messageFieldLocator).sendKeys(messageText);

        driver.findElement(submitButtonLocator).click();

        driver.switchTo().alert().accept();
    }

    public WebElement getSuccessMessageElement() {
        return fluentWait.until(webDriver -> driver.findElement(successMessageLocator));
    }
}
