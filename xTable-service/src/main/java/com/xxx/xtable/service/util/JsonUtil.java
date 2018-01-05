package com.xxx.xtable.service.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

public class JsonUtil {
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String serialize(Object object) {
        if (object == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static <T> T deserialize(String content, Class<T> clazz) {
        if (content == null || StringUtils.isEmpty(content)) {
            return null;
        }

        try {
            return objectMapper.readValue(content, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserialize(JsonParser jp, Class<T> clazz) {
        if (jp == null) {
            return null;
        }

        try {
            return objectMapper.readValue(jp, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}