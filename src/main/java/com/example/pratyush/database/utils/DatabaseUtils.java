package com.example.pratyush.database.utils;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.Objects;

public class DatabaseUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseUtils.class);

    private DatabaseUtils() {}

    public static JsonObject getPostgresConfigurations() throws Exception {
        JsonObject configurations = new JsonObject();
        JsonObject appConfigurations = Vertx.currentContext().config();
        if (Objects.isNull(appConfigurations) && appConfigurations.isEmpty()) {
            LOGGER.error("Application configurations not found or are null");
            throw new Exception("Application configurations not found or are null");
        }
        try {
            populateDatabaseConfigurations(appConfigurations, configurations);
        } catch (Exception exception) {
            LOGGER.error("Failed to load database configurations: {0}", exception, configurations);
            throw new Exception("Failed to load database configurations");
        }
        return configurations;
    }

    private static void populateDatabaseConfigurations(
            JsonObject appConfigurations,
            JsonObject databaseConfigurations
    ) {
        databaseConfigurations
                .put(DatabaseConstants.DATABASE_DRIVE, appConfigurations.getString("database.driver"))
                .put(DatabaseConstants.DATABASE_MAX_POOL_SIZE, Integer.parseInt(appConfigurations.getString("database.max_pool_size")))
                .put(DatabaseConstants.DATABASE_PASSWORD, appConfigurations.getString("database.password"))
                .put(DatabaseConstants.DATABASE_URL, appConfigurations.getString("database.url"))
                .put(DatabaseConstants.DATABASE_USER, appConfigurations.getString("database.user"));
    }
}
