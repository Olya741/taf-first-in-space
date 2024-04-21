package ru.firstinspace.abramovicho.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseSpecification {
    final static String BASE_URI = "https://www.first-in-space.ru";

    static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType("application/json")
            .addFilter(new AllureRestAssured())
            .build();
}
