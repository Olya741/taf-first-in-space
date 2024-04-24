package ru.firstinspace.abramovicho.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    public void warmUp() {
        Driver.getDriver()
                .get("https://www.first-in-space.ru/");
    }

    @AfterEach
    public void tearDown() {
        Driver.quitDriver();
    }
}
