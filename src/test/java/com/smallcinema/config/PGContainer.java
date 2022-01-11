package com.smallcinema.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class PGContainer extends PostgreSQLContainer<PGContainer> {

    private PGContainer() {
        super("postgres:13-alpine");
    }

    private static class Holder {
        private static final PGContainer INSTANCE = new PGContainer()
                .withInitScript("db/migration/initial_schema.sql");
    }

    public static PGContainer getInstance() {
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
