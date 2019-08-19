package com.example.pratyush.route;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSubRouter {
    private static final Set<HttpMethod> CORS_METHODS = new HashSet<>(
            Arrays.asList(
                    HttpMethod.DELETE,
                    HttpMethod.GET,
                    HttpMethod.POST,
                    HttpMethod.PUT
            )
    );

    protected abstract Router buildRouter(Vertx vertx);

    protected abstract void addRoutePaths();

    protected static void addCorsMethods(Router router) {
        CorsHandler corsHandler = CorsHandler.create("*").allowedMethods(CORS_METHODS);
        router.route().handler(corsHandler);
    }
}
