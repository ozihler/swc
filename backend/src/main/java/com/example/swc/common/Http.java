package com.example.swc.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class Http {
    public static <T> T get(
            String uri,
            Class<T> targetDtoClass)
            throws IOException {

        return new ObjectMapper()
                .readValue(getAsString(uri),
                        targetDtoClass);
    }


    public static <T> T getAndMapToType(
            String uri,
            TypeReference<T> targetType)
            throws IOException {

        return new ObjectMapper()
                .readValue(getAsString(uri),
                        targetType);
    }

    public static String getAsString(String uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .toString();
    }
}
