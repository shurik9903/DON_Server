package org.example.model.event;

import jakarta.ws.rs.core.Response;

public interface IEvent {

    Response getEventData(String eventID, String userID);

    Response addEventData(String JSONData, String userID);

    Response updateEventData(String JSONData, String userID);

    Response deleteEventData(String eventID, String userID);
}
