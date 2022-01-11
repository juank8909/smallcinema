package com.smallcinema.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class LiberatedPGContainer extends PostgreSQLContainer<LiberatedPGContainer> {

    private LiberatedPGContainer() {
        super("postgres:13-alpine");
    }

    private static class Holder {
        private static final LiberatedPGContainer INSTANCE = new LiberatedPGContainer();
    }

    public static LiberatedPGContainer getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DATABASE_JDBC_URL", this.getJdbcUrl());
        System.setProperty("DATABASE_USERNAME", this.getUsername());
        System.setProperty("DATABASE_PASSWORD", this.getPassword());
    }

    @Override
    public void stop() {
        // do nothing, JVM handles shut down
    }
}
