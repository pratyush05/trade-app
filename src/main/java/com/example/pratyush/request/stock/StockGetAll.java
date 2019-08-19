package com.example.pratyush.request.stock;

import com.example.pratyush.middleware.handler.StockHandler;
import com.example.pratyush.request.AbstractRequest;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonArray;

public class StockGetAll extends AbstractRequest {
    @Override
    public Future<JsonArray> run(HttpServerRequest request) {
        Future<JsonArray> future = Future.future();
        StockHandler
                .getStocks(Vertx.currentContext().owner())
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
