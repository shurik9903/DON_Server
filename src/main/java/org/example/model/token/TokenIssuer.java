package org.example.model.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class TokenIssuer implements ITokenIssuer{

    private Key key;

    public TokenIssuer(Key Key) {
        this.key = Key;
    }

    @Override
    public String issueToken(String username, String teacher, String userID) {

        return Jwts.builder()
                .setSubject(
                    "{" +
                        "\"login\":\""+ username +"\"," +
                        "\"teacher\":\""+ teacher +"\"," +
                        "\"userID\":\""+ userID +"\"" +
                    "}")
                .claim("scope", "user")
                .signWith(key, SignatureAlgorithm.HS256)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + (1000 * 60 * 10)))
                .compact();
    }
}
