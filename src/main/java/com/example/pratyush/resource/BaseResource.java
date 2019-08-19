package com.example.pratyush.resource;

import com.example.pratyush.response.ResponseStatus;
import com.example.pratyush.response.ResponseUtils;
import io.vertx.ext.web.RoutingContext;

public abstract class BaseResource {
    public void delete(RoutingContext routingContext) {
        methodNotImplemented(routingContext);
    }

    public void deleteAll(RoutingContext routingContext) {
        methodNotImplemented(routingContext);
    }

    public void get(RoutingContext routingContext) {
        methodNotImplemented(routingContext);
    }

    public void getAll(RoutingContext routingContext) {
        methodNotImplemented(routingContext);
    }

    public void post(RoutingContext routingContext) {
        methodNotImplemented(routingContext);
    }

    public void put(RoutingContext routingContext) {
        methodNotImplemented(routingContext);
    }

    private void methodNotImplemented(RoutingContext routingContext) {
        ResponseUtils.sendResponse(
                routingContext.response(),
                ResponseStatus.NOT_IMPLEMENTED
        );
    }
}
