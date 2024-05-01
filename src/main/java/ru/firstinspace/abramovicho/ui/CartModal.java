package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.firstinspace.abramovicho.driver.Driver;
import ru.firstinspace.abramovicho.utils.UIWait;

import java.util.List;

public class CartModal {
    private WebDriver driver;

    public CartModal() {
        this.driver = Driver.getDriver();
    }

    private final By PRODUCT_ITEM = By.xpath("//ul[@class='modalCart__list']/li");
    private final By PRODUCT_NAME = By.cssSelector(".modalCart__product-name");
    private final By PRODUCT_SIZE = By.cssSelector(".modalCart__product-option_size .modalCart__product-size");

    private List<WebElement> getListOfAddedProducts() {
        return driver.findElements(PRODUCT_ITEM);
    }

    public String getProductName() {
        UIWait.waitElementIsVisible(driver, PRODUCT_NAME);
        return getListOfAddedProducts().get(0).findElement(PRODUCT_NAME).getText();
    }

    public String getProductSize() {
        return getListOfAddedProducts().get(0).findElement(PRODUCT_SIZE).getText();
    }
}
