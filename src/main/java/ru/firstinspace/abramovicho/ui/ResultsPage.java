package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultsPage {
    private WebDriver driver;

    public ResultsPage() {
        this.driver = Driver.getDriver();
    }

    final private By catalogItem = By.cssSelector(".catalog__card");
    final private By emptySearchResultMessage = By.xpath("//p[@class='catalog__info']");
    final private By addToCartButton = By.xpath("//div[@class='card-sizes card__sizes']/button");


    public String getEmptySearchResultMessage() {
        return driver.findElement(emptySearchResultMessage).getText();
    }

    private List<WebElement> getListOfModels() {
        return driver.findElements(catalogItem);
    }

    public ModelInfoPage openModelDescription() {
        getListOfModels().get(0).click();
        return new ModelInfoPage();
    }
}
