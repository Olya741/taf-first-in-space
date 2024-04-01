package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.*;

import java.util.List;

public class NavigationMenu {
    WebDriver driver;

    public NavigationMenu() {
        this.driver = Driver.getDriver();
    }

    final private By searchField = By.cssSelector("button.header__option_search");
    final private By searchInput = By.xpath("//input[@class='searchForm__input']");
    final private By favoritesButton = By.cssSelector(".header__wrapper.mobile-none [href='/favorites/']");
    final private By cartButton = By.xpath("(//div[contains(@class,'mobile-none')]//button)[2]");
    final private By cartCounter = By.xpath("//div[contains(@class,'mobile-none')]//span[@class='header__option-count']");
    final private By profileButton = By.xpath("(//div[contains(@class,'mobile-none')]//button)[3]");
    final private By section = By.xpath("//div[contains(@class, 'mobile-none')]//li[@class='nav__item']");

    public LoginPage openLoginPage() {
        driver.findElement(profileButton).click();
        return new LoginPage();
    }

    public void inputTextInSearchField(String text) {
        driver.findElement(searchField).click();
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
