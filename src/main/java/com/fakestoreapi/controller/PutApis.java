package com.fakestoreapi.controller;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static com.fakestoreapi.utils.Constant.*;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PutApis implements Task {

    private static ExtentTest test;
    private final List<String> endpoints;
    private final Map<String, String> jsonFilePaths; // Map de endpoint → archivo JSON

    public PutApis(List<String> endpoints, Map<String, String> jsonFilePaths) {
        this.endpoints = endpoints;
        this.jsonFilePaths = jsonFilePaths;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        for (String endpoint : endpoints) {
            String jsonBody = leerJsonDesdeArchivo(jsonFilePaths.get(endpoint)); // Obtiene el JSON correcto
            ejecutarPut(actor, endpoint, jsonBody);
        }
        extent.flush(); // Guardar los reportes
    }

    private void ejecutarPut(Actor actor, String endpoint, String jsonBody) {

        try {

            test = extent.createTest("PUT " + endpoint);
            test.log(Status.INFO, "Ejecutando PUT en: " + endpoint);
            test.log(Status.INFO, "Body enviado: " + jsonBody);

            Response response = RestAssured.given()
                    .baseUri(BASE_URL)
                    .basePath(endpoint)
                    .header("Content-Type", "application/json")
                    .body(jsonBody)
                    .when()
                    .put();

            int statusCode = response.getStatusCode();
            String responseBody = response.getBody().asString();

            test.log(Status.INFO, "Código de respuesta: " + statusCode);
            test.log(Status.INFO, "Cuerpo de respuesta: " + responseBody);

            if (statusCode == 200) {
                test.log(Status.PASS, "PUT exitoso en " + endpoint);
            } else {
                test.log(Status.FAIL, "Error en PUT " + endpoint + " - Código recibido: " + statusCode);
            }

            actor.remember("statusCode", statusCode);
            actor.remember("responseBody", responseBody);
            actor.remember("requestType", "PUT");

            // Imprimir la respuesta en consola
            System.out.println("PUT Response:");
            response.prettyPrint();

        } catch (Exception e) {
            test.log(Status.FAIL, "Error en la prueba con " + endpoint + " - " + e.getMessage());
        }
    }

    private String leerJsonDesdeArchivo(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo JSON: " + filePath, e);
        }
    }

    public static PutApis putApis(List<String> endpoints, Map<String, String> jsonFilePaths) {
        return Tasks.instrumented(PutApis.class, endpoints, jsonFilePaths);
    }


}
