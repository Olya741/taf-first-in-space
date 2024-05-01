package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.firstinspace.abramovicho.driver.Driver;
import ru.firstinspace.abramovicho.utils.UIWait;

import java.util.List;
import java.util.NoSuchElementException;

public class ResultsPage {
    private WebDriver driver;

    public ResultsPage() {
        this.driver = Driver.getDriver();
    }

    private final By LIST_OF_ITEMS = By.xpath("//div[@class='catalog__card']");
    private final String CATALOG_ITEM = "//div[@class='catalog__card']";
    private final By EMPTY_SEARCH_RESULT_MESSAGE = By.xpath("//p[@class='catalog__info']");
    private final String SIZE_ITEM = "//ul[@class='card-sizes__list']/li";
    private final String MODEL_NAME = "//div[@class='card__name']";
    private final String ADD_TO_CART_BUTTON = "//div[@class='card-sizes card__sizes']/button";
    private final int ITEM_NUMBER_IN_DOM = 2;

    public String getEmptySearchResultMessage() {
        UIWait.waitElementIsVisible(driver, EMPTY_SEARCH_RESULT_MESSAGE);
        return driver.findElement(EMPTY_SEARCH_RESULT_MESSAGE).getText();
    }

    private List<WebElement> getListOfModels() {
        UIWait.waitListIsNotEmpty(driver, LIST_OF_ITEMS);
        return driver.findElements(By.xpath(CATALOG_ITEM));
    }

    private WebElement getFirstModel() {
        UIWait.waitListIsNotEmpty(driver, LIST_OF_ITEMS);
        return driver.findElement(By.xpath(CATALOG_ITEM));
    }

    private WebElement getSingleModel() {
        return getListOfModels().get(ITEM_NUMBER_IN_DOM - 1);
    }

    private String getExactPath(String basePath) {
        return CATALOG_ITEM + "[" + ITEM_NUMBER_IN_DOM + "]" + basePath;
    }

    public ModelInfoPage openModelDescription() {
        getFirstModel().click();
        return new ModelInfoPage();
    }

    public String getModelName() {
        return getSingleModel().findElement(By.xpath(getExactPath(MODEL_NAME))).getText();
    }

    private List<WebElement> getListOfSuggestedSizes() {
        return driver.findElements(By.xpath(getExactPath(SIZE_ITEM)));
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
        driver.findElement(By.xpath(getExactPath(ADD_TO_CART_BUTTON))).click();
    }

    public CartModal addProductToCart() {
        pointCursorOnElement();
        findFirstAvailableSize().click();
        clickAddToCartButton();
        return new CartModal();
    }
}
