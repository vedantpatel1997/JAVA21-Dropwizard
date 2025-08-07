package com.example.app.resources;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Map;

@Path("/env")
@Produces(MediaType.APPLICATION_JSON)
public class EnvResource {

    @GET
    public Map<String, String> getEnvVariables() {
        return System.getenv();
    }
}
