package com.example.pratyush.route;

import com.example.pratyush.resource.TraderResource;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;

public class TraderSubRouter extends AbstractSubRouter {
    private Router traderRouter;

    public static Router getRouter(Vertx vertx) {
        return new TraderSubRouter().buildRouter(vertx);
    }

    @Override
    protected Router buildRouter(Vertx vertx) {
        traderRouter = Router.router(vertx);
        addCorsMethods(traderRouter);
        addRoutePaths();
        return traderRouter;
    }

    @Override
    protected void addRoutePaths() {
        traderRouter
                .route()
                .method(HttpMethod.GET)
                .path("/trader")
                .handler(TraderResource.getInstance()::getAll);
        traderRouter
                .route()
                .method(HttpMethod.POST)
                .path("/trader")
                .handler(TraderResource.getInstance()::post);
        traderRouter
                .route()
                .method(HttpMethod.DELETE)
                .path("/trader/:traderId")
                .handler(TraderResource.getInstance()::delete);
        traderRouter
                .route()
                .method(HttpMethod.GET)
                .path("/trader/:traderId")
                .handler(TraderResource.getInstance()::get);
        traderRouter
                .route()
                .method(HttpMethod.PUT)
                .path("/trader/:traderId")
                .handler(TraderResource.getInstance()::put);

    }
}
