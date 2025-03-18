package com.fakestoreapi.stepDefinitions;

import com.fakestoreapi.controller.DeleteApis;
import com.fakestoreapi.controller.GetApis;
import com.fakestoreapi.controller.PostApis;
import com.fakestoreapi.controller.PutApis;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.actors.OnStage;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ApisStepDefinitions {


    @When("realizo un GET a {string}")
    public void realizoUnGETA(String string) {

       OnStage.theActorInTheSpotlight().attemptsTo(GetApis.getApis());

    }
    @When("realizo un POST a {string} con el JSON {string}")
        public void realizoUnPOSTAConElJSON(String endpoint,String String) {

        List<String> endpoints = Arrays.asList("/products", "/users", "/carts");

        Map<String, String> jsonFilePaths = Map.of(
                "/products", "src/test/resources/postData/products.json",
                "/users", "src/test/resources/postData/users.json",
                "/carts", "src/test/resources/postData/carts.json");

        OnStage.theActorInTheSpotlight().attemptsTo(PostApis.postApis(endpoints,jsonFilePaths));

    }

    @When("realizo un PUT a {string} con el JSON {string}")
    public void realizoUnPUTAConElJSON(String endpoint, String jsonFile) {

        List<String> endpoints = Arrays.asList("/products/1", "/users/2", "/carts/3");

        Map<String, String> jsonFilePaths = Map.of(
                "/products/1", "src/test/resources/putData/update_product.json",
                "/users/2", "src/test/resources/putData/update_user.json",
                "/carts/3", "src/test/resources/putData/update_cart.json" );

        OnStage.theActorInTheSpotlight().attemptsTo(PutApis.putApis(endpoints,jsonFilePaths));

    }

    @When("realizo un DELETE a {string}")
    public void realizoUnDELETEA(String endpoint) {

        List<String> endpoints = Arrays.asList("/products/1", "/users/2", "/carts/3");

        OnStage.theActorInTheSpotlight().attemptsTo(DeleteApis.deleteApis(endpoints));

    }

    @Then("el código de respuesta debe ser {int}")
    public void elCódigoDeRespuestaDebeSer(Integer expectedStatusCode) {

        Integer actualStatusCode = OnStage.theActorInTheSpotlight().recall("statusCode");
        String requestType = OnStage.theActorInTheSpotlight().recall("requestType"); // Recupera el tipo de request GET

        System.out.println(requestType + " Relsponse Code: " + actualStatusCode);

        if (actualStatusCode == null) {
            throw new AssertionError("No se encontró el código de estado en el actor para " + requestType);
        }

        if (!actualStatusCode.equals(expectedStatusCode)) {
            throw new AssertionError("Código de estado esperado para " + requestType + ": " + expectedStatusCode +
                    ", pero fue: " + actualStatusCode);
        }
    }

}
