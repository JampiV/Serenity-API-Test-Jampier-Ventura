package com.jampi.nttdata.testing.stepDefinitions;

import com.jampi.nttdata.testing.questions.ResponseCode;
import com.jampi.nttdata.testing.tasks.GetAirlineById;
import com.jampi.nttdata.testing.tasks.GetAirlines;
import com.jampi.nttdata.testing.tasks.PatchPassenger;
import com.jampi.nttdata.testing.tasks.PostAirline;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class AirlinesStepDefinition
{

    public static Logger LOGGER = LoggerFactory.getLogger(AirlinesStepDefinition.class);

    @Before
    public void setTheStage()
    {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el {actor} establece el endpoint GET para obtener las aerolineas")
    public void elActorEstableceElEndpointGETParaObtenerLasAerolineas(Actor actor)
    {
        actor.whoCan(CallAnApi.at("https://api.instantwebtools.net/v1"));
    }

    @When("el {actor} envia una solicitud GET")
    public void elActorEnviaUnaSolicitudGET(Actor actor)
    {
        actor.attemptsTo(GetAirlines.fromEndpoint("/airlines"));
    }

    @Then("el codigo de respuesta deberia ser {int}")
    public void elCodigoDeRespuestaDeberiaSer(int responseCode)
    {
        theActorInTheSpotlight()
                .should(seeThat(
                        "El codigo de respuesta",
                        ResponseCode.getStatus(),
                        equalTo(responseCode)));
    }

    @Given("el {actor} establece el endpoint POST para crear una aerolinea")
    public void elActorEstableceElEndpointPOSTParaCrearUnaAerolinea(Actor actor)
    {
        actor.whoCan(CallAnApi.at("https://api.instantwebtools.net/v1"));
    }

    @When("el envia una solicitud HTTP POST con el {string} {string} {string} {string} {string} {string} {string} {string}")
    public void elEnviaUnaSolicitudHTTPPOSTConEl(String _id, String name, String country, String logo, String slogan, String head_quaters, String website, String established)
    {
        theActorInTheSpotlight().attemptsTo(PostAirline.fromPage(_id, name, country, logo, slogan, head_quaters, website, established));
    }

    @When("el actor envia una solicitud GET incluyendo el {string}")
    public void elActorEnviaUnaSolicitudGETIncluyendoEl(String _id)
    {
        theActorInTheSpotlight().attemptsTo(GetAirlineById.fromEndpoint("/airlines/:id", _id));
    }

    @Given("el {actor} establece el endpoint PATCH para actualizar el nombre de un pasajero")
    public void elActorEstableceElEndpointPATCHParaActualizarElNombreDeUnPasajero(Actor actor)
    {
        actor.whoCan(CallAnApi.at("https://api.instantwebtools.net/v1"));
    }

    @When("el actor envia una solicitud PATCH incluyendo el id {string} y el nombre {string}")
    public void elActorEnviaUnaSolicitudPATCHIncluyendoElIdYElNombre(String _id, String name)
    {
        theActorInTheSpotlight().attemptsTo(PatchPassenger.fromPage("/passenger/:id" ,_id, name));
    }
}
