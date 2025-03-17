package com.fakestoreapi.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteApis implements Task {

    private final String endpoint;

    public DeleteApis(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    @Step("{0} hace un DELETE a #endpoint")
    public <T extends Actor> void performAs(T t) {

        // Enviar solicitud DELETE con RestAssured
        Response response = RestAssured.given()
                .baseUri("https://fakestoreapi.com")
                .basePath(endpoint)
                .header("Content-Type", "application/json")
                .when()
                .delete();

        // Imprimir la respuesta en consola
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

    }

    public static DeleteApis deleteApis(String endpoint) {
        return instrumented(DeleteApis.class, endpoint);
    }
}
