package com.example.pratyush.request.trader;

import com.example.pratyush.middleware.handler.TraderHandler;
import com.example.pratyush.request.AbstractRequest;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;

public class TraderGet extends AbstractRequest {
    @Override
    public Future<JsonObject> run(HttpServerRequest request) {
        Future<JsonObject> future = Future.future();
        int traderId = Integer.parseInt(request.getParam("traderId"));
        TraderHandler
                .getTrader(Vertx.currentContext().owner(), traderId)
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
