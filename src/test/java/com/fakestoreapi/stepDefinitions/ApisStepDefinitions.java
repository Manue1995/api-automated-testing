package com.fakestoreapi.stepDefinitions;

import com.fakestoreapi.controller.DeleteApis;
import com.fakestoreapi.controller.GetApis;
import com.fakestoreapi.controller.PostApis;
import com.fakestoreapi.controller.PutApis;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.testng.Assert;

public class ApisStepDefinitions {


    private final Actor tester = OnStage.theActorCalled("Tester");

    @When("realizo un GET a {string}")
    public void realizoUnGETA(String endpoint) {

       OnStage.theActorInTheSpotlight().attemptsTo(GetApis.getApis(endpoint));

    }
    @When("realizo un POST a {string} con el JSON {string}")
    public void realizoUnPOSTAConElJSON(String endpoint,String strings) {

       OnStage.theActorInTheSpotlight().attemptsTo(PostApis.postApis(endpoint));

    }

    @When("realizo un PUT a {string} con el JSON {string}")
    public void realizoUnPUTAConElJSON(String endpoint, String jsonFile) {

        OnStage.theActorInTheSpotlight().attemptsTo(PutApis.putApis(endpoint,jsonFile));

    }

    @When("realizo un DELETE a {string}")
    public void realizoUnDELETEA(String endpoint) {

        OnStage.theActorInTheSpotlight().attemptsTo(DeleteApis.deleteApis(endpoint));

    }

    @Then("el código de respuesta debe ser {int}")
    public void elCódigoDeRespuestaDebeSer(Integer expectedStatusCode) {

      /*  System.out.println("GET Response:"+expectedStatusCode);

        int actualStatusCode = OnStage.theActorInTheSpotlight().recall("statusCode");

        if (actualStatusCode != expectedStatusCode) {
            throw new AssertionError("Código de estado esperado: " + expectedStatusCode + ", pero fue: " + actualStatusCode);
        }
*/
    }

}
