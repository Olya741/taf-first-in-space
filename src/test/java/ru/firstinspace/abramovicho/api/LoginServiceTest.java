package ru.firstinspace.abramovicho.api;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.firstinspace.abramovicho.Util;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.firstinspace.abramovicho.api.BaseSpecification.BASE_URI;
import static ru.firstinspace.abramovicho.api.LoginService.setBodyForLogin;


public class LoginServiceTest {
    @Test
    @DisplayName("POST 400: Test login with invalid credentials")
    public void testLogin1() {
        given()
                .spec(BaseSpecification.requestSpec)
                .body(setBodyForLogin(Util.getRandomEmail(), RandomStringUtils.randomAlphanumeric(10)))
                .post(LoginService.endpoint)
                .then()
                .statusCode(400)
                .body("user[0]", equalTo("Неверные имя пользователя или пароль"));
    }

    @Test
    @DisplayName("POST 400: Test login with empty")
    public void testLogin2() {
        given()
                .spec(BaseSpecification.requestSpec)
                .body(setBodyForLogin("", RandomStringUtils.randomAlphanumeric(10)))
                .post(LoginService.endpoint)
                .then()
                .statusCode(400)
                .body("email[0]", equalTo("Это поле не может быть пустым."));
    }

    @Test
    @DisplayName("POST 400: Test login with empty password")
    public void testLogin3() {
        given()
                .spec(BaseSpecification.requestSpec)
                .body(setBodyForLogin(Util.getRandomEmail(), ""))
                .post(LoginService.endpoint)
                .then()
                .statusCode(400)
                .body("password[0]", equalTo("Это поле не может быть пустым."));
    }

    @Test
    @DisplayName("POST 405: Test login with wrong method")
    public void testLogin4() {
        given()
                .spec(BaseSpecification.requestSpec)
                .body(setBodyForLogin(Util.getRandomEmail(), RandomStringUtils.randomAlphanumeric(10)))
                .put(LoginService.endpoint)
                .then()
                .statusCode(405)
                .body("detail", equalTo("Метод \"PUT\" не разрешен."));
    }

    @Test
    @DisplayName("POST 400: Test login without body")
    public void testLogin5() {
        given()
                .spec(BaseSpecification.requestSpec)
                .post(LoginService.endpoint)
                .then()
                .statusCode(400)
                .body("email[0]", equalTo("Обязательное поле."))
                .and()
                .body("password[0]", equalTo("Обязательное поле."));
    }

    @Test
    @DisplayName("POST 415: Test login without 'Content-type' header")
    public void testLogin6() {
        given()
                .body(setBodyForLogin(Util.getRandomEmail(), RandomStringUtils.randomAlphanumeric(10)))
                .log().all()
                .post(BASE_URI + LoginService.endpoint)
                .then()
                .statusCode(415)
                .body("detail", equalTo("Неподдерживаемый тип данных \"text/plain; charset=ISO-8859-1\" в запросе."));
    }
}