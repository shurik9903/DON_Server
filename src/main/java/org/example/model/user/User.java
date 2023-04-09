package org.example.model.user;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Response;
import org.example.data.entity.EUser;
import org.example.model.database.IDBController;

import java.util.HashMap;
import java.util.Map;

public class User implements IUser {

    @Inject
    private IDBController dbController;

    @Override
    public Response getUserData(String userID) {
        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        try {

            if (userID.isEmpty()){
                Result.put("Msg", "No user id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            EUser eUser = dbController.getUserData(userID, msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson(eUser)).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error: " + e.getMessage()).build();
        }

    }

    @Override
    public Response UpdateUserData(String userJSON) {
        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        EUser eUser = jsonb.fromJson(userJSON, EUser.class);

        try {

            if (eUser.getUser_id().toString().isEmpty()){
                Result.put("Msg", "No user id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (eUser.getFirstName().isEmpty()){
                Result.put("Msg", "No user FirstName entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (eUser.getLastName().isEmpty()){
                Result.put("Msg", "No user LastName entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (eUser.getSecondName().isEmpty()){
                Result.put("Msg", "No user SecondName entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            dbController.updateUserData(eUser.getUser_id().toString(), eUser.getFirstName(), eUser.getLastName(), eUser.getSecondName(), msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson("ok")).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error: " + e.getMessage()).build();
        }

    }

}
