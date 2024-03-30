package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CapsulesPage {
    private WebDriver driver;

    public CapsulesPage() {
        this.driver = Driver.getDriver();
    }

    final private By capsuleItem = By.xpath("//a[@class='capsules__link']");

    private List<WebElement> getListOfCapsule() {
        return driver.findElements(capsuleItem);
    }

    public List<String> getCapsuleNames() {
        return getListOfCapsule()
                .stream()
                .map(e -> e.findElement(By.cssSelector("[alt]")).getAttribute("alt").replace("Обложка для капсулы ", ""))
                .collect(Collectors.toList());
    }
}
