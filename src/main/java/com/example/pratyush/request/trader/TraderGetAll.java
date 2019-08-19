package com.example.pratyush.request.trader;

import com.example.pratyush.middleware.handler.TraderHandler;
import com.example.pratyush.request.AbstractRequest;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonArray;

public class TraderGetAll extends AbstractRequest {
    @Override
    public Future<JsonArray> run(HttpServerRequest request) {
        Future<JsonArray> future = Future.future();
        TraderHandler
                .getTraders(Vertx.currentContext().owner())
                .setHandler(resultHandler -> {
                    if (resultHandler.succeeded()) {
                        future.complete(resultHandler.result());
                    } else {
                        future.fail(resultHandler.cause());
                    }
                });
        return future;
    }
}
