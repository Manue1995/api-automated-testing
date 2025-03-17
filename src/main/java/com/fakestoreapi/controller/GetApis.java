package com.fakestoreapi.controller;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static com.fakestoreapi.utils.Constant.BASE_URL;
import static com.fakestoreapi.utils.Constant.extent;

public class GetApis implements Task {

    private static ExtentTest test;

    @Override
    public <T extends Actor> void performAs(T actor) {

        test = extent.createTest("Prueba API - GET");

        String[] endpoints = {"/products/1", "/users/2", "/carts/3"};

        for (String endpoint : endpoints) {
            ejecutarPrueba(actor, endpoint);
        }

        extent.flush();
    }

    private void ejecutarPrueba(Actor actor, String endpoint) {

        try {
            test.log(Status.INFO, "Ejecutando GET en: " + endpoint);

            Response response = RestAssured.given()
                    .baseUri(BASE_URL)
                    .basePath(endpoint)
                    .when()
                    .get();

            int statusCode = response.getStatusCode();
            String responseBody = response.getBody().asString();

            test.log(Status.INFO, "Código de respuesta: " + statusCode);
            test.log(Status.INFO, "Cuerpo de respuesta: " + responseBody);

            // Validar si el código de respuesta es 200
            if (statusCode == 200) {
                test.log(Status.PASS, "La solicitud GET fue exitosa en " + endpoint);
            } else {
                test.log(Status.FAIL, "Error en " + endpoint + " - Código recibido: " + statusCode);
            }

            // Guardar valores en la memoria del actor
            actor.remember("statusCode", statusCode);
            actor.remember("responseBody", responseBody);
            actor.remember("requestType", "GET");

            // Imprimir la respuesta en consola
            System.out.println("POST Response:");
            response.prettyPrint();


        } catch (Exception e) {
            test.log(Status.FAIL, "Error en la prueba con endpoint: " + endpoint + " - " + e.getMessage());
        }
    }

    public static GetApis getApis() {
        return Tasks.instrumented(GetApis.class);
    }
}
