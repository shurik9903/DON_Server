package org.example.model.eventQuestions;

import jakarta.ws.rs.core.Response;

public interface IEventQuestions {
    Response getEventQuestionsData(String eventQuestionsID);

    Response addEventQuestionsData(String JSONData);

    Response updateEventQuestionsData(String JSONData);

    Response deleteEventQuestionsData(String eventQuestionsID);
}
