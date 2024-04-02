package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultsPage {
    private WebDriver driver;

    public ResultsPage() {
        this.driver = Driver.getDriver();
    }

    final private By catalogItem = By.xpath("//div[@class='catalog__card']");
    final private By emptySearchResultMessage = By.xpath("//p[@class='catalog__info']");
    final private By availableSize = By.xpath("//ul[@class='card-sizes__list']/li");
    final private By modelName = By.xpath("//div[@class='card__name']");
    final private By addToCartButton = By.xpath("//div[@class='card-sizes card__sizes']/button");

    /*
    //a[contains(@href, '035')]/preceding-sibling::div//li
     */

    public String getEmptySearchResultMessage() {
        return driver.findElement(emptySearchResultMessage).getText();
    }

    private List<WebElement> getListOfModels() {
        return driver.findElements(catalogItem);
    }

    private WebElement getSingleModel() {
        return getListOfModels().get(0);
    }

    public ModelInfoPage openModelDescription() {
        getSingleModel().click();
        return new ModelInfoPage();
    }

    public String getModelName() {
        return getSingleModel().findElement(modelName).getText();
    }

    public String getModelArticle() {
        Pattern pattern = Pattern.compile("\\d*-\\d*");
        Matcher matcher = pattern.matcher(getModelName());
        String fragment = "";
        if (matcher.find()) {
            fragment = matcher.group();
        }
        return fragment;
    }

    public void addProductToCart() {
        getSingleModel().findElement(addToCartButton).click();
    }

    public List<WebElement> getAvailableSizes() {
        return getSingleModel().findElements(availableSize);
    }
}
