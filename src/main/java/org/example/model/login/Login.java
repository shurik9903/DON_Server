package org.example.model.login;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Response;
import org.example.data.entity.EUser;
import org.example.model.database.IDBController;
import org.example.model.token.IToken;

import java.util.HashMap;
import java.util.Map;

public class Login implements ILogin{

    @Inject
    private IToken token;

    @Inject
    private IDBController dbController;

    @Override
    public Response Authorization(String jsonData) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        Map<String, String> dLogin = new HashMap<>();
        dLogin = jsonb.fromJson(jsonData, dLogin.getClass());

        String login = dLogin.getOrDefault("login", null);
        String password = dLogin.getOrDefault("password", null);


        try {

            if (login.isEmpty() || password.isEmpty()) {
                Result.put("msg", "Fill in all the fields");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            EUser eUser = dbController.login(login, password, msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            String newToken = token.create(login, String.valueOf(eUser.isTeacher()), eUser.getUser_id().toString());

            Result.put("token", newToken);
            Result.put("userID", eUser.getUser_id().toString());
            Result.put("login", login);

            return Response.ok(jsonb.toJson(Result)).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error: " + e.getMessage()).build();
        }

    }

}
