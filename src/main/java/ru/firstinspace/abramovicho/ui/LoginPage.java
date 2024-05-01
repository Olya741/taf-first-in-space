package ru.firstinspace.abramovicho.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.firstinspace.abramovicho.driver.Driver;
import ru.firstinspace.abramovicho.utils.UIWait;

public class LoginPage {
    private WebDriver driver;

    public LoginPage() {
        this.driver = Driver.getDriver();
    }

    private final By EMAIL_FIELD = By.xpath("//form[@class='auth-form__form']//input[@type='email']");
    private final By PASSWORD_FIELD = By.xpath("//form[@class='auth-form__form']//input[@type='password']");
    private final By SUBMIT_BUTTON = By.xpath("//form[@class='auth-form__form']//button[@type='submit']");
    private final By ERROR_MESSAGE = By.xpath("//p[@class='auth-form__error']");

    private static final Logger logger = LogManager.getLogger();

    private void inputEmail(String email) {
        logger.info("User inputs email");
        UIWait.waitElementIsVisible(driver, EMAIL_FIELD);
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    private void inputPassword(String password) {
        logger.info("User inputs password");
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    public void inputUserCredentials(String email, String password) {
        inputEmail(email);
        inputPassword(password);
    }

    public void clickSubmitButton() {
        logger.info("User clicks Submit button");
        driver.findElement(SUBMIT_BUTTON).click();
    }

    public String getErrorText() {
        logger.info("User gets error message");
        UIWait.waitElementIsVisible(driver, ERROR_MESSAGE);
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public boolean isConfirmButtonEnabled() {
        return driver.findElement(SUBMIT_BUTTON).isEnabled();
    }
}
