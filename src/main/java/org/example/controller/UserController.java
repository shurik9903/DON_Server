package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.model.token.TokenRequired;
import org.example.model.user.IUser;

@Path("/user")
public class UserController {

    @Inject
    private IUser user;

    @GET
    @Produces("application/json")
    @TokenRequired
    public Response doGet(@QueryParam("userID") String userID) {
        try {
            return user.getUserData(userID);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @TokenRequired
    public Response doPut(String JSONData) {
        try {
            return user.UpdateUserData(JSONData);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

}
