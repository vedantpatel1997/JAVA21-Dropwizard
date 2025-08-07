package com.example.app.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.WebApplicationException;
import java.io.File;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

@Path("/logs")
@Produces(MediaType.APPLICATION_JSON)
public class LogFileResource {

    private static final String DEFAULT_LOG_DIR = "C:/mounts/customLogs/WindowsApp";
    private static final String ENV_VAR_NAME = "LOG_DIR_PATH";

    private final String logDir;

    public LogFileResource() {
        String envPath = System.getenv(ENV_VAR_NAME);
        this.logDir = (envPath != null && !envPath.isEmpty()) ? envPath : DEFAULT_LOG_DIR;
    }

    @GET
    public List<Map<String, Object>> listLogFiles() {
        File folder = new File(logDir);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new WebApplicationException("Log directory not found: " + logDir, 404);
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".log") || name.endsWith(".gz"));
        if (files == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(files)
                .sorted(Comparator.comparingLong(File::lastModified).reversed())
                .map(file -> {
                    Map<String, Object> metadata = new LinkedHashMap<>();
                    try {
                        java.nio.file.Path path = file.toPath();
                        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);

                        metadata.put("name", file.getName());
                        metadata.put("absolutePath", file.getAbsolutePath());
                        metadata.put("sizeKB", file.length() / 1024);
                        metadata.put("createdTime", attrs.creationTime().toString());
                        metadata.put("lastModifiedTime", attrs.lastModifiedTime().toString());
                        metadata.put("lastAccessTime", attrs.lastAccessTime().toString());
                        metadata.put("isCompressed", file.getName().endsWith(".gz"));
                    } catch (Exception e) {
                        metadata.put("error", e.getMessage());
                    }
                    return metadata;
                })
                .collect(Collectors.toList());
    }
}
