package com.example.pratyush.request;

import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;

public abstract class AbstractRequest {
    public abstract Future<? extends Object> run(HttpServerRequest request);
}
