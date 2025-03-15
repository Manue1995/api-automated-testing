package com.fakestoreapi.controller;

import io.restassured.RestAssured;
import  io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.model.domain.TestResult;


import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetApis implements Task {

    private final String endpoint;

    public GetApis(String endpoint) {
        this.endpoint = endpoint;
    }


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
        actor.remember("statusCode", response.getStatusCode());

    }

    public static GetApis getApis (String endpoint){
        return instrumented (GetApis.class, endpoint);}


}
