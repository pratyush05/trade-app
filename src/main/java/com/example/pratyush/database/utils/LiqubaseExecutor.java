package com.example.pratyush.database.utils;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.resource.ResourceAccessor;

import java.sql.Connection;

public class LiqubaseExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LiqubaseExecutor.class);
    private String changeLogFile;
    private Connection connection;
    private String rootFolder;
    private Vertx vertx;

    public LiqubaseExecutor(Vertx vertx, Connection connection) {
        this.connection = connection;
        this.vertx = vertx;
    }

    public LiqubaseExecutor setChangeLogFile(String changeLogFile) {
        this.changeLogFile = changeLogFile;
        return this;
    }

    public LiqubaseExecutor setRootFolder(String rootFolder) {
        this.rootFolder = rootFolder;
        return this;
    }

    public Future<Void> execute() {
        Future<Void> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                Database database = DatabaseFactory
                        .getInstance()
                        .findCorrectDatabaseImplementation(new JdbcConnection(connection));
                ResourceAccessor resourceAccessor = new FileSystemResourceAccessor(rootFolder);
                Liquibase liquibase = new Liquibase(changeLogFile, resourceAccessor, database);
                liquibase.update(new Contexts());
                blockingCodeHandler.complete();
            } catch (LiquibaseException liquibaseException) {
                LOGGER.error("Error while executing liquibase script", liquibaseException);
                blockingCodeHandler.fail(liquibaseException);
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                LOGGER.info("Liquibase script successfully executed");
                future.complete();
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }
}
