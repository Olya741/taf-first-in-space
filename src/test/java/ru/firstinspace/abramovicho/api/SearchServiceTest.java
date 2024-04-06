package ru.firstinspace.abramovicho.api;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static ru.firstinspace.abramovicho.api.SearchService.getParams;

public class SearchServiceTest {
    @Test
    @DisplayName("GET 200: search with correct min_price and max_price values")
    public void testSearch_1() {
        int min = 7000;
        int max = 7500;
        JsonPath jsonPath = given()
                .spec(BaseSpecification.requestSpec)
                .params(getParams(min, max))
                .get(EndPoints.CATALOG)
                .then()
                .statusCode(200).extract().jsonPath();
        List<Integer> price = jsonPath.getList("results.price");
        List<Integer> priceWithSale = jsonPath.getList("results.price_with_sale");

        Assertions.assertEquals(0, SearchService.findPriceOutOfRange(price, priceWithSale, min, max));
    }

    @Test
    @DisplayName("GET 200: search when params are double")
    public void testSearch_2() {
        given()
                .spec(BaseSpecification.requestSpec)
                .params(getParams(10000.1, 10100.1))
                .get(EndPoints.CATALOG)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("GET 200: search with a narrow price range")
    public void testSearch_3() {
        given()
                .spec(BaseSpecification.requestSpec)
                .params(getParams(10000, 10050))
                .get(EndPoints.CATALOG)
                .then()
                .statusCode(200)
                .body("results", empty());
    }

    @Test
    @DisplayName("GET 200: search when the min_price is greater than the max_price")
    public void testSearch_4() {
        given()
                .spec(BaseSpecification.requestSpec)
                .params(getParams(50000, 10000))
                .get(EndPoints.CATALOG)
                .then()
                .statusCode(200)
                .body("results", empty());
    }

    @Test
    @DisplayName("GET 400: search when params have wrong format")
    public void testSearch_5() {
        given()
                .spec(BaseSpecification.requestSpec)
                .params(getParams("abc", "1-2-3"))
                .get(EndPoints.CATALOG)
                .then()
                .statusCode(400)
                .body("price[0]", equalTo("Введите число."))
                .body("price[1]", equalTo("Введите число."));
    }

    @Test
    @DisplayName("GET 500: search with wrong param name")
    public void testSearch_6() {
        given()
                .spec(BaseSpecification.requestSpec)
                .params("pricemin", 10000)
                .params("price_max", 15000)
                .get(EndPoints.CATALOG)
                .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("GET 405: search with wrong method")
    public void testSearch_7() {
        given()
                .spec(BaseSpecification.requestSpec)
                .params(getParams(10000, 15000))
                .put(EndPoints.CATALOG)
                .then()
                .statusCode(405)
                .body("detail", equalTo("Метод \"PUT\" не разрешен."));
    }
}
