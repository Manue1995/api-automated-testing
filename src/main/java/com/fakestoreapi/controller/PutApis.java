package com.fakestoreapi.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.io.File;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PutApis implements Task {

    private final String endpoint;
    private final String jsonFile;

    public PutApis(String endpoint, String jsonFile) {
        this.endpoint = endpoint;
        this.jsonFile = jsonFile;
    }

    @Override
    @Step("{0} hace un PUT a #endpoint con el JSON #jsonFile")
    public <T extends Actor> void performAs(T t) {

        try {
            // Leer el JSON desde el archivo
            File postbody = new File("src/test/resources/update_product.json");

            // Enviar solicitud PUT con RestAssured
            Response response = RestAssured.given()
                    .baseUri("https://fakestoreapi.com")
                    .basePath(endpoint)
                    .header("Content-Type", "application/json")
                    .body(postbody)
                    .when()
                    .put();

            // Imprimir la respuesta en consola
            System.out.println("Response Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asString());

        } catch (Exception e) {
            throw new RuntimeException("Error al leer el archivo JSON", e);
        }
    }
        public static PutApis putApis (String endpoint, String jsonFile) {
            return instrumented(PutApis.class, endpoint, jsonFile);


        }
}
