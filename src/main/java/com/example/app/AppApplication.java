package com.example.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
// Add these imports:
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;

import com.example.app.resources.EnvResource;
import com.example.app.resources.HomeResource;
import com.example.app.resources.LogFileResource;
import com.example.app.resources.LogResource;

public class AppApplication extends Application<AppConfiguration> {
    public static void main(String[] args) throws Exception {
        new AppApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-logback-slf4j-demo";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        // Enable ${HTTP_PLATFORM_PORT} substitution in config.yml:
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)));
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        environment.jersey().register(new LogResource());
        environment.jersey().register(new HomeResource());
        environment.jersey().register(new EnvResource());
        environment.jersey().register(new LogFileResource());
    }
}

// To build and run the application, use these commands:
// mvn clean package
// java -jar target/dropwizard-1.0.jar server src/main/resources/WindowsConfig.yml       