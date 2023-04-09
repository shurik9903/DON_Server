package org.example.model.eventResults;

import jakarta.ws.rs.core.Response;

public interface IEventResults {
    Response getEventResultsData(String eventResultsID);

    Response addEventResultsData(String JSONData);

    Response updateEventResultsData(String JSONData);

    Response deleteEventResultsData(String eventResultsID);
}
