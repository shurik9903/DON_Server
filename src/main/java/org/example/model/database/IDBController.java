package org.example.model.database;


import org.apache.commons.lang3.mutable.MutableBoolean;
import org.example.data.entity.*;

public interface IDBController {

    EUser login(String login, String password, StringBuilder msg);

    EUser getUserData(String userID, StringBuilder msg);

    void updateUserData(String userID, String firstName, String lastName, String secondName, StringBuilder msg);

    EEvent getEventData(String eventID, String teacherID, StringBuilder msg);

    void addEventData(String teacherID, String name, String startDate, String endDate, String type, StringBuilder msg);

    void  updateEventData(String eventID, String userID, String name, String startDate, String endDate, String type, StringBuilder msg);

    void deleteEventData(String eventID, String userID, StringBuilder msg);

    EEventQuestions getEventQuestionsData(String eventQuestionsID, StringBuilder msg);

    void addEventQuestionsData(String event_id, String question, String answer, StringBuilder msg);

    void updateEventQuestionsData(String eventQuestionsID, String question, String answer, StringBuilder msg);

    void deleteEventQuestionsData(String eventQuestionsID, StringBuilder msg);

    EEventResults getEventResultData(String eventResultsID, StringBuilder msg);

    void addEventResultsData(String student_id, String eventID, String additionalMaterialsID, String grade, StringBuilder msg);

    void updateEventResultsData(String eventResultsID, String grade, StringBuilder msg);

    void deleteEventResultsData(String eventResultsID, StringBuilder msg);

    EAdditionalMaterials getAdditionalMaterialsData(String eventResultsID, StringBuilder msg);

    void addAdditionalMaterialsData(byte[] audio, byte[] video, String transcription, StringBuilder msg);

    void updateAdditionalMaterialsData(String additionalMaterialsID, byte[] audio, byte[] video, String transcription, StringBuilder msg);

    void deleteAdditionalMaterialsData(String additionalMaterialsID, StringBuilder msg);

    boolean ping();

}
