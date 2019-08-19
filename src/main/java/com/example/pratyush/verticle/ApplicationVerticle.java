package com.example.pratyush.verticle;

import com.example.pratyush.database.utils.HikariConnectionPool;
import com.example.pratyush.database.utils.LiqubaseExecutor;
import com.example.pratyush.route.MainRouter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.sql.Connection;

public class ApplicationVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationVerticle.class);

    private HttpServer httpServer;

    @Override
    public void start(Future<Void> startFuture) {
        Future<Void> future = connectToDatabase()
                .compose(voidArg -> executeLiquibase())
                .compose(voidArg -> startServer());

        future.setHandler(handler -> {
            if (handler.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(handler.cause());
            }
        });
    }

    private Future<Void> connectToDatabase() {
        Future<Void> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                HikariConnectionPool.getOrCreateDataSource();
                blockingCodeHandler.complete();
            } catch (Exception exception) {
                LOGGER.error("Encountered error while connecting to database", exception);
                blockingCodeHandler.fail(exception);
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                LOGGER.info("Connected to database");
                future.complete();
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }

    private Future<Void> executeLiquibase() {
        Future<Void> future = Future.future();
        boolean execute = Boolean.parseBoolean(config().getString("liquibase.execute", "false"));
        if (!execute) {
            future.complete();
            return future;
        }
        try {
            Connection connection = HikariConnectionPool.getOrCreateDataSource().getConnection();
            LiqubaseExecutor executor = new LiqubaseExecutor(vertx, connection)
                    .setChangeLogFile("db-changelog-master.xml")
                    .setRootFolder("src/main/resources/liquibase/");
            executor.execute().setHandler(handler -> {
                if (handler.succeeded()) {
                    future.complete();
                } else {
                    future.fail(handler.cause());
                }
            });
        } catch (Exception exception) {
            LOGGER.error("Some unanticipated error has occurred", exception);
            future.fail(exception);
        }
        return future;
    }

    private Future<Void> startServer() {
        Future<Void> future = Future.future();
        int port = Integer.parseInt(config().getString("http.port", "9999"));
        httpServer = vertx.createHttpServer();
        httpServer.requestHandler(MainRouter.getRouter(vertx));
        httpServer.listen(port, listenHandler -> {
            if (listenHandler.succeeded()) {
                LOGGER.info("Server started at port: {0}", port);
                future.complete();
            } else {
                LOGGER.error("Encountered error while starting server");
                future.fail(listenHandler.cause());
            }
        });
        return future;
    }

    @Override
    public void stop(Future<Void> stopFuture) {
        httpServer.close(completionHandler -> {
            stopFuture.complete();
        });
    }
}
