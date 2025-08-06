package com.example.app.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/env")
@Produces(MediaType.APPLICATION_JSON)
public class EnvResource {

    @GET
    public Map<String, String> getEnvVariables() {
        return System.getenv();
    }
}
