package com.example.pratyush.request.trade;

import com.example.pratyush.middleware.handler.TradeHandler;
import com.example.pratyush.request.AbstractRequest;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;

public class TradeDelete extends AbstractRequest {
    @Override
    public Future<Integer> run(HttpServerRequest request) {
        Future<Integer> future = Future.future();
        int tradeId = Integer.parseInt(request.getParam("tradeId"));
        TradeHandler
                .deleteTrade(Vertx.currentContext().owner(), tradeId)
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
