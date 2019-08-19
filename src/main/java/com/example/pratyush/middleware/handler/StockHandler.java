package com.example.pratyush.middleware.handler;

import com.example.pratyush.database.service.StockService;
import com.example.pratyush.middleware.utils.JsonUtils;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.jooq.generated.tables.pojos.Stock;

import java.sql.Timestamp;
import java.util.List;

public class StockHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockHandler.class);

    public static Future<JsonObject> addStock(Vertx vertx, JsonObject stock) {
        Future<JsonObject> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                Stock stockObject = new Stock(
                        null,
                        stock.getString("symbol"),
                        stock.getString("name"),
                        stock.getDouble("price"),
                        null
                );
                Stock result = StockService.getInstance().addStock(stockObject);
                int addedId = result.getId();
                if (addedId != 0) {
                    blockingCodeHandler.complete(addedId);
                } else {
                    LOGGER.error("Encountered error while inserting data");
                    blockingCodeHandler.fail("Encountered error while inserting data");
                }
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call StockService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call StockService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    int result = Integer.parseInt(resultHandler.result().toString());
                    JsonObject resultJson = new JsonObject().put("id", result);
                    future.complete(resultJson);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from StockService", exception);
                    future.fail("Got unexpected result from StockService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }

    public static Future<Integer> deleteStock(Vertx vertx, int stockId) {
        Future<Integer> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                int result = StockService.getInstance().deleteStock(stockId);
                if (result != 0) {
                    blockingCodeHandler.complete(result);
                } else {
                    LOGGER.error("Encountered error while deleting data");
                    blockingCodeHandler.fail("Encountered error while deleting data");
                }
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call StockService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call StockService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    int result = Integer.parseInt(resultHandler.result().toString());
                    future.complete(result);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from StockService", exception);
                    future.fail("Got unexpected result from StockService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }

    public static Future<JsonObject> getStock(Vertx vertx, int stockId) {
        Future<JsonObject> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                Stock result = StockService.getInstance().getStock(stockId);
                blockingCodeHandler.complete(result);
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call StockService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call StockService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    JsonObject stock = JsonUtils.toJsonObject(resultHandler.result());
                    future.complete(stock);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from StockService", exception);
                    future.fail("Got unexpected result from StockService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }

    public static Future<JsonArray> getStocks(Vertx vertx) {
        Future<JsonArray> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                List<Stock> result = StockService.getInstance().getStocks();
                blockingCodeHandler.complete(result);
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call StockService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call StockService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    JsonArray stocks = JsonUtils.toJsonArray(resultHandler.result());
                    future.complete(stocks);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from StockService", exception);
                    future.fail("Got unexpected result from StockService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }

    public static Future<Integer> updateStock(Vertx vertx, JsonObject stock) {
        Future<Integer> future = Future.future();
        vertx.executeBlocking(blockingCodeHandler -> {
            try {
                Stock stockObject = new Stock(
                        stock.getInteger("id"),
                        stock.getString("symbol"),
                        stock.getString("name"),
                        stock.getDouble("price"),
                        Timestamp.valueOf(stock.getString("deleted"))
                );
                int result = StockService.getInstance().updateStock(stockObject);
                if (result != 0) {
                    blockingCodeHandler.complete(result);
                } else {
                    LOGGER.error("Encountered error while updating data");
                    blockingCodeHandler.fail("Encountered error while updating data");
                }
            } catch (Exception exception) {
                LOGGER.error("Encountered error while trying to call StockService", exception);
                blockingCodeHandler.fail("Encountered error while trying to call StockService");
            }
        }, resultHandler -> {
            if (resultHandler.succeeded()) {
                try {
                    int result = Integer.parseInt(resultHandler.result().toString());
                    future.complete(result);
                } catch (Exception exception) {
                    LOGGER.error("Got unexpected result from StockService", exception);
                    future.fail("Got unexpected result from StockService");
                }
            } else {
                future.fail(resultHandler.cause());
            }
        });
        return future;
    }
}
