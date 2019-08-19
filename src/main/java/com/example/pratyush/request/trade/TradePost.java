package com.example.pratyush.request.trade;

import com.example.pratyush.middleware.handler.TradeHandler;
import com.example.pratyush.request.AbstractRequest;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.Objects;

public class TradePost extends AbstractRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TradePost.class);

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
                    TradeHandler
                            .addTrade(Vertx.currentContext().owner(), body)
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
                !body.containsKey("type")
                || Objects.isNull(body.getString("type"))
                || body.getString("type").isBlank()
        ) {
            return "Valid value of field `type` key must be specified";
        } else if (
                !body.containsKey("price")
                || Objects.isNull(body.getDouble("price"))
                || body.getDouble("price") <= 0
        ) {
            return "Valid value of field `price` key must be specified";
        }  else if (
                !body.containsKey("shares")
                || Objects.isNull(body.getInteger("shares"))
                || body.getInteger("shares") <= 0
        ) {
            return "Valid value of field `shares` key must be specified";
        }  else if (
                !body.containsKey("stockId")
                || Objects.isNull(body.getInteger("stockId"))
        ) {
            return "Valid value of field `stockId` key must be specified";
        }  else if (
                !body.containsKey("traderId")
                || Objects.isNull(body.getInteger("traderId"))
        ) {
            return "Valid value of field `traderId` key must be specified";
        }
        return null;
    }
}
