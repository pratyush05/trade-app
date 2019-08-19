package com.example.pratyush.request.trade;

import com.example.pratyush.middleware.handler.TradeHandler;
import com.example.pratyush.request.AbstractRequest;
import com.example.pratyush.request.trader.TraderPut;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class TradePut extends AbstractRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraderPut.class);

    @Override
    public Future<Integer> run(HttpServerRequest request) {
        Future<Integer> future = Future.future();
        request.bodyHandler(handler -> {
            JsonObject body;
            try {
                body = handler.toJsonObject();
                TradeHandler
                        .updateTrade(Vertx.currentContext().owner(), body)
                        .setHandler(resultHandler -> {
                            if (resultHandler.succeeded()) {
                                future.complete(resultHandler.result());
                            } else {
                                future.fail(resultHandler.cause());
                            }
                        });
            } catch (DecodeException decodeException) {
                LOGGER.error("Body is not a valid JSON", decodeException);
                future.fail("Body is not a valid JSON");
            }
        });
        return future;
    }
}
