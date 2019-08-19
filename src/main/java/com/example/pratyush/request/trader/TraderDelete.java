package com.example.pratyush.request.trader;

import com.example.pratyush.middleware.handler.TraderHandler;
import com.example.pratyush.request.AbstractRequest;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;

public class TraderDelete extends AbstractRequest {
    @Override
    public Future<Integer> run(HttpServerRequest request) {
        Future<Integer> future = Future.future();
        int traderId = Integer.parseInt(request.getParam("traderId"));
        TraderHandler
                .deleteTrader(Vertx.currentContext().owner(), traderId)
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
