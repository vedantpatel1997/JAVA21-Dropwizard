package com.example.app.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/log")
@Produces(MediaType.APPLICATION_JSON)
public class LogResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogResource.class);

    @GET
    public ResponseMessage logAndRespond() {
        Map<String, String> dummyData = new HashMap<>();
        for (int i = 0; i < 500; i++) {
            dummyData.put("key" + i, "value" + i);
        }

        // This will be pretty heavy in the logs
        LOGGER.info("Endpoint /log was called. Dumping dummy JSON data: {}", dummyData);

        return new ResponseMessage("Logged your request with big JSON data.");
    }

    public static class ResponseMessage {
        private String message;

        public ResponseMessage() {
        }

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}