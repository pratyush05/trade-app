package com.example.pratyush.middleware.utils;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

public class JsonUtils {
    private JsonUtils() {}

    public static JsonObject toJsonObject(Object object) {
        return JsonObject.mapFrom(object);
    }

    public static JsonArray toJsonArray(List<?> objects) {
        JsonArray jsonArray = new JsonArray();
        objects
                .stream()
                .map(JsonUtils::toJsonObject)
                .forEach(jsonArray::add);
        return jsonArray;
    }

    public static JsonArray toJsonArray(Object objects) {
        JsonArray jsonArray = new JsonArray();
        if (objects instanceof List) {
            List<?> list = (List<?>) objects;
            jsonArray = toJsonArray(list);
        }
        return jsonArray;
    }
}
