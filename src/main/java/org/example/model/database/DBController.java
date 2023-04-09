package org.example.model.database;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.transaction.UserTransaction;
import org.example.data.entity.*;

public class DBController implements IDBController {

    //@PersistenceUnit(unitName = "DONJDBC_PersistenceUnit")
    private EntityManagerFactory EMF;

    @PostConstruct
    public void PersisInit(){
        EMF = Persistence.createEntityManagerFactory("DONJDBC_PersistenceUnit");
    }

    @Resource
    private UserTransaction Transaction;

    @Override
    public EUser login(String login, String password, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return null;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select * from users where BINARY login = ? and BINARY password = ?", EUser.class);

            query.setParameter(1, login)
                    .setParameter(2, password);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("Invalid username / mail or password");
                return null;
            }

            EUser user = (EUser) query.getSingleResult();

            Transaction.commit();

            return user;

        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
            msg.append("Login error: ").append(e.getMessage());
            return null;
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public EUser getUserData(String userID, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return null;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select * from users where user_id = ?", EUser.class);

            query.setParameter(1, userID);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("User with given id was not found");
                return null;
            }

            EUser user = (EUser) query.getSingleResult();

            Transaction.commit();

            return user;

        } catch (Exception e) {
            System.out.println("Get user data error: " + e.getMessage());
            msg.append("Get user data error: ").append(e.getMessage());
            return null;
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public void updateUserData(String userID, String firstName, String lastName, String secondName, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select * from users where user_id = ?", EUser.class);

            query.setParameter(1, userID);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("User with given id was not found");
                return;
            }

            query = entityManager.createNativeQuery("Update users set firstName = ?, lastName = ?, secondName = ? where user_id = ?");
            query.setParameter(1, firstName)
                    .setParameter(2, lastName)
                    .setParameter(3, secondName)
                    .setParameter(4, userID)
                    .executeUpdate();

            Transaction.commit();

        } catch (Exception e) {
            System.out.println("Update user data error: " + e.getMessage());
            msg.append("Update user data error: ").append(e.getMessage());
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public EEvent getEventData(String eventID, String teacherID, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return null;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select * from event where event_id = ? and teacher_id = ?", EEvent.class);

            query.setParameter(1, eventID)
                    .setParameter(2, teacherID);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("Event with given id was not found");
                return null;
            }

            EEvent event = (EEvent) query.getSingleResult();


            Transaction.commit();

            return event;

        } catch (Exception e) {
            System.out.println("Get event data error: " + e.getMessage());
            msg.append("Get event data error: ").append(e.getMessage());
            return null;
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public void addEventData(String userID, String name, String startDate, String endDate, String type, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Insert into event (teacher_id, name, startDate, endDate, type) values (?, ?, ?, ?, ?)");
            query.setParameter(1, userID)
                    .setParameter(2, name)
                    .setParameter(3, startDate)
                    .setParameter(4, endDate)
                    .setParameter(5, type)
                    .executeUpdate();

            Transaction.commit();

        } catch (Exception e) {
            System.out.println("Get event data error: " + e.getMessage());
            msg.append("Get event data error: ").append(e.getMessage());
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public void updateEventData(String eventID, String userID, String name, String startDate, String endDate, String type, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select * from event where event_id = ? and teacher_id = ?", EEvent.class);

            query.setParameter(1, eventID)
                    .setParameter(2, userID);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("Event with given id was not found");
                return;
            }

            EEvent event = (EEvent) query.getSingleResult();

            query = entityManager.createNativeQuery("Update event set name = ?, startDate = ?, endDate = ?, type = ?  where event_id = ?");
            query.setParameter(1, name.isEmpty() ? event.getName() : name)
                    .setParameter(2, startDate.isEmpty() ? event.getStartDate() : startDate)
                    .setParameter(3, endDate.isEmpty() ? event.getEndDate() : endDate)
                    .setParameter(4, type.isEmpty() ? event.getType() : type)
                    .setParameter(5, eventID.isEmpty() ? event.getEvent_id() : eventID)
                    .executeUpdate();

            Transaction.commit();

        } catch (Exception e) {
            System.out.println("Update event data error: " + e.getMessage());
            msg.append("Update event data error: ").append(e.getMessage());
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public void deleteEventData(String eventID, String userID, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select * from event where event_id = ? and teacher_id = ?", EEvent.class);

            query.setParameter(1, eventID)
                    .setParameter(2, userID);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("Event with given id was not found");
                return;
            }

            query = entityManager.createNativeQuery("Delete from event where event_id = ? and teacher_id = ?");
            query.setParameter(1, eventID)
                    .setParameter(1, userID)
                    .executeUpdate();

            Transaction.commit();

        } catch (Exception e) {
            System.out.println("Delete event data error: " + e.getMessage());
            msg.append("Delete event data error: ").append(e.getMessage());
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public EEventQuestions getEventQuestionsData(String eventQuestionsID, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return null;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select * from event_questions where event_questions_id = ?", EEventQuestions.class);

            query.setParameter(1, eventQuestionsID);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("Event questions with given id was not found");
                return null;
            }

            EEventQuestions event = (EEventQuestions) query.getSingleResult();

            Transaction.commit();

            return event;

        } catch (Exception e) {
            System.out.println("Get event questions data error: " + e.getMessage());
            msg.append("Get event questions data error: ").append(e.getMessage());
            return null;
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public void addEventQuestionsData(String event_id, String question, String answer, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Insert into event_questions (event_id, question, answer) values (?, ?, ?)");
            query.setParameter(1, event_id)
                    .setParameter(2, question)
                    .setParameter(3, answer)
                    .executeUpdate();

            Transaction.commit();

        } catch (Exception e) {
            System.out.println("Add event questions data error: " + e.getMessage());
            msg.append("Add event questions data error: ").append(e.getMessage());
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public void updateEventQuestionsData(String eventQuestionsID, String question, String answer, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select * from event_questions where event_questions_id = ?", EEventQuestions.class);

            query.setParameter(1, eventQuestionsID);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("Event questions with given id was not found");
                return;
            }

            EEventQuestions event = (EEventQuestions) query.getSingleResult();

            query = entityManager.createNativeQuery("Update event_questions set question = ?, answer = ? where event_questions_id = ?");
            query.setParameter(1, question.isEmpty() ? event.getQuestion() : question)
                    .setParameter(2, answer.isEmpty() ? event.getAnswer() : answer)
                    .setParameter(3, eventQuestionsID)
                    .executeUpdate();

            Transaction.commit();

        } catch (Exception e) {
            System.out.println("Update event questions data error: " + e.getMessage());
            msg.append("Update event questions data error: ").append(e.getMessage());
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public void deleteEventQuestionsData(String eventQuestionsID, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select * from event_questions where event_questions_id = ?", EEventQuestions.class);

            query.setParameter(1, eventQuestionsID);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("Event questions with given id was not found");
                return;
            }

            query = entityManager.createNativeQuery("Delete from event_questions where event_questions_id = ?");
            query.setParameter(1, eventQuestionsID)
                    .executeUpdate();

            Transaction.commit();

        } catch (Exception e) {
            System.out.println("Delete event questions data error: " + e.getMessage());
            msg.append("Delete event questions data error: ").append(e.getMessage());
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public EEventResults getEventResultData(String eventResultsID, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return null;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select * from event_results where event_results_id = ?", EEventResults.class);

            query.setParameter(1, eventResultsID);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("Event results with given id was not found");
                return null;
            }

            EEventResults event = (EEventResults) query.getSingleResult();

            Transaction.commit();

            return event;

        } catch (Exception e) {
            System.out.println("Get event results data error: " + e.getMessage());
            msg.append("Get event results data error: ").append(e.getMessage());
            return null;
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public void addEventResultsData(String student_id, String eventID, String additionalMaterialsID, String grade, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Event results while Entity Manager initializing");
                return;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Insert into event_results (student_id, event_id, additional_materials_id, grade) values (?, ?, ?, ?)");
            query.setParameter(1, student_id)
                    .setParameter(2, eventID)
                    .setParameter(3, additionalMaterialsID)
                    .setParameter(4, grade)
                    .executeUpdate();

            Transaction.commit();

        } catch (Exception e) {
            System.out.println("Add event results data error: " + e.getMessage());
            msg.append("Add event results data error: ").append(e.getMessage());
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public void updateEventResultsData(String eventResultsID, String grade, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select * from event_results where event_results_id = ?", EEventResults.class);

            query.setParameter(1, eventResultsID);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("Event results with given id was not found");
                return;
            }

            EEventResults event = (EEventResults) query.getSingleResult();

            query = entityManager.createNativeQuery("Update event_results set grade = ? where event_results_id = ?");
            query.setParameter(1, grade.isEmpty() ? event.getGrade() : grade)
                    .setParameter(2, eventResultsID)
                    .executeUpdate();

            Transaction.commit();

        } catch (Exception e) {
            System.out.println("Update event results data error: " + e.getMessage());
            msg.append("Update event results data error: ").append(e.getMessage());
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public void deleteEventResultsData(String eventResultsID, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select * from event_results where event_results_id = ?", EEventResults.class);

            query.setParameter(1, eventResultsID);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("Event results with given id was not found");
                return;
            }

            query = entityManager.createNativeQuery("Delete from event_results where event_results_id = ?");
            query.setParameter(1, eventResultsID)
                    .executeUpdate();

            Transaction.commit();

        } catch (Exception e) {
            System.out.println("Delete event results data error: " + e.getMessage());
            msg.append("Delete event results data error: ").append(e.getMessage());
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public EAdditionalMaterials getAdditionalMaterialsData(String eventResultsID, StringBuilder msg){

        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return null;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select * from additional_materials where additional_materials_id = ?", EAdditionalMaterials.class);

            query.setParameter(1, eventResultsID);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("Additional materials with given id was not found");
                return null;
            }

            EAdditionalMaterials event = (EAdditionalMaterials) query.getSingleResult();

            Transaction.commit();

            return event;

        } catch (Exception e) {
            System.out.println("Get additional materials data error: " + e.getMessage());
            msg.append("Get additional materials data error: ").append(e.getMessage());
            return null;
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public void addAdditionalMaterialsData(byte[] audio, byte[] video, String transcription, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Insert into additional_materials (audio, video, transcription) values (?, ?, ?)");
            query.setParameter(1, audio)
                    .setParameter(2, video)
                    .setParameter(3, transcription)
                    .executeUpdate();

            Transaction.commit();

        } catch (Exception e) {
            System.out.println("Add additional materials data error: " + e.getMessage());
            msg.append("Add additional materials data error: ").append(e.getMessage());
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public void updateAdditionalMaterialsData(String additionalMaterialsID, byte[] audio, byte[] video, String transcription, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select additional_materials_id from additional_materials where additional_materials_id = ?", EAdditionalMaterials.class);

            query.setParameter(1, additionalMaterialsID);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("Additional materials with given id was not found");
                return;
            }

            if(audio == null || audio.length != 0){
                query = entityManager.createNativeQuery("Update additional_materials set audio = ? where additional_materials_id = ?");
                query.setParameter(1, audio)
                        .setParameter(2, additionalMaterialsID)
                        .executeUpdate();
            }

            if(video == null || video.length != 0){
                query = entityManager.createNativeQuery("Update additional_materials set video = ? where additional_materials_id = ?");
                query.setParameter(1, video)
                        .setParameter(2, additionalMaterialsID)
                        .executeUpdate();
            }

            if(!transcription.isEmpty()){
                query = entityManager.createNativeQuery("Update additional_materials set transcription = ? where additional_materials_id = ?");
                query.setParameter(1, transcription)
                        .setParameter(2, additionalMaterialsID)
                        .executeUpdate();
            }

            Transaction.commit();

        } catch (Exception e) {
            System.out.println("Update additional materials data error: " + e.getMessage());
            msg.append("Update additional materials data error: ").append(e.getMessage());
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public void deleteAdditionalMaterialsData(String additionalMaterialsID, StringBuilder msg){
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                msg.append("Error while Entity Manager initializing");
                return;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select additional_materials_id from additional_materials where additional_materials_id = ?", EAdditionalMaterials.class);

            query.setParameter(1, additionalMaterialsID);

            if (query.getResultList().size() == 0) {
                Transaction.commit();
                msg.append("Additional materials with given id was not found");
                return;
            }

            query = entityManager.createNativeQuery("Delete from additional_materials where additional_materials_id = ?");
            query.setParameter(1, additionalMaterialsID)
                    .executeUpdate();

            Transaction.commit();

        } catch (Exception e) {
            System.out.println("Delete additional materials data error: " + e.getMessage());
            msg.append("Delete additional materials data error: ").append(e.getMessage());
        } finally {
            assert entityManager != null;
            entityManager.close();
        }
    }

    @Override
    public boolean ping(){
        try {
            EMF.createEntityManager();
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
