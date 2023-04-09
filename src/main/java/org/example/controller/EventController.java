package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.model.event.IEvent;
import org.example.model.token.TokenRequired;
import org.example.model.user.IUser;

@Path("/event")
public class EventController {

    @Inject
    private IEvent event;

    @GET
    @Produces("application/json")
    @TokenRequired(teacher = true)
    public Response doGet(@HeaderParam("X-Authentication-decrypted") String userID, @QueryParam("eventID") String eventID) {

        try {
            return event.getEventData(eventID, userID);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @TokenRequired(teacher = true)
    public Response doPost(@HeaderParam("X-Authentication-decrypted") String userID, String JSONData) {
        try {
            return event.addEventData(JSONData, userID);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @TokenRequired(teacher = true)
    public Response doPut(@HeaderParam("X-Authentication-decrypted") String userID, String JSONData) {
        try {
            return event.updateEventData(JSONData, userID);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Produces("application/json")
    @TokenRequired(teacher = true)
    public Response doDelete(@HeaderParam("X-Authentication-decrypted") String userID, @QueryParam("eventID") String eventID) {
        try {
            return event.deleteEventData(eventID, userID);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }
}
