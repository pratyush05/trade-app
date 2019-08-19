package com.example.pratyush.middleware.handler;

import com.example.pratyush.database.service.TradeService;
import com.example.pratyush.middleware.utils.JsonUtils;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.jooq.generated.tables.pojos.Trade;

import java.sql.Timestamp;
import java.util.List;

public class TradeHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TradeHandler.class);

    public static Future<JsonObject> addTrade(Vertx vertx, JsonObject trade) {
        Future<JsonObject> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                Trade tradeObject = new Trade(
                        null,
                        trade.getString("type"),
                        trade.getDouble("price"),
                        trade.getInteger("shares"),
                        trade.getInteger("stockId"),
                        trade.getInteger("traderId"),
                        new Timestamp(System.currentTimeMillis()),
                        null
                );
                Trade result = TradeService.getInstance().addTrade(tradeObject);
                int addedId = result.getId();
                if (addedId != 0) {
                    blockingCodeHandler.complete(addedId);
                } else {
                    LOGGER.error("Encountered error while inserting data");
                    blockingCodeHandler.fail("Encountered error while inserting data");
                }
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call TradeService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call TradeService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    int result = Integer.parseInt(resultHandler.result().toString());
                    JsonObject resultJson = new JsonObject().put("id", result);
                    future.complete(resultJson);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from TradeService", exception);
                    future.fail("Got unexpected result from TradeService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }

    public static Future<Integer> deleteTrade(Vertx vertx, int tradeId) {
        Future<Integer> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                int result = TradeService.getInstance().deleteTrade(tradeId);
                if (result != 0) {
                    blockingCodeHandler.complete(result);
                } else {
                    LOGGER.error("Encountered error while deleting data");
                    blockingCodeHandler.fail("Encountered error while deleting data");
                }
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call TradeService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call TradeService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    int result = Integer.parseInt(resultHandler.result().toString());
                    future.complete(result);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from TradeService", exception);
                    future.fail("Got unexpected result from TradeService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }

    public static Future<Integer> deleteTrades(Vertx vertx) {
        Future<Integer> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                int result = TradeService.getInstance().deleteTrades();
                if (result != 0) {
                    blockingCodeHandler.complete(result);
                } else {
                    LOGGER.error("Encountered error while deleting data");
                    blockingCodeHandler.fail("Encountered error while deleting data");
                }
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call TradeService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call TradeService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    int result = Integer.parseInt(resultHandler.result().toString());
                    future.complete(result);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from TradeService", exception);
                    future.fail("Got unexpected result from TradeService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }

    public static Future<JsonObject> getTrade(Vertx vertx, int tradeId) {
        Future<JsonObject> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                Trade result = TradeService.getInstance().getTrade(tradeId);
                blockingCodeHandler.complete(result);
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call TradeService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call TradeService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    JsonObject trade = JsonUtils.toJsonObject(resultHandler.result());
                    future.complete(trade);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from TradeService", exception);
                    future.fail("Got unexpected result from TradeService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }

    public static Future<JsonArray> getTrades(Vertx vertx) {
        Future<JsonArray> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                List<Trade> result = TradeService.getInstance().getTrades();
                blockingCodeHandler.complete(result);
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call TradeService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call TradeService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    JsonArray trades = JsonUtils.toJsonArray(resultHandler.result());
                    future.complete(trades);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from TradeService", exception);
                    future.fail("Got unexpected result from TradeService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }

    public static Future<Integer> updateTrade(Vertx vertx, JsonObject trade) {
        Future<Integer> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                Trade tradeObject = new Trade(
                        trade.getInteger("id"),
                        trade.getString("type"),
                        trade.getDouble("price"),
                        trade.getInteger("shares"),
                        trade.getInteger("stockId"),
                        trade.getInteger("traderId"),
                        Timestamp.valueOf(trade.getString("created")),
                        Timestamp.valueOf(trade.getString("deleted"))
                );
                int result = TradeService.getInstance().updateTrade(tradeObject);
                if (result != 0) {
                    blockingCodeHandler.complete(result);
                } else {
                    LOGGER.error("Encountered error while updating data");
                    blockingCodeHandler.fail("Encountered error while updating data");
                }
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call TradeService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call TradeService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    int result = Integer.parseInt(resultHandler.result().toString());
                    future.complete(result);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from TradeService", exception);
                    future.fail("Got unexpected result from TradeService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }
}
