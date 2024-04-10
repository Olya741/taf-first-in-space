package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ModelInfoPage {
    private WebDriver driver;

    public ModelInfoPage() {
        this.driver = Driver.getDriver();
    }

    private By modelDescription = By.xpath("//div[@class='info-block__description']//p");

    public String getDescription() {
        UIWait.waitElementIsVisible(driver, modelDescription);
        return driver.findElement(modelDescription).getText();
    }
}
