package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.model.login.ILogin;

@Path("/login")
public class LoginController {

    @Inject
    private ILogin log;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response doPost(String jsonData) {
        try {
            return log.Authorization(jsonData);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }
}
