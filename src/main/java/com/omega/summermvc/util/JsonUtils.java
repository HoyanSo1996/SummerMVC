package com.omega.summermvc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class JsonUtils
 *
 * @author KennySo
 * @date 2024/7/13
 */
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJsonString(Object var) {
        try {
            return objectMapper.writeValueAsString(var);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
