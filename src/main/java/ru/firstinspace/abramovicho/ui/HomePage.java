package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public HomePage() {
        this.driver = Driver.getDriver();
    }

    private By searchField = By.cssSelector("button.header__option_search");
    private By favoritesButton = By.cssSelector(".header__wrapper.mobile-none [href='/favorites/']");
    private By cartButton = By.cssSelector("");
    private By cartCounter = By.cssSelector("//div[contains(@class,'mobile-none')]//span[@class='header__option-count']");
    private By profileButton = By.cssSelector("");

    public LoginPage openLoginPage() {
        driver.findElement(profileButton).click();
        return new LoginPage();
    }

}
