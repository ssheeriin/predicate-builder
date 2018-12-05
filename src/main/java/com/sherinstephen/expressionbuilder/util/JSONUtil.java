package com.sherinstephen.expressionbuilder.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONUtil {
    static ObjectMapper mapper = new ObjectMapper();

    public static String toJSON(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Class<E>, E> E toObject(String json, T clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }
}
