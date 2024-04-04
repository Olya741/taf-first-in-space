package ru.firstinspace.abramovicho.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    WebDriver driver;

    @BeforeEach
    public void warmUp() {
        driver = Driver.getDriver();
        driver.get("https://www.first-in-space.ru/");
    }

    @AfterEach
    public void tearDown() {
        Driver.quitDriver();
    }
}
