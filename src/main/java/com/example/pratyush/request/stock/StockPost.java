package com.example.pratyush.request.stock;

import com.example.pratyush.middleware.handler.StockHandler;
import com.example.pratyush.request.AbstractRequest;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.Objects;

public class StockPost extends AbstractRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockPost.class);

    @Override
    public Future<JsonObject> run(HttpServerRequest request) {
        Future<JsonObject> future = Future.future();
        request.bodyHandler(handler -> {
            JsonObject body;
            try {
                body = handler.toJsonObject();
                String errorMessage = validateBody(body);
                if (Objects.nonNull(errorMessage)) {
                    future.fail(errorMessage);
                } else {
                    StockHandler
                            .addStock(Vertx.currentContext().owner(), body)
                            .setHandler(resultHandler -> {
                                if (resultHandler.succeeded()) {
                                    future.complete(resultHandler.result());
                                } else {
                                    future.fail(resultHandler.cause());
                                }
                            });
                }
            } catch (DecodeException decodeException) {
                LOGGER.error("Body is not a valid JSON", decodeException);
                future.fail("Body is not a valid JSON");
            }
        });
        return future;
    }

    private String validateBody(JsonObject body) {
        if (
                !body.containsKey("symbol")
                || Objects.isNull(body.getString("symbol"))
                || body.getString("symbol").isBlank()
        ) {
            return "Valid value of field `symbol` key must be specified";
        } else if (
                !body.containsKey("name")
                || Objects.isNull(body.getString("name"))
                || body.getString("name").isBlank()
        ) {
            return "Valid value of field `name` key must be specified";
        } else if (
                !body.containsKey("price")
                || Objects.isNull(body.getDouble("price"))
                || body.getDouble("price") <= 0
        ) {
            return "Valid value of field `price` key must be specified";
        }
        return null;
    }
}
