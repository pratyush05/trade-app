package com.example.pratyush.resource;

import com.example.pratyush.request.trader.*;
import com.example.pratyush.response.ResponseStatus;
import com.example.pratyush.response.ResponseUtils;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class TraderResource extends BaseResource {
    private TraderResource() {}

    private static class InstanceHolder {
        private static final TraderResource INSTANCE = new TraderResource();
    }

    public static TraderResource getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public void delete(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();
        TraderDelete traderDelete = new TraderDelete();
        traderDelete
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
        TraderGet traderGet = new TraderGet();
        traderGet
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
        TraderGetAll traderGetAll = new TraderGetAll();
        traderGetAll
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
        TraderPost traderPost = new TraderPost();
        traderPost
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
        TraderPut traderPut = new TraderPut();
        traderPut
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
