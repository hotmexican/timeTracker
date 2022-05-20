package com.tracker.spring.rest.containers;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import com.tracker.spring.rest.config.HibernateConfig;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;


public class PostgresTestContainers extends PostgreSQLContainer<PostgresTestContainers> {


    private static final String IMAGE_VERSION = "postgres:latest";
    private static final String DATABASE_NAME = "test";
    private static final int LOCAL_PORT = 51355;
    private static PostgreSQLContainer<PostgresTestContainers> container;


    public PostgresTestContainers() {
        super(IMAGE_VERSION);
    }

    public static PostgreSQLContainer<PostgresTestContainers> getInstance(){
        if(container == null){
            container = new PostgresTestContainers()
                    .withDatabaseName(DATABASE_NAME)
                    .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                            new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(LOCAL_PORT), new ExposedPort(5432)))
                    ));
            container.start();

        }
        return container;
    }

    @Override
    public void start(){
        super.start();

        Properties hibernateProperties = new Properties();
        try {


            hibernateProperties.load(PostgresTestContainers.class
                    .getClassLoader()
                    .getResourceAsStream("hibernate.properties"));


            URL resourceUrl = PostgresTestContainers.class
                    .getClassLoader().getResource("hibernate.properties");

            File file = new File(resourceUrl.toURI());
            OutputStream output = new FileOutputStream(file);

            hibernateProperties.setProperty("qweqw","adada");
            hibernateProperties.store(output, null);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

//        System.setProperty("DB_URL", container.getJdbcUrl());
//        System.setProperty("DB_USERNAME", container.getUsername());
//        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop(){
        super.stop();
    }
}
