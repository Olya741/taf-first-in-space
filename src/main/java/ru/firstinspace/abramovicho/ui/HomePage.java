package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.firstinspace.abramovicho.driver.Driver;
import ru.firstinspace.abramovicho.utils.UIWait;

public class HomePage {
    private WebDriver driver;

    public HomePage() {
        this.driver = Driver.getDriver();
    }

    private final By SUBSCRIPTION_DIALOG = By.xpath("//div[@class='subscription']");
    private final By CLOSE_SUBSCRIPTION = By.cssSelector("button.subscription__close");

    private static final Logger LOGGER = LogManager.getLogger();

    public void closeSubscriptionDialog() {
        LOGGER.info("User closes subscription dialog");
        UIWait.waitElementIsVisible(driver, SUBSCRIPTION_DIALOG);
        driver.findElement(CLOSE_SUBSCRIPTION).click();
    }
}
