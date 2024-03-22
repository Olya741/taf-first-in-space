package ru.firstinspace.abramovicho.ui;

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

    public void inputEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    public String getErrorText() {
        return driver.findElement(errorMessage).getText();
    }
}
