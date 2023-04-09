package org.example.model.token;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Token implements IToken {

    @Override
    public String create(String login, String teacher, String userID){
        ITokenKey tokenKey = new TokenKey();
        ITokenIssuer tokenIssuer = new TokenIssuer(tokenKey.getKey());
        return tokenIssuer.issueToken(login, teacher, userID);
    }

    @Override
    public Map<String, String> getData(String token){
        ITokenKey tokenKey = new TokenKey();
        ITokenValidator tokenValidator = new TokenValidator(tokenKey.getKey());

        Jsonb jsonb = JsonbBuilder.create();
        Map<String, String> dBody = new HashMap<>();
        return (Map<String, String>) jsonb.fromJson(tokenValidator.validate(token), dBody.getClass());
    }

    @Override
    public boolean check(String login, String token, boolean teacher) throws NoSuchAlgorithmException {
        ITokenKey tokenKey = new TokenKey();
        ITokenValidator tokenValidator = new TokenValidator(tokenKey.getKey());

        Jsonb jsonb = JsonbBuilder.create();
        Map<String, String> dBody = new HashMap<>();
        dBody = (Map<String, String>) jsonb.fromJson(tokenValidator.validate(token), dBody.getClass());

        if (teacher)
            return login.equals(dBody.getOrDefault("login", ""))
                    && String.valueOf(true).equals(dBody.getOrDefault("teacher", ""));

        return login.equals(dBody.getOrDefault("login", ""));
    }

}
