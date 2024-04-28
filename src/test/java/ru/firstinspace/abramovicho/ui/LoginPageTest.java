package ru.firstinspace.abramovicho.ui;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.firstinspace.abramovicho.utils.Util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

public class LoginPageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger();

    @Test
    @DisplayName("Login with wrong email and password")
    public void testLoginWithWrongCredentials() {
        logger.info("--- testLoginWithWrongCredentials --- method starts");
        LoginPage loginPage = new NavigationMenu().openLoginPage();
        loginPage.inputUserCredentials(Util.getRandomEmail(), RandomStringUtils.randomAlphanumeric(8));
        loginPage.clickSubmitButton();
        Assertions.assertEquals("Неверные имя пользователя или пароль", loginPage.getErrorText());
    }

    @ParameterizedTest
    @MethodSource("provideEmptyField")
    @DisplayName("Login with empty email or password")
    public void testLoginWithEmptyObligatoryField(String email, String password) {
        logger.info("--- testLoginWithEmptyObligatoryField --- method starts");
        LoginPage loginPage = new NavigationMenu().openLoginPage();
        loginPage.inputUserCredentials(email, password);
        Assertions.assertFalse(loginPage.isConfirmButtonEnabled());
    }

    private static Stream<Arguments> provideEmptyField() {
        return Stream.of(
                Arguments.of(Util.getRandomEmail(), ""),
                Arguments.of("", RandomStringUtils.randomAlphanumeric(8))
        );
    }
}
