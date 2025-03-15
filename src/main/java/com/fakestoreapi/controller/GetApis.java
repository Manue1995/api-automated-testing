package com.fakestoreapi.controller;

import io.restassured.RestAssured;
import  io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.model.domain.TestResult;


import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetApis implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        Response response = RestAssured.given()
                .baseUri("https://fakestoreapi.com")
                .basePath("/products")
                .when()
                .get();

        // Imprimir respuesta en consola
        response.prettyPrint();

        // Validar código de respuesta
        assertThat(response.getStatusCode())
                .isEqualTo(String.valueOf(200));
    }

    private TestResult assertThat(int statusCode) {
        return null;
    }

    public static GetApis getApis (){
        return instrumented (GetApis.class);}


}
