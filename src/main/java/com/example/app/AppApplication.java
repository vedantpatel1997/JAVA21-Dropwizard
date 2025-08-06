package com.example.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
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
        // no-op
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        environment.jersey().register(new LogResource());
    }
}
// To run the application, use the following commands in the terminal:
//  mvn clean package                                                                                      
// java -jar target/dropwizard-logback-slf4j-demo-1.0-SNAPSHOT.jar server src/main/resources/config.yml
