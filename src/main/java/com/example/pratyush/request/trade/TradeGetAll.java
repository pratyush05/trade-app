package com.example.pratyush.request.trade;

import com.example.pratyush.middleware.handler.TradeHandler;
import com.example.pratyush.request.AbstractRequest;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonArray;

public class TradeGetAll extends AbstractRequest {
    @Override
    public Future<JsonArray> run(HttpServerRequest request) {
        Future<JsonArray> future = Future.future();
        TradeHandler
                .getTrades(Vertx.currentContext().owner())
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