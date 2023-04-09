package org.example.model.additionalMaterials;

import jakarta.ws.rs.core.Response;

public interface IAdditionalMaterials {
    Response getAdditionalMaterialsData(String additionalMaterialsID);

    Response addAdditionalMaterialsData(String JSONData);

    Response updateAdditionalMaterialsData(String JSONData);

    Response deleteAdditionalMaterialsData(String additionalMaterialsID);
}
