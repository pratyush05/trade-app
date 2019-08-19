package com.example.pratyush.database.utils;

import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;

public class JooqUtils {
    private static DefaultConfiguration jooqConfigurations;

    private JooqUtils() {}

    public static DefaultConfiguration getJooqConfigurations() throws Exception {
        if (jooqConfigurations == null) {
            synchronized (JooqUtils.class) {
                if (jooqConfigurations == null) {
                    jooqConfigurations = new DefaultConfiguration();
                    jooqConfigurations.set(SQLDialect.POSTGRES_10);
                    jooqConfigurations.set(HikariConnectionPool.getOrCreateDataSource());
                }
            }
        }
        return jooqConfigurations;
    }
}
