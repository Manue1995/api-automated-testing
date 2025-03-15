package com.fakestoreapi.stepDefinitions;

import com.fakestoreapi.controller.GetApis;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

public class ApisStepDefinitions {


    @Before
    public void setup() {
        OnStage.setTheStage(new Cast());
        OnStage.theActorCalled("Tester");
    }

    @When("realizo un GET a {string}")
    public void realizoUnGETA(String string) {

        OnStage.theActorInTheSpotlight().attemptsTo(GetApis.getApis());

    }
    @Then("el código de respuesta debe ser {int}")
    public void elCódigoDeRespuestaDebeSer(Integer int1) {


    }

}
