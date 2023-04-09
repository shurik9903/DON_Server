package org.example.model.eventQuestions;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Response;
import org.example.data.entity.EEvent;
import org.example.data.entity.EEventQuestions;
import org.example.model.database.IDBController;
import org.example.model.token.IToken;

import java.util.HashMap;
import java.util.Map;

public class EventQuestions implements  IEventQuestions{

    @Inject
    private IToken token;

    @Inject
    private IDBController dbController;

    @Override
    public Response getEventQuestionsData(String eventQuestionsID) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        try {

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            EEventQuestions eEventQuestions = dbController.getEventQuestionsData(eventQuestionsID ,msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson(eEventQuestions)).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error: " + e.getMessage()).build();
        }

    }

    @Override
    public Response addEventQuestionsData(String JSONData) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        EEventQuestions event = jsonb.fromJson(JSONData, EEventQuestions.class);

        try {

            if (event.getAnswer().isEmpty()){
                Result.put("Msg", "No event questions answer entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (event.getQuestion().isEmpty()){
                Result.put("Msg", "No event questions question entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (event.getEvent_id().toString().isEmpty()){
                Result.put("Msg", "No event questions event_id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }


            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            dbController.addEventQuestionsData(event.getEvent_id().toString(), event.getQuestion(), event.getAnswer(), msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson("ok")).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error add event questions: " + e.getMessage()).build();
        }
    }

    @Override
    public Response updateEventQuestionsData(String JSONData) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        EEventQuestions event = jsonb.fromJson(JSONData, EEventQuestions.class);

        try {

            if (event.getEvent_questions_id().toString().isEmpty()){
                Result.put("Msg", "No event questions event_questions_id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }


            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            dbController.updateEventQuestionsData(event.getEvent_questions_id().toString(), event.getQuestion(), event.getAnswer(), msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson("ok")).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error update event questions: " + e.getMessage()).build();
        }
    }

    @Override
    public Response deleteEventQuestionsData(String eventQuestionsID) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        try {

            if (eventQuestionsID.isEmpty()){
                Result.put("Msg", "No event questions event_questions_id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            dbController.deleteEventQuestionsData(eventQuestionsID, msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson("ok")).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error delete event questions: " + e.getMessage()).build();
        }
    }

}
