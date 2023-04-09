package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/ping")
public class Ping {

    @GET
    public String ping() {
        return "OK PING";
    }
}

