package com.hb.auth.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.Gson;

import java.io.IOException;

public class JsonSerializer {
    public static <T> String JacksonToJsonRedisSerializer(T object) {
            Gson gson = new Gson();
            return gson.toJson(object);
    }

    public static <T> T fromJsonToClass(String json, Class<T> classType) {
        Gson gson = new Gson();
        return gson.fromJson(json, classType);
    }
}
