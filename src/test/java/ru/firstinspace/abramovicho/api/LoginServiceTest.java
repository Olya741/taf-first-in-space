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
                .post(EndPoints.LOGIN)
                .then()
                .statusCode(400)
                .body("user[0]", equalTo(LoginService.wrongLoginOrPasswordError));
    }

    @Test
    @DisplayName("POST 400: Test login with empty")
    public void testLogin2() {
        given()
                .spec(BaseSpecification.requestSpec)
                .body(setBodyForLogin("", RandomStringUtils.randomAlphanumeric(10)))
                .post(EndPoints.LOGIN)
                .then()
                .statusCode(400)
                .body("email[0]", equalTo(LoginService.emptyFieldError));
    }

    @Test
    @DisplayName("POST 400: Test login with empty password")
    public void testLogin3() {
        given()
                .spec(BaseSpecification.requestSpec)
                .body(setBodyForLogin(Util.getRandomEmail(), ""))
                .post(EndPoints.LOGIN)
                .then()
                .statusCode(400)
                .body("password[0]", equalTo(LoginService.emptyFieldError));
    }

    @Test
    @DisplayName("POST 405: Test login with wrong method")
    public void testLogin4() {
        given()
                .spec(BaseSpecification.requestSpec)
                .body(setBodyForLogin(Util.getRandomEmail(), RandomStringUtils.randomAlphanumeric(10)))
                .put(EndPoints.LOGIN)
                .then()
                .statusCode(405)
                .body("detail", equalTo("Метод \"PUT\" не разрешен."));
    }

    @Test
    @DisplayName("POST 400: Test login without body")
    public void testLogin5() {
        given()
                .spec(BaseSpecification.requestSpec)
                .post(EndPoints.LOGIN)
                .then()
                .statusCode(400)
                .body("email[0]", equalTo(LoginService.requiredFieldError))
                .and()
                .body("password[0]", equalTo(LoginService.requiredFieldError));
    }

    @Test
    @DisplayName("POST 415: Test login without 'Content-type' header")
    public void testLogin6() {
        given()
                .body(setBodyForLogin(Util.getRandomEmail(), RandomStringUtils.randomAlphanumeric(10)))
                .log().all()
                .post(BASE_URI + EndPoints.LOGIN)
                .then()
                .statusCode(415)
                .body("detail", equalTo("Неподдерживаемый тип данных \"text/plain; charset=ISO-8859-1\" в запросе."));
    }

    @Test
    @DisplayName("POST 400: Test login with wrong email format")
    public void testLogin7() {
        given()
                .spec(BaseSpecification.requestSpec)
                .body(setBodyForLogin(Util.getRandomNumber(100), RandomStringUtils.randomAlphanumeric(10)))
                .log().all()
                .post(EndPoints.LOGIN)
                .then()
                .statusCode(400)
                .body("email[0]", equalTo(LoginService.invalidEmailFormatError));
    }

    @Test
    @DisplayName("POST 400: Test login with wrong password format")
    public void testLogin8() {
        given()
                .spec(BaseSpecification.requestSpec)
                .body(setBodyForLogin(Util.getRandomEmail(), true))
                .post(EndPoints.LOGIN)
                .then()
                .statusCode(400)
                .body("password[0]", equalTo(LoginService.invalidPasswordFormatError));
    }
}
