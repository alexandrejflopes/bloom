package ua.p50.sensorsApp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {

	private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static <T> T fromJson(String json, Class<T> tClass) throws JsonProcessingException {
        return mapper.readValue(json, tClass);
    }
}