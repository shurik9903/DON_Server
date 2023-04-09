package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;
import org.example.model.token.IToken;
import org.example.model.token.TokenRequired;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Provider
@TokenRequired
public class RestAuthorizedFilter implements ContainerRequestFilter {

    @Inject
    private IToken token;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        String getLogin = containerRequestContext.getHeaderString("login");
        String getToken = containerRequestContext.getHeaderString("token");

        Map<String, String> dBody = new HashMap<>();
        dBody = token.getData(getToken);

        String userID = dBody.getOrDefault("userID", "");

        containerRequestContext.getHeaders().add("X-Authentication-decrypted", userID);

        Method method = resourceInfo.getResourceMethod();

        final TokenRequired tokenReq = method.getAnnotation(TokenRequired.class);
        boolean teacher = tokenReq.teacher();


        try {
            if (!token.check(getLogin, getToken, teacher))
                throw new NotAuthorizedException("Invalid Token");;
        } catch (NoSuchAlgorithmException e) {
            throw new NotAuthorizedException("Invalid Token");
        }
    }
}
