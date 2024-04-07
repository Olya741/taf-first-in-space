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

    final private String catalogItem = "//div[@class='catalog__card']";
    final private By emptySearchResultMessage = By.xpath("//p[@class='catalog__info']");
    final private String sizeItem = "//ul[@class='card-sizes__list']/li";
    final private String modelName = "//div[@class='card__name']";
    final private String addToCartButton = "//div[@class='card-sizes card__sizes']/button";
    private int itemNumber = 0;

    public String getEmptySearchResultMessage() {
        return driver.findElement(emptySearchResultMessage).getText();
    }

    private List<WebElement> getListOfModels() {
        return driver.findElements(By.xpath(catalogItem));
    }

    private WebElement getSingleModel() {
        return getListOfModels().get(itemNumber);
    }

    private String getExactPath(String basePath) {
        int i = itemNumber + 1;
        return catalogItem + "[" + i + "]" + basePath;
    }

    public ModelInfoPage openModelDescription() {
        getSingleModel().click();
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

    public void addProductToCart() {
        pointCursorOnElement();
        findFirstAvailableSize().click();
        clickAddToCartButton();
    }
}
