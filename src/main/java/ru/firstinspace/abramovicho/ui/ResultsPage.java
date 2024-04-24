package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.NoSuchElementException;

public class ResultsPage {
    private WebDriver driver;

    public ResultsPage() {
        this.driver = Driver.getDriver();
    }

    private By listOfItems = By.xpath("//div[@class='catalog__card']");
    private String catalogItem = "//div[@class='catalog__card']";
    private By emptySearchResultMessage = By.xpath("//p[@class='catalog__info']");
    private String sizeItem = "//ul[@class='card-sizes__list']/li";
    private String modelName = "//div[@class='card__name']";
    private String addToCartButton = "//div[@class='card-sizes card__sizes']/button";
    private int itemNumberInDOM = 2;

    public String getEmptySearchResultMessage() {
        UIWait.waitElementIsVisible(driver, emptySearchResultMessage);
        return driver.findElement(emptySearchResultMessage).getText();
    }

    private List<WebElement> getListOfModels() {
        UIWait.waitListIsNotEmpty(driver, listOfItems);
        return driver.findElements(By.xpath(catalogItem));
    }

    private WebElement getFirstModel() {
        UIWait.waitListIsNotEmpty(driver, listOfItems);
        return driver.findElement(By.xpath(catalogItem));
    }

    private WebElement getSingleModel() {
        return getListOfModels().get(itemNumberInDOM - 1);
    }

    private String getExactPath(String basePath) {
        return catalogItem + "[" + itemNumberInDOM + "]" + basePath;
    }

    public ModelInfoPage openModelDescription() {
        getFirstModel().click();
        return new ModelInfoPage();
    }

    public String getModelName() {
        return getSingleModel().findElement(By.xpath(getExactPath(modelName))).getText();
    }

    private List<WebElement> getListOfSuggestedSizes() {
        return driver.findElements(By.xpath(getExactPath(sizeItem)));
    }

    private WebElement findFirstAvailableSize() {
        return getListOfSuggestedSizes()
                .stream()
                .filter(e -> e.getAttribute("class").equals("card-sizes__item bg-circle-link"))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Доступных размеров нет."));
    }

    public String getAvailableSize() {
        return findFirstAvailableSize().getAttribute("textContent").trim();
    }

    private void pointCursorOnElement() {
        Actions action = new Actions(driver);
        action.moveToElement(getSingleModel()).build().perform();
    }

    private void clickAddToCartButton() {
        driver.findElement(By.xpath(getExactPath(addToCartButton))).click();
    }

    public CartModal addProductToCart() {
        pointCursorOnElement();
        findFirstAvailableSize().click();
        clickAddToCartButton();
        return new CartModal();
    }
}
