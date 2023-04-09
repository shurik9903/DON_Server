package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.data.entity.EAdditionalMaterials;
import org.example.model.additionalMaterials.IAdditionalMaterials;
import org.example.model.event.IEvent;
import org.example.model.token.TokenRequired;

@Path("/additionalMaterials")
public class AdditionalMaterialsController {

    @Inject
    private IAdditionalMaterials event;

    @GET
    @Produces("application/json")
    @TokenRequired
    public Response doGet(@QueryParam("additionalMaterialsID") String additionalMaterialsID) {
        try {
            return event.getAdditionalMaterialsData(additionalMaterialsID);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @TokenRequired
    public Response doPost(String JSONData) {
        try {
            return event.addAdditionalMaterialsData(JSONData);
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
            return event.updateAdditionalMaterialsData(JSONData);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Produces("application/json")
    @TokenRequired
    public Response doDelete(@QueryParam("additionalMaterialsID") String additionalMaterialsID) {
        try {
            return event.deleteAdditionalMaterialsData(additionalMaterialsID);
        } catch (Exception e) {
            System.out.println("|Error: " + e);
            return Response.status(Response.Status.BAD_REQUEST).entity("|Error: " + e.getMessage()).build();
        }
    }

}
