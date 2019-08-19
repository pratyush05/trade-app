package com.example.pratyush.route;

import com.example.pratyush.response.ResponseStatus;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.Router;

public final class MainRouter {
    private static Router ROUTER;
    private static final String ROUTE_CONTEXT = "/v1";

    public static Router getRouter(Vertx vertx) {
        ROUTER = Router.router(vertx);
        mountSubRouters(vertx);
        setFailureHandler(ResponseStatus.INTERNAL_SERVER_ERROR);
        return ROUTER;
    }

    private static void mountSubRouters(Vertx vertx) {
        ROUTER.mountSubRouter(ROUTE_CONTEXT, TradeSubRouter.getRouter(vertx));
        ROUTER.mountSubRouter(ROUTE_CONTEXT, StockSubRouter.getRouter(vertx));
        ROUTER.mountSubRouter(ROUTE_CONTEXT, TraderSubRouter.getRouter(vertx));
    }

    private static void setFailureHandler(ResponseStatus responseStatus) {
        ROUTER.errorHandler(responseStatus.getStatusCode(), failureHandler -> {
            failureHandler
                    .response()
                    .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .setStatusCode(responseStatus.getStatusCode())
                    .setStatusMessage(responseStatus.getStatusMessage())
                    .end();
        });
    }
}
