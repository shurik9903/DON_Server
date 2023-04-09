package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.model.event.IEvent;
import org.example.model.eventQuestions.IEventQuestions;
import org.example.model.token.TokenRequired;

@Path("/eventQuestions")
public class EventQuestionsController {

    @Inject
    private IEventQuestions event;

    @GET
    @Produces("application/json")
    @TokenRequired(teacher = true)
    public Response doGet(@QueryParam("eventQuestionsID") String eventQuestionsID) {
        try {
            return event.getEventQuestionsData(eventQuestionsID);
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
            return event.addEventQuestionsData(JSONData);
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
            return event.updateEventQuestionsData(JSONData);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Produces("application/json")
    @TokenRequired(teacher = true)
    public Response doDelete(@QueryParam("eventQuestionsID") String eventQuestionsID) {
        try {
            return event.deleteEventQuestionsData(eventQuestionsID);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

}
