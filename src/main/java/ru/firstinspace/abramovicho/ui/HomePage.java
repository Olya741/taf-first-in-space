package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    public HomePage() {
        this.driver = Driver.getDriver();
    }

    final private By subscriptionDialog = By.xpath("//div[@class='subscription']");
    final private By closeSubscription = By.cssSelector("button.subscription__close");

    public void closeSubscriptionDialog() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionDialog));
        driver.findElement(closeSubscription).click();
    }
}
