package com.example.pratyush.resource;

import com.example.pratyush.request.trade.*;
import com.example.pratyush.response.ResponseStatus;
import com.example.pratyush.response.ResponseUtils;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class TradeResource extends BaseResource {
    private TradeResource() {}

    private static class InstanceHolder {
        private static final TradeResource INSTANCE = new TradeResource();
    }

    public static TradeResource getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public void delete(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();
        TradeDelete tradeDelete = new TradeDelete();
        tradeDelete
                .run(request)
                .setHandler(completionHandler -> {
                    if (completionHandler.succeeded()) {
                        ResponseUtils.sendResponse(
                                response,
                                ResponseStatus.OK
                        );
                    } else {
                        ResponseUtils.sendResponse(
                                response,
                                ResponseStatus.INTERNAL_SERVER_ERROR,
                                completionHandler.cause().getMessage()
                        );
                    }
                });
    }

    @Override
    public void deleteAll(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();
        TradeDeleteAll tradeDeleteAll = new TradeDeleteAll();
        tradeDeleteAll
                .run(request)
                .setHandler(completionHandler -> {
                    if (completionHandler.succeeded()) {
                        ResponseUtils.sendResponse(
                                response,
                                ResponseStatus.OK
                        );
                    } else {
                        ResponseUtils.sendResponse(
                                response,
                                ResponseStatus.INTERNAL_SERVER_ERROR,
                                completionHandler.cause().getMessage()
                        );
                    }
                });
    }

    @Override
    public void get(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();
        TradeGet tradeGet = new TradeGet();
        tradeGet
                .run(request)
                .setHandler(completionHandler -> {
                    if (completionHandler.succeeded()) {
                        ResponseUtils.sendResponse(
                                response,
                                ResponseStatus.OK,
                                completionHandler.result()
                        );
                    } else {
                        ResponseUtils.sendResponse(
                                response,
                                ResponseStatus.INTERNAL_SERVER_ERROR,
                                completionHandler.cause().getMessage()
                        );
                    }
                });
    }

    @Override
    public void getAll(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();
        TradeGetAll tradeGetAll = new TradeGetAll();
        tradeGetAll
                .run(request)
                .setHandler(completionHandler -> {
                    if (completionHandler.succeeded()) {
                        ResponseUtils.sendResponse(
                                response,
                                ResponseStatus.OK,
                                completionHandler.result()
                        );
                    } else {
                        ResponseUtils.sendResponse(
                                response,
                                ResponseStatus.INTERNAL_SERVER_ERROR,
                                completionHandler.cause().getMessage()
                        );
                    }
                });
    }

    @Override
    public void post(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();
        TradePost tradePost = new TradePost();
        tradePost
                .run(request)
                .setHandler(completionHandler -> {
                    if (completionHandler.succeeded()) {
                        ResponseUtils.sendResponse(
                                response,
                                ResponseStatus.OK,
                                completionHandler.result()
                        );
                    } else {
                        ResponseUtils.sendResponse(
                                response,
                                ResponseStatus.INTERNAL_SERVER_ERROR,
                                completionHandler.cause().getMessage()
                        );
                    }
                });
    }

    @Override
    public void put(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();
        TradePut tradePut = new TradePut();
        tradePut
                .run(request)
                .setHandler(completionHandler -> {
                    if (completionHandler.succeeded()) {
                        ResponseUtils.sendResponse(
                                response,
                                ResponseStatus.OK
                        );
                    } else {
                        ResponseUtils.sendResponse(
                                response,
                                ResponseStatus.INTERNAL_SERVER_ERROR,
                                completionHandler.cause().getMessage()
                        );
                    }
                });
    }
}
