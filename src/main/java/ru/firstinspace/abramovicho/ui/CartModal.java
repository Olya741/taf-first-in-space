package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartModal {
    WebDriver driver;

    public CartModal() {
        this.driver = Driver.getDriver();
    }

    private By productItem = By.xpath("//ul[@class='modalCart__list']/li");
    private By productName = By.cssSelector(".modalCart__product-name");
    private By productSize = By.cssSelector(".modalCart__product-option_size .modalCart__product-size");

    private List<WebElement> getListOfAddedProducts() {
        return driver.findElements(productItem);
    }

    public String getProductName() {
        return getListOfAddedProducts().get(0).findElement(productName).getText();
    }

    public String getProductSize() {
        return getListOfAddedProducts().get(0).findElement(productSize).getText();
    }
}
