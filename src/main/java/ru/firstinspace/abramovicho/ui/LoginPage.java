package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage() {
        this.driver = Driver.getDriver();
    }

    final private By emailField = By.xpath("//form[@class='auth-form__form']//input[@type='email']");
    final private By passwordField = By.xpath("//form[@class='auth-form__form']//input[@type='password']");
    final private By submitButton = By.xpath("//form[@class='auth-form__form']//button[@type='submit']");
    final private By errorMessage = By.xpath("//p[@class='auth-form__error']");

    private void inputEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
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
        return driver.findElement(errorMessage).getText();
    }

    public boolean isConfirmButtonEnabled() {
        return driver.findElement(submitButton).isEnabled();
    }
}
