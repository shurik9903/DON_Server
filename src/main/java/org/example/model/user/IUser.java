package org.example.model.user;

import jakarta.ws.rs.core.Response;

public interface IUser {
    Response getUserData(String userID);

    Response UpdateUserData(String userJSON);
}
