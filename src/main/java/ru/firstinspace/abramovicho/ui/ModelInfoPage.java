package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.firstinspace.abramovicho.driver.Driver;
import ru.firstinspace.abramovicho.utils.UIWait;

public class ModelInfoPage {
    private WebDriver driver;

    public ModelInfoPage() {
        this.driver = Driver.getDriver();
    }

    private final By MODEL_DESCRIPTION = By.xpath("//div[@class='info-block__description']//p");

    public String getDescription() {
        UIWait.waitElementIsVisible(driver, MODEL_DESCRIPTION);
        return driver.findElement(MODEL_DESCRIPTION).getText();
    }
}
