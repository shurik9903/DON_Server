package org.example.model.event;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Response;
import org.example.data.entity.EEvent;
import org.example.data.entity.EUser;
import org.example.model.database.IDBController;
import org.example.model.token.IToken;

import java.util.HashMap;
import java.util.Map;

public class Event implements IEvent {

    @Inject
    private IToken token;

    @Inject
    private IDBController dbController;

    @Override
    public Response getEventData(String eventID, String userID) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        try {

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            EEvent eEvent = dbController.getEventData(eventID, userID ,msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson(eEvent)).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error get event: " + e.getMessage()).build();
        }
    }

    @Override
    public Response addEventData(String JSONData, String userID) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        EEvent event = jsonb.fromJson(JSONData, EEvent.class);

        try {

            if (event.getStartDate().toString().isEmpty()){
                Result.put("Msg", "No event startDate entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (event.getEndDate().toString().isEmpty()){
                Result.put("Msg", "No event endDate entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (event.getName().isEmpty()){
                Result.put("Msg", "No event name entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (event.getType().isEmpty()){
                Result.put("Msg", "No event type entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            dbController.addEventData(userID, event.getName(), event.getStartDate().toString(), event.getEndDate().toString(), event.getType()  ,msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson("ok")).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error add event: " + e.getMessage()).build();
        }
    }

    @Override
    public Response updateEventData(String JSONData, String userID) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        EEvent event = jsonb.fromJson(JSONData, EEvent.class);

        try {

            if (event.getEvent_id().toString().isEmpty()){
                Result.put("Msg", "No event id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            dbController.updateEventData(event.getEvent_id().toString(), userID, event.getName(), event.getStartDate().toString(), event.getEndDate().toString(), event.getType()  ,msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson("ok")).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error update event: " + e.getMessage()).build();
        }
    }

    @Override
    public Response deleteEventData(String eventID, String userID) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        try {

            if (eventID.isEmpty()){
                Result.put("Msg", "No event id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (userID.isEmpty()){
                Result.put("Msg", "No event id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            dbController.deleteEventData(eventID, userID, msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson("ok")).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error delete event: " + e.getMessage()).build();
        }
    }

}
