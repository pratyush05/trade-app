package com.example.pratyush.middleware.handler;

import com.example.pratyush.database.service.TraderService;
import com.example.pratyush.middleware.utils.JsonUtils;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.jooq.generated.tables.pojos.Trader;

import java.sql.Timestamp;
import java.util.List;

public class TraderHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraderHandler.class);

    public static Future<JsonObject> addTrader(Vertx vertx, JsonObject trader) {
        Future<JsonObject> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                Trader traderObject = new Trader(
                        null,
                        trader.getString("name"),
                        null
                );
                Trader result = TraderService.getInstance().addTrader(traderObject);
                int addedId = result.getId();
                if (addedId != 0) {
                    blockingCodeHandler.complete(addedId);
                } else {
                    LOGGER.error("Encountered error while inserting data");
                    blockingCodeHandler.fail("Encountered error while inserting data");
                }
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call TraderService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call TraderService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    int result = Integer.parseInt(resultHandler.result().toString());
                    JsonObject resultJson = new JsonObject().put("id", result);
                    future.complete(resultJson);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from TraderService", exception);
                    future.fail("Got unexpected result from TraderService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }

    public static Future<Integer> deleteTrader(Vertx vertx, int traderId) {
        Future<Integer> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                int result = TraderService.getInstance().deleteTrader(traderId);
                if (result != 0) {
                    blockingCodeHandler.complete(result);
                } else {
                    LOGGER.error("Encountered error while inserting data");
                    blockingCodeHandler.fail("Encountered error while inserting data");
                }
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call TraderService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call TraderService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    int result = Integer.parseInt(resultHandler.result().toString());
                    future.complete(result);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from TraderService", exception);
                    future.fail("Got unexpected result from TraderService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }

    public static Future<JsonObject> getTrader(Vertx vertx, int traderId) {
        Future<JsonObject> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                Trader result = TraderService.getInstance().getTrader(traderId);
                blockingCodeHandler.complete(result);
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call TraderService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call TraderService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    JsonObject trader = JsonUtils.toJsonObject(resultHandler.result());
                    future.complete(trader);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from TraderService", exception);
                    future.fail("Got unexpected result from TraderService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }

    public static Future<JsonArray> getTraders(Vertx vertx) {
        Future<JsonArray> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                List<Trader> result = TraderService.getInstance().getTraders();
                blockingCodeHandler.complete(result);
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call TraderService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call TraderService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    JsonArray traders = JsonUtils.toJsonArray(resultHandler.result());
                    future.complete(traders);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from TraderService", exception);
                    future.fail("Got unexpected result from TraderService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }

    public static Future<Integer> updateTrader(Vertx vertx, JsonObject trader) {
        Future<Integer> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                Trader traderObject = new Trader(
                        trader.getInteger("id"),
                        trader.getString("name"),
                        Timestamp.valueOf(trader.getString("deleted"))
                );
                int result = TraderService.getInstance().updateTrader(traderObject);
                if (result != 0) {
                    blockingCodeHandler.complete(result);
                } else {
                    LOGGER.error("Encountered error while inserting data");
                    blockingCodeHandler.fail("Encountered error while inserting data");
                }
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call TraderService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call TraderService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    int result = Integer.parseInt(resultHandler.result().toString());
                    future.complete(result);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from TraderService", exception);
                    future.fail("Got unexpected result from TraderService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }
}
