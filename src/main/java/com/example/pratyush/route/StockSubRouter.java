package com.example.pratyush.route;

import com.example.pratyush.resource.StockResource;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;

public final class StockSubRouter extends AbstractSubRouter {
    private Router stockRouter;

    public static Router getRouter(Vertx vertx) {
        return new StockSubRouter().buildRouter(vertx);
    }

    @Override
    protected Router buildRouter(Vertx vertx) {
        stockRouter = Router.router(vertx);
        addCorsMethods(stockRouter);
        addRoutePaths();
        return stockRouter;
    }

    @Override
    protected void addRoutePaths() {
        stockRouter
                .route()
                .method(HttpMethod.GET)
                .path("/stock")
                .handler(StockResource.getInstance()::getAll);
        stockRouter
                .route()
                .method(HttpMethod.POST)
                .path("/stock")
                .handler(StockResource.getInstance()::post);
        stockRouter
                .route()
                .method(HttpMethod.DELETE)
                .path("/stock/:stockId")
                .handler(StockResource.getInstance()::delete);
        stockRouter
                .route()
                .method(HttpMethod.GET)
                .path("/stock/:stockId")
                .handler(StockResource.getInstance()::get);
        stockRouter
                .route()
                .method(HttpMethod.PUT)
                .path("/stock/:stockId")
                .handler(StockResource.getInstance()::put);
    }
}
