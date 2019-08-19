package com.example.pratyush.database.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.vertx.core.json.JsonObject;

import javax.sql.DataSource;

public class HikariConnectionPool {
    private static DataSource dataSource;
    private static HikariConfig hikariConfig;

    private HikariConnectionPool() throws Exception {
        JsonObject databaseConfigurations = DatabaseUtils.getPostgresConfigurations();
        hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(databaseConfigurations.getString(DatabaseConstants.DATABASE_DRIVE));
        hikariConfig.setJdbcUrl(databaseConfigurations.getString(DatabaseConstants.DATABASE_URL));
        hikariConfig.setMaximumPoolSize(databaseConfigurations.getInteger(DatabaseConstants.DATABASE_MAX_POOL_SIZE));
        hikariConfig.setPassword(databaseConfigurations.getString(DatabaseConstants.DATABASE_PASSWORD));
        hikariConfig.setUsername(databaseConfigurations.getString(DatabaseConstants.DATABASE_USER));
        dataSource = new HikariDataSource(hikariConfig);
    }

    private DataSource getDataSource() {
        return dataSource;
    }

    public static synchronized DataSource getOrCreateDataSource() throws Exception {
        if (dataSource == null) {
            dataSource = new HikariConnectionPool().getDataSource();
        }
        return dataSource;
    }
}
