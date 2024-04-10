package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public HomePage() {
        this.driver = Driver.getDriver();
    }

    private By subscriptionDialog = By.xpath("//div[@class='subscription']");
    private By closeSubscription = By.cssSelector("button.subscription__close");

    public void closeSubscriptionDialog() {
        UIWait.waitElementIsVisible(driver, subscriptionDialog);
        driver.findElement(closeSubscription).click();
    }
}
