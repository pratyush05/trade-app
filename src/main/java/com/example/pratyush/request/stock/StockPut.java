package com.example.pratyush.request.stock;

import com.example.pratyush.middleware.handler.StockHandler;
import com.example.pratyush.request.AbstractRequest;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;

public class StockPut extends AbstractRequest {
    @Override
    public Future<Integer> run(HttpServerRequest request) {
        Future<Integer> future = Future.future();
        request.bodyHandler(handler -> {
            JsonObject body = handler.toJsonObject();
            StockHandler
                    .updateStock(Vertx.currentContext().owner(), body)
                    .setHandler(resultHandler -> {
                        if (resultHandler.succeeded()) {
                            future.complete(resultHandler.result());
                        } else {
                            future.fail(resultHandler.cause());
                        }
                    });
        });
        return future;
    }
}
