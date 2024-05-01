package ru.firstinspace.abramovicho.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.firstinspace.abramovicho.driver.Driver;

public class BaseTest {

    @BeforeEach
    public void warmUp() {
        Driver.getDriver()
                .get("https://www.first-in-space.ru/");
        HomePage homePage = new HomePage();
        homePage.closeSubscriptionDialog();
    }

    @AfterEach
    public void tearDown() {
        Driver.quitDriver();
    }
}
