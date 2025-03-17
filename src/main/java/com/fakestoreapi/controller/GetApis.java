package com.fakestoreapi.controller;

import io.restassured.RestAssured;
import  io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetApis implements Task {

    String path ="./Reporte/Apis.html";
    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter(path);

    ExtentTest test;

    private final String endpoint;

    public GetApis(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        extent.attachReporter(spark);
        test = extent.createTest("Prueba2");

        Response response = RestAssured.given()
                .baseUri("https://fakestoreapi.com")
                .basePath("/products")
                .when()
                .get("1");

        // Imprimir la respuesta en consola
        System.out.println("GET Response:");
        response.prettyPrint();

        // Guardar el código de respuesta en la memoria del actor
        actor.remember("statusCode", response.getStatusCode());
        actor.remember("responseBody", response.getBody().asString());

    }

    public static GetApis getApis (String endpoint){
        return instrumented (GetApis.class, endpoint);}


}
