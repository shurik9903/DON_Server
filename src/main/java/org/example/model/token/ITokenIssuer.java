package org.example.model.token;

public interface ITokenIssuer {
    String issueToken(String username, String teacher, String userID);
}
