package com.fakestoreapi.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.apache.commons.io.IOUtils;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostApis implements Task {


    private final String endpoint;
   // private final String jsonFile;

    public PostApis(String endpoint) {
        this.endpoint = endpoint;
       // this.jsonFile = jsonFile;
    }

    @Override
   // @Step("{0} hace un POST a #endpoint con el JSON #jsonFile")
    public <T extends Actor> void performAs(T actor) {


            // Leer el JSON desde el archivo

            File postbody = new File("src/test/resources/valid_product.json");

            Response response = RestAssured.given()
                    .baseUri("https://fakestoreapi.com")
                    .basePath("/products")
                    .header("Content-Type", "application/json")
                    .body(postbody)
                    .when()
                    .post();

            // Imprimir la respuesta en consola
            System.out.println("POST Response:");
            response.prettyPrint();

            // Guardar el código de respuesta en la memoria del actor
            actor.remember("statusCode", response.getStatusCode());
            actor.remember("responseBody", response.getBody().asString());

    }
        public static PostApis postApis ( String endpoint){
            return instrumented(PostApis.class, endpoint);
        }

}
