package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.*;
import ru.firstinspace.abramovicho.enums.Menu;

import java.util.List;

public class NavigationMenu {
    WebDriver driver;

    public NavigationMenu() {
        this.driver = Driver.getDriver();
    }

    private By searchField = By.cssSelector("button.header__option_search");
    private By searchInput = By.xpath("//input[@class='searchForm__input']");
    private By profileButton = By.xpath("(//div[contains(@class,'mobile-none')]//button)[3]");
    private By section = By.xpath("//div[contains(@class, 'mobile-none')]//li[@class='nav__item']");

    public LoginPage openLoginPage() {
        driver.findElement(profileButton).click();
        return new LoginPage();
    }

    public void inputTextInSearchField(String text) {
        driver.findElement(searchField).click();
        UIWait.waitElementIsClickable(driver, searchInput, 2);
        WebElement search = driver.findElement(searchInput);
        search.sendKeys(text);
        search.sendKeys(Keys.ENTER);
    }

    private List<WebElement> getListOfMenuItems() {
        return driver.findElements(section);
    }

    private WebElement filterList(Menu menu) {
        List<WebElement> itemsList = getListOfMenuItems();
        return itemsList.stream()
                .filter(e -> e.getText().equals(menu.getName()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(menu.getName() + " field not found"));
    }

    public void openSection(Menu menuItems) {
        filterList(menuItems).click();
    }
}
