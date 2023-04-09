package org.example.model.token;

import io.jsonwebtoken.*;

import java.security.Key;
import java.security.NoSuchAlgorithmException;


public class TokenValidator implements ITokenValidator{

    private Key key;

    public TokenValidator(Key key) {
        this.key = key;
    }

    @Override
    public String validate(String token){
        Jws<Claims> claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return claims.getBody().getSubject();
    }

}
