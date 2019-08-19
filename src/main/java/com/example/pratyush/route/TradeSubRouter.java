package com.example.pratyush.route;

import com.example.pratyush.resource.TradeResource;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;

public final class TradeSubRouter extends AbstractSubRouter {
    private Router tradeRouter;

    public static Router getRouter(Vertx vertx) {
        return new TradeSubRouter().buildRouter(vertx);
    }

    @Override
    protected Router buildRouter(Vertx vertx) {
        tradeRouter = Router.router(vertx);
        addCorsMethods(tradeRouter);
        addRoutePaths();
        return tradeRouter;
    }

    @Override
    protected void addRoutePaths() {
        tradeRouter
                .route()
                .method(HttpMethod.DELETE)
                .path("/trade")
                .handler(TradeResource.getInstance()::deleteAll);
        tradeRouter
                .route()
                .method(HttpMethod.GET)
                .path("/trade")
                .handler(TradeResource.getInstance()::getAll);
        tradeRouter
                .route()
                .method(HttpMethod.POST)
                .path("/trade")
                .handler(TradeResource.getInstance()::post);
        tradeRouter
                .route()
                .method(HttpMethod.DELETE)
                .path("/trade/:tradeId")
                .handler(TradeResource.getInstance()::delete);
        tradeRouter
                .route()
                .method(HttpMethod.GET)
                .path("/trade/:tradeId")
                .handler(TradeResource.getInstance()::get);
        tradeRouter
                .route()
                .method(HttpMethod.PUT)
                .path("/trade/:tradeId")
                .handler(TradeResource.getInstance()::put);
    }
}
