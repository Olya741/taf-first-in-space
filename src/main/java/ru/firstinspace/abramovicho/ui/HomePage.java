package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePage {
    private WebDriver driver;

    public HomePage() {
        this.driver = Driver.getDriver();
    }

    private By subscriptionDialog = By.xpath("//div[@class='subscription']");
    private By closeSubscription = By.cssSelector("button.subscription__close");

    private static final Logger logger = LogManager.getLogger();

    public void closeSubscriptionDialog() {
        logger.info("User closes subscription dialog");
        UIWait.waitElementIsVisible(driver, subscriptionDialog);
        driver.findElement(closeSubscription).click();
    }
}
