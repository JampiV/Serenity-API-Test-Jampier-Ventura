package com.jampi.nttdata.testing.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Patch;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PatchPassenger implements Task
{
    private final String passengerName;
    private final String endpoint;
    private final String passengerId;

    public PatchPassenger(String endpoint, String passengerId, String passengerName)
    {
        this.passengerName = passengerName;
        this.endpoint = endpoint;
        this.passengerId = passengerId;
    }

    public static Performable fromPage(String endpoint, String _id ,String passengerName)
    {
        return instrumented(PatchPassenger.class, endpoint, _id ,passengerName);
    }

    @Override
    public <T extends Actor> void performAs(T actor)
    {
        String resolvedEndpoint = endpoint.replace(":id", passengerId);
        actor.attemptsTo(Patch.to(resolvedEndpoint).with(requestSpecification -> requestSpecification.contentType(ContentType.JSON)
                .body("{\"name\":\"" + passengerName + "\"}")
                .log()
                .all()));
    }
}
