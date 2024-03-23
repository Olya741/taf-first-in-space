package ru.firstinspace.abramovicho.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.firstinspace.abramovicho.Util;

import java.util.stream.Stream;

public class LoginPageTest extends BaseTest {
    @Test
    @DisplayName("Login with wrong email and password")
    public void testLoginWithWrongCredentials() {
        HomePage homePage = new HomePage();
        homePage.closeSubscriptionDialog();
        LoginPage loginPage = homePage.openLoginPage();
        loginPage.inputUserCredentials(Util.getRandomEmail(), "A-12345");
        loginPage.clickSubmitButton();
        Assertions.assertEquals("Неверные имя пользователя или пароль", loginPage.getErrorText());
    }

    @ParameterizedTest
    @MethodSource("provideEmptyField")
    @DisplayName("Login with empty email or password")
    public void testLoginWithEmptyObligatoryField(String email, String password) {
        HomePage homePage = new HomePage();
        homePage.closeSubscriptionDialog();
        LoginPage loginPage = homePage.openLoginPage();
        loginPage.inputUserCredentials(email, password);
        Assertions.assertEquals(false, loginPage.isConfirmButtonEnabled());
    }

    private static Stream<Arguments> provideEmptyField() {
        return Stream.of(
                Arguments.of(Util.getRandomEmail(), ""),
                Arguments.of("", "Qq1234")
        );
    }
}
