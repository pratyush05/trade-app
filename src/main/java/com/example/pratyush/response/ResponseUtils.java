package com.example.pratyush.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public final class ResponseUtils {
    private ResponseUtils() {}
    private static final String CONTENT_TYPE = "application/json";

    public static void sendResponse(HttpServerResponse response, ResponseStatus status) {
        buildResponse(response, status, null);
    }

    public static void sendResponse(HttpServerResponse response, ResponseStatus status, JsonArray body) {
        buildResponse(response, status, body);
    }

    public static void sendResponse(HttpServerResponse response, ResponseStatus status, JsonObject body) {
        buildResponse(response, status, body);
    }

    public static void sendResponse(HttpServerResponse response, ResponseStatus status, String body) {
        buildResponse(response, status, body);
    }

    private static void buildResponse(HttpServerResponse response,  ResponseStatus status, Object body) {
        setHeaders(response);
        setStatus(response, status);
        setBody(response, body);
    }

    private static void setHeaders(HttpServerResponse response) {
        response.putHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE);
    }

    private static void setStatus(HttpServerResponse response,  ResponseStatus status) {
        response.setStatusCode(status.getStatusCode());
        response.setStatusMessage(status.getStatusMessage());
    }

    private static void setBody(HttpServerResponse response, Object body) {
        Json.prettyMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        response.end(Json.encodePrettily(body));
    }
}
