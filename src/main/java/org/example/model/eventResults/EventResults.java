package org.example.model.eventResults;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Response;
import org.example.data.entity.EEventQuestions;
import org.example.data.entity.EEventResults;
import org.example.model.database.IDBController;
import org.example.model.token.IToken;

import java.util.HashMap;
import java.util.Map;

public class EventResults implements IEventResults{

    @Inject
    private IToken token;

    @Inject
    private IDBController dbController;

    @Override
    public Response getEventResultsData(String eventResultsID) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        try {

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            EEventResults eEventResults = dbController.getEventResultData(eventResultsID ,msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson(eEventResults)).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error: " + e.getMessage()).build();
        }
    }

    @Override
    public Response addEventResultsData(String JSONData) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        EEventResults event = jsonb.fromJson(JSONData, EEventResults.class);

        try {

            if (event.getEvent_id().toString().isEmpty()){
                Result.put("Msg", "No event result event_id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (event.getAdditional_materials_id().toString().isEmpty()){
                Result.put("Msg", "No event result additional_materials_id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (event.getStudent_id().toString().isEmpty()){
                Result.put("Msg", "No event result student_id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            dbController.addEventResultsData(event.getStudent_id().toString(), event.getEvent_id().toString(), event.getAdditional_materials_id().toString(), event.getGrade().toString(), msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson("ok")).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error add event result: " + e.getMessage()).build();
        }
    }

    @Override
    public Response updateEventResultsData(String JSONData) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        EEventResults event = jsonb.fromJson(JSONData, EEventResults.class);

        try {

            if (event.getEvent_results_id().toString().isEmpty()){
                Result.put("Msg", "No event results event_results_id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            dbController.updateEventResultsData(event.getEvent_results_id().toString(), event.getGrade().toString(), msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson("ok")).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error update event results: " + e.getMessage()).build();
        }
    }

    @Override
    public Response deleteEventResultsData(String eventResultsID) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        try {

            if (eventResultsID.isEmpty()) {
                Result.put("Msg", "No event results event_results_id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            dbController.deleteEventResultsData(eventResultsID, msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson("ok")).build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error delete event results: " + e.getMessage()).build();
        }
    }
}
