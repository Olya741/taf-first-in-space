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

    private final By productItem = By.xpath("//ul[@class='modalCart__list']/li");
    private final By productName = By.cssSelector(".modalCart__product-name");
    private final By deleteProductButton = By.cssSelector(".modalCart__product-delete");
    private final By productCounter = By.cssSelector(".modalCart__product-option_quantity .counter");

    public List<WebElement> getListOfAddedProducts() {
        return driver.findElements(productItem);
    }

    public String getProductName() {
        return getListOfAddedProducts().get(0).findElement(productName).getText();
    }

    public int getNumberOfSingleProduct() {
        return Integer.valueOf(getListOfAddedProducts().get(0).findElement(productCounter).getText());
    }

}
