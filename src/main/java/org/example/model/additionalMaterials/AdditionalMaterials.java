package org.example.model.additionalMaterials;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Response;
import org.example.data.entity.EAdditionalMaterials;
import org.example.data.entity.EEvent;
import org.example.data.entity.EEventResults;
import org.example.model.database.IDBController;
import org.example.model.token.IToken;

import java.util.HashMap;
import java.util.Map;

public class AdditionalMaterials implements IAdditionalMaterials {

    @Inject
    private IToken token;

    @Inject
    private IDBController dbController;

    @Override
    public Response getAdditionalMaterialsData(String additionalMaterialsID) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        try {

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            EAdditionalMaterials eAdditionalMaterials = dbController.getAdditionalMaterialsData(additionalMaterialsID ,msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson(eAdditionalMaterials)).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error: " + e.getMessage()).build();
        }
    }

    @Override
    public Response addAdditionalMaterialsData(String JSONData) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        EAdditionalMaterials event = jsonb.fromJson(JSONData, EAdditionalMaterials.class);

        try {

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            dbController.addAdditionalMaterialsData(event.getAudio(), event.getVideo(), event.getTranscription(), msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson("ok")).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error add additional materials: " + e.getMessage()).build();
        }
    }

    @Override
    public Response updateAdditionalMaterialsData(String JSONData) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        EAdditionalMaterials event = jsonb.fromJson(JSONData, EAdditionalMaterials.class);

        try {

            if (event.getAdditional_materials_id().toString().isEmpty()){
                Result.put("Msg", "No additional materials additional_materials_id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            dbController.updateAdditionalMaterialsData(event.getAdditional_materials_id().toString(), event.getAudio(), event.getVideo(), event.getTranscription(), msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson("ok")).build();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error update additional materials: " + e.getMessage()).build();
        }
    }

    @Override
    public Response deleteAdditionalMaterialsData(String additionalMaterialsID) {

        Jsonb jsonb = JsonbBuilder.create();

        Map<String, String> Result = new HashMap<>();

        try {

            if (additionalMaterialsID.isEmpty()) {
                Result.put("Msg", "No additional materials additional_materials_id entered.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            if (!dbController.ping()) {
                Result.put("Msg", "No connection to server.");
                return Response.ok(jsonb.toJson(Result)).build();
            }

            StringBuilder msg = new StringBuilder();
            dbController.deleteAdditionalMaterialsData(additionalMaterialsID, msg);

            if (!msg.isEmpty()) {
                Result.put("msg", msg.toString());
                return Response.ok(jsonb.toJson(Result)).build();
            }

            return Response.ok(jsonb.toJson("ok")).build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error delete additional materials: " + e.getMessage()).build();
        }
    }

}
