package com.fakestoreapi.controller;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.List;

import static com.fakestoreapi.utils.Constant.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteApis implements Task {

    private static ExtentTest test;
    private final List<String> endpoints;
    private final int expectedStatusCode = 200; // Código esperado para una eliminación exitosa

    public DeleteApis(List<String> endpoints) {
        this.endpoints = endpoints;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        for (String endpoint : endpoints) {
            ejecutarDelete(actor, endpoint);
        }
        extent.flush(); // Guardar y cerrar el reporte
    }

    private void ejecutarDelete(Actor actor, String endpoint) {
        try {
            // Crear un test para el endpoint actual
            test = extent.createTest("DELETE " + endpoint);
            test.log(Status.INFO, "Ejecutando DELETE en: " + endpoint);

            // Enviar la solicitud DELETE
            Response response = RestAssured.given()
                    .baseUri(BASE_URL)
                    .basePath(endpoint)
                    .header("Content-Type", "application/json")
                    .when()
                    .delete();

            int statusCode = response.getStatusCode();
            String responseBody = response.getBody().asString();

            // Registrar en el reporte la respuesta
            test.log(Status.INFO, "Código de respuesta: " + statusCode);
            test.log(Status.INFO, "Cuerpo de respuesta: " + responseBody);

            // Validar el código de respuesta esperado
            if (statusCode == expectedStatusCode) {
                test.log(Status.PASS, "DELETE exitoso en " + endpoint);
            } else {
                test.log(Status.FAIL, "DELETE fallido en " + endpoint + " - Código recibido: " + statusCode);
                throw new AssertionError("DELETE en " + endpoint + " falló. Código esperado: "
                        + expectedStatusCode + ", recibido: " + statusCode);
            }

            // Guardar la información en la memoria del actor para futuros pasos
            actor.remember("statusCode", statusCode);
            actor.remember("responseBody", responseBody);
            actor.remember("requestType", "DELETE");

            // Imprimir la respuesta en consola
            System.out.println("DELETE Response:");
            response.prettyPrint();

        } catch (Exception e) {
            test.log(Status.FAIL, "Error en DELETE en " + endpoint + " - " + e.getMessage());
            throw new RuntimeException("Error en DELETE en " + endpoint, e);
        }
    }

    public static DeleteApis deleteApis(List<String> endpoints) {
        return Tasks.instrumented(DeleteApis.class, endpoints);
    }

}
