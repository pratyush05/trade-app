package com.example.pratyush.request.stock;

import com.example.pratyush.middleware.handler.StockHandler;
import com.example.pratyush.request.AbstractRequest;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;

public class StockDelete extends AbstractRequest {
    @Override
    public Future<Integer> run(HttpServerRequest request) {
        Future<Integer> future = Future.future();
        int stockId = Integer.parseInt(request.getParam("stockId"));
        StockHandler
                .deleteStock(Vertx.currentContext().owner(), stockId)
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
