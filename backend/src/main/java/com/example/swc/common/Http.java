package com.example.swc.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class Http {
    public static <T> T get(
            String uri,
            Class<T> targetDtoClass)
            throws IOException {

        String response = Request.Get(uri)
                .execute()
                .returnContent()
                .toString();

        return new ObjectMapper()
                .readValue(
                        response,
                        targetDtoClass);
    }
}
