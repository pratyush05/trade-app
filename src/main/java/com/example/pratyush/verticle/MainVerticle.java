package com.example.pratyush.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class MainVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);

    public void start() {
        JsonObject config = config();
        deployVerticle(
                new ApplicationVerticle(),
                new DeploymentOptions().setConfig(config),
                vertx
        );
    }

    private void deployVerticle(Verticle verticle, DeploymentOptions options, Vertx vertx) {
        vertx.deployVerticle(verticle, options, completionHandler -> {
            if (completionHandler.succeeded()) {
                LOGGER.info(
                        "Verticle {0} with deploymentId {1} successfully started",
                        verticle.getClass().getName(),
                        completionHandler.result()
                );
            } else {
                LOGGER.error(
                        "Verticle {0} could not be started",
                        completionHandler.cause(),
                        verticle.getClass().getName()
                );
            }
        });
    }
}
