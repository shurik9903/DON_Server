package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.model.eventQuestions.IEventQuestions;
import org.example.model.eventResults.IEventResults;
import org.example.model.token.TokenRequired;

@Path("/eventResults")
public class EventResultsController {

    @Inject
    private IEventResults event;

    @GET
    @Produces("application/json")
    @TokenRequired(teacher = true)
    public Response doGet(@QueryParam("eventResultsID") String eventResultsID) {
        try {
            return event.getEventResultsData(eventResultsID);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @TokenRequired(teacher = true)
    public Response doPost(String JSONData) {
        try {
            return event.addEventResultsData(JSONData);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @TokenRequired(teacher = true)
    public Response doPut(String JSONData) {
        try {
            return event.updateEventResultsData(JSONData);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Produces("application/json")
    @TokenRequired(teacher = true)
    public Response doDelete(@QueryParam("eventResultsID") String eventResultsID) {
        try {
            return event.deleteEventResultsData(eventResultsID);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }
}
