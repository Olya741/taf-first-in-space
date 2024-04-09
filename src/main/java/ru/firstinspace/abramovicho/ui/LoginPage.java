package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    public LoginPage() {
        this.driver = Driver.getDriver();
    }

    private By emailField = By.xpath("//form[@class='auth-form__form']//input[@type='email']");
    private By passwordField = By.xpath("//form[@class='auth-form__form']//input[@type='password']");
    private By submitButton = By.xpath("//form[@class='auth-form__form']//button[@type='submit']");
    private By errorMessage = By.xpath("//p[@class='auth-form__error']");

    private void inputEmail(String email) {
        // UIWait.waitElementIsVisible(driver, emailField);

        Wait<WebDriver> wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        e.sendKeys(email);
    }

    private void inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void inputUserCredentials(String email, String password) {
        inputEmail(email);
        inputPassword(password);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    public String getErrorText() {
     UIWait.waitElementIsVisible(driver, errorMessage);
        return driver.findElement(errorMessage).getText();
    }

    public boolean isConfirmButtonEnabled() {
        return driver.findElement(submitButton).isEnabled();
    }
}
