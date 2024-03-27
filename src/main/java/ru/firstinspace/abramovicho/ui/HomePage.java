package ru.firstinspace.abramovicho.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {
    private WebDriver driver;

    public HomePage() {
        this.driver = Driver.getDriver();
    }

    private By subscriptionDialog = By.xpath("//div[@class='subscription']");
    private By closeSubscription = By.cssSelector("button.subscription__close");
    private By searchField = By.cssSelector("button.header__option_search");
    private By favoritesButton = By.cssSelector(".header__wrapper.mobile-none [href='/favorites/']");
    private By cartButton = By.xpath("(//div[contains(@class,'mobile-none')]//button)[2]");
    private By cartCounter = By.xpath("//div[contains(@class,'mobile-none')]//span[@class='header__option-count']");
    private By profileButton = By.xpath("(//div[contains(@class,'mobile-none')]//button)[3]");
    private By section = By.xpath("//div[contains(@class, 'mobile-none')]//li[@class='nav__item']");
    private By catalogItem = By.cssSelector(".catalog__card");

    public LoginPage openLoginPage() {
        driver.findElement(profileButton).click();
        return new LoginPage();
    }

    public void inputTextInSearchField(String text) {
        WebElement search = driver.findElement(searchField);
        search.sendKeys(text);
        search.sendKeys(Keys.ENTER);
    }

    public void closeSubscriptionDialog() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionDialog));
        driver.findElement(closeSubscription).click();
    }

    private List<WebElement> getListOfMenuItems() {
        return driver.findElements(section);
    }

    private WebElement filterList(Menu menu) {
        List<WebElement> itemsList = getListOfMenuItems();
        return itemsList.stream()
                .filter(e -> e.getText().equals(menu.getName()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Field does not found: " + menu.getName()));
    }

    public void openSection(Menu menuItems) {
        filterList(menuItems).click();
    }
}
