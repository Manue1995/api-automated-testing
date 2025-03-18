package com.fakestoreapi.stepDefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static com.fakestoreapi.utils.Constant.Tester;

public class Hook extends PageObject {

    @Before
    public void setTheStageAndConfigureApi() {

        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled(String.valueOf(Tester));
        System.out.println("🌟 Escenario inicializado con el actor 'Tester'");

    }
}
