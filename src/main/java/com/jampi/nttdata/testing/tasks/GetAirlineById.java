package com.jampi.nttdata.testing.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetAirlineById implements Task
{
    private final String endpoint;
    private final String airlineId;

    public GetAirlineById(String endpoint, String id)
    {
        this.endpoint = endpoint;
        this.airlineId = id;
    }

    public static Performable fromEndpoint(String endpoint, String id)
    {
        return instrumented(GetAirlineById.class, endpoint, id);
    }

    @Override
    public <T extends Actor> void performAs(T actor)
    {
        String resolvedEndpoint = endpoint.replace(":id", airlineId);

        actor.attemptsTo(
                Get.resource(resolvedEndpoint)
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .log()
                                .all()));
    }
}
