package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.*;
import ru.firstinspace.abramovicho.driver.Driver;
import ru.firstinspace.abramovicho.enums.Menu;
import ru.firstinspace.abramovicho.utils.UIWait;

import java.util.List;

public class NavigationMenu {
    private WebDriver driver;

    public NavigationMenu() {
        this.driver = Driver.getDriver();
    }

    private final By SEARCH_FIELD = By.cssSelector("button.header__option_search");
    private final By SEARCH_INPUT = By.xpath("//input[@class='searchForm__input']");
    private final By PROFILE_BUTTON = By.xpath("(//div[contains(@class,'mobile-none')]//button)[3]");
    private final By SECTION = By.xpath("//div[contains(@class, 'mobile-none')]//li[@class='nav__item']");

    public LoginPage openLoginPage() {
        UIWait.waitElementIsPresents(driver, PROFILE_BUTTON);
        driver.findElement(PROFILE_BUTTON).click();
        return new LoginPage();
    }

    private void pointCursorOnSearchField() {
        driver.findElement(SEARCH_FIELD).click();
    }

    public void inputTextInSearchField(String text) {
        pointCursorOnSearchField();
        UIWait.waitElementIsClickable(driver, SEARCH_INPUT);
        WebElement search = driver.findElement(SEARCH_INPUT);
        search.sendKeys(text);
        search.sendKeys(Keys.ENTER);
    }

    private List<WebElement> getListOfMenuItems() {
        return driver.findElements(SECTION);
    }

    private WebElement filterList(Menu menu) {
        UIWait.waitListIsNotEmpty(driver, SECTION);
        List<WebElement> itemsList = getListOfMenuItems();
        return itemsList.stream()
                .filter(e -> e.getText().equals(menu.getName()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Раздел " + menu.getName() + " не найден."));
    }

    public void openSection(Menu menuItems) {
        filterList(menuItems).click();
    }
}
