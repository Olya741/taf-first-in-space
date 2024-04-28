package ru.firstinspace.abramovicho.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage() {
        this.driver = Driver.getDriver();
    }

    private By emailField = By.xpath("//form[@class='auth-form__form']//input[@type='email']");
    private By passwordField = By.xpath("//form[@class='auth-form__form']//input[@type='password']");
    private By submitButton = By.xpath("//form[@class='auth-form__form']//button[@type='submit']");
    private By errorMessage = By.xpath("//p[@class='auth-form__error']");

    private static final Logger logger = LogManager.getLogger();

    private void inputEmail(String email) {
        logger.info("User inputs email");
        UIWait.waitElementIsVisible(driver, emailField);
        driver.findElement(emailField).sendKeys(email);
    }

    private void inputPassword(String password) {
        logger.info("User inputs password");
        driver.findElement(passwordField).sendKeys(password);
    }

    public void inputUserCredentials(String email, String password) {
        inputEmail(email);
        inputPassword(password);
    }

    public void clickSubmitButton() {
        logger.info("User clicks Submit button");
        driver.findElement(submitButton).click();
    }

    public String getErrorText() {
        logger.info("User gets error message");
        UIWait.waitElementIsVisible(driver, errorMessage);
        return driver.findElement(errorMessage).getText();
    }

    public boolean isConfirmButtonEnabled() {
        return driver.findElement(submitButton).isEnabled();
    }
}
