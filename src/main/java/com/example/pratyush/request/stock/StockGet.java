package com.example.pratyush.request.stock;

import com.example.pratyush.middleware.handler.StockHandler;
import com.example.pratyush.request.AbstractRequest;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;

public class StockGet extends AbstractRequest {
    @Override
    public Future<JsonObject> run(HttpServerRequest request) {
        Future<JsonObject> future = Future.future();
        int stockId = Integer.parseInt(request.getParam("stockId"));
        StockHandler
                .getStock(Vertx.currentContext().owner(), stockId)
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
