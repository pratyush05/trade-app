package com.example.pratyush.resource;

import com.example.pratyush.request.stock.*;
import com.example.pratyush.response.ResponseStatus;
import com.example.pratyush.response.ResponseUtils;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class StockResource extends BaseResource {
    private StockResource() {}

    private static class InstanceHolder {
        private static final StockResource INSTANCE = new StockResource();
    }

    public static StockResource getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public void delete(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();
        StockDelete stockDelete = new StockDelete();
        stockDelete
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
        StockGet stockGet = new StockGet();
        stockGet
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
        StockGetAll stockGetAll = new StockGetAll();
        stockGetAll
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
        StockPost stockPost = new StockPost();
        stockPost
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
        StockPut stockPut = new StockPut();
        stockPut
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
